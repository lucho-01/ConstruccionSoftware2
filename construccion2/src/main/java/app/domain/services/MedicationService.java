package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.model.Medications;
import app.domain.model.enums.Role;
import app.domain.port.EmployeePort;
import app.domain.port.MedicationsPort;

@Service
public class MedicationService {
	@Autowired
	private MedicationsPort medicationPort;
	@Autowired
	private EmployeePort employeePort;
	
	
	public void createMedication(Medications medication) throws Exception{
		if(medicationPort.findByOrderNumber(medication)!=null) {
			throw new Exception("Este numero de orden ya esta asignado a otro medicamento.");
		}
		
		Employee doctor = employeePort.findByDocument(medication.getDoctor());
		
		if(doctor == null || doctor.getRole().equals(Role.DOCTORS)) {
			throw new Exception("Para recetar un medicamento debe haber un doctor asignado");
		}
		
		
		medication.setDoctor(doctor);
		medicationPort.save(medication);
	}
}
