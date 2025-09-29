package app.adapter.in.validators;

import java.sql.Date;

import org.springframework.stereotype.Component;

import app.domain.model.enums.Role;

@Component
public class RegisterVisitValidator extends SimpleValidator{
	public String bloddPressureValidator(String value) throws Exception {
		return stringValidator("La presion arterial del paciente", value);
	}
	
	public double temperatureValidator(String value) throws Exception {
		return doubleValidator("La temperatura del paciente", value);
	}
	
	public int pulseValidator(String value) throws Exception {
		return integrerValidator("El pulso del paciente", value);
	}
	
	public int oxygenLevelValidator(String value) throws Exception {
		return integrerValidator("El nivel de oxigeno del paciente", value);
	}
	
	public String medicationValidator(String value) throws Exception {
		return stringValidator("El medicamento del paciente", value);
	}
	
	public String proceduresValidator(String value) throws Exception {
		return stringValidator("El procedimiento del paciente", value);
	}
	
	public String diagnosticAidsValidator(String value) throws Exception {
		return stringValidator("La ayuda diagnostica del paciente", value);
	}
}
