package app.domain.model;

import java.sql.Date;

import app.domain.model.enums.Role;

public class employee {

	private long id;
	private long docoument;
	private long phoneNumber;
	private String fullName;
	private String email;
	private Date birthdate;
	private String address;
	private String userName;
	private String password;
	private Role role;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDocoument() {
		return docoument;
	}
	public void setDocoument(long docoument) {
		this.docoument = docoument;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void assigCredentials(String userName, String password) {
		this.userName = userName;
		this.password = password;
			
	}
	
	
}
