package app.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.infrastructure.entities.DiagnosticAidEntity;

@Repository
public interface DiagnosticAidRepository extends JpaRepository<DiagnosticAidEntity, Long> {
    DiagnosticAidEntity findByOrderNumber(Long orderNumber);
}
