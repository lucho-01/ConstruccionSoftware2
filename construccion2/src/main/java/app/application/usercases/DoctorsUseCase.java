package app.application.usercases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.MedicalRecord;
import app.domain.model.Order;
import app.domain.model.Patient;
import app.domain.services.MedicalRecordService;
import app.domain.services.OrderService;
import app.domain.services.SearchMedicalRecordService;

@Service
public class DoctorsUseCase {
	@Autowired
	private MedicalRecordService medicalRecordService;
	@Autowired
	private SearchMedicalRecordService searchMedicalRecordService;
	@Autowired
	private OrderService orderService;
	
	public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) throws Exception {
		
		return medicalRecordService.create(medicalRecord);		
	}
	
    public void updateMedicalRecord(MedicalRecord medicalRecord) throws Exception {
    	
        medicalRecordService.update(medicalRecord);
    }

	public List<MedicalRecord> getAllMedicalRecords() throws Exception {
		return medicalRecordService.getAll();
	}

	public void deleteMedicalRecord(MedicalRecord medicalRecord) throws Exception {
		medicalRecordService.delete(medicalRecord);
	}
    
	public List<MedicalRecord> searchMedicalRecord(Patient patient) throws Exception{
		return searchMedicalRecordService.search(patient);
	}
	
	public Order createOrder(Order order) throws Exception {
		
		return orderService.createOrder(order);		
	}

	public List<Order> getAllOrders() throws Exception {
		return orderService.getAllOrders();
	}

	public List<Order> searchOrdersByPatient(long patientDocument) throws Exception {
		return orderService.findByPatientDocument(patientDocument);
	}

	public void updateOrder(Order order) throws Exception {
		orderService.updateOrder(order);
	}

	public void deleteOrder(Order order) throws Exception {
		orderService.deleteOrder(order);
	}
}
