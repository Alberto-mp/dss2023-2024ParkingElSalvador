package es.uca.ParkingElSalvador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/vehiculos")
public class CarController {

    private final CarService carService;
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public void saveCar(@RequestBody Vehiculo vehiculo) {
        carService.save(vehiculo);
    }

    @DeleteMapping("/{matricula}")
    public void deleteCar(@PathVariable String matricula) {
        Vehiculo v = carService.getVehiculo(matricula);
        if (v != null) 
            carService.delete(matricula);
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
