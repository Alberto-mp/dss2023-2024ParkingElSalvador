package es.uca.ParkingElSalvador;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepositoryRepositoryJPA extends JpaRepository<Vehiculo, String>{
    public Vehiculo getByMatricula(String matricula);
}
