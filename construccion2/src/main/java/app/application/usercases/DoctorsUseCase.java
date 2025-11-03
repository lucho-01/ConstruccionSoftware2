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
	
	public void createMedicalRecord(MedicalRecord medicalRecord) throws Exception {
		
		medicalRecordService.create(medicalRecord);		
	}
	
    public void updateMedicalRecord(MedicalRecord medicalRecord) throws Exception {
    	
        medicalRecordService.update(medicalRecord);
    }
    
	public List<MedicalRecord> searchMedicalRecord(Patient patient) throws Exception{
		return searchMedicalRecordService.search(patient);
	}
	
	public void createOrder(Order order) throws Exception {
		
		orderService.createOrder(order);		
	}
}
