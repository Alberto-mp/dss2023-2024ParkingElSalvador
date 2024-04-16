package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

public abstract class Bono {
    private Vehiculo v;

    public Bono(Vehiculo vehiculo){
        v = vehiculo;
    }
    public abstract BigDecimal getPrecio();
    public Vehiculo getVehiculo() {return v;}
    public abstract String tipoBono();
    public String toString() {
        return "Bono del vehiculo: "+v.matricula();
    }
}

