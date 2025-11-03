package app.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.entities.ProcedureEntity;

@Repository
public interface ProcedureRepository extends JpaRepository<ProcedureEntity, Long> {
    ProcedureEntity findByOrderNumber(Long orderNumber);
}
