package es.uca.ParkingElSalvador;

import javax.persistence.Entity;

@Entity
public class Vehiculo {
    private final String matricula;
    private Estancia estancia;

    public Vehiculo(String m){
        matricula = m;
        estancia = new Estancia(this);
    }

    public void sale(){
        estancia.termina();
    }

    // Metodos observadores
    public String matricula(){
        return matricula;
    }

    public Estancia estancia(){
        return estancia;
    }

    public String toString(){
        return "Vehiculo con matricula " + matricula;
    }

}
