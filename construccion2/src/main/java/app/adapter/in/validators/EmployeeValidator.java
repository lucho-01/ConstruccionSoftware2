package app.adapter.in.validators;

import java.sql.Date;

import org.springframework.stereotype.Component;

import app.domain.model.enums.Role;

@Component
public class EmployeeValidator extends SimpleValidator{
	
	public String fullNameValidator(String value) throws Exception {
		return stringValidator("El nombre completo del empleado", value);
	}
	public int ageValidator(String value) throws Exception{
		return integrerValidator("la edad del empleado", value);
	}
	
	public String userNameValidator(String value) throws Exception {
		return stringValidator("El nombre de usuario del empleado", value);
	}
	
	public String passwordValidator(String value) throws Exception {
		return stringValidator("La contraseña del empleado", value);
	}
	
	public String addressValidator(String value) throws Exception {
		return stringValidator("La dirección del empleado", value);
	}
	
	public String emailValidator(String value) throws Exception {
		return stringValidator("El email del empelado", value);
	}
	
	public long documentValidator(String value) throws Exception {
		return longValidator("el documento del empleado", value);
	}
	
	public long phoneNumberValidator(String value) throws Exception {
		return longValidator("el numero de telefono del empleado", value);
	}
	
	public Role roleValidator(String value) throws Exception {
		 stringValidator("el rol del empleado", value);
		 return Role.valueOf(value);
	}
	
	public Date birthDateValidator(String value) throws Exception {
		stringValidator("la fecha de nacimiento del empleado", value);
		return Date.valueOf(value);
	}
}
