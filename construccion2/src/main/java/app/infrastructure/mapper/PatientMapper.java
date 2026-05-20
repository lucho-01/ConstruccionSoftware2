package app.infrastructure.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

import app.domain.model.Patient;
import app.infrastructure.entities.EmployeeEntity;
import app.infrastructure.entities.PatientEntity;

@Component
public class PatientMapper {

    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
        entity.setUsername(patient.getUserName());
        entity.setPassword(patient.getPassword());

        if (patient.getBirthdate() != null && !patient.getBirthdate().isEmpty()) {
            entity.setBirthdate(parseBirthdate(patient.getBirthdate()));
        }

        entity.setGender(patient.getGender());
        entity.setWeight(patient.getWeigth());
        entity.setSize(patient.getSize());
        entity.setPolicyNumber(patient.getPolicyNumber());
        entity.setInsuranceCompanyName(patient.getInsuranceCompanyName());

        if (patient.getPolicyValidity() != null && !patient.getPolicyValidity().isEmpty()) {
            entity.setPolicyValidity(LocalDate.parse(patient.getPolicyValidity()));
        }

        if (patient.getPolicyEndDate() != null && !patient.getPolicyEndDate().isEmpty()) {
            entity.setPolicyEndDate(LocalDate.parse(patient.getPolicyEndDate()));
        }

        if (patient.getDoctorDocument() != null) {
            EmployeeEntity doctorEntity = new EmployeeEntity();
            doctorEntity.setId(patient.getDoctorDocument().getId());
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
        patient.setUserName(entity.getUsername());
        patient.setPassword(entity.getPassword());

        if (entity.getBirthdate() != null) {
            patient.setBirthdate(entity.getBirthdate().format(DISPLAY_FORMATTER));
        }

        patient.setGender(entity.getGender());
        patient.setWeigth(entity.getWeight());
        patient.setSize(entity.getSize());
        if (entity.getPolicyNumber() != null) {
            patient.setPolicyNumber(entity.getPolicyNumber());
        }
        patient.setInsuranceCompanyName(entity.getInsuranceCompanyName());
        if (entity.getPolicyValidity() != null) {
            patient.setPolicyValidity(entity.getPolicyValidity().toString());
        }
        if (entity.getPolicyEndDate() != null) {
            patient.setPolicyEndDate(entity.getPolicyEndDate().toString());
        }

        if (entity.getDoctor() != null) {
            app.domain.model.Employee doctor = new app.domain.model.Employee();
            doctor.setId(entity.getDoctor().getId());
            if (entity.getDoctor().getDocument() != null) {
                try {
                    doctor.setDocument(Long.parseLong(entity.getDoctor().getDocument()));
                } catch (NumberFormatException e) {
                    doctor.setDocument(0);
                }
            }
            doctor.setFullName(entity.getDoctor().getFullName());
            patient.setDoctorDocument(doctor);
        }

        return patient;
    }

    private static LocalDate parseBirthdate(String value) {
        String trimmed = value.trim();
        try {
            return LocalDate.parse(trimmed);
        } catch (DateTimeParseException ignored) {
            return LocalDate.parse(trimmed, DISPLAY_FORMATTER);
        }
    }
}

