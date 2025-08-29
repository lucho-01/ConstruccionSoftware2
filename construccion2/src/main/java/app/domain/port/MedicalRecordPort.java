package app.domain.port;

import java.util.List;

import app.domain.model.Patient;
import app.domain.model.MedicalRecord;

public interface MedicalRecordPort {
	
	public void save(MedicalRecord medicalRecord) throws Exception;
	public List<MedicalRecord> findByPatient(Patient patient) throws Exception;

}
