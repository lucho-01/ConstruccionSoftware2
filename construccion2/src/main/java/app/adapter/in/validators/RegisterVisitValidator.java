package app.adapter.in.validators;

import app.domain.model.DiagnosticAid;
import app.domain.model.Medications;
import app.domain.model.Procedure;
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
	
	public Medications medicationsValidator(String value, Medications medications) throws Exception {
		return medicationsValidator("El medicamento del paciente", medications);
	}
	
	public Procedure procedureValidator(String value, Procedure procedure) throws Exception {
		return procedureValidator("El procedimiento del paciente", procedure);
	}
	
	public DiagnosticAid diagnosticAidValidator(String value, DiagnosticAid diagnosticAid) throws Exception {
		return diagnosticAidValidator("La ayuda diagnostica del paciente", diagnosticAid);

        }
}
