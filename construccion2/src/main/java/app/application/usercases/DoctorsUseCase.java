package app.application.usercases;

import app.domain.model.MedicalRecord;
import app.domain.services.medicalRecordService;

public class DoctorsUseCase {
	
	private medicalRecordService medicalRecordService;
	
	public void createMedicalRecord(MedicalRecord MedicalRecord) throws Exception {
		
		medicalRecordService.create(MedicalRecord);
		
	}
	
    public void updateMedicalRecord(MedicalRecord medicalRecord) throws Exception {
    	
        medicalRecordService.update(medicalRecord);
    }
}
