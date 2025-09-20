package app.adapter.in.builder;

import app.adapter.in.validators.RegisterVisitValidator;
import app.domain.model.Order;
import app.domain.model.RegisterVisit;

public class RegisterVisitBuilder {

	private RegisterVisitValidator registerVisitValidator;
	
	public RegisterVisit build(String bloodPressure, String oxygenLevel, String pulse, String temperature,String medication, String procedures, String diagnosticAids) throws Exception{
		RegisterVisit registerVisit = new RegisterVisit();
		registerVisit.setBloodPressure(registerVisitValidator.bloddPressureValidator(bloodPressure));
		registerVisit.setOxygenLevel(registerVisitValidator.oxygenLevelValidator(oxygenLevel));
		registerVisit.setPulse(registerVisitValidator.pulseValidator(pulse));
		registerVisit.setTemperature(registerVisitValidator.temperatureValidator(temperature));
		registerVisit.setDiagnosticAids(registerVisitValidator.diagnosticAidsValidator(diagnosticAids));
		registerVisit.setMedication(registerVisitValidator.medicationValidator(medication));
		registerVisit.setProcedures(registerVisitValidator.proceduresValidator(procedures));
				
		return registerVisit;
	}
}
