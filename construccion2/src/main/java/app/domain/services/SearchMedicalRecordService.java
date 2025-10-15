package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Patient;
import app.domain.model.MedicalRecord;
import app.domain.port.PatientPort;
import app.domain.port.SearchMedicalRecordPort;
@Service
public class SearchMedicalRecordService {
	@Autowired
	private PatientPort patientPort;
	@Autowired
	private SearchMedicalRecordPort searchMedicalRecordPort;

	public List<MedicalRecord> search(Patient patient) throws Exception {
		patient = patientPort.findById(patient);
		if (patient == null) {
			throw new Exception("debe consultar historia clinica de un paciente registrado");
		}
		return searchMedicalRecordPort.findByPatient(patient);

	}
}
