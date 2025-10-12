package app.domain.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.model.Patient;
import app.domain.port.EmployeePort;
import app.domain.port.PatientPort;

@Service
public class PatientServices {
	@Autowired
	private PatientPort patientPort;
	@Autowired
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
		public void updatePatient(Patient patient) throws Exception{
			
			if(patientPort.findByDocument(patient)==null) {
				throw new Exception("El paciente no existe");
			}
			else {
				patientPort.updatePatient(patient);
			}
			
		}
}
