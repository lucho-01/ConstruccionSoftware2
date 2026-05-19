package app.adapter.out;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.EmergencyContact;
import app.domain.port.EmergencyContactPort;
import app.infrastructure.entities.EmergencyContactEntity;
import app.infrastructure.mapper.EmergencyContactMapper;
import app.infrastructure.repository.EmergencyContactRepository;

@Service
public class EmergencyContactAdapter implements EmergencyContactPort {

    @Autowired
    private EmergencyContactRepository emergencyContactRepository;

    @Autowired
    private EmergencyContactMapper emergencyContactMapper;

    @Override
    public void save(EmergencyContact emergencyContact) throws Exception {
        EmergencyContactEntity entity = emergencyContactMapper.toEntity(emergencyContact);
        entity.setId(null); 
        emergencyContactRepository.save(entity);

        System.out.println("Contacto de emergencia guardado correctamente.");
    }

    @Override
    public EmergencyContact findById(EmergencyContact emergencyContact) throws Exception {
        if (emergencyContact.getId() == null) {
            return null;
        }

        Optional<EmergencyContactEntity> entity = emergencyContactRepository.findById(emergencyContact.getId());
        return entity.map(emergencyContactMapper::toDomain).orElse(null);
    }

    @Override
    public EmergencyContact update(EmergencyContact emergencyContact) throws Exception {
        Optional<EmergencyContactEntity> existingEntityOpt = emergencyContactRepository.findById(emergencyContact.getId());
        if (existingEntityOpt.isEmpty()) {
            throw new Exception("No se pudo actualizar. Contacto de emergencia no encontrado con id: " + emergencyContact.getId());
        }

        EmergencyContactEntity existingEntity = existingEntityOpt.get();
        existingEntity.setName(emergencyContact.getName());
        existingEntity.setLastName(emergencyContact.getLastName());
        existingEntity.setPhoneNumber(emergencyContact.getPhoneNumber());

        return emergencyContactMapper.toDomain(emergencyContactRepository.save(existingEntity));
    }

    @Override
    public EmergencyContact deleteById(EmergencyContact emergencyContact) throws Exception {
        Optional<EmergencyContactEntity> optionalEntity = emergencyContactRepository.findById(emergencyContact.getId());
        if (optionalEntity.isEmpty()) {
            return null;
        }

        EmergencyContactEntity entityToDelete = optionalEntity.get();
        EmergencyContact deletedContact = emergencyContactMapper.toDomain(entityToDelete);
        emergencyContactRepository.delete(entityToDelete);
        return deletedContact;
    }

    @Override
    public java.util.List<EmergencyContact> findAll() throws Exception {
        return emergencyContactRepository.findAll().stream()
            .map(emergencyContactMapper::toDomain)
            .collect(Collectors.toList());
    }
}
