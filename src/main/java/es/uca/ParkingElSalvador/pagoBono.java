package es.uca.ParkingElSalvador;

import java.time.LocalDateTime;

public class pagoBono{
    private Vehiculo vehiculo;
    public pagoBono(Vehiculo v){
        vehiculo = v;
    }

    public void comprarBonoMensual(int meses) throws Exception {
        if(!vehiculo.poseeBono()){
            BonoMensual bono = new BonoMensual(meses);
            double pago = (double)bono.precioBono();
            vehiculo.setDineroPagado(pago);
            vehiculo.compraBono();
            vehiculo.setFinBono(LocalDateTime.now().plusMonths(meses));
        }
    }

    public void comprarBonoTrimestral(int trimestres) throws Exception {
        if(!vehiculo.poseeBono()){
            BonoTrimestral bono = new BonoTrimestral(trimestres);
            double pago = (double)bono.precioBono();
            vehiculo.setDineroPagado(pago);
            vehiculo.compraBono();
            vehiculo.setFinBono(LocalDateTime.now().plusMonths(3*trimestres));
        }
    }

    public void comprarBonoAnual(int annos) throws Exception {
        if(!vehiculo.poseeBono()){
            BonoAnual bono = new BonoAnual(annos);
            double pago = (double)bono.precioBono();
            vehiculo.setDineroPagado(pago);
            vehiculo.compraBono();
            vehiculo.setFinBono(LocalDateTime.now().plusYears(annos));
        }
    }

}