package es.uca.ParkingElSalvador.Bonos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.uca.ParkingElSalvador.Estancias.Estancia;
import es.uca.ParkingElSalvador.Estancias.EstanciasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController 
@RequestMapping("/api/v1/bonos") 
public class BonoController {

    private final BonoService bonoService; 
    private final EstanciasService estanciaService;
    private static final Logger log = LoggerFactory.getLogger(BonoController.class);

    @Autowired 
    public BonoController(BonoService bonoService, EstanciasService estanciasService) {
        this.estanciaService = estanciasService;
        this.bonoService = bonoService;
    }

     @PostMapping
    @Operation(summary = "Guardar un nuevo bono", description = "Asocia y guarda un bono a una estancia específica basada en la matrícula")
    @ApiResponse(responseCode = "200", description = "Bono guardado correctamente")
    @ApiResponse(responseCode = "400", description = "Estancia no encontrada")
    @ApiResponse(responseCode = "500", description = "Error interno al guardar el bono")
    public ResponseEntity<?> saveBono(@RequestBody Bono bono, @RequestParam String matricula) {
    try {
        // Buscar la estancia usando el ID proporcionado
        Estancia estancia;
        int index = estanciaService.getEstancias(matricula).size();
        if(index==0)
            estancia = estanciaService.getEstancias(matricula).get(0);
        else
            estancia = estanciaService.getEstancias(matricula).get(index-1);
        if (estancia==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estancia no encontrada.");
        }

        // Asociar el bono a la estancia
        bono.setEstancia(estancia);
        estancia.setBono(bono);  // Asegúrate de que la relación bidireccional esté completa
        bono.setMatricula(matricula);
        // Guardar el bono
        bonoService.save(bono);
        return ResponseEntity.ok().build();
    } catch (Exception e) {
        log.error("Error saving bono", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el bono: " + e.getMessage());
        }
    }

    @DeleteMapping("/{matricula}")
    @Operation(summary = "Eliminar un bono", description = "Elimina el bono más reciente asociado a la matrícula proporcionada")
    @ApiResponse(responseCode = "200", description = "Bono eliminado correctamente")
    @ApiResponse(responseCode = "400", description = "No se encontró la estancia asociada al bono")
    public ResponseEntity<?> deleteBono(@PathVariable String matricula) {
        Bono b;
        int index = bonoService.getBonos(matricula).size();
        if(index==0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estancia no encontrada.");
        else
            b = bonoService.getBonos(matricula).get(index-1);
        List<Estancia> e = estanciaService.getEstancias(matricula);
        for (Estancia estancia : e) {
            estancia.setBono(null);  // Desvincula el bono
            //estanciaService.save(estancia);  // Guarda la estancia actualizada
        }
        bonoService.delete(b);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{matricula}")
    @Operation(summary = "Obtener bonos por matrícula", description = "Devuelve todos los bonos asociados a una matrícula específica")
    @ApiResponse(responseCode = "200", description = "Bonos devueltos correctamente",
                 content = @Content(array = @ArraySchema(schema = @Schema(implementation = Bono.class))))
    public List<Bono> getBonosByMatricula(@PathVariable String matricula) {
        return bonoService.getBonos(matricula);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los bonos", description = "Devuelve todos los bonos registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Todos los bonos devueltos correctamente",
                 content = @Content(array = @ArraySchema(schema = @Schema(implementation = Bono.class))))
    public List<Bono> getAllBonos() {
        return bonoService.getBonos();
    }
}
