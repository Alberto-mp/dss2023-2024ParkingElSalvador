package es.uca.ParkingElSalvador;

import java.math.BigDecimal;
import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Mensual")
@Entity
public class BonoMensual extends Bono {
    private static BigDecimal precio = new BigDecimal(0);

    public BonoMensual(Estancia estancia) {
        super(estancia);
    }

    public BonoMensual() {
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
        return "Mensual";
    }
}
