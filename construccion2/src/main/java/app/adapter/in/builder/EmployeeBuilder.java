package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.EmployeeValidator;
import app.domain.model.Employee;

@Component
public class EmployeeBuilder {
		@Autowired
		private EmployeeValidator employeeValidator;
		
		public Employee build(String fullName, String document, String email, String birthDate, String address,String phoneNumber, String userName, String password, String age, String roleInput ) throws Exception{
			Employee employee = new Employee();
			employee.setFullName(employeeValidator.fullNameValidator(fullName));
			employee.setDocument(employeeValidator.documentValidator(document));
			employee.setEmail(employeeValidator.emailValidator(email));
			employee.setBirthdate(employeeValidator.addressValidator(birthDate));
			employee.setAddress(employeeValidator.addressValidator(address));
			employee.setPhoneNumber(employeeValidator.phoneNumberValidator(phoneNumber));
			employee.setUserName(employeeValidator.userNameValidator(userName));
			employee.setPassword(employeeValidator.passwordValidator(password));
			employee.setAge(employeeValidator.ageValidator(age));
			employee.setRole(employeeValidator.roleValidator(roleInput));
			return employee;
		}
		
}
