package app.adapter.in.validators;
import org.springframework.stereotype.Component;


@Component
public class RegisterVisitValidator extends SimpleValidator{
	public double bloddPressureValidator(String value) throws Exception {
		return doubleValidator("La presion arterial del paciente", value);
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
	
}
