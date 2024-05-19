package es.uca.ParkingElSalvador.Parking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private Parking p = null;

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping("/configurar/parking")
    @Operation(summary = "Configurar parking", description = "Este endpoint permite configurar los datos del parking")
    @ApiResponse(responseCode = "200", description = "Parking configurado exitosamente")
    public void setParking(@RequestParam("nombre") String nombre, 
                       @RequestParam("direccionPostal") String direccionPostal, 
                       @RequestParam("capacidadTotal") int capacidadTotal) {
    p = new Parking();
    p.setNombre(nombre);
    p.setDireccionPostal(direccionPostal);
    p.setCapacidadTotal(capacidadTotal);
    p.setPlazasDisponibles(capacidadTotal); // Asumiendo que todas las plazas están disponibles inicialmente
    p.setPlazasOcupadas(0); // Asumiendo que no hay plazas ocupadas inicialmente

    
    parkingService.setP(p);
}


    @PostMapping("/entrada")
    @Operation(summary = "Entrada", description = "Este endpoint permite la entrada al parking")
    @ApiResponse(responseCode = "200", description = "Vehiculo ha entrado exitosamente")
    public void entrada() throws Exception {
        parkingService.entrada();
        parkingService.setP(p);
    }

    @PostMapping("/entrada/manual")
    @Operation(summary = "Entrada manual", description = "Este endpoint permite la entrada manual al parking")
    @ApiResponse(responseCode = "200", description = "Vehiculo ha entrado exitosamente")
    public void entradaManual(@RequestParam String matricula) {
        parkingService.entrada(matricula);
        parkingService.setP(p);
    }

    @PostMapping("/salida")
    @Operation(summary = "Salida", description = "Este endpoint permite la salida del parking")
    @ApiResponse(responseCode = "200", description = "Vehículo ha salido exitosamente")
    public void salida() throws Exception {
        parkingService.salida();
        parkingService.setP(p);
    }

    @PostMapping("/salida/manual")
    @Operation(summary = "Salida manual", description = "Este endpoint permite la salida manual del parking")
    @ApiResponse(responseCode = "200", description = "Vehículo ha salido exitosamente")
    public void salidaManual(@RequestParam String matricula) {
        parkingService.salida(matricula);
        parkingService.setP(p);
    }

    @PostMapping("/configurar/tarifa")
    @Operation(summary = "Configurar tarifa", description = "Este endpoint permite configurar la tarifa de estacionamiento")
    @ApiResponse(responseCode = "200", description = "Tarifa configurada exitosamente")
    public void configurarPrecioEstandar(@RequestParam long min) {
        parkingService.precioEstandar(min);
    }

    @PostMapping("/configurar/preciosBonos")
    @Operation(summary = "Configurar precios de bonos", description = "Este endpoint permite configurar los precios de los bonos de estacionamiento")
    @ApiResponse(responseCode = "200", description = "Precios de bonos configurados exitosamente")
    public void configurarPreciosBonos(@RequestParam double mes, @RequestParam double tri, @RequestParam double anno) {
        parkingService.ponerPrecioBonos(mes, tri, anno);
    }

    @PostMapping("/pago/estandar")
    @Operation(summary = "Pago estándar", description = "Este endpoint permite realizar el pago estándar de estacionamiento")
    @ApiResponse(responseCode = "200", description = "Pago realizado exitosamente")
    public void pagarEstandar(@RequestParam BigDecimal entregado, @RequestParam String matricula, @RequestParam char formato) {
        parkingService.vehiculoPagaEstandar(entregado, matricula, formato);
    }

    @PostMapping("/pago/bonoMensual")
    @Operation(summary = "Pago con bono mensual", description = "Este endpoint permite realizar el pago de estacionamiento con un bono mensual")
    @ApiResponse(responseCode = "200", description = "Pago con bono mensual realizado exitosamente")
    public void pagarBonoMensual(@RequestParam BigDecimal entregado, @RequestParam int nMeses, @RequestParam String matricula, @RequestParam char formato) {
        parkingService.vehiculoPagaBonoMensual(entregado, nMeses, matricula, formato);
    }

    @PostMapping("/pago/bonoTrimestral")
    @Operation(summary = "Pago con bono trimestral", description = "Este endpoint permite realizar el pago de estacionamiento con un bono trimestral")
    @ApiResponse(responseCode = "200", description = "Pago con bono trimestral realizado exitosamente")
    public void pagarBonoTrimestral(@RequestParam BigDecimal entregado, @RequestParam int nTrimestres, @RequestParam String matricula, @RequestParam char formato) {
        parkingService.vehiculoPagaBonoTrimestral(entregado, nTrimestres, matricula, formato);
    }

    @PostMapping("/pago/bonoAnual")
    @Operation(summary = "Pago con bono anual", description = "Este endpoint permite realizar el pago de estacionamiento con un bono anual")
    @ApiResponse(responseCode = "200", description = "Pago con bono anual realizado exitosamente")
    public void pagarBonoAnual(@RequestParam BigDecimal entregado, @RequestParam int nAnnos, @RequestParam String matricula, @RequestParam char formato) {
        parkingService.vehiculoPagaBonoAnual(entregado, nAnnos, matricula, formato);
    }


}
