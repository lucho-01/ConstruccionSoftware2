package app.adapter.out;

import org.springframework.stereotype.Service;

import app.domain.model.EmergencyContact;
import app.domain.port.EmergencyContactPort;

@Service
public class EmergencyContactAdapter implements EmergencyContactPort {

	@Override
	public void save(EmergencyContact emergencycontact) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
