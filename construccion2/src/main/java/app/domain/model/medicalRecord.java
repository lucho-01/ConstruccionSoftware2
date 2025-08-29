package app.domain.model;

import java.sql.Date;

public class medicalRecord {
	private long doctorDocument;
	private String symptomatology;
	private String reasonConsultation;
	private String diagnosis;
	private Date date;
		
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
}
