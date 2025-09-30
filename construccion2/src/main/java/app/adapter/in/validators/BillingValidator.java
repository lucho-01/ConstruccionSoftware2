package app.adapter.in.validators;

import app.domain.model.Employee;
import app.domain.model.Patient;
import java.sql.Date;

import org.springframework.stereotype.Component;

import app.domain.model.enums.Gender;

@Component
public class BillingValidator extends SimpleValidator{

	public Employee doctorNameValidator(String value, Employee doctor) throws Exception {
		return doctorNameValidator("El nombre del doctor", doctor);
	}
        
        public Patient patientNameValidator(String value, Patient patient) throws Exception {
		return patientNameValidator("El nombre del paciente", patient);
	}
	
	public long PatientDocumentValidator(String value) throws Exception {
		return longValidator("El documento del paciente", value);
	}
	
	public long policyNumberValidator(String value) throws Exception {
		return longValidator("el numero de poliza del paciente", value);
	}
	
	public int patientAgeValidator(String value) throws Exception {
		return integrerValidator("La edad del paciente paciente", value);
	}
	
	public String insuranceCompanyNameValidator(String value) throws Exception {
		return stringValidator("el nombre de la compañia de seguros del paciente", value);
	}
	
	public Date policyValidityValidator(String value) throws Exception {
		return dateValidator("La validacion de la poliza del paciente", value);
	}
	
	public Date policyEndDateValidator(String value) throws Exception {
		return dateValidator("la fecha de finalizacion de la poliza del paciente", value);
	}
	
}