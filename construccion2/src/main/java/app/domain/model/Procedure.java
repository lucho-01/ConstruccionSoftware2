package app.domain.model;

public class Procedure {
	private long orderNumber;
	private double amount;
	private String repetitionFrequency;
	private boolean specialistAssistance;
	private long IdTypeSpecialist;
	private int item;
	

	public long getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getRepetitionFrequency() {
		return repetitionFrequency;
	}
	public void setRepetitionFrequency(String repetitionFrequency) {
		this.repetitionFrequency = repetitionFrequency;
	}
	public boolean isSpecialistAssistance() {
		return specialistAssistance;
	}
	public void setSpecialistAssistance(boolean specialistAssistance) {
		this.specialistAssistance = specialistAssistance;
	}
	public long getIdTypeSpecialist() {
		return IdTypeSpecialist;
	}
	public void setIdTypeSpecialist(long idTypeSpecialist) {
		IdTypeSpecialist = idTypeSpecialist;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	
	
}
