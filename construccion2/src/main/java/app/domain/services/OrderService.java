package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Order;
import app.domain.model.Patient;
import app.domain.port.OrderPort;
import app.domain.port.PatientPort;
import jakarta.transaction.Transactional;
@Service
public class OrderService {
	@Autowired
	private OrderPort orderPort;
	@Autowired
	private PatientPort patientPort;

	
	@Transactional
    public Order createOrder(Order order) throws Exception {
        validateOrderContent(order);
    	Patient patient= patientPort.findByDocument(order.getPatient());
        if (patient == null) {
            throw new Exception("La orden debe estar asociada a un paciente.");
        }
        order.setPatient(patient);
        
        return orderPort.save(order);
    }

    public java.util.List<Order> getAllOrders() throws Exception {
        return orderPort.findAll();
    }

    public java.util.List<Order> findByPatientDocument(long document) throws Exception {
        return orderPort.findByPatientDocument(document);
    }

    public void updateOrder(Order order) throws Exception {
        if (order.getOrderId() <= 0 || orderPort.findById(order) == null) {
            throw new Exception("La orden médica no existe.");
        }
        validateOrderContent(order);
        orderPort.update(order);
    }

    public void deleteOrder(Order order) throws Exception {
        if (order.getOrderId() <= 0 || orderPort.findById(order) == null) {
            throw new Exception("La orden médica no existe.");
        }
        orderPort.deleteById(order);
    }

    private void validateOrderContent(Order order) throws Exception {
        if ((order.getMedications() == null || order.getMedications().trim().isEmpty()) &&
            (order.getProcedure() == null || order.getProcedure().trim().isEmpty()) &&
            (order.getDiagnosticAid() == null || order.getDiagnosticAid().trim().isEmpty())) {
            throw new Exception("La orden debe tener al menos un medicamento, procedimiento o ayuda diagnóstica.");
        }
    }
}
