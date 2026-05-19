package app.domain.model;

import java.time.LocalDateTime;

public class MedicalAppointment {
	private long AppointmentId;
	private Employee Doctor;
	private Patient patient;
	private LocalDateTime date;
	
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
	public long getAppointmentId() {
		return AppointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		AppointmentId = appointmentId;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public LocalDateTime getDateTime() {
		return date;
	}
	
}
