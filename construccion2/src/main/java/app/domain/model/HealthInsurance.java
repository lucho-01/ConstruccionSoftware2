package app.domain.model;

import java.sql.Date;

public class HealthInsurance {
	private String insuranceCompanyName;
	private long policyNumber;
	private boolean policyStatus;
	private Date policyValidity;
	
	
	
	public String getInsuranceCompanyName() {
		return insuranceCompanyName;
	}
	public void setInsuranceCompanyName(String insuranceCompanyName) {
		this.insuranceCompanyName = insuranceCompanyName;
	}
	public long getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(long policyNumber) {
		this.policyNumber = policyNumber;
	}
	public boolean isPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(boolean policyStatus) {
		this.policyStatus = policyStatus;
	}
	public Date getPolicyValidity() {
		return policyValidity;
	}
	public void setPolicyValidity(Date policyValidity) {
		this.policyValidity = policyValidity;
	}
}
