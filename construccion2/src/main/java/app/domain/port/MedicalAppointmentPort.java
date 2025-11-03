package app.domain.port;

import java.sql.Date;

import app.domain.model.Employee;
import app.domain.model.MedicalAppointment;
import app.domain.model.Patient;

public interface MedicalAppointmentPort {
	
	public MedicalAppointment findById(MedicalAppointment appointment) throws Exception;
	public boolean isDoctorAvailable(Employee doctor, Date date) throws Exception;
	public boolean isPatientAvailable(Patient patient, Date date) throws Exception;
	public void save(MedicalAppointment appointment) throws Exception;
}

