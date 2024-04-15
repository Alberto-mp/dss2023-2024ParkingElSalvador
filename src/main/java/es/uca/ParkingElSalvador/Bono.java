package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

public abstract class Bono {
    private Vehiculo v;

    public Bono(Vehiculo vehiculo){
        v = vehiculo;
    }
    public Vehiculo getVehiculo() {return v;}
    public abstract BigDecimal getPrecio();
    public abstract String tipoBono();
}

