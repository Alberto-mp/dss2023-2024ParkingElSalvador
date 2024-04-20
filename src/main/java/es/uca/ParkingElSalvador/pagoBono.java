package es.uca.ParkingElSalvador;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagoBono {
    private Vehiculo vehiculo;

    public PagoBono(Vehiculo v) {
        vehiculo = v;

    }

    public void comprarBono(BigDecimal entregado, Bono bono, int duracion, char F) {
        if (!vehiculo.estancia().poseeBono()) {
            TipoPago p = null;
            if(F == 'E')
                p = new PagoEfectivo();
            else 
                p = new PagoTarjeta();
           
            double pago = bono.getPrecio().doubleValue() * duracion;
            p.procesarPago(entregado);
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

    public void comprarBonoMensual(BigDecimal entregado,int meses, char F) {
        BonoMensual bono = new BonoMensual(vehiculo);
        comprarBono(entregado,bono, meses,F);
    }

    public void comprarBonoTrimestral(BigDecimal entregado,int trimestres, char F) {
        BonoTrimestral bono = new BonoTrimestral(vehiculo);
        comprarBono(entregado,bono, trimestres,F);
    }

    public void comprarBonoAnual(BigDecimal entregado,int annos, char F) {
        BonoAnual bono = new BonoAnual(vehiculo);
        comprarBono(entregado,bono, annos,F);
    }
}
