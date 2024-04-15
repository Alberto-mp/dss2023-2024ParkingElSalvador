package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

public class BonoTrimestral extends Bono {
    private static BigDecimal precio;

    public BonoTrimestral(Vehiculo vehiculo) {
        super(vehiculo);
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
        return "Trimestral";
    }
}
