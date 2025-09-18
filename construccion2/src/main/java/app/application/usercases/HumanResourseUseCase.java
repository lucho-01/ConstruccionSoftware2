package app.application.usercases;

import app.domain.model.Employee;
import app.domain.model.enums.Role;
import app.domain.services.EmployeeService;

public class HumanResourseUseCase {
	
	private EmployeeService employeeService;
	
	public void createEmployee(Employee employee) throws Exception {
		
		employeeService.createEmployee(employee);		
	}
	
	public void deleteEmployee(Employee employee) throws Exception {
		
		employeeService.deleteEmployee(employee);		
	}
	
	public void updateEmployee(Employee employee) throws Exception {
		
		employeeService.updateEmployee(employee);
		
	}
}
