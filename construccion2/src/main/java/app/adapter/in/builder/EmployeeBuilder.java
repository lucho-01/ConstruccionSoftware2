package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.EmployeeValidator;
import app.domain.model.Employee;

@Component
public class EmployeeBuilder {
		@Autowired
		private EmployeeValidator employeeValidator;
		
		public Employee build(String fullName, String document, String userName, String email, String phoneNumber, String password, String address, String role) throws Exception{
			Employee employee = new Employee();
			employee.setFullName(employeeValidator.fullNameValidator(fullName));
			employee.setDocoument(employeeValidator.documentValidator(document));
			employee.setUserName(employeeValidator.userNameValidator(userName));
			employee.setPassword(employeeValidator.passwordValidator(password));
			employee.setEmail(employeeValidator.emailValidator(email));
			employee.setAddress(employeeValidator.addressValidator(address));
			employee.setPhoneNumber(employeeValidator.phoneNumberValidator(phoneNumber));
			employee.setRole(employeeValidator.roleValidator(role));
			employee.setBirthdate(employeeValidator.addressValidator(address));
			return employee;
		}
		
}
