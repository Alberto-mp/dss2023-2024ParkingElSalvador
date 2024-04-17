package es.uca.ParkingElSalvador;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class PagoTarjeta implements TipoPago{
    @Override
    public boolean procesarPago(BigDecimal entregado, BigDecimal d){
        try {
            TimeUnit.SECONDS.sleep(1); // Retardo de 1 segundo
        } catch (InterruptedException e) {
            // Manejo de la excepción si es interrumpido mientras está dormido
        }
        System.out.println("Pagando " + d.doubleValue() + " con tarjeta" );
        return true;
    }

}
