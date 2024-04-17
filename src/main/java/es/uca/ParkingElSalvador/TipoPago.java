package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

public interface TipoPago {
    boolean procesarPago(BigDecimal entregado,BigDecimal d);
}
