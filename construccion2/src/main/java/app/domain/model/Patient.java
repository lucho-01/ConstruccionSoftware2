package app.domain.model;



import app.domain.model.enums.Gender;

public class Patient extends User{

	private  Gender gender;
	private double weigth;
	private double size;
	private Employee doctorDocument;
	private long policyNumber;
	private String insuranceCompanyName;
	private String policyValidity;
	private String policyEndDate;
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public double getWeigth() {
		return weigth;
	}
	public void setWeigth(double weigth) {
		this.weigth = weigth;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public Employee getDoctorDocument() {
		return doctorDocument;
	}
	public void setDoctorDocument(Employee doctorDocument) {
		this.doctorDocument = doctorDocument;
	}
	public long getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(long policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getInsuranceCompanyName() {
		return insuranceCompanyName;
	}
	public void setInsuranceCompanyName(String insuranceCompanyName) {
		this.insuranceCompanyName = insuranceCompanyName;
	}
	public String getPolicyValidity() {
		return policyValidity;
	}
	public void setPolicyValidity(String policyValidity) {
		this.policyValidity = policyValidity;
	}
	public String getPolicyEndDate() {
		return policyEndDate;
	}
	public void setPolicyEndDate(String policyEndDate) {
		this.policyEndDate = policyEndDate;
	}
	
}
