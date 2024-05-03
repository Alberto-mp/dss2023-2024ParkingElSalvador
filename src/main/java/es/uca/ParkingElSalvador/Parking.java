package es.uca.ParkingElSalvador;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private final String direccion_postal;
    private final int capacidadTotal;
    private int plazasDisponibles;
    private int plazasOcupadas;

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

    public void decPlazasDisponibles() {
        plazasDisponibles--;
    }
    
    public void decPlazasOcupadas() {
        plazasOcupadas--;
    }

    public void incPlazasDisponibles() {
        plazasDisponibles++;
    }
    
    public void incPlazasOcupadas() {
        plazasOcupadas++;
    }


    public void setPlazasDisponibles(int nuevasPlazasDisponibles) {
        this.plazasDisponibles = nuevasPlazasDisponibles;
    }
    
    public void setPlazasOcupadas(int nuevasPlazasOcupadas) {
        this.plazasOcupadas = nuevasPlazasOcupadas;
    }
    

    public String toString(){
        return nombre+" con direccion postal "+direccion_postal+" tiene una capacidad de "+capacidadTotal;
    }

}
