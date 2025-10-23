package app.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.entities.BillingEntity;


@Repository
public interface BillingRepository extends JpaRepository<BillingEntity, Long> {
	BillingEntity findByPatientDocument(Long patientDocument);
	
}
