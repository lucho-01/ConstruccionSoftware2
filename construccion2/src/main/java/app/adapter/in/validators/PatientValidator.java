package app.adapter.in.validators;

import org.springframework.stereotype.Component;

import app.domain.model.enums.Gender;

@Component
public class PatientValidator extends SimpleValidator{

	public String fullNameValidator(String value) throws Exception {
		return stringValidator("El nombre completo del paciente", value);
	}
	
	public String addressValidator(String value) throws Exception {
		return stringValidator("La dirección del paciente", value);
	}
	
	public String emailValidator(String value) throws Exception {
		return stringValidator("El email del paciente", value);
	}
	
	public long documentValidator(String value) throws Exception {
		return longValidator("el documento del paciente", value);
	}
	
	public double weigthValidator(String value) throws Exception {
		return doubleValidator("el peso del paciente", value);
	}
	
	public double sizeValidator(String value) throws Exception {
		return doubleValidator("el tamaño del paciente", value);
	}
	
	public long phoneNumberValidator(String value) throws Exception {
		return longValidator("el documento del paciente", value);
	}
	
	public String birthdateValidator(String value) throws Exception {
		return super.stringValidator("la fecha de nacimiento del paciente", value);
	}
	
	public Gender genderValidator(String value) throws Exception {
		stringValidator("el genero del paciente", value);
		return Gender.valueOf(value);
	}
}
