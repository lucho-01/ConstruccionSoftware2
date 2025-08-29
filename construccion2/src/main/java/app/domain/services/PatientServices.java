package app.domain.services;
import app.domain.model.patient;
import app.domain.port.PatientPort;


public class PatientServices {
	
	private PatientPort patientPort;
	
	public void createPatient(patient patient) throws Exception {
		
		if(patientPort.findByDocument(patient)!=null) {
			throw new Exception("Ya existe una persona con esa cedula");
		}
		
		patientPort.save(patient);
		
	}
	
}
