package app.adapter.in.builder;

import app.adapter.in.validators.MedicalRecordValidator;
import app.domain.model.MedicalRecord;
import app.domain.model.Patient;

public class MedicalRecordBuilder {

	private MedicalRecordValidator medicalRecordValidator;
	
	public MedicalRecord build(String nameDoctor, String symptomatology, String reasonConsultation, String diagnosis, String date, String doctorDocument, String namePatient) throws Exception{
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setNameDoctor(medicalRecordValidator.nameDoctorValidator(nameDoctor));
		medicalRecord.setDoctorDocument(medicalRecordValidator.doctorDocumentValidator(doctorDocument));
		medicalRecord.setDiagnosis(medicalRecordValidator.diagnosisValidator(diagnosis));
		medicalRecord.setReasonConsultation(medicalRecordValidator.reasonConsultationValidator(reasonConsultation));
		medicalRecord.setSymptomatology(medicalRecordValidator.symptomatologyValidator(symptomatology));
		medicalRecord.setDate(medicalRecordValidator.dateValidator(date));
		medicalRecord.setNamePatient(medicalRecordValidator.namePatientValidator(namePatient));
		
		return medicalRecord;
	
	}
}
