package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

public class PagoEstandar{
    private Vehiculo vehiculo;
    private double pMinuto;

    public PagoEstandar(Vehiculo v, double p){
        vehiculo = v;
        pMinuto = p;
    }

    public double cantidad(int minutos){
        return (double)minutos*pMinuto;
    }

    public void pagar(char F) throws Exception {
        if(!vehiculo.estancia().haPagado()){
            TipoPago p = null;
            if(F == 'E')
                p = new PagoEfectivo();
            else 
                p = new PagoTarjeta();
            double pago = cantidad(vehiculo.estancia().duracion());
            p.procesarPago(new BigDecimal(pago));
            vehiculo.estancia().setDineroPagado(pago);
            vehiculo.estancia().pagarEstandar();
        }
    }
}