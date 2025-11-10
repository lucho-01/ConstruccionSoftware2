package app.domain.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.port.EmployeePort;
@Service
public class EmployeeService {
	@Autowired
	private EmployeePort employeePort;
    
	public void createEmployee(Employee employee) throws Exception {
		
	    long phoneNumber = employee.getPhoneNumber();
	    if (String.valueOf(phoneNumber).length() != 10) {
	        throw new Exception("El número de teléfono debe tener exactamente 10 dígitos");
	    }
	    if (employee.getBirthdate() == null || employee.getBirthdate().trim().isEmpty()) {
	        throw new Exception(" la fecha no puede estar vacia");
	    }

	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    formatter.setLenient(false);

	    try {
	        formatter.parse(employee.getBirthdate().trim());
	    } catch (ParseException e) {
	        throw new Exception(" debe ser una fecha válida en formato dd/MM/yyyy (ej. 15/05/1990)");
	    }
	    
	    if(employee.getAge()<18) {
	    	throw new Exception("La edad debe ser mayor o igual a 18 años");
	    }
	    
	    if (employee.getEmail() == null || employee.getEmail().trim().isEmpty()) {
	        throw new Exception("El correo electrónico no puede estar vacío");
	    }

	    // Expresión regular para validar formato de email
	    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

	    if (!employee.getEmail().matches(emailRegex)) {
	        throw new Exception("El correo electrónico no tiene un formato válido (ej. usuario@dominio.com)");
	    }
	    long employeeDocument = employee.getDocument();
	    if (String.valueOf(employeeDocument).length() < 6 || String.valueOf(employeeDocument).length() >10) {
	        throw new Exception("El número de documento es invalido, debe tener entre 6 y 10 digitos");
	    }
		if(employeePort.findByDocument(employee)!=null) {
			throw new Exception("Ya existe un empleado con esa cedula");		
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

