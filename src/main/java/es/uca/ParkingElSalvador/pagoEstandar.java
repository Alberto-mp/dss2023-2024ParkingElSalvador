package es.uca.ParkingElSalvador;

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

    public void pagar() throws Exception {
        if(!vehiculo.estancia().haPagado()){
            // La persona paga
            double pago = cantidad(vehiculo.estancia().duracion());
            vehiculo.estancia().setDineroPagado(pago);
            vehiculo.estancia().pagarEstandar();
        }
    }

}