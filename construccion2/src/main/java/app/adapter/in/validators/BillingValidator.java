package app.adapter.in.validators;

import java.sql.Date;

import app.domain.model.enums.Gender;

public class BillingValidator extends SimpleValidator{

	public String namePatientValidator(String value) throws Exception {
		return stringValidator("El nombre del paciente", value);
	}
	
	public String doctorNameValidator(String value) throws Exception {
		return stringValidator("el nombre del doctor", value);
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