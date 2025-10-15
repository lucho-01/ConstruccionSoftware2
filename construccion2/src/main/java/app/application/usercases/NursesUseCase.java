package app.application.usercases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Order;
import app.domain.model.Patient;
import app.domain.model.RegisterVisit;
import app.domain.services.RegisterVisitService;
import app.domain.services.SearchOrderService;
import app.domain.services.SearchPatientService;
@Service
public class NursesUseCase {
	@Autowired
	private RegisterVisitService registerVisitService;
	@Autowired
	private SearchPatientService searchPatientService;
	@Autowired
	private SearchOrderService searchOrderService;
	
	public void registerVisit(RegisterVisit registerVisit) throws Exception {		
		registerVisitService.registerVisit(registerVisit);	
	}
	
	public List<Patient> searchPatient(Patient patient) throws Exception{
		return searchPatientService.search(patient);
	}
	
	public List<Order> searchOrder(Order order) throws Exception{
		return searchOrderService.search(order);
	}
	
}


