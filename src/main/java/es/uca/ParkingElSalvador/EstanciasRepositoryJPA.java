package es.uca.ParkingElSalvador;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstanciasRepositoryJPA extends JpaRepository<Estancia, String>{
    boolean existsByVehiculoMatricula(String matricula);
    public List<Estancia> findByVehiculoMatricula(String matricula);
}
