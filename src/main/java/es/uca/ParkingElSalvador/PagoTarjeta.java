package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

public class PagoTarjeta implements TipoPago{
    @Override
    public void procesarPago(BigDecimal d){
        System.out.println("Pagando " + d.doubleValue() + " con tarjeta" );
    }
}
