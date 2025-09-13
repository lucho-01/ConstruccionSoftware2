package app.domain.port;

import app.domain.model.EmergencyContact;

public interface EmergencyContactPort {
	
	public void save (EmergencyContact emergencycontact) throws Exception;
}
