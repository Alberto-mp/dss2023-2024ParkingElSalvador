package es.uca.ParkingElSalvador;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class PagoEfectivo implements TipoPago{
    @Override
    public void procesarPago(BigDecimal d){

        System.out.println("Pagando " + d.doubleValue() + " en efectivo..." );
        try {
            TimeUnit.SECONDS.sleep(1); // Retardo de 1 segundo
        } catch (InterruptedException e) {
            // Manejo de la excepción si es interrumpido mientras está dormido
        }
    }
}
