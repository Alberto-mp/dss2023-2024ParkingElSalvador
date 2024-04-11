package es.uca.ParkingElSalvador;

import java.time.LocalDateTime;

public class PagoBono{
    private Vehiculo vehiculo;
    public PagoBono(Vehiculo v){
        vehiculo = v;
    }

    public void comprarBonoMensual(int meses) throws Exception {
        if(!vehiculo.estancia().poseeBono()){
            BonoMensual bono = new BonoMensual(meses);
            double pago = (double)bono.precioBono();
            vehiculo.estancia().setDineroPagado(pago);
            vehiculo.estancia().compraBono();
            vehiculo.estancia().setFinBono(LocalDateTime.now().plusMonths(meses));
        }
    }

    public void comprarBonoTrimestral(int trimestres) throws Exception {
        if(!vehiculo.estancia().poseeBono()){
            BonoTrimestral bono = new BonoTrimestral(trimestres);
            double pago = (double)bono.precioBono();
            vehiculo.estancia().setDineroPagado(pago);
            vehiculo.estancia().compraBono();
            vehiculo.estancia().setFinBono(LocalDateTime.now().plusMonths(3*trimestres));
        }
    }

    public void comprarBonoAnual(int annos) throws Exception {
        if(!vehiculo.estancia().poseeBono()){
            BonoAnual bono = new BonoAnual(annos);
            double pago = (double)bono.precioBono();
            vehiculo.estancia().setDineroPagado(pago);
            vehiculo.estancia().compraBono();
            vehiculo.estancia().setFinBono(LocalDateTime.now().plusYears(annos));
        }
    }

}