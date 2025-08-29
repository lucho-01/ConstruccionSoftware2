package app.domain.model;

public class Medications {
	private Employee doctor;
	private long orderNumber;
	private long iDmedications;
	private int item;
	private String dose;
	private String treatmentDuration;
	
	
	
	public Employee getDoctor() {
		return doctor;
	}
	public void setDoctor(Employee doctor) {
		this.doctor = doctor;
	}
	public long getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}
	public long getiDmedications() {
		return iDmedications;
	}
	public void setiDmedications(long iDmedications) {
		this.iDmedications = iDmedications;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public String getDose() {
		return dose;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}
	public String getTreatmentDuration() {
		return treatmentDuration;
	}
	public void setTreatmentDuration(String treatmentDuration) {
		this.treatmentDuration = treatmentDuration;
	}
	
}
