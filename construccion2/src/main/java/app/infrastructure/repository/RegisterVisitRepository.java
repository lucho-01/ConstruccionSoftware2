package app.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.entities.RegisterVisitEntity;

@Repository
public interface RegisterVisitRepository extends JpaRepository<RegisterVisitEntity, Long> {
    RegisterVisitEntity findByPatient_Document(long document);
}
