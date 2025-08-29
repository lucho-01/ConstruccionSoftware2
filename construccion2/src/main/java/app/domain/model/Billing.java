package app.domain.model;

import java.sql.Date;

public class Billing {
	private long patientDocument;
	private long policyNumber;
	private int patientAge;
	private String patientNAme;
	private String doctorName;
	private String  insuranceCompanyName;
	private Date policyValidity;
	private Date policyEndDate;
	
	
	public long getPatientDocument() {
		return patientDocument;
	}
	public void setPatientDocument(long patientDocument) {
		this.patientDocument = patientDocument;
	}
	public long getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(long policyNumber) {
		this.policyNumber = policyNumber;
	}
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	public String getPatientNAme() {
		return patientNAme;
	}
	public void setPatientNAme(String patientNAme) {
		this.patientNAme = patientNAme;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getInsuranceCompanyName() {
		return insuranceCompanyName;
	}
	public void setInsuranceCompanyName(String insuranceCompanyName) {
		this.insuranceCompanyName = insuranceCompanyName;
	}
	public Date getPolicyValidity() {
		return policyValidity;
	}
	public void setPolicyValidity(Date policyValidity) {
		this.policyValidity = policyValidity;
	}
	public Date getPolicyEndDate() {
		return policyEndDate;
	}
	public void setPolicyEndDate(Date policyEndDate) {
		this.policyEndDate = policyEndDate;
	}
}
