package app.adapter.in.validators;

import org.springframework.stereotype.Component;

@Component
public class EmergencyContactValidator extends SimpleValidator{

	public String nameValidator(String value) throws Exception{
		return stringValidator("El nombre del contacto de emergencia", value);
	}
	
	public String lasNameValidator(String value) throws Exception{
		return stringValidator("el apellido del contacto de emergencia", value);
	}
	
	public String phoneNumberValidator(String value) throws Exception{
		return stringValidator("el numero de telefono", value);
	}
}
