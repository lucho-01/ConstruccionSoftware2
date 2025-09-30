package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.RegisterVisitValidator;
import app.domain.model.Order;
import app.domain.model.RegisterVisit;
@Component
public class RegisterVisitBuilder {
	@Autowired
	private RegisterVisitValidator registerVisitValidator;
	
	public RegisterVisit build(String bloodPressure, String oxygenLevel, String pulse, String temperature,String medication, String procedures, String diagnosticAids) throws Exception{
		RegisterVisit registerVisit = new RegisterVisit();
		registerVisit.setBloodPressure(registerVisitValidator.bloddPressureValidator(bloodPressure));
		registerVisit.setOxygenLevel(registerVisitValidator.oxygenLevelValidator(oxygenLevel));
		registerVisit.setPulse(registerVisitValidator.pulseValidator(pulse));
		registerVisit.setTemperature(registerVisitValidator.temperatureValidator(temperature));
		registerVisit.setDiagnosticAid(registerVisitValidator.diagnosticAidValidator(diagnosticAids, null));
		registerVisit.setMedications(registerVisitValidator.medicationsValidator(medication, null));
		registerVisit.setProcedure(registerVisitValidator.procedureValidator(procedures, null));
				
		return registerVisit;
	}
}
