package app.domain.port;

import app.domain.model.MedicalRecord;

public interface MedicalRecordPort {
	
	public void save(MedicalRecord medicalRecord) throws Exception;
	public void update(MedicalRecord medicalRecord) throws Exception;

}
