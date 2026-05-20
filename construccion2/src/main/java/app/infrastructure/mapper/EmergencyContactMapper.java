package app.infrastructure.mapper;

import org.springframework.stereotype.Component;

import app.domain.model.EmergencyContact;
import app.domain.model.Patient;
import app.infrastructure.entities.EmergencyContactEntity;
import app.infrastructure.entities.PatientEntity;

@Component
public class EmergencyContactMapper {

    // === Domain → Entity ===
    public EmergencyContactEntity toEntity(EmergencyContact contact) {
        if (contact == null) return null;

        EmergencyContactEntity entity = new EmergencyContactEntity();
        entity.setId(contact.getId());
        entity.setName(contact.getName());
        entity.setLastName(contact.getLastName());
        entity.setPhoneNumber(contact.getPhoneNumber());
        if (contact.getPatient() != null) {
            PatientEntity patient = new PatientEntity();
            patient.setId(contact.getPatient().getId());
            patient.setDocument(contact.getPatient().getDocument());
            entity.setPatient(patient);
        }

        return entity;
    }

    // === Entity → Domain ===
    public EmergencyContact toDomain(EmergencyContactEntity entity) {
        if (entity == null) return null;

        EmergencyContact contact = new EmergencyContact();
        contact.setId(entity.getId());
        contact.setName(entity.getName());
        contact.setLastName(entity.getLastName());
        contact.setPhoneNumber(entity.getPhoneNumber());
        if (entity.getPatient() != null) {
            Patient patient = new Patient();
            patient.setId(entity.getPatient().getId());
            patient.setDocument(entity.getPatient().getDocument());
            patient.setFullName(entity.getPatient().getFullName());
            contact.setPatient(patient);
        }

        return contact;
    }
}
