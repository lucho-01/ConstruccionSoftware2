package app.infrastructure.mapper;

import org.springframework.stereotype.Component;
import app.domain.model.DiagnosticAid;
import app.infrastructure.entities.DiagnosticAidEntity;

@Component
public class DiagnosticAidMapper {

    public DiagnosticAidEntity toEntity(DiagnosticAid diagnosticAid) {
        if (diagnosticAid == null) return null;

        DiagnosticAidEntity entity = new DiagnosticAidEntity();
        entity.setIdDiagnosticAid(diagnosticAid.getIdDiagnosticAid());
        entity.setOrderNumber(diagnosticAid.getOrderNumber());
        entity.setAmount(diagnosticAid.getAmount());
        entity.setSpecialistAssistance(diagnosticAid.isSpecialistAssistance());
        entity.setIdTypeSpecialist(diagnosticAid.getIdTypeSpecialist());
        entity.setItem(diagnosticAid.getItem());
        return entity;
    }

    public DiagnosticAid toDomain(DiagnosticAidEntity entity) {
        if (entity == null) return null;

        DiagnosticAid diagnosticAid = new DiagnosticAid();
        diagnosticAid.setIdDiagnosticAid(entity.getIdDiagnosticAid());
        diagnosticAid.setOrderNumber(entity.getOrderNumber());
        diagnosticAid.setAmount(entity.getAmount());
        diagnosticAid.setSpecialistAssistance(entity.isSpecialistAssistance());
        diagnosticAid.setIdTypeSpecialist(entity.getIdTypeSpecialist());
        diagnosticAid.setItem(entity.getItem());
        return diagnosticAid;
    }
}
