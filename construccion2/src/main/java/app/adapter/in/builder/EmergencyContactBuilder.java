package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.EmergencyContactValidator;
import app.domain.model.EmergencyContact;
import app.domain.model.Order;
@Component
public class EmergencyContactBuilder {
	@Autowired
	private EmergencyContactValidator emergencyContactValidator;
	
	public EmergencyContact build(String name, String lastName, String phoneNumber) throws Exception{
		EmergencyContact emergencyContact = new EmergencyContact();
		emergencyContact.setName(emergencyContactValidator.nameValidator(name));
		emergencyContact.setLastName(emergencyContactValidator.lasNameValidator(lastName));
		emergencyContact.setPhoneNumber(emergencyContactValidator.phoneNumberValidator(phoneNumber));
		
		return emergencyContact;
		
	}
}
