package app.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.infrastructure.entities.MedicationsEntity;

@Repository
public interface MedicationsRepository extends JpaRepository<MedicationsEntity, Long> {
    MedicationsEntity findByOrderNumber(Long orderNumber);
}
