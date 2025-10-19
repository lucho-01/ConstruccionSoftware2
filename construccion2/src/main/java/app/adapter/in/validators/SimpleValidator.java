package app.adapter.in.validators;

import app.domain.model.DiagnosticAid;
import app.domain.model.Employee;
import app.domain.model.Medications;
import app.domain.model.Patient;
import app.domain.model.Procedure;
import java.sql.Date;
import java.util.List;



import app.domain.model.enums.Gender;
import app.domain.model.enums.Role;
import app.domain.port.EmployeePort;


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
	


}
