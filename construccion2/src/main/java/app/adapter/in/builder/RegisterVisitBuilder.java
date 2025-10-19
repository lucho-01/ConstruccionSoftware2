package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.OrderValidator;
import app.adapter.in.validators.RegisterVisitValidator;
import app.domain.model.Order;
import app.domain.model.RegisterVisit;
@Component
public class RegisterVisitBuilder {
	@Autowired
	private RegisterVisitValidator registerVisitValidator;
	@Autowired
	private OrderValidator orderValidator;
	
	public RegisterVisit build(String bloodPressure, String oxygenLevel, String pulse, String temperature,String medication, String procedures, String diagnosticAids) throws Exception{
		RegisterVisit registerVisit = new RegisterVisit();
		Order order = new Order();
		registerVisit.setBloodPressure(registerVisitValidator.bloddPressureValidator(bloodPressure));
		registerVisit.setOxygenLevel(registerVisitValidator.oxygenLevelValidator(oxygenLevel));
		registerVisit.setPulse(registerVisitValidator.pulseValidator(pulse));
		registerVisit.setTemperature(registerVisitValidator.temperatureValidator(temperature));
		order.setDiagnosticAid(orderValidator.DiagnosticAidValidator(diagnosticAids));
		order.setMedications(orderValidator.MedicationsValidator(medication));
		order.setProcedure(orderValidator.ProcedureValidator(procedures));
				
		return registerVisit;
	}
}
