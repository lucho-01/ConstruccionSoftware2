package app.domain.port;

import java.util.List;

import app.domain.model.MedicalRecord;
import app.domain.model.Patient;

public interface SearchMedicalRecordPort {
	
	public SearchMedicalRecordPort findById(Patient patient) throws Exception;
	public List<MedicalRecord> findByPatient(Patient patient)throws Exception;

}
