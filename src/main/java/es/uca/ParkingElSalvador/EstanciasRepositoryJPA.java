import org.springframework.data.jpa.repository.JpaRepository;

public interface EstanciasRepositoryJPA extends JpaRepository<Vehiculo, String>{
    public boolean beenCar(String matricula);
    public Vector<Estancia> getEstanciasByMatricula(String matricula);
}
