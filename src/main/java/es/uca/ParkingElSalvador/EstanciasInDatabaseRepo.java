package es.uca.ParkingElSalvador;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EstanciasInDatabaseRepo implements EstanciasRepository {
    private final EstanciasRepositoryJPA estanciasRepositoryJPA;

    @Autowired
    public EstanciasInDatabaseRepo(EstanciasRepositoryJPA estanciasRepositoryJPA) {
        this.estanciasRepositoryJPA = estanciasRepositoryJPA;
    }

    @Override
    public void almacenar(Vehiculo vehiculo) {
        estanciasRepositoryJPA.save(vehiculo.estancia());
    }

    @Override
    public boolean haestadoCoche(String matricula) {
        return estanciasRepositoryJPA.existsByVehiculoMatricula(matricula);
    }

    @Override
    public List<Estancia> estancias(String matricula) {
        return estanciasRepositoryJPA.findByVehiculoMatricula(matricula); // Suponiendo que esto devuelve una List<Estancia>
    }
    

    @Override
    public List<Estancia> estancias() {
        return estanciasRepositoryJPA.findAll(); // Esto devuelve una List<Estancia>
    }
    

    @Override
    public long numEstancias() {
        return estanciasRepositoryJPA.count();
    }
}
