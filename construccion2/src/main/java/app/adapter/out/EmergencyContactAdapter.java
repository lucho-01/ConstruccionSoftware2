package app.adapter.out;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.domain.model.EmergencyContact;
import app.domain.port.EmergencyContactPort;
import app.infrastructure.entities.EmergencyContactEntity;
import app.infrastructure.entities.PatientEntity;
import app.infrastructure.mapper.EmergencyContactMapper;
import app.infrastructure.repository.EmergencyContactRepository;
import app.infrastructure.repository.PatientRepository;

@Service
public class EmergencyContactAdapter implements EmergencyContactPort {

    @Autowired
    private EmergencyContactRepository emergencyContactRepository;

    @Autowired
    private EmergencyContactMapper emergencyContactMapper;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void save(EmergencyContact emergencyContact) throws Exception {
        EmergencyContactEntity entity = emergencyContactMapper.toEntity(emergencyContact);
        entity.setPatient(resolvePatient(emergencyContact));
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
        existingEntity.setPatient(resolvePatient(emergencyContact));

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
    @Transactional(readOnly = true)
    public java.util.List<EmergencyContact> findAll() throws Exception {
        return emergencyContactRepository.findAll().stream()
            .map(emergencyContactMapper::toDomain)
            .collect(Collectors.toList());
    }

    private PatientEntity resolvePatient(EmergencyContact emergencyContact) throws Exception {
        if (emergencyContact.getPatient() == null) {
            throw new Exception("El contacto de emergencia debe estar asociado a un paciente.");
        }

        PatientEntity patient = null;
        if (emergencyContact.getPatient().getId() > 0) {
            patient = patientRepository.findById(emergencyContact.getPatient().getId()).orElse(null);
        }

        if (patient == null && emergencyContact.getPatient().getDocument() > 0) {
            patient = patientRepository.findByDocument(emergencyContact.getPatient().getDocument());
        }

        if (patient == null) {
            throw new Exception("No se encontró un paciente para asociar el contacto de emergencia.");
        }

        return patient;
    }
}
