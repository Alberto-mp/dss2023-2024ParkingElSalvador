package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

public class BonoMensual extends Bono {
    private static BigDecimal precio;

    public BonoMensual(Vehiculo vehiculo) {
        super(vehiculo);
        precio = BigDecimal.ZERO;
    }

    @Override
    public Vehiculo getVehiculo() {
        return super.getVehiculo();
    }

    @Override
    public BigDecimal getPrecio() {
        return precio;
    }


    public static void setPrecio(BigDecimal p) {
        precio = p;
    }

    @Override
    public String tipoBono() {
        return "Mensual";
    }
}
