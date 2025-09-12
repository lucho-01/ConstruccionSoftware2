package app.domain.port;

import java.time.LocalDateTime;

import app.domain.model.Employee;
import app.domain.model.MedicalAppointment;
import app.domain.model.Patient;

public interface MedicalAppointmentPort {
	
	public MedicalAppointmentPort findById(MedicalAppointment appointment) throws Exception;
	public boolean isDoctorAvailable(Employee doctor, LocalDateTime dateTime) throws Exception;
	public boolean isPatientAvailable(Patient patient, LocalDateTime dateTime) throws Exception;
	public void save(MedicalAppointment appointment) throws Exception;
}
