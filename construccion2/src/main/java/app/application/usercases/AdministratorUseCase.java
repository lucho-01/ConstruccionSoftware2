package app.application.usercases;

import app.domain.model.Patient;
import app.domain.services.PatientServices;

public class AdministratorUseCase {
	
	private PatientServices patientServices;
	
	public void createPatient(Patient patient) throws Exception {
		
		patientServices.createPatient(patient);
		
	}
}
