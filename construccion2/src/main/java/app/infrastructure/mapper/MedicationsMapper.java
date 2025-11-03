package app.infrastructure.mapper;

import org.springframework.stereotype.Component;

import app.domain.model.Employee;
import app.domain.model.Medications;
import app.infrastructure.entities.MedicationsEntity;
import app.domain.model.User;

@Component
public class MedicationsMapper {

    public MedicationsEntity toEntity(Medications medications) {
        if (medications == null) return null;

        MedicationsEntity entity = new MedicationsEntity();
        entity.setIdMedications(medications.getiDmedications());
        entity.setOrderNumber(medications.getOrderNumber());
        entity.setItem(medications.getItem());
        entity.setDose(medications.getDose());
        entity.setTreatmentDuration(medications.getTreatmentDuration());
        entity.setDoctorId(
            medications.getDoctor() != null ? medications.getDoctor().getId() : null
        );

        return entity;
    }

    public Medications toDomain(MedicationsEntity entity) {
        if (entity == null) return null;

        Medications medications = new Medications();
        medications.setiDmedications(entity.getIdMedications());
        medications.setOrderNumber(entity.getOrderNumber());
        medications.setItem(entity.getItem());
        medications.setDose(entity.getDose());
        medications.setTreatmentDuration(entity.getTreatmentDuration());

        // Crea un objeto Employee solo con el ID (sin cargar todo el médico)
        if (entity.getDoctorId() != null) {
            Employee doctor = new Employee();
            doctor.setId(entity.getDoctorId());
            medications.setDoctor(doctor);
        }

        return medications;
    }
}
