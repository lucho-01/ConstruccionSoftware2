package app.application.usercases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Order;
import app.domain.model.Patient;
import app.domain.model.RegisterVisit;
import app.domain.model.EmergencyContact;
import app.domain.services.EmergencyContactService;
import app.domain.services.RegisterVisitService;
import app.domain.services.SearchOrderService;
import app.domain.services.SearchPatientService;
@Service
public class NursesUseCase {
	@Autowired
	private RegisterVisitService registerVisitService;
	@Autowired
	private SearchPatientService searchPatientService;
	@Autowired
	private SearchOrderService searchOrderService;
	@Autowired
	private EmergencyContactService emergencyContactService;
	
	public void registerVisit(RegisterVisit registerVisit) throws Exception {		
		registerVisitService.registerVisit(registerVisit);	
	}

	public java.util.List<RegisterVisit> getAllVisits() throws Exception {
		return registerVisitService.getAllVisits();
	}

	public RegisterVisit searchVisitByPatient(long patientDocument) throws Exception {
		return registerVisitService.findByPatientDocument(patientDocument);
	}

	public void updateVisit(RegisterVisit registerVisit) throws Exception {
		registerVisitService.updateVisit(registerVisit);
	}

	public void deleteVisit(RegisterVisit registerVisit) throws Exception {
		registerVisitService.deleteVisit(registerVisit);
	}
	
	public List<Patient> searchPatient(Patient patient) throws Exception{
		return searchPatientService.search(patient);
	}
	
	public List<Order> searchOrder(Order order) throws Exception{
		return searchOrderService.search(order);
	}

	public void createEmergencyContact(EmergencyContact emergencyContact) throws Exception {
		emergencyContactService.createEmergencyContact(emergencyContact);
	}

	public void updateEmergencyContact(EmergencyContact emergencyContact) throws Exception {
		emergencyContactService.updateEmergencyContact(emergencyContact);
	}

	public void deleteEmergencyContact(EmergencyContact emergencyContact) throws Exception {
		emergencyContactService.deleteEmergencyContact(emergencyContact);
	}

	public java.util.List<EmergencyContact> getAllEmergencyContacts() throws Exception {
		return emergencyContactService.getAllEmergencyContacts();
	}
	
}


