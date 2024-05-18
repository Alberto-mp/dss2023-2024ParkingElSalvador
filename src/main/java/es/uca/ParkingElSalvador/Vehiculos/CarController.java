package es.uca.ParkingElSalvador.Vehiculos;

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

    @PostMapping("/{matricula}")
    public void saveNewCar(@PathVariable String matricula) {
        Vehiculo v = new Vehiculo(matricula);
        carService.save(v);
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
