package app.domain.model;

import java.sql.Date;

public class Invoice {
	private long id;
	private Pet pet;
	private Person person;
	private ClinicalOrder order;
	private String productName;
	private double productAmount;
	private Date date;
	
	
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
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public ClinicalOrder getOrder() {
		return order;
	}
	public void setOrder(ClinicalOrder order) {
		this.order = order;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(double productAmount) {
		this.productAmount = productAmount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
