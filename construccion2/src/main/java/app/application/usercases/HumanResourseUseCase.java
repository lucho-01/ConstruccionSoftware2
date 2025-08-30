package app.application.usercases;

import app.domain.model.Employee;
import app.domain.model.enums.Role;
import app.domain.services.EmployeeService;

public class HumanResourseUseCase {
	
	private EmployeeService employeeService;
	
		
	public void createAdministrator(Employee employee) throws Exception {
		
		employee.setRole(Role.ADMINISTRATOR);
		employeeService.createEmployee(employee);
		
	}
	
	public void createInformationSupport(Employee employee) throws Exception {
		
		employee.setRole(Role.INFORMATIONSUPPORT);
		employeeService.createEmployee(employee);
		
	}
	
	public void createNurses(Employee employee) throws Exception {
		
		employee.setRole(Role.NURSES);
		employeeService.createEmployee(employee);
		
	}
	
	public void createDoctor(Employee employee) throws Exception {
		
		employee.setRole(Role.DOCTORS);
		employeeService.createEmployee(employee);
		
	}
	public void deleteInformationSupport(Employee employee) throws Exception {
		
		employeeService.deleteEmployee(employee);
		
	}
	public void deleteAdministrator(Employee employee) throws Exception {
		
		employeeService.deleteEmployee(employee);
		
	}
	public void deleteNurses(Employee employee) throws Exception {
		
		employeeService.deleteEmployee(employee);
		
	}
	
	public void deleteDoctor(Employee employee) throws Exception {
		
		employeeService.deleteEmployee(employee);
		
	}
	
}
