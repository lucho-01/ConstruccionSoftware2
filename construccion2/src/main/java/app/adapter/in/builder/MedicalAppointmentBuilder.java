package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.EmployeeValidator;
import app.adapter.in.validators.MedicalAppointmentValidator;
import app.adapter.in.validators.PatientValidator;
import app.domain.model.Employee;
import app.domain.model.MedicalAppointment;
import app.domain.model.Patient;

@Component
public class MedicalAppointmentBuilder {
	@Autowired
	private MedicalAppointmentValidator medicalAppointmentValidator;
	@Autowired
	private EmployeeValidator employeeValidator;
	@Autowired
	private PatientValidator patientValidator;
	
	public MedicalAppointment build(String doctorName, String patientName, String date) throws Exception{
		MedicalAppointment medicalAppointment = new MedicalAppointment();
		Employee doctor= new Employee();
		Patient patient = new Patient();
		doctor.setFullName(employeeValidator.fullNameValidator(doctorName));
		patient.setFullName(patientValidator.fullNameValidator(patientName));
		medicalAppointment.setDate(medicalAppointmentValidator.dateValidator(date));	
		
		return medicalAppointment;
		
	}
}
