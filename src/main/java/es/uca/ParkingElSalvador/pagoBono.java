package es.uca.ParkingElSalvador;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagoBono {
    private Vehiculo vehiculo;

    public PagoBono(Vehiculo v) {
        vehiculo = v;
    }

    public void comprarBono(Bono bono, int duracion, char F) {
        if (!vehiculo.estancia().poseeBono()) {
            TipoPago p = null;
            if(F == 'E')
                p = new PagoEfectivo();
            else 
                p = new PagoTarjeta();
           
            double pago = bono.getPrecio().doubleValue() * duracion;
            p.procesarPago(new BigDecimal(pago));
            
            vehiculo.estancia().setDineroPagado(pago);
            vehiculo.estancia().compraBono();
            LocalDateTime finBono = LocalDateTime.now();
            if (bono instanceof BonoMensual) {
                finBono = finBono.plusMonths(duracion);
            } else if (bono instanceof BonoTrimestral) {
                finBono = finBono.plusMonths(3 * duracion);
            } else if (bono instanceof BonoAnual) {
                finBono = finBono.plusYears(duracion);
            }
            vehiculo.estancia().setFinBono(finBono);
        }
    }

    public void comprarBonoMensual(int meses, char F) {
        BonoMensual bono = new BonoMensual(vehiculo);
        comprarBono(bono, meses,F);
    }

    public void comprarBonoTrimestral(int trimestres, char F) {
        BonoTrimestral bono = new BonoTrimestral(vehiculo);
        comprarBono(bono, trimestres,F);
    }

    public void comprarBonoAnual(int annos, char F) {
        BonoAnual bono = new BonoAnual(vehiculo);
        comprarBono(bono, annos,F);
    }
}
