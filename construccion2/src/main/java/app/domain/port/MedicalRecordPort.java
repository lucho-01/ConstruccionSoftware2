package app.domain.port;

import app.domain.model.MedicalRecord;

public interface MedicalRecordPort {
	
	public MedicalRecord save(MedicalRecord medicalRecord) throws Exception;
	public MedicalRecord findById(MedicalRecord medicalRecord) throws Exception;
	public java.util.List<MedicalRecord> findAll() throws Exception;
	public java.util.List<MedicalRecord> findByPatientDocument(long patientDocument) throws Exception;
	public void update(MedicalRecord medicalRecord) throws Exception;
	public void deleteById(MedicalRecord medicalRecord) throws Exception;

}
