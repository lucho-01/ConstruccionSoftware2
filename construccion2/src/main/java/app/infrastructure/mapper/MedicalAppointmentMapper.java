package app.infrastructure.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.domain.model.MedicalAppointment;
import app.infrastructure.entities.EmployeeEntity;
import app.infrastructure.entities.MedicalAppointmentEntity;
import app.infrastructure.entities.PatientEntity;

@Component
public class MedicalAppointmentMapper {

    @Autowired
    private final EmployeeMapper employeeMapper;

    @Autowired
    private final PatientMapper patientMapper;

    public MedicalAppointmentMapper(EmployeeMapper employeeMapper, PatientMapper patientMapper) {
        this.employeeMapper = employeeMapper;
        this.patientMapper = patientMapper;
    }

    // === Domain → Entity ===
    public MedicalAppointmentEntity toEntity(MedicalAppointment appointment) {
        if (appointment == null) return null;

        MedicalAppointmentEntity entity = new MedicalAppointmentEntity();
        entity.setAppointmentId(appointment.getAppointmentId());

        if (appointment.getDoctor() != null) {
            EmployeeEntity doctor = new EmployeeEntity();
            doctor.setId(appointment.getDoctor().getId());
            entity.setDoctor(doctor);
        }

        if (appointment.getPatient() != null) {
            PatientEntity patient = new PatientEntity();
            patient.setId(appointment.getPatient().getId());
            entity.setPatient(patient);
        }

        if (appointment.getDate() != null)
            entity.setDate(appointment.getDate());

        return entity;
    }

    // === Entity → Domain ===
    public MedicalAppointment toDomain(MedicalAppointmentEntity entity) {
        if (entity == null) return null;

        MedicalAppointment appointment = new MedicalAppointment();
        appointment.setAppointmentId(entity.getAppointmentId());
        appointment.setDoctor(employeeMapper.toDomain(entity.getDoctor()));
        appointment.setPatient(patientMapper.toDomain(entity.getPatient()));

        if (entity.getDate() != null)
            appointment.setDate(entity.getDate());

        return appointment;
    }
}
