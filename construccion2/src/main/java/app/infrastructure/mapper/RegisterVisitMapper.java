package app.infrastructure.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.domain.model.RegisterVisit;
import app.infrastructure.entities.RegisterVisitEntity;

@Component
public class RegisterVisitMapper {

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private MedicationsMapper medicationsMapper;

    @Autowired
    private ProcedureMapper procedureMapper;

    @Autowired
    private DiagnosticAidMapper diagnosticAidMapper;

    public RegisterVisit toDomain(RegisterVisitEntity entity) {
        if (entity == null) return null;

        RegisterVisit registerVisit = new RegisterVisit();
        registerVisit.setBloodPressure(entity.getBloodPressure());
        registerVisit.setTemperature(entity.getTemperature());
        registerVisit.setPulse(entity.getPulse());
        registerVisit.setOxygenLevel(entity.getOxygenLevel());
        registerVisit.setPatient(patientMapper.toDomain(entity.getPatient()));
        registerVisit.setMedications(medicationsMapper.toDomain(entity.getMedications()));
        registerVisit.setProcedure(procedureMapper.toDomain(entity.getProcedure()));
        registerVisit.setDiagnosticAid(diagnosticAidMapper.toDomain(entity.getDiagnosticAid()));

        return registerVisit;
    }

    public RegisterVisitEntity toEntity(RegisterVisit registerVisit) {
        if (registerVisit == null) return null;

        RegisterVisitEntity entity = new RegisterVisitEntity();
        entity.setBloodPressure(registerVisit.getBloodPressure());
        entity.setTemperature(registerVisit.getTemperature());
        entity.setPulse(registerVisit.getPulse());
        entity.setOxygenLevel(registerVisit.getOxygenLevel());
        entity.setPatient(patientMapper.toEntity(registerVisit.getPatient()));
        entity.setMedications(medicationsMapper.toEntity(registerVisit.getMedications()));
        entity.setProcedure(procedureMapper.toEntity(registerVisit.getProcedure()));
        entity.setDiagnosticAid(diagnosticAidMapper.toEntity(registerVisit.getDiagnosticAid()));

        return entity;
    }
}
