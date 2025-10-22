package app.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import app.infrastructure.entities.PatientEntity;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long>{
	PatientEntity findByDocument(Long document);
}
