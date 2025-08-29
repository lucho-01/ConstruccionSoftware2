package app.domain.model;

import java.sql.Date;

public class MedicalRecord {
	private Employee doctor;
	private long doctorDocument;
	private String symptomatology;
	private String reasonConsultation;
	private String diagnosis;
	private Date date;
	private Patient fullName;
	private Patient birthdate;
	private String allergics;
	private boolean familyHistory;
	private int age;
	private Patient patient;
	
	
	
	
	public Employee getDoctor() {
		return doctor;
	}
	public void setDoctor(Employee doctor) {
		this.doctor = doctor;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getDoctorDocument() {
		return doctorDocument;
	}
	public void setDoctorDocument(long doctorDocument) {
		this.doctorDocument = doctorDocument;
	}
	public String getSymptomatology() {
		return symptomatology;
	}
	public void setSymptomatology(String symptomatology) {
		this.symptomatology = symptomatology;
	}
	public String getReasonConsultation() {
		return reasonConsultation;
	}
	public void setReasonConsultation(String reasonConsultation) {
		this.reasonConsultation = reasonConsultation;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Patient getFullName() {
		return fullName;
	}
	public void setFullName(Patient fullName) {
		this.fullName = fullName;
	}
	public Patient getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Patient birthdate) {
		this.birthdate = birthdate;
	}
	public String getAllergics() {
		return allergics;
	}
	public void setAllergics(String allergics) {
		this.allergics = allergics;
	}
	public boolean isFamilyHistory() {
		return familyHistory;
	}
	public void setFamilyHistory(boolean familyHistory) {
		this.familyHistory = familyHistory;
	}
	
}
