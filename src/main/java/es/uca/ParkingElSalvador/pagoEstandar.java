package es.uca.ParkingElSalvador;

public class pagoEstandar{
    private Vehiculo vehiculo;
    private double pMinuto;

    public pagoEstandar(Vehiculo v, double p){
        vehiculo = v;
        pMinuto = p;
    }

    public double cantidad(int minutos){
        return (double)minutos*pMinuto;
    }

    public void pagar() throws Exception {
        if(!vehiculo.haPagado()){
            // La persona paga
            double pago = cantidad(vehiculo.estancia().duracion());
            vehiculo.setDineroPagado(pago);
            vehiculo.pagarEstandar();
        }
    }

}