package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

public class Bono {
    private Vehiculo v;
    private BigDecimal precio;

    public Bono(Vehiculo vehiculo){
        v = vehiculo;
        precio = new BigDecimal(0);
    }

    public Vehiculo getVehiculo() {
        return v;
    }

    public BigDecimal getPrecio(){
        return precio;
    }

    public void setPrecio(BigDecimal x){
        precio = x;
    }

    public String tipoBono(){
        return "Bono Estandar";
    }

}

