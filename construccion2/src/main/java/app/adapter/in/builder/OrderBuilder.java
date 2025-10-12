package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.OrderValidator;
import app.domain.model.Order;

@Component
public class OrderBuilder {
	@Autowired
	private OrderValidator orderValidator;
	
	public OrderBuilder() {
		this.orderValidator = new OrderValidator();
	}
	
	public Order build(String medications, String procedure, String diagnosticAid) throws Exception{
		Order order = new Order();
		order.setDiagnosticAid(orderValidator.listDiagnosticAidValidator(diagnosticAid, null));
		order.setMedications(orderValidator.listMedicationsValidator(medications, null));
		order.setProcedure(orderValidator.listProcedureValidator(procedure, null));	
		
		return order;
		
	}
}
