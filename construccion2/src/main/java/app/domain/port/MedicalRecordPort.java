package app.domain.port;

import java.util.List;

import app.domain.model.patient;
import app.domain.model.medicalRecord;

public interface MedicalRecordPort {
	
	public void save(medicalRecord medicalRecord) throws Exception;
	public List<medicalRecord> findByPatient(patient patient) throws Exception;

}
