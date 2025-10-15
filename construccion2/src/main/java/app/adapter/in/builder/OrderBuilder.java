package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.OrderValidator;
import app.domain.model.DiagnosticAid;
import app.domain.model.Order;

@Component
public class OrderBuilder {
	@Autowired
	private OrderValidator orderValidator;
	
	public Order build(String medications, String procedure, String diagnosticAid, String patientId) throws Exception{
		Order order = new Order();
		order.setDiagnosticAid(orderValidator.DiagnosticAidValidator(diagnosticAid));
		order.setMedications(orderValidator.MedicationsValidator(medications));
		order.setProcedure(orderValidator.ProcedureValidator(procedure));	
		order.setPatientId(orderValidator.patientIdValidator(patientId));
		
		return order;
		
	}
}
