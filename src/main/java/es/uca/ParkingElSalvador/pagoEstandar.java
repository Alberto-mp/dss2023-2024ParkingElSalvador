package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

public class PagoEstandar{
    private Vehiculo vehiculo;
    private double pMinuto;

    public PagoEstandar(Cajero cajero, Vehiculo v, double p){
        vehiculo = v;
        pMinuto = p;
    }

    public double cantidad(int minutos){
        return (double)minutos*pMinuto;
    }

    public void pagar(BigDecimal entregado, char F) throws Exception {
        if(!vehiculo.estancia().haPagado()){
            TipoPago p = null;
            double pago = cantidad(vehiculo.estancia().duracion());
            if(F == 'E')
                p = new PagoEfectivo();
            else 
                p = new PagoTarjeta();
            p.procesarPago(entregado);
            vehiculo.estancia().setDineroPagado(pago);
            vehiculo.estancia().pagarEstandar();
            
        }
    }
}