package es.uca.ParkingElSalvador.Informes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/informes")
public class InformeController {

    private final Informe informeService;

    @Autowired
    public InformeController(Informe informeService) {
        this.informeService = informeService;
    }

    @GetMapping("/fecha")
    @Operation(summary = "Obtener fecha del informe", description = "Devuelve la fecha del último informe generado")
    @ApiResponse(responseCode = "200", description = "Fecha del informe devuelta correctamente")
    public String getFechaInforme() {
        return informeService.fechaInforme();
    }

    @GetMapping("/ingreso/diario")
    @Operation(summary = "Obtener ingreso diario", description = "Devuelve el total de ingresos del día actual")
    @ApiResponse(responseCode = "200", description = "Ingreso diario devuelto correctamente")
    public double getIngresoDiario() {
        return informeService.ingresoDiario();
    }

    @GetMapping("/ingreso/semanal")
    @Operation(summary = "Obtener ingreso semanal", description = "Devuelve el total de ingresos de la semana actual")
    @ApiResponse(responseCode = "200", description = "Ingreso semanal devuelto correctamente")
    public double getIngresoSemanal() {
        return informeService.ingresoSemanal();
    }

    @GetMapping("/ingreso/mensual")
    @Operation(summary = "Obtener ingreso mensual", description = "Devuelve el total de ingresos del mes actual")
    @ApiResponse(responseCode = "200", description = "Ingreso mensual devuelto correctamente")
    public double getIngresoMensual() {
        return informeService.ingresoMensual();
    }

    @GetMapping("/toString")
    @Operation(summary = "Obtener representación de texto del informe", description = "Devuelve una representación en texto del informe actual")
    @ApiResponse(responseCode = "200", description = "Representación de texto del informe devuelta correctamente")
    public String getInformeToString() {
        return informeService.toString();
    }
}
