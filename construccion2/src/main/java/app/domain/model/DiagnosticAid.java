package app.domain.model;

public class DiagnosticAid {
	private long orderNumber;
	private long idDiagnosticAid;
	private int amount;
	private boolean specialistAssistance;
	private long IdTypeSpecialist;
	private int item;
	
	
	public long getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}
	public long getIdDiagnosticAid() {
		return idDiagnosticAid;
	}
	public void setIdDiagnosticAid(long idDiagnosticAid) {
		this.idDiagnosticAid = idDiagnosticAid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
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
