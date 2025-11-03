package app.adapter.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.DiagnosticAid;
import app.domain.port.DiagnosticAidPort;
import app.infrastructure.entities.DiagnosticAidEntity;
import app.infrastructure.mapper.DiagnosticAidMapper;
import app.infrastructure.repository.DiagnosticAidRepository;

@Service
public class DiagnosticAidAdapter implements DiagnosticAidPort {

    @Autowired
    private DiagnosticAidRepository diagnosticAidRepository;

    @Autowired
    private DiagnosticAidMapper diagnosticAidMapper;

    @Override
    public DiagnosticAid findByOrderNumber(DiagnosticAid diagnosticAid) throws Exception {
        DiagnosticAidEntity entity = diagnosticAidRepository.findByOrderNumber(diagnosticAid.getOrderNumber());
        return diagnosticAidMapper.toDomain(entity);
    }

    @Override
    public void save(DiagnosticAid diagnosticAid) throws Exception {
        DiagnosticAidEntity entity = diagnosticAidMapper.toEntity(diagnosticAid);
        entity.setIdDiagnosticAid(null); 
        diagnosticAidRepository.save(entity);
        System.out.println("Ayuda diagnóstica guardada correctamente con orden #" + diagnosticAid.getOrderNumber());
    }
}
