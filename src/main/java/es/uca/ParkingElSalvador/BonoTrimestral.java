package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Trimestral")
@Entity
public class BonoTrimestral extends Bono {
    private static BigDecimal precio = new BigDecimal(0);

    public BonoTrimestral(Estancia estancia) {
        super(estancia);
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
        return "Trimestral";
    }
}
