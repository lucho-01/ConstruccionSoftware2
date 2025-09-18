package app.adapter.in.validators;

import java.sql.Date;

import app.domain.model.enums.Role;

public abstract class SimpleValidator {
	
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
	
	public Role roleValidator(String element, String value) throws Exception {
		roleValidator(element,value);
		try {
			 Role roleValue = Role.valueOf(value.toUpperCase());
			return roleValue;
		}catch(Exception e) {
			throw new Exception(element + " debe ser un valor numerico"); 
		}
	}
	
	public Date dateValidator(String element, String value) throws Exception {
		dateValidator(element,value);
		try {
			 Date dateValue = Date.valueOf(value);
			return dateValue;
		}catch(Exception e) {
			throw new Exception(element + " debe ser un valor numerico"); 
		}
	}
}
