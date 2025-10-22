package app.domain.services;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.model.Patient;
import app.domain.port.EmployeePort;
import app.domain.port.PatientPort;

@Service
public class PatientServices {
	@Autowired
	private PatientPort patientPort;
	@Autowired
	private EmployeePort employeePort;
	
	
	public void createPatient(Patient patient) throws Exception {
		
		if(patientPort.findByDocument(patient)!=null) {
			throw new Exception("Ya existe una persona con esa cedula");
		}
		long phoneNumber = patient.getPhoneNumber();
	    if (String.valueOf(phoneNumber).length() != 10) {
	        throw new Exception("El número de teléfono debe tener exactamente 10 dígitos");
	    }
	    if (patient.getBirthdate() == null || patient.getBirthdate().trim().isEmpty()) {
	        throw new Exception(" no puede estar vacío");
	    }

	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    formatter.setLenient(false);

	    try {
	        formatter.parse(patient.getBirthdate().trim());
	    } catch (ParseException e) {
	        throw new Exception(" debe ser una fecha válida en formato dd/MM/yyyy (ej. 15/05/1990)");
	    }
	    
	    if(patient.getSize()<=0) {
	    	throw new Exception("El peso debe ser un valor valido");
	    }
	    if(patient.getWeigth()<=0) {
	    	throw new Exception("La altura debe ser un valor valido");
	    }
	    
	    if (patient.getEmail() == null || patient.getEmail().trim().isEmpty()) {
	        throw new Exception("El correo electrónico no puede estar vacío");
	    }
	    long patientDocument = patient.getDocument();
	    if (String.valueOf(patientDocument).length() < 6 || String.valueOf(patientDocument).length() >10) {
	        throw new Exception("El número de documento es invalido, debe tener entre 6 y 10 digitos");
	    }

	    // Expresión regular para validar formato de email
	    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

	    if (!patient.getEmail().matches(emailRegex)) {
	        throw new Exception("El correo electrónico no tiene un formato válido (ej. usuario@dominio.com)");
	    }

	    if (patient.getDoctor() == null) {
	        throw new Exception("Debe asignar un doctor al paciente antes de crearlo.");
	    }

	    Employee doctor = employeePort.findByDocument(patient.getDoctor());
	    if (doctor == null) {
	        throw new Exception("El paciente debe estar asignado a un doctor válido.");
	    }

	    patient.setDoctor(doctor);
	    patientPort.save(patient);
		
	}
		public void updatePatient(Patient patient) throws Exception{
			
			if(patientPort.findById(patient)==null) {
				throw new Exception("El paciente no existe");
			}
			else {
				patientPort.updatePatient(patient);
				System.out.println("Paciente actualizado correctamente! ");
			}
			
		}
}
