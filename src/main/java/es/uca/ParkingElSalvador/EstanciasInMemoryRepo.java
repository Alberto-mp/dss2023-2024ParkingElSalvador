package es.uca.ParkingElSalvador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Vector;

@Repository
public class EstanciasInDatabaseRepo implements EstanciasRepository {
    private final EstanciasRepositoryJPA estanciasRepositoryJPA;

    @Autowired
    public EstanciasInDatabaseRepo(EstanciasRepositoryJPA estanciasRepositoryJPA) {
        this.estanciasRepositoryJPA = estanciasRepositoryJPA;
    }

    @Override
    public void almacenar(Vehiculo vehiculo) {
        estanciasRepositoryJPA.save(new Estancia(vehiculo));
    }

    @Override
    public boolean haestadoCoche(String matricula) {
        return estanciasRepositoryJPA.beenCar(matricula);
    }

    @Override
    public Vector<Estancia> estancias(String matricula) {
        List<Estancia> estanciasList = estanciasRepositoryJPA.getEstanciasByMatricula(matricula);
        return new Vector<>(estanciasList);
    }

    @Override
    public Vector<Estancia> estancias() {
        List<Estancia> estanciasList = estanciasRepositoryJPA.findAll();
        return new Vector<>(estanciasList);
    }

    @Override
    public int numEstancias() {
        return (int) estanciasRepositoryJPA.count();
    }
}
