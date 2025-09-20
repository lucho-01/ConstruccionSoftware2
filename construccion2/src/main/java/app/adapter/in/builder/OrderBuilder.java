package app.adapter.in.builder;

import app.adapter.in.validators.OrderValidator;
import app.domain.model.Order;

public class OrderBuilder {
	private OrderValidator orderValidator;
	
	public Order build(String medications, String procedures, String diagnosticAids) throws Exception{
		Order order = new Order();
		order.setMedication(orderValidator.medicationValidator(medications));
		order.setDiagnosticAids(orderValidator.diagnosticAidsValidator(diagnosticAids));
		order.setProcedures(orderValidator.proceduresValidator(procedures));	
		
		return order;
		
	}
}
