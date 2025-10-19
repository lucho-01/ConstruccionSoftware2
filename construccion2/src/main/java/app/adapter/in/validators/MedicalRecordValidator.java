package app.adapter.in.validators;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class MedicalRecordValidator extends SimpleValidator{


	
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
		stringValidator("La fecha de la historia", value);
		return Date.valueOf(value);
	}
		
}
