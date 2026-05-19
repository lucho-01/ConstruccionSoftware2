package app.adapter.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.stream.Collectors;

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
        entity.setId(null);
        registerVisitRepository.save(entity);
        System.out.println("Visita registrada correctamente para el paciente: " + registerVisit.getPatient().getFullName());
    }

    @Override
    public RegisterVisit findById(RegisterVisit registerVisit) throws Exception {
        if (registerVisit.getId() == null) {
            return null;
        }
        return registerVisitRepository.findById(registerVisit.getId())
                .map(registerVisitMapper::toDomain)
                .orElse(null);
    }

    @Override
    public java.util.List<RegisterVisit> findAll() throws Exception {
        return registerVisitRepository.findAll().stream()
                .map(registerVisitMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public RegisterVisit findByPatientDocument(long document) throws Exception {
        RegisterVisitEntity entity = registerVisitRepository.findByPatient_Document(document);
        return registerVisitMapper.toDomain(entity);
    }

    @Override
    public RegisterVisit update(RegisterVisit registerVisit) throws Exception {
        Optional<RegisterVisitEntity> existingEntityOpt = registerVisitRepository.findById(registerVisit.getId());
        if (existingEntityOpt.isEmpty()) {
            throw new Exception("No se encontró la visita con id: " + registerVisit.getId());
        }

        RegisterVisitEntity existingEntity = existingEntityOpt.get();
        existingEntity.setBloodPressure(registerVisit.getBloodPressure());
        existingEntity.setTemperature(registerVisit.getTemperature());
        existingEntity.setPulse(registerVisit.getPulse());
        existingEntity.setOxygenLevel(registerVisit.getOxygenLevel());
        existingEntity.setMedications(registerVisit.getMedications());
        existingEntity.setProcedure(registerVisit.getProcedure());
        existingEntity.setDiagnosticAid(registerVisit.getDiagnosticAid());

        return registerVisitMapper.toDomain(registerVisitRepository.save(existingEntity));
    }

    @Override
    public RegisterVisit deleteById(RegisterVisit registerVisit) throws Exception {
        Optional<RegisterVisitEntity> optionalEntity = registerVisitRepository.findById(registerVisit.getId());
        if (optionalEntity.isEmpty()) {
            return null;
        }

        RegisterVisitEntity entityToDelete = optionalEntity.get();
        RegisterVisit deletedVisit = registerVisitMapper.toDomain(entityToDelete);
        registerVisitRepository.delete(entityToDelete);
        return deletedVisit;
    }
}
