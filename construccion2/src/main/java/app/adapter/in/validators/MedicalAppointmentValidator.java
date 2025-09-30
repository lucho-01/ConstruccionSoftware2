package app.adapter.in.validators;

import app.domain.model.Employee;
import app.domain.model.Patient;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class MedicalAppointmentValidator extends SimpleValidator{
	
	public Employee doctorNameValidator(String value, Employee doctor) throws Exception {
		return doctorNameValidator("El nombre del doctor", doctor);
	}
        
        public Patient patientNameValidator(String value, Patient patient) throws Exception {
		return patientNameValidator("El nombre del paciente", patient);
	}
	
	public Date dateValidator(String value) throws Exception{
		return dateValidator("ffecha de la cita", value);
	}
}
