package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

public class PagoEstandar{
    private Vehiculo vehiculo;
    private double pMinuto;
    private Cajero c;

    public PagoEstandar(Cajero cajero, Vehiculo v, double p){
        vehiculo = v;
        pMinuto = p;
        c = cajero;
    }

    public double cantidad(int minutos){
        return (double)minutos*pMinuto;
    }

    public void pagar(BigDecimal entregado, char F) throws Exception {
        if(!vehiculo.estancia().haPagado()){
            TipoPago p = null;
            double pago = cantidad(vehiculo.estancia().duracion());
            if(F == 'E')
                p = new PagoEfectivo(c);
            else 
                p = new PagoTarjeta();
            if(p.procesarPago(entregado,new BigDecimal(pago))){
                vehiculo.estancia().setDineroPagado(pago);
                vehiculo.estancia().pagarEstandar();
            }
        }
    }
}