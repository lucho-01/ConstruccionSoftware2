package app.adapter.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Procedure;
import app.domain.port.ProcedurePort;
import app.infrastructure.entities.ProcedureEntity;
import app.infrastructure.mapper.ProcedureMapper;
import app.infrastructure.repository.ProcedureRepository;

@Service
public class ProcedureAdapter implements ProcedurePort {

    @Autowired
    private ProcedureRepository procedureRepository;

    @Autowired
    private ProcedureMapper procedureMapper;

    @Override
    public Procedure findByOrderNumber(Procedure procedure) throws Exception {
        ProcedureEntity entity = procedureRepository.findByOrderNumber(procedure.getOrderNumber());
        return procedureMapper.toDomain(entity);
    }

    @Override
    public void save(Procedure procedure) throws Exception {
        ProcedureEntity entity = procedureMapper.toEntity(procedure);
        entity.setId(null); 
        procedureRepository.save(entity);
        System.out.println("Procedimiento guardado correctamente con número de orden: " + procedure.getOrderNumber());
    }
}
