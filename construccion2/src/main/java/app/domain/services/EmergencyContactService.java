package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.EmergencyContact;
import app.domain.port.EmergencyContactPort;
@Service
public class EmergencyContactService {
	@Autowired
	private EmergencyContactPort emergencyContactPort;
	
    public void createEmergencyContact(EmergencyContact emergencyContact) throws Exception {
        validateEmergencyContact(emergencyContact);
        emergencyContactPort.save(emergencyContact);
    }

    public void updateEmergencyContact(EmergencyContact emergencyContact) throws Exception {
        if (emergencyContact.getId() == null) {
            throw new Exception("El contacto de emergencia debe incluir un id válido.");
        }

        validateEmergencyContact(emergencyContact);

        if (emergencyContactPort.findById(emergencyContact) == null) {
            throw new Exception("El contacto de emergencia no existe.");
        }

        emergencyContactPort.update(emergencyContact);
    }

    public void deleteEmergencyContact(EmergencyContact emergencyContact) throws Exception {
        if (emergencyContact.getId() == null) {
            throw new Exception("El contacto de emergencia debe incluir un id válido.");
        }

        if (emergencyContactPort.findById(emergencyContact) == null) {
            throw new Exception("El contacto de emergencia no existe.");
        }

        emergencyContactPort.deleteById(emergencyContact);
    }

    private void validateEmergencyContact(EmergencyContact emergencyContact) throws Exception {
        if (emergencyContact.getPhoneNumber() == null || emergencyContact.getPhoneNumber().length() != 10) {
            throw new Exception("El número de teléfono del contacto de emergencia no puede estar vacio y debe tener 10 digitos.");
        }
        
        if (emergencyContact.getName() == null) {
            throw new Exception("El nombre del contacto de emergencia no puede estar vacío.");
        }
        
        if (emergencyContact.getLastName() == null) {
            throw new Exception("El Apellido del contacto de emergencia no puede estar vacío.");
        }
    }

    public java.util.List<EmergencyContact> getAllEmergencyContacts() throws Exception {
        return emergencyContactPort.findAll();
    }
}
