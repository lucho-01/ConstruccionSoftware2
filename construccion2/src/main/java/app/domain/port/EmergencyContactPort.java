package app.domain.port;

import app.domain.model.EmergencyContact;

public interface EmergencyContactPort {
	
	public void save (EmergencyContact emergencycontact) throws Exception;
	public EmergencyContact findById(EmergencyContact emergencyContact) throws Exception;
	public EmergencyContact update(EmergencyContact emergencyContact) throws Exception;
	public EmergencyContact deleteById(EmergencyContact emergencyContact) throws Exception;
	public java.util.List<EmergencyContact> findAll() throws Exception;
}
