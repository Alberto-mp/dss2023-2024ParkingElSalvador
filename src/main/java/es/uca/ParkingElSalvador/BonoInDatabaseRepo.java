package es.uca.ParkingElSalvador;

import java.util.List;

@Repository
public class BonoInDatabaseRepo implements BonoRepository {
    private final BonoRepositoryJPA bonoRepositoryJPA;

    public BonoInDatabaseRepo(BonoRepositoryJPA bonoRepositoryJPA) {
        this.bonoRepositoryJPA = bonoRepositoryJPA;
    }

    @Override
    public void meter(Bono b) {
        bonoRepositoryJPA.save(b);
    }

    @Override
    public void sacar(Bono b) {
        bonoRepositoryJPA.delete(b);
    }

    @Override
    public List<Bono> bonos(String matricula) {
        return bonoRepositoryJPA.getBonoByMatricula(matricula);
    }

    @Override
    public List<Bono> getAllBonos() {
        return bonoRepositoryJPA.findAll();
    }
}
