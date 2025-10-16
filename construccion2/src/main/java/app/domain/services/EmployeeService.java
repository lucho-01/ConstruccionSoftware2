package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.port.EmployeePort;
@Service
public class EmployeeService {
	@Autowired
	private EmployeePort employeePort;
    
	public void createEmployee(Employee employee) throws Exception {
		if(employeePort.findByDocument(employee)!=null) {
			throw new Exception("Ya existe un empleado con esa cedula");		
		}		
		if(employeePort.findByEmployeeName(employee)!=null) {
			throw new Exception("Ya existe un empleado con ese nombre de usuario");
		}
		employeePort.save(employee);
		
	}
	public void deleteEmployee(Employee employee) throws Exception {
		Employee existing = employeePort.findById(employee);

	    if (existing == null) {
	        throw new Exception("El empleado no existe");
	    } else {
	        employeePort.deleteById(employee);
	        System.out.println("Empleado eliminado correctamente: " + existing.getFullName());
	    }

	}
	
	public void updateEmployee(Employee employee) throws Exception {
		if(employeePort.findById(employee)== null) {
			
			throw new Exception("El empleado no existe");
		}
		else {
			employeePort.update(employee);
			System.out.println("Empleado actualizado correctamente! ");
		}
	}
}

