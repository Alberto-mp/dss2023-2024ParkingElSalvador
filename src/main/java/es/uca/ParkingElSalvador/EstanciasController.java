package es.uca.ParkingElSalvador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estancias")
public class EstanciasController {

    private final EstanciasService estanciasService;

    @Autowired
    public EstanciasController(EstanciasService estanciasService) {
        this.estanciasService = estanciasService;
    }

    @PostMapping
    public void saveEstancia(@RequestBody Vehiculo vehiculo) {
        estanciasService.save(vehiculo);
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
