package app.domain.services;

import java.util.List;

import app.domain.model.Patient;
import app.domain.model.MedicalRecord;
import app.domain.port.PatientPort;
import app.domain.port.SearchMedicalRecordPort;

public class SearchMedicalRecordService {
	
	private PatientPort patientPort;
	private SearchMedicalRecordPort searchMedicalRecordPort;

	public List<MedicalRecord> search(Patient patient) throws Exception {
		patient = patientPort.findById(patient);
		if (patient == null) {
			throw new Exception("debe consultar historia clinica de un paciente registrado");
		}
		return searchMedicalRecordPort.findByPatient(patient);

	}
}
