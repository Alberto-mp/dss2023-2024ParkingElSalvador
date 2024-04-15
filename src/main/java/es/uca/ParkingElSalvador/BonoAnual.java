package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

public class BonoAnual extends Bono {
    private static BigDecimal precio;

    public BonoAnual(Vehiculo vehiculo) {
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
        return "Anual";
    }
}
