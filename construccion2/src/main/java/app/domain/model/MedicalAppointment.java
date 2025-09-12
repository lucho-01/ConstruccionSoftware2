package app.domain.model;

import java.sql.Date;
import java.time.LocalDateTime;

import app.domain.model.enums.Role;

public class MedicalAppointment {
	private long AppointmentId;
	private Employee Doctor;
	private Patient patient;
	private LocalDateTime dateTime;
	
	public Employee getDoctor() {
		return Doctor;
	}
	public void setDoctor(Employee doctor) {
		Doctor = doctor;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public long getAppointmentId() {
		return AppointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		AppointmentId = appointmentId;
	}
	
}
