package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

public class Cajero {
    private BigDecimal dinero;
    
    public Cajero() {
        dinero = new BigDecimal(0);
    }
    
    public BigDecimal getDinero() {
        return dinero;
    }
    
    public void setDinero(BigDecimal dinero) {
        this.dinero = dinero;
    }

    public void meterDinero(BigDecimal d) {
        dinero = dinero.add(d);
    }

    public void sacarDinero(BigDecimal d) {
        dinero = dinero.subtract(d);
    }
 
    
    public boolean hayCambio(BigDecimal otroDinero) {
        return (otroDinero.compareTo(dinero) <= 0);
    }
}
