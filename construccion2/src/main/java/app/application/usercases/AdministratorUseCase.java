package app.application.usercases;

import app.domain.model.Billing;
import app.domain.model.EmergencyContact;
import app.domain.model.MedicalAppointment;
import app.domain.model.Patient;
import app.domain.services.BillingService;
import app.domain.services.EmergencyContactService;
import app.domain.services.MedicalAppointmentService;
import app.domain.services.PatientServices;

public class AdministratorUseCase {
	
	private PatientServices patientServices;
	private MedicalAppointmentService medicalAppointmentService;
	private EmergencyContactService emergencyContactService;
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
