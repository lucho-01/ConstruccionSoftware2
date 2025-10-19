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

        if (emergencyContact.getPhoneNumber() == null || emergencyContact.getPhoneNumber().length() != 10) {
            throw new Exception("El número de teléfono del contacto de emergencia no puede estar vacio y debe tener 10 digitos.");
        }
        
        if (emergencyContact.getName() == null) {
            throw new Exception("El nombre del contacto de emergencia no puede estar vacío.");
        }
        
        if (emergencyContact.getLastName() == null) {
            throw new Exception("El Apellido del contacto de emergencia no puede estar vacío.");
        }

        emergencyContactPort.save(emergencyContact);
    }
}
