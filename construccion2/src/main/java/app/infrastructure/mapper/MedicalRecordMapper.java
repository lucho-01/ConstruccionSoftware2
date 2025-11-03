package app.infrastructure.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.domain.model.MedicalRecord;
import app.infrastructure.entities.MedicalRecordEntity;

@Component
public class MedicalRecordMapper {

    @Autowired
    private final EmployeeMapper employeeMapper;

    @Autowired
    private final PatientMapper patientMapper;

    public MedicalRecordMapper(EmployeeMapper employeeMapper, PatientMapper patientMapper) {
        this.employeeMapper = employeeMapper;
        this.patientMapper = patientMapper;
    }

    // === Domain → Entity ===
    public MedicalRecordEntity toEntity(MedicalRecord record) {
        if (record == null) return null;

        MedicalRecordEntity entity = new MedicalRecordEntity();
        entity.setDoctorDocument(record.getDoctorDocument());
        entity.setSymptomatology(record.getSymptomatology());
        entity.setReasonConsultation(record.getReasonConsultation());
        entity.setDiagnosis(record.getDiagnosis());

        if (record.getDate() != null)
            entity.setDate(record.getDate().toLocalDate());

        entity.setDoctor(employeeMapper.toEntity(record.getDoctor()));
        entity.setPatient(patientMapper.toEntity(record.getPatient()));

        return entity;
    }

    // === Entity → Domain ===
    public MedicalRecord toDomain(MedicalRecordEntity entity) {
        if (entity == null) return null;

        MedicalRecord record = new MedicalRecord();
        record.setDoctorDocument(entity.getDoctorDocument());
        record.setSymptomatology(entity.getSymptomatology());
        record.setReasonConsultation(entity.getReasonConsultation());
        record.setDiagnosis(entity.getDiagnosis());

        if (entity.getDate() != null)
            record.setDate(java.sql.Date.valueOf(entity.getDate()));

        record.setDoctor(employeeMapper.toDomain(entity.getDoctor()));
        record.setPatient(patientMapper.toDomain(entity.getPatient()));

        return record;
    }
}
