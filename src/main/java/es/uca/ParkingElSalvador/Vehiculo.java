package es.uca.ParkingElSalvador;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricula;

    @OneToOne
    private Estancia estancia;

    public Vehiculo(String m){
        matricula = m;
    }

    public void iniciarEstancia(){
        estancia  = new Estancia(this);
    }

    public Vehiculo(){
        matricula = ""; 
    }

    public void setMatricula(String m){
        matricula = m;
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
