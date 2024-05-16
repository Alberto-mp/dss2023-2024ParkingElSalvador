package com.example.CommandCLI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@ShellComponent
public class MyCommands {

    private final WebClient webClient;

    @Autowired
    public MyCommands(WebClient webClient) {
        this.webClient = webClient;
    }

    @ShellMethod("Obtiene la fecha del sistema")
    public String getSystemDate() {
        return callApi("/informes/fecha");
    }

    @ShellMethod("Obtiene ingresos diarios")
    public String getDailyIncome() {
        return callApi("/informes/ingreso/diario");
    }

    @ShellMethod("Obtiene ingresos semanales")
    public String getWeeklyIncome() {
        return callApi("/informes/ingreso/semanal");
    }

    @ShellMethod("Obtiene ingresos mensuales")
    public String getMonthlyIncome() {
        return callApi("/informes/ingreso/mensual");
    }

    @ShellMethod("Obtiene la representación en texto del estado actual")
    public String getStateAsString() {
        return callApi("/informes/toString");
    }

    // Método helper para realizar llamadas GET y manejar la respuesta
    private String callApi(String uri) {
        Mono<String> response = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class);
        
        return safeCall(response);
    }

    // Método helper para manejar bloqueo y errores
    private String safeCall(Mono<String> call) {
        try {
            return call.block();
        } catch (Exception e) {
            return "Error al procesar la solicitud: " + e.getMessage();
        }
    }
}
