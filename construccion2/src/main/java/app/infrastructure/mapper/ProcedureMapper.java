package app.infrastructure.mapper;

import org.springframework.stereotype.Component;

import app.domain.model.Procedure;
import app.infrastructure.entities.ProcedureEntity;

@Component
public class ProcedureMapper {

    // === Domain → Entity ===
    public ProcedureEntity toEntity(Procedure procedure) {
        if (procedure == null) return null;

        ProcedureEntity entity = new ProcedureEntity();
        entity.setOrderNumber(procedure.getOrderNumber());
        entity.setAmount(procedure.getAmount());
        entity.setRepetitionFrequency(procedure.getRepetitionFrequency());
        entity.setSpecialistAssistance(procedure.isSpecialistAssistance());
        entity.setIdTypeSpecialist(procedure.getIdTypeSpecialist());
        entity.setItem(procedure.getItem());

        return entity;
    }

    // === Entity → Domain ===
    public Procedure toDomain(ProcedureEntity entity) {
        if (entity == null) return null;

        Procedure procedure = new Procedure();
        procedure.setOrderNumber(entity.getOrderNumber());
        procedure.setAmount(entity.getAmount());
        procedure.setRepetitionFrequency(entity.getRepetitionFrequency());
        procedure.setSpecialistAssistance(entity.isSpecialistAssistance());
        procedure.setIdTypeSpecialist(entity.getIdTypeSpecialist());
        procedure.setItem(entity.getItem());

        return procedure;
    }
}
