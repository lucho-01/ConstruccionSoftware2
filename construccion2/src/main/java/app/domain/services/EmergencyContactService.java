package app.domain.services;

import app.domain.model.EmergencyContact;
import app.domain.port.EmergencyContactPort;

public class EmergencyContactService {
	
	private EmergencyContactPort emergencyContactPort;
	
    public void createEmergencyContact(EmergencyContact emergencyContact) throws Exception {

        if (emergencyContact.getPhoneNumber() > 10) {
            throw new Exception("El número de teléfono del contacto de emergencia no puede ser mayor a 10 digitos.");
        }
        
        if (emergencyContact.getName() == null) {
            throw new Exception("El nombre del contacto de emergencia no puede estar vacío.");
        }

        emergencyContactPort.save(emergencyContact);
    }
}
