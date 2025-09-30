package app.adapter.in.validators;

import app.domain.model.Employee;
import app.domain.model.Patient;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class MedicalRecordValidator extends SimpleValidator{

	public Employee doctorNameValidator(String value, Employee doctor) throws Exception {
		return doctorNameValidator("El nombre del doctor", doctor);
	}
        
        public Patient patientNameValidator(String value, Patient patient) throws Exception {
		return patientNameValidator("El nombre del paciente", patient);
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
		
}
