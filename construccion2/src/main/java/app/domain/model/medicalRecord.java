package app.domain.model;

import java.sql.Date;

public class medicalRecord {
	private long doctorDocument;
	private String symptomatology;
	private String reasonConsultation;
	private String diagnosis;
	private Date date;
	private patient fullName;
	private patient birthdate;
	private String allergics;
	private boolean familyHistory;
	private int age;
	
	
	
	
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
	public patient getFullName() {
		return fullName;
	}
	public void setFullName(patient fullName) {
		this.fullName = fullName;
	}
	public patient getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(patient birthdate) {
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
