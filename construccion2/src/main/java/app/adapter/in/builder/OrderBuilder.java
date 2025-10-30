package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.OrderValidator;
import app.adapter.in.validators.PatientValidator;
import app.domain.model.Order;
import app.domain.model.Patient;

@Component
public class OrderBuilder {
	@Autowired
	private OrderValidator orderValidator;
	@Autowired
	private PatientValidator patientValidator;
	
	public Order build(String medications, String procedure, String diagnosticAid, String patientDocument) throws Exception{
		Order order = new Order();
		Patient patient= new Patient();
		order.setDiagnosticAid(orderValidator.DiagnosticAidValidator(diagnosticAid));
		order.setMedications(orderValidator.MedicationsValidator(medications));
		order.setProcedure(orderValidator.ProcedureValidator(procedure));	
		patient.setDocument(patientValidator.documentValidator(patientDocument));
		order.setPatient(patient);

		
		return order;
		
	}
}
