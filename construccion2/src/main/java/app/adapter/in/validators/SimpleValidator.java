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
	
        	public Medications medicationsValidator(String element, Medications medications) throws Exception {
		medicationsValidator(element,medications);
	    if (medications == null) {
	        throw new Exception(element + " no puede ser nulo");
	    }

	    if (medications.getDose()==null) {
	        throw new Exception(element + " debe ingresar un valor válido");
	    }

	    if(medications.getTreatmentDuration()==null) {
	    	throw new Exception(element + "debe ingresar un valor valido");
	    }

	    return medications;
	}

	public Procedure procedureValidator(String element, Procedure procedure) throws Exception {
	    if (procedure == null) {
	        throw new Exception(element + " no puede ser nulo");
	    }

	    if (procedure.isSpecialistAssistance()!=true && procedure.isSpecialistAssistance()!=false) {
	        throw new Exception(element + " debe ingresar un valor válido");
	    }

	    return procedure;
	}

	public DiagnosticAid diagnosticAidValidator(String element, DiagnosticAid diagnosticAid) throws Exception {
		if (diagnosticAid.equals("")) {
	        throw new Exception(element + " no puede ser nulo");
	    }

	    if (diagnosticAid.isSpecialistAssistance() != true && diagnosticAid.isSpecialistAssistance() != false) {
	        throw new Exception(element + " debe ingresar un valor válido");
	    }

	    return diagnosticAid;
	}

	public List<String> listValidator(String element, List<String> list) throws Exception {
	    if (list == null) {
	        throw new Exception(element + " no puede ser nulo");
	    }
	    if (list.isEmpty()) {
	        throw new Exception(element + " no puede estar vacío");
	    }
	    return list;
	}

	public Patient patientNameValidator(String element, Patient patient) throws Exception {
	    if (patient == null) {
	        throw new Exception(element + " no puede ser nulo");
	    }

	    if (patient.getFullName() == null || patient.getFullName().trim().isEmpty()) {
	        throw new Exception(element + " debe tener un nombre válido");
	    }

	    return patient;
	}

	public Employee doctorNameValidator(String element, Employee doctor) throws Exception {
	    if (doctor == null) {
	        throw new Exception(element + " no puede ser nulo");
	    }

	    if (doctor.getFullName() == null || doctor.getFullName().trim().isEmpty()) {
	        throw new Exception(element + " debe tener un nombre válido");
	    }

	    return doctor;
	}
}
