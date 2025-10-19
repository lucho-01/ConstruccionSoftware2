package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Order;
import app.domain.model.Patient;
import app.domain.port.OrderPort;
import app.domain.port.PatientPort;
@Service
public class OrderService {
	@Autowired
	private OrderPort orderPort;
	@Autowired
	private PatientPort patientPort;

    public void createOrder(Order order) throws Exception {
    	Patient patient= patientPort.findByDocument(order.getPatient());
        if (patient == null) {
            throw new Exception("La orden debe estar asociada a un paciente.");
        }
        order.setPatient(patient);

        if ((order.getMedications() == null) &&
            (order.getProcedure() == null) &&
            (order.getDiagnosticAid() == null)) {
            throw new Exception("La orden debe tener al menos un medicamento, procedimiento o ayuda diagnóstica.");
        }
        
        orderPort.save(order);
    }
}
