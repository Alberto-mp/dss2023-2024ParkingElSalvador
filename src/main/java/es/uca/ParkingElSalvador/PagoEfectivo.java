package es.uca.ParkingElSalvador;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class PagoEfectivo implements TipoPago{
    private Cajero c;
    public PagoEfectivo(Cajero ca){
        c = ca;
    }
    @Override
    public boolean procesarPago(BigDecimal entregado, BigDecimal d){
        if(c.hayCambio(d)){
            System.out.println("Pagando " + d.doubleValue() + " en efectivo..." );
            try {
                TimeUnit.SECONDS.sleep(1); // Retardo de 1 segundo
            } catch (InterruptedException e) {
                // Manejo de la excepción si es interrumpido mientras está dormido
            }
            BigDecimal cambio = entregado.subtract(d);
            c.sacarDinero(cambio); // Restar la cantidad pagada del cajero
            c.meterDinero(d);
            return true;
        }
        else{
            System.out.println("No hay cambio disponible para pagar " + d.doubleValue() + " en efectivo..." );
            return false;
        }
    }
    

}
