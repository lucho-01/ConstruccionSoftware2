package app.infrastructure.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.domain.model.MedicalAppointment;
import app.infrastructure.entities.MedicalAppointmentEntity;

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
        entity.setDoctor(employeeMapper.toEntity(appointment.getDoctor()));
        entity.setPatient(patientMapper.toEntity(appointment.getPatient()));

        if (appointment.getDate() != null)
            entity.setDate(appointment.getDate().toLocalDate());

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
            appointment.setDate(java.sql.Date.valueOf(entity.getDate()));

        return appointment;
    }
}
