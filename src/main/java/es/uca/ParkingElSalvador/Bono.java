package es.uca.ParkingElSalvador;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public abstract class Bono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricula;
    @OneToOne
    private Vehiculo v;

    public Bono(Vehiculo vehiculo){
        v = vehiculo;
        matricula = v.matricula();
    }

    public abstract BigDecimal getPrecio();

    public Vehiculo getVehiculo() {
        return v;
    }

    public String getMatriculaCoche(){
        return matricula;
    }

    public abstract String tipoBono();

    @Override
    public String toString() {
        return "Bono del vehiculo: " + v.matricula();
    }
}
