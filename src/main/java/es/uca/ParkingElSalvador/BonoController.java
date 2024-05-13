package es.uca.ParkingElSalvador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public List<Bono> getBonosByMatricula(@PathVariable String matricula) {
        return bonoService.getBonos(matricula);
    }

    @GetMapping 
    public List<Bono> getAllBonos() {
        return bonoService.getBonos();
    }
}
