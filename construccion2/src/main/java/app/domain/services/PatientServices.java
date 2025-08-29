package app.domain.services;
import app.domain.model.Patient;
import app.domain.port.PatientPort;
import app.domain.port.EmployeePort;
import app.domain.model.Employee;


public class PatientServices {
	
	private PatientPort patientPort;
	private EmployeePort employeePort;
	
	
	public void createPatient(Patient patient) throws Exception {
		
		if(patientPort.findByDocument(patient)!=null) {
			throw new Exception("Ya existe una persona con esa cedula");
		}
		
		Employee doctor = employeePort.findByDocument(patient.getDoctor());
		
		if(patient.getDoctor()==null) {
			throw new Exception("Para crear un paciente, debe tener un doctor asignado");
		}
		patient.setDoctor(doctor);
		patientPort.save(patient);
		
	}
	
}
