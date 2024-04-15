package es.uca.ParkingElSalvador;

import java.time.LocalDateTime;

public class PagoBono {
    private Vehiculo vehiculo;

    public PagoBono(Vehiculo v) {
        vehiculo = v;
    }

    public void comprarBono(Bono bono, int duracion) {
        if (!vehiculo.estancia().poseeBono()) {
            double pago = bono.getPrecio().doubleValue() * duracion;
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

    public void comprarBonoMensual(int meses) {
        BonoMensual bono = new BonoMensual(vehiculo);
        comprarBono(bono, meses);
    }

    public void comprarBonoTrimestral(int trimestres) {
        BonoTrimestral bono = new BonoTrimestral(vehiculo);
        comprarBono(bono, trimestres);
    }

    public void comprarBonoAnual(int annos) {
        BonoAnual bono = new BonoAnual(vehiculo);
        comprarBono(bono, annos);
    }
}
