import org.springframework.data.jpa.repository.JpaRepository;

public interface BonoRepositoryJPA extends JpaRepository<Bono, String> {
    public List<Bono> getBonoByMatricula(String matricula);
}
