package es.uca.ParkingElSalvador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estancias")
public class EstanciasController {

    private final EstanciasService estanciasService;
    private static final Logger log = LoggerFactory.getLogger(EstanciasController.class);

    @Autowired
    public EstanciasController(EstanciasService estanciasService) {
        this.estanciasService = estanciasService;
    }

    @PostMapping
    public ResponseEntity<?> saveEstancia(@RequestBody Vehiculo vehiculo) {
    try {
        estanciasService.save(vehiculo);
        return ResponseEntity.ok().build();
    } catch (Exception e) {
        // Log the exception details to help diagnose issues.
        log.error("Error saving estancia", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


    @GetMapping("/existe/{matricula}")
    public boolean existeEstancia(@PathVariable String matricula) {
        return estanciasService.esta(matricula);
    }

    @GetMapping("/{matricula}")
    public List<Estancia> getEstanciasPorMatricula(@PathVariable String matricula) {
        return estanciasService.getEstancias(matricula);
    }

    @GetMapping
    public List<Estancia> getAllEstancias() {
        return estanciasService.getAllEstancias();
    }

    @GetMapping("/count")
    public long getNumeroDeEstancias() {
        return estanciasService.numEstancias();
    }
}
