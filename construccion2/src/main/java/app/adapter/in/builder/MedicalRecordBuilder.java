package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.MedicalRecordValidator;
import app.domain.model.MedicalRecord;
import app.domain.model.Patient;

@Component
public class MedicalRecordBuilder {
	@Autowired
	private MedicalRecordValidator medicalRecordValidator;
	
	public MedicalRecord build(String doctorName, String symptomatology, String reasonConsultation, String diagnosis, String date, String doctorDocument, String patientName) throws Exception{
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setDoctor(medicalRecordValidator.doctorNameValidator(doctorName, null));
		medicalRecord.setDoctorDocument(medicalRecordValidator.doctorDocumentValidator(doctorDocument));
		medicalRecord.setDiagnosis(medicalRecordValidator.diagnosisValidator(diagnosis));
		medicalRecord.setReasonConsultation(medicalRecordValidator.reasonConsultationValidator(reasonConsultation));
		medicalRecord.setSymptomatology(medicalRecordValidator.symptomatologyValidator(symptomatology));
		medicalRecord.setDate(medicalRecordValidator.dateValidator(date));
		medicalRecord.setPatient(medicalRecordValidator.patientNameValidator(patientName, null));
		
		return medicalRecord;
	
	}
}
