package app.adapter.out;

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
}
