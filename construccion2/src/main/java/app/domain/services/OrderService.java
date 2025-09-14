package app.domain.services;

import app.domain.model.Order;
import app.domain.port.OrderPort;

public class OrderService {

	private OrderPort orderPort;

    public void createOrder(Order order) throws Exception {
        if (order.getPatientId() == null) {
            throw new Exception("La orden debe estar asociada a un paciente.");
        }

        if ((order.getMedications() == null) &&
            (order.getProcedures() == null) &&
            (order.getDiagnosticAids() == null)) {
            throw new Exception("La orden debe tener al menos un medicamento, procedimiento o ayuda diagnóstica.");
        }
        
        orderPort.save(order);
    }
}
