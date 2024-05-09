package es.uca.ParkingElSalvador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepositoryInDatabase implements CarRepository {
    private final CarRepositoryRepositoryJPA carRepositoryJPA;

    @Autowired
    public CarRepositoryInDatabase(CarRepositoryRepositoryJPA carRepositoryJPA) {
        this.carRepositoryJPA = carRepositoryJPA;
    }

    @Override
    public void meter(Vehiculo v) {
        carRepositoryJPA.save(v);
    }

    @Override
    public void sacar(String mat) {
        carRepositoryJPA.delete(carRepositoryJPA.getByMatricula(mat));
    }

    @Override
    public Vehiculo obtener(String matricula) {
        return carRepositoryJPA.getByMatricula(matricula);
    }

    @Override
    public int numCoches() {
        return (int) carRepositoryJPA.count();
    }
}
