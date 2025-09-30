package app.domain.model;

import java.sql.Date;
import java.time.LocalDateTime;

import app.domain.model.enums.Role;

public class MedicalAppointment {
	private long AppointmentId;
	private Employee Doctor;
	private Patient patient;
	private Date date;
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDateTime() {
		return date;
	}
	
}
