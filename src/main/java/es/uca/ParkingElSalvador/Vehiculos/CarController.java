package es.uca.ParkingElSalvador.Vehiculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/vehiculos")
public class CarController {

    private final CarService carService;
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/{matricula}")
    @Operation(summary = "Registrar nuevo vehículo", description = "Registra un nuevo vehículo usando la matrícula")
    @ApiResponse(responseCode = "200", description = "Vehículo registrado exitosamente")
    public void saveNewCar(@PathVariable String matricula) {
        Vehiculo v = new Vehiculo(matricula);
        carService.save(v);
    }

    @PostMapping
    @Operation(summary = "Guardar vehículo", description = "Guarda un vehículo proporcionado en el cuerpo de la petición")
    @ApiResponse(responseCode = "200", description = "Vehículo guardado exitosamente")
    public void saveCar(@RequestBody Vehiculo vehiculo) {
        carService.save(vehiculo);
    }

    @DeleteMapping("/{matricula}")
    @Operation(summary = "Eliminar vehículo", description = "Elimina un vehículo basado en la matrícula proporcionada")
    @ApiResponse(responseCode = "200", description = "Vehículo eliminado exitosamente", 
                 content = @Content(schema = @Schema(implementation = Vehiculo.class)))
    public void deleteCar(@PathVariable String matricula) {
        Vehiculo v = carService.getVehiculo(matricula);
        if (v != null) 
            carService.delete(matricula);
    }

    @GetMapping("/{matricula}")
    @Operation(summary = "Obtener vehículo", description = "Obtiene detalles de un vehículo por su matrícula")
    @ApiResponse(responseCode = "200", description = "Vehículo encontrado",
                 content = @Content(schema = @Schema(implementation = Vehiculo.class)))
    public Vehiculo getVehiculo(@PathVariable String matricula) {
        return carService.getVehiculo(matricula);
    }

    @GetMapping("/count")
    @Operation(summary = "Contar vehículos", description = "Devuelve el número total de vehículos registrados")
    @ApiResponse(responseCode = "200", description = "Número de vehículos contados",
                 content = @Content(schema = @Schema(implementation = Integer.class)))
    public Integer getNumCoches() {
        return carService.getNumCoches();
    }
}
