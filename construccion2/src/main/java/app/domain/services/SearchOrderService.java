package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Order;
import app.domain.model.Patient;
import app.domain.port.PatientPort;
import app.domain.port.SearchOrderPort;
@Service
public class SearchOrderService {
	@Autowired
	private PatientPort patientPort;
	@Autowired
	private SearchOrderPort searchOrderPort;

	public List<Order> search(Order order) throws Exception {
		if (order == null || order.getPatient() == null || order.getPatient().getDocument() <= 0) {
			throw new Exception("Debe ingresar el documento del paciente para buscar órdenes médicas.");
		}

		Patient patient = patientPort.findByDocument(order.getPatient());
		if (patient == null) {
			throw new Exception("Debe consultar una orden de un paciente registrado.");
		}

		order.setPatient(patient);
		return searchOrderPort.findByOrder(order);

	}
}
