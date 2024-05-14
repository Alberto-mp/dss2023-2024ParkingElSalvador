package es.uca.ParkingElSalvador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
    Parking findFirstBy();  // Recupera la primera instancia encontrada, asumiendo que solo hay una.
}
