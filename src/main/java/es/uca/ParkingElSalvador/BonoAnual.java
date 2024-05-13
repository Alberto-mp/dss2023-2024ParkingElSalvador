package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Anual")
@Entity
public class BonoAnual extends Bono {
    private static BigDecimal precio = new BigDecimal(0);

    public BonoAnual(Estancia estancia) {
        super(estancia);
    }

    public BonoAnual() {
        super(null);
    }

    @Override
    public BigDecimal getPrecio() {
        return precio;
    }

    public static void setPrecio(BigDecimal p) {
        precio = p;
    }

    @Override
    public String getTipoBono() {
        return "Anual";
    }
}
