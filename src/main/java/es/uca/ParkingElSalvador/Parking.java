package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Parking {
    private String nombre;
    private final String direccion_postal;
    public final int capacidadTotal;
    public int plazasDisponibles;
    public int plazasOcupadas;

    public Parking(String n, String d, int capT){
        nombre = n;
        direccion_postal = d;
        capacidadTotal = capT;
        plazasDisponibles = capT; 
        plazasOcupadas = 0;
    }

    // Getters y setters 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nuevo_nombre){
        nombre = nuevo_nombre;
    }

    public String getDireccionPostal() {
        return direccion_postal;
    }

    public int getCapacidadTotal() {
        return capacidadTotal;
    }

    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public int getPlazasOcupadas() {
        return plazasOcupadas;
    }

    public String toString(){
        return nombre+" con direccion postal "+direccion_postal+" tiene una capacidad de "+capacidadTotal;
    }

}
