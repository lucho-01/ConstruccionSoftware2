package app.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.entities.MedicalAppointmentEntity;

@Repository
public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointmentEntity, Long> {

    @Override
    @EntityGraph(attributePaths = {"doctor", "patient"})
    List<MedicalAppointmentEntity> findAll();

    boolean existsByDoctorIdAndDate(Long doctorId, LocalDateTime date);

    boolean existsByPatientIdAndDate(Long patientId, LocalDateTime date);

    @EntityGraph(attributePaths = {"doctor", "patient"})
    List<MedicalAppointmentEntity> findByPatient_Document(Long document);
}
