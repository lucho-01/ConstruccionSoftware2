package app.domain.services;

import app.domain.model.medications;
import app.domain.port.MedicationsPort;

public class MedicationService {
	
	private MedicationsPort medicationPort;
	
	public void createMedication(medications medication) throws Exception{
		if(medicationPort.findByOrderNumber(medication)!=null) {
			throw new Exception("Este numero de orden ya esta asignado a otro medicamento.");
		}
		
		medicationPort.save(medication);
	}
}
