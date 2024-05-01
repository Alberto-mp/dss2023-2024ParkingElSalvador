package es.uca.ParkingElSalvador;

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
        // No se proporciona un método específico para almacenar una instancia de vehículo en EstanciasRepositoryJPA.
        // Se puede implementar según sea necesario, dependiendo de cómo se maneje la lógica en tu aplicación.
    }

    @Override
    public boolean haestadoCoche(String matricula) {
        return estanciasRepositoryJPA.beenCar(matricula);
    }

    @Override
    public Vector<Estancia> estancias(String matricula) {
        return estanciasRepositoryJPA.getEstanciasByMatricula(matricula);
    }

    @Override
    public Vector<Estancia> estancias() {
        // No se proporciona un método específico para obtener todas las estancias en EstanciasRepositoryJPA.
        // Se puede implementar según sea necesario, dependiendo de cómo se maneje la lógica en tu aplicación.
        return null;
    }

    @Override
    public int numEstancias() {
        // No se proporciona un método específico para contar el número de estancias en EstanciasRepositoryJPA.
        // Se puede implementar según sea necesario, dependiendo de cómo se maneje la lógica en tu aplicación.
        return 0;
    }
}
