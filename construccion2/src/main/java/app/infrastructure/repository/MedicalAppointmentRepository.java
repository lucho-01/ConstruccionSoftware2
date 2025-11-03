package app.infrastructure.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.entities.MedicalAppointmentEntity;

@Repository
public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointmentEntity, Long> {

    boolean existsByDoctorIdAndDate(Long doctorId, LocalDate date);

    boolean existsByPatientIdAndDate(Long patientId, LocalDate date);
}

