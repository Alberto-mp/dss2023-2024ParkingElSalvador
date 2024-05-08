package es.uca.ParkingElSalvador;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class PagoBono {
    private Vehiculo vehiculo;
    private CarService v;

    public PagoBono(CarService c) {
        v = c;
    }

    public void comprarBono(BigDecimal entregado, Bono bono, String mat, int duracion, char F) {
        vehiculo = v.getVehiculo(mat);
        v.delete(vehiculo);
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
            v.save(vehiculo);
        }
    }

    public void comprarBonoMensual(BigDecimal entregado, String mat, int meses, char F) {
        BonoMensual bono = new BonoMensual(vehiculo);
        comprarBono(entregado,bono, mat, meses,F);
    }

    public void comprarBonoTrimestral(BigDecimal entregado, String mat, int trimestres, char F) {
        BonoTrimestral bono = new BonoTrimestral(vehiculo);
        comprarBono(entregado,bono, mat, trimestres,F);
    }

    public void comprarBonoAnual(BigDecimal entregado, String mat, int annos, char F) {
        BonoAnual bono = new BonoAnual(vehiculo);
        comprarBono(entregado,bono, mat, annos,F);
    }
}
