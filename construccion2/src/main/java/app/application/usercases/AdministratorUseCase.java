package app.application.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Billing;
import app.domain.model.EmergencyContact;
import app.domain.model.MedicalAppointment;
import app.domain.model.Patient;
import app.domain.services.BillingService;
import app.domain.services.EmergencyContactService;
import app.domain.services.MedicalAppointmentService;
import app.domain.services.PatientServices;

@Service
public class AdministratorUseCase {
	
	@Autowired
	private PatientServices patientServices;
	@Autowired
	private MedicalAppointmentService medicalAppointmentService;
	@Autowired
	private EmergencyContactService emergencyContactService;
	@Autowired
	private BillingService billingService;
	
	public void createPatient(Patient patient) throws Exception {	
		patientServices.createPatient(patient);		
	}
	
	public void updatePatient(Patient patient) throws Exception {	
		patientServices.updatePatient(patient);		
	}
	public void createAppointment(MedicalAppointment appointment) throws Exception {	
		medicalAppointmentService.createMedicalAppointment(appointment);		
	}
	public void createBilling(Billing billing) throws Exception {	
		billingService.createBilling(billing);		
	}
	public void createEmergencyContact(EmergencyContact emergencyContact) throws Exception {	
		emergencyContactService.createEmergencyContact(emergencyContact);		
	}
}
