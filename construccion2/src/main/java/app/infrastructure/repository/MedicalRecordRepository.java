package app.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import app.infrastructure.entities.MedicalRecordEntity;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity, Long> {
    MedicalRecordEntity findByDoctorDocumentAndPatient_Id(Long doctorDocument, Long patientId);
    Optional<MedicalRecordEntity> findById(Long id);
    List<MedicalRecordEntity> findByPatient_Document(Long document);
}
