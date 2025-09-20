package app.domain.model;

import java.sql.Date;

import app.domain.model.enums.Role;

public class MedicalAppointment {
	private String nameDoctor;
	private String namepatient;
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
	public String getNameDoctor() {
		return nameDoctor;
	}
	public void setNameDoctor(String nameDoctor) {
		this.nameDoctor = nameDoctor;
	}
	public String getNamepatient() {
		return namepatient;
	}
	public void setNamepatient(String namepatient) {
		this.namepatient = namepatient;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
