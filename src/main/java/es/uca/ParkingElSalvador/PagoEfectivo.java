package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

public class PagoEfectivo implements TipoPago{
    @Override
    public void procesarPago(BigDecimal d){
        System.out.println("Pagando " + d.doubleValue() + " en efectivo" );
    }
}
