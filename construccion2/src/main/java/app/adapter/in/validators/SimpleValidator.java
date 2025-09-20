package app.adapter.in.validators;

import java.sql.Date;
import java.util.List;

import app.domain.model.enums.Gender;
import app.domain.model.enums.Role;
import app.domain.port.EmployeePort;

public abstract class SimpleValidator {
	private EmployeePort employeePort;
	
	public String stringValidator(String element, String value)throws Exception {
		if(value == null || value.equals("")) {
			throw new Exception(element + " no puede tener un valor vacio o nulo");
		}
		return value;
	}
	
	public int integrerValidator(String element, String value) throws Exception {
		stringValidator(element,value);
		try {
			int intValue = Integer.parseInt(value);
			return intValue;
		}catch(Exception e) {
			throw new Exception(element + " debe ser un valor numerico"); 
		}
	}
	
	public long longValidator(String element, String value) throws Exception {
		stringValidator(element,value);
		try {
			long longValue = Long.parseLong(value);
			return longValue;
		}catch(Exception e) {
			throw new Exception(element + " debe ser un valor numerico"); 
		}
	}
	
	public double doubleValidator(String element, String value) throws Exception {
		stringValidator(element,value);
		try {
			double doubleValue = Double.parseDouble(value);
			return doubleValue;
		}catch(Exception e) {
			throw new Exception(element + " debe ser un valor numerico"); 
		}
	}
	
	public Role roleValidator(String element, String value) throws Exception {
		roleValidator(element,value);
		try {
			 Role roleValue = Role.valueOf(value.toUpperCase());
			return roleValue;
		}catch(Exception e) {
			throw new Exception(element + " debe ser un rol valido"); 
		}
	}
	
	public Gender genderValidator(String element, String value) throws Exception {
		genderValidator(element,value);
		try {
			 Gender genderValue = Gender.valueOf(value.toUpperCase());
			return genderValue;
		}catch(Exception e) {
			throw new Exception(element + " debe ser un genero valido"); 
		}
	}
	
	public Date dateValidator(String element, String value) throws Exception {
		dateValidator(element,value);
		try {
			 Date dateValue = Date.valueOf(value);
			return dateValue;
		}catch(Exception e) {
			throw new Exception(element + " debe ser una fecha valida"); 
		}
	}
	
}
