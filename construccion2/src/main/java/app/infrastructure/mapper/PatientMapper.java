package app.infrastructure.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import app.domain.model.Patient;
import app.infrastructure.entities.EmployeeEntity;
import app.infrastructure.entities.PatientEntity;

@Component
public class PatientMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // 🔁 Domain → Entity
    public static PatientEntity toEntity(Patient patient) {
        if (patient == null) return null;

        PatientEntity entity = new PatientEntity();
        entity.setId(patient.getId());
        entity.setDocument(patient.getDocument());
        entity.setPhoneNumber(patient.getPhoneNumber());
        entity.setFullName(patient.getFullName());
        entity.setAddress(patient.getAddress());
        entity.setEmail(patient.getEmail());

        if (patient.getBirthdate() != null && !patient.getBirthdate().isEmpty()) {
            entity.setBirthdate(LocalDate.parse(patient.getBirthdate(), FORMATTER));
        }

        entity.setGender(patient.getGender());
        entity.setWeight(patient.getWeigth());
        entity.setSize(patient.getSize());

        if (patient.getDoctor() != null) {
            EmployeeEntity doctorEntity = new EmployeeEntity();
            doctorEntity.setId(patient.getDoctor().getId());
            entity.setDoctor(doctorEntity);
        }

        return entity;
    }

    // 🔁 Entity → Domain
    public static Patient toDomain(PatientEntity entity) {
        if (entity == null) return null;

        Patient patient = new Patient();
        patient.setId(entity.getId());
        patient.setDocument(entity.getDocument());
        patient.setPhoneNumber(entity.getPhoneNumber());
        patient.setFullName(entity.getFullName());
        patient.setAddress(entity.getAddress());
        patient.setEmail(entity.getEmail());

        if (entity.getBirthdate() != null) {
            patient.setBirthdate(entity.getBirthdate().format(FORMATTER));
        }

        patient.setGender(entity.getGender());
        patient.setWeigth(entity.getWeight());
        patient.setSize(entity.getSize());

        if (entity.getDoctor() != null) {
            app.domain.model.Employee doctor = new app.domain.model.Employee();
            doctor.setId(entity.getDoctor().getId());
            patient.setDoctor(doctor);
        }

        return patient;
    }
}

