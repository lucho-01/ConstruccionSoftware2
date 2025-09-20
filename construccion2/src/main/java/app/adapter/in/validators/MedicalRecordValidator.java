package app.adapter.in.validators;

import java.sql.Date;

public class MedicalRecordValidator extends SimpleValidator{

	public String nameDoctorValidator(String value) throws Exception {
		return stringValidator("El nombre del doctor", value);
	}
	
	public String symptomatologyValidator(String value) throws Exception {
		return stringValidator("La sintomatologia del paciente", value);
	}
	
	public String reasonConsultationValidator(String value) throws Exception {
		return stringValidator("La razón de consulta del paciente", value);
	}
	
	public String diagnosisValidator(String value) throws Exception {
		return stringValidator("El diagnostoc del paciente", value);
	}
	
	public Date dateValidator(String value) throws Exception {
		return dateValidator("La fecha de la historia", value);
	}
	
	public long doctorDocumentValidator(String value) throws Exception {
		return longValidator("el documento del doctor", value);
	}
	
	public String namePatientValidator(String value) throws Exception {
		return stringValidator("El nombre del paciente", value);
	}
	
	
}
