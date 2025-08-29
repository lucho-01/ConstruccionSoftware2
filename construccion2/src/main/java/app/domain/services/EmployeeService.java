package app.domain.services;

import app.domain.model.employee;
import app.domain.port.EmployeePort;

public class EmployeeService {
	private EmployeePort employeePort;
	
	public void createEmployee(employee employee) throws Exception {
		if(employeePort.findByDocument(employee)!=null) {
			throw new Exception("Ya existe un empleado con esa cedula");		
		}		
		if(employeePort.findByEmployeeName(employee)!=null) {
			throw new Exception("Ya existe un empleado con ese nombre de usuario");
		}
		employeePort.save(employee);
		
	}
}
