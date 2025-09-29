package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.OrderValidator;
import app.domain.model.Order;

@Component
public class OrderBuilder {
	@Autowired
	private OrderValidator orderValidator;
	
	public Order build(String medications, String procedures, String diagnosticAids) throws Exception{
		Order order = new Order();
		order.setMedication(orderValidator.medicationValidator(medications));
		order.setDiagnosticAids(orderValidator.diagnosticAidsValidator(diagnosticAids));
		order.setProcedures(orderValidator.proceduresValidator(procedures));	
		
		return order;
		
	}
}
