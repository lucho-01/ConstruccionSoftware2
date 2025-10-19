package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.EmployeeValidator;
import app.adapter.in.validators.MedicalRecordValidator;
import app.adapter.in.validators.PatientValidator;
import app.domain.model.Employee;
import app.domain.model.MedicalRecord;
import app.domain.model.Patient;

@Component
public class MedicalRecordBuilder {
	@Autowired
	private MedicalRecordValidator medicalRecordValidator;
	@Autowired
	private EmployeeValidator employeeValidator;
	@Autowired
	private PatientValidator patientValidator;
	
	public MedicalRecord build(String doctorName, String symptomatology, String reasonConsultation, String diagnosis, String date, String doctorDocument, String patientName) throws Exception{
		MedicalRecord medicalRecord = new MedicalRecord();
		Employee doctor = new Employee();
		Patient patient = new Patient();
		doctor.setFullName(employeeValidator.fullNameValidator(doctorName));
		doctor.setDocument(employeeValidator.documentValidator(doctorDocument));
		medicalRecord.setDiagnosis(medicalRecordValidator.diagnosisValidator(diagnosis));
		medicalRecord.setReasonConsultation(medicalRecordValidator.reasonConsultationValidator(reasonConsultation));
		medicalRecord.setSymptomatology(medicalRecordValidator.symptomatologyValidator(symptomatology));
		medicalRecord.setDate(medicalRecordValidator.dateValidator(date));
		patient.setFullName(patientValidator.fullNameValidator(patientName));
		
		return medicalRecord;
	
	}
}
