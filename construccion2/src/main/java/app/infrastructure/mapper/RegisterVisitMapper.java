package app.infrastructure.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.domain.model.RegisterVisit;
import app.infrastructure.entities.RegisterVisitEntity;

@Component
public class RegisterVisitMapper {

    @Autowired
    private PatientMapper patientMapper;

    public RegisterVisit toDomain(RegisterVisitEntity entity) {
        if (entity == null) return null;

        RegisterVisit registerVisit = new RegisterVisit();
        registerVisit.setBloodPressure(entity.getBloodPressure());
        registerVisit.setTemperature(entity.getTemperature());
        registerVisit.setPulse(entity.getPulse());
        registerVisit.setOxygenLevel(entity.getOxygenLevel());
        registerVisit.setPatient(patientMapper.toDomain(entity.getPatient()));
        registerVisit.setMedications(entity.getMedications());
        registerVisit.setProcedure(entity.getProcedure());
        registerVisit.setDiagnosticAid(entity.getDiagnosticAid());

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
        entity.setMedications(registerVisit.getMedications());
        entity.setProcedure(registerVisit.getProcedure());
        entity.setDiagnosticAid(registerVisit.getDiagnosticAid());

        return entity;
    }
}
