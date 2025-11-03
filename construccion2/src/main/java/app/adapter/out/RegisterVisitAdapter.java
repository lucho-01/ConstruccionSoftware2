package app.adapter.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.RegisterVisit;
import app.domain.port.RegisterVisitPort;
import app.infrastructure.entities.RegisterVisitEntity;
import app.infrastructure.mapper.RegisterVisitMapper;
import app.infrastructure.repository.RegisterVisitRepository;

@Service
public class RegisterVisitAdapter implements RegisterVisitPort {

    @Autowired
    private RegisterVisitRepository registerVisitRepository;

    @Autowired
    private RegisterVisitMapper registerVisitMapper;

    @Override
    public void save(RegisterVisit registerVisit) throws Exception {
        RegisterVisitEntity entity = registerVisitMapper.toEntity(registerVisit);
        registerVisitRepository.save(entity);
        System.out.println("Visita registrada correctamente para el paciente: " + registerVisit.getPatient().getFullName());
    }

    @Override
    public RegisterVisit findByPatientDocument(long document) throws Exception {
        RegisterVisitEntity entity = registerVisitRepository.findByPatient_Document(document);
        return registerVisitMapper.toDomain(entity);
    }
}
