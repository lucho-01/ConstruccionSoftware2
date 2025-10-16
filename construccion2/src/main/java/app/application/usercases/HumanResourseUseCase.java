package app.application.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.model.enums.Role;
import app.domain.services.EmployeeService;
@Service
public class HumanResourseUseCase {
	@Autowired
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
