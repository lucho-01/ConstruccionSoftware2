package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Patient;
import app.domain.port.PatientPort;
@Service
public class SearchPatientService {
	@Autowired
	private PatientPort patientPort;

	public List<Patient> search(Patient patient) throws Exception {
		patient = patientPort.findById(patient);
		if (patient == null) {
			throw new Exception("debe consultar un paciente registrado");
		}
		return patientPort.findByPatient(patient);

	}
}
