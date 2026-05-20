package app.domain.port;

import java.time.LocalDateTime;

import app.domain.model.Employee;
import app.domain.model.MedicalAppointment;
import app.domain.model.Patient;

public interface MedicalAppointmentPort {
	
	public MedicalAppointment findById(MedicalAppointment appointment) throws Exception;
	public boolean isDoctorAvailable(Employee doctor, LocalDateTime date) throws Exception;
	public boolean isPatientAvailable(Patient patient, LocalDateTime date) throws Exception;
	public void save(MedicalAppointment appointment) throws Exception;
	public java.util.List<MedicalAppointment> findAll() throws Exception;
	public java.util.List<MedicalAppointment> findByPatientDocument(long patientDocument) throws Exception;
	public void update(MedicalAppointment appointment) throws Exception;
	public void deleteById(MedicalAppointment appointment) throws Exception;
}
