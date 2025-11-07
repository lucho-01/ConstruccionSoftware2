package app.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.stereotype.Repository;

import app.infrastructure.entities.PatientEntity;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long>{
	PatientEntity findByDocument(Long document);
	List<PatientEntity> findAllByDocument(Long document);
}
