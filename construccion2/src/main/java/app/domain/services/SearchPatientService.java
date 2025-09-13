package app.domain.services;

import java.util.List;

import app.domain.model.Patient;
import app.domain.port.PatientPort;

public class SearchPatientService {
	
	private PatientPort patientPort;

	public List<Patient> search(Patient patient) throws Exception {
		patient = patientPort.findById(patient);
		if (patient == null) {
			throw new Exception("debe consultar un paciente registrado");
		}
		return patientPort.findByPatient(patient);

	}
}
