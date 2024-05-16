package es.uca.ParkingElSalvador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void entrada() throws Exception {
        parkingService.entrada();
        parkingService.setP(p);
    }

    @PostMapping("/entrada/manual")
    public void entradaManual(@RequestParam String matricula) {
        parkingService.entrada(matricula);
        parkingService.setP(p);
    }

    @PostMapping("/salida")
    public void salida() throws Exception {
        parkingService.salida();
        parkingService.setP(p);
    }

    @PostMapping("/salida/manual")
    public void salidaManual(@RequestParam String matricula) {
        parkingService.salida(matricula);
        parkingService.setP(p);
    }

    @PostMapping("/configurar/tarifa")
    public void configurarPrecioEstandar(@RequestParam long min) {
        parkingService.precioEstandar(min);
    }

    @PostMapping("/configurar/preciosBonos")
    public void configurarPreciosBonos(@RequestParam double mes, @RequestParam double tri, @RequestParam double anno) {
        parkingService.ponerPrecioBonos(mes, tri, anno);
    }

    @PostMapping("/pago/estandar")
    public void pagarEstandar(@RequestParam BigDecimal entregado, @RequestParam String matricula, @RequestParam char formato) {
        parkingService.vehiculoPagaEstandar(entregado, matricula, formato);
    }

    @PostMapping("/pago/bonoMensual")
    public void pagarBonoMensual(@RequestParam BigDecimal entregado, @RequestParam int nMeses, @RequestParam String matricula, @RequestParam char formato) {
        parkingService.vehiculoPagaBonoMensual(entregado, nMeses, matricula, formato);
    }

    @PostMapping("/pago/bonoTrimestral")
    public void pagarBonoTrimestral(@RequestParam BigDecimal entregado, @RequestParam int nTrimestres, @RequestParam String matricula, @RequestParam char formato) {
        parkingService.vehiculoPagaBonoTrimestral(entregado, nTrimestres, matricula, formato);
    }

    @PostMapping("/pago/bonoAnual")
    public void pagarBonoAnual(@RequestParam BigDecimal entregado, @RequestParam int nAnnos, @RequestParam String matricula, @RequestParam char formato) {
        parkingService.vehiculoPagaBonoAnual(entregado, nAnnos, matricula, formato);
    }

}
