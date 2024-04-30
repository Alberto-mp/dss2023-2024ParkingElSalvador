package es.uca.ParkingElSalvador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/bonos") 
public class BonoController {

    private final BonoService bonoService; 

    @Autowired 
    public BonoController(BonoService bonoService) {
        this.bonoService = bonoService;
    }

    @PostMapping 
    public void saveBono(@RequestBody Bono bono) {
        bonoService.save(bono);
    }

    @DeleteMapping("/{id}") 
    public void deleteBono(@PathVariable String id) {
        bonoService.delete(bono);
    }

    @GetMapping("/{matricula}") 
    public List<Bono> getBonosByMatricula(@PathVariable String matricula) {
        return bonoService.getBonos(matricula);
    }

    @GetMapping // Mapea las solicitudes GET a este método
    public List<Bono> getAllBonos() {
        return bonoService.getBonos();
    }
}
