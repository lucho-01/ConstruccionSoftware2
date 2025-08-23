package app.domain.model;

import java.sql.Date;

public class ClinicalRecord {
	private long id;
	private Pet pet;
	private User veterinarian;
	private Date date;
	private String motive;
	private String diagnosis;
	private String medicine;
	private String medicalProcedure;
	private String doce;
	private ClinicalOrder clinicalorder;
	private String vaccationRecord;
	private String allergies;
	private String proceddureDetails;
	private String symptoms;
	private boolean status;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	public User getVeterinarian() {
		return veterinarian;
	}
	public void setVeterinarian(User veterinarian) {
		this.veterinarian = veterinarian;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMotive() {
		return motive;
	}
	public void setMotive(String motive) {
		this.motive = motive;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public String getMedicalProcedure() {
		return medicalProcedure;
	}
	public void setMedicalProcedure(String medicalProcedure) {
		this.medicalProcedure = medicalProcedure;
	}
	public String getDoce() {
		return doce;
	}
	public void setDoce(String doce) {
		this.doce = doce;
	}
	public ClinicalOrder getClinicalorder() {
		return clinicalorder;
	}
	public void setClinicalorder(ClinicalOrder clinicalorder) {
		this.clinicalorder = clinicalorder;
	}
	public String getVaccationRecord() {
		return vaccationRecord;
	}
	public void setVaccationRecord(String vaccationRecord) {
		this.vaccationRecord = vaccationRecord;
	}
	public String getAllergies() {
		return allergies;
	}
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	public String getProceddureDetails() {
		return proceddureDetails;
	}
	public void setProceddureDetails(String proceddureDetails) {
		this.proceddureDetails = proceddureDetails;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
