package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class BonoAnual extends Bono {
    private static BigDecimal precio;

    public BonoAnual(Vehiculo vehiculo) {
        super(vehiculo);
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
