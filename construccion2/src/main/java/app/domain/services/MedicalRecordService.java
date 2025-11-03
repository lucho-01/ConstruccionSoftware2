package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.model.MedicalRecord;
import app.domain.model.Patient;
import app.domain.model.enums.Role;
import app.domain.port.EmployeePort;
import app.domain.port.MedicalRecordPort;
import app.domain.port.PatientPort;

@Service
public class MedicalRecordService {
	@Autowired
	private MedicalRecordPort medicalRecordPort;
	@Autowired
	private EmployeePort employeePort;
	@Autowired
	private PatientPort patientPort;
	
	public void create(MedicalRecord medicalRecord) throws Exception{
		Patient patient = patientPort.findByDocument(medicalRecord.getPatient());
		if(patient == null) {
			throw new Exception("La historia clinica debe tener un paciente valido");
		}
		
		Employee doctor = employeePort.findByDocument(medicalRecord.getDoctor());
		
		if(doctor == null || !doctor.getRole().equals(Role.DOCTORS)) {
			throw new Exception("La historia clinica debe ser registrada por un doctor valido");
		}
		
		medicalRecord.setPatient(patient);
		medicalRecord.setDoctor(doctor);
		medicalRecordPort.save(medicalRecord);
	}
	
	public void update(MedicalRecord medicalRecord) throws Exception{
		Patient patient = patientPort.findByDocument(medicalRecord.getPatient());
		if(patient == null) {
			throw new Exception("La historia clinica debe tener un paciente valido");					
		}
		medicalRecordPort.update(medicalRecord);
	}
	
}
