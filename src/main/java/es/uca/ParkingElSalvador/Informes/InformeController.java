package es.uca.ParkingElSalvador.Informes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/informes")
public class InformeController {

    private final Informe informeService;

    @Autowired
    public InformeController(Informe informeService) {
        this.informeService = informeService;
    }

    @GetMapping("/fecha")
    public String getFechaInforme() {
        return informeService.fechaInforme();
    }

    @GetMapping("/ingreso/diario")
    public double getIngresoDiario() {
        return informeService.ingresoDiario();
    }

    @GetMapping("/ingreso/semanal")
    public double getIngresoSemanal() {
        return informeService.ingresoSemanal();
    }

    @GetMapping("/ingreso/mensual")
    public double getIngresoMensual() {
        return informeService.ingresoMensual();
    }

    @GetMapping("/toString")
    public String getInformeToString() {
        return informeService.toString();
    }
}
