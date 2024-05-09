package es.uca.ParkingElSalvador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehiculos")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public void saveCar(@RequestBody Vehiculo vehiculo) {
        carService.save(vehiculo);
    }

    @DeleteMapping("/{matricula}")
    public void deleteCar(@PathVariable String mat) {
        Vehiculo v = carService.getVehiculo(mat);
        if (v != null) 
            carService.delete(mat);
    }

    @GetMapping("/{matricula}")
    public Vehiculo getVehiculo(@PathVariable String matricula) {
        Vehiculo vehiculo = carService.getVehiculo(matricula);
        return vehiculo;
    }

    @GetMapping("/count")
    public Integer getNumCoches() {
        return carService.getNumCoches();
    }
}
