package app.domain.model;

import app.domain.model.enums.Role;

public class Employee extends User{


	private int age;
	private Role role;
	

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
