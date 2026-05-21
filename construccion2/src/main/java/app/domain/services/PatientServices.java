package app.domain.services;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
		validateColombianDocumentAndPhone(patient);
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

	    validatePolicy(patient);
	    
	    if (patient.getEmail() == null || patient.getEmail().trim().isEmpty()) {
	        throw new Exception("El correo electrónico no puede estar vacío");
	    }
	    // Expresión regular para validar formato de email
	    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

	    if (!patient.getEmail().matches(emailRegex)) {
	        throw new Exception("El correo electrónico no tiene un formato válido (ej. usuario@dominio.com)");
	    }

	    if (patient.getDoctorDocument() == null) {
	        throw new Exception("Debe asignar un doctor al paciente antes de crearlo.");
	    }

	    Employee doctor = employeePort.findByDocument(patient.getDoctorDocument	());
	    if (doctor == null) {
	        throw new Exception("El paciente debe estar asignado a un doctor válido.");
	    }

	    patient.setDoctorDocument(doctor);
	    patientPort.save(patient);
		
	}
		public void updatePatient(Patient patient) throws Exception{
			
			if(patientPort.findById(patient)==null) {
				throw new Exception("El paciente no existe");
			}
			else {
				validateColombianDocumentAndPhone(patient);
				validatePolicy(patient);
				patientPort.updatePatient(patient);
				System.out.println("Paciente actualizado correctamente! ");
			}
			
		}

	private void validatePolicy(Patient patient) throws Exception {
		if (patient.getPolicyNumber() <= 0) {
			throw new Exception("El número de póliza del paciente debe ser un número positivo");
		}

		if (patient.getInsuranceCompanyName() == null || patient.getInsuranceCompanyName().trim().isEmpty()) {
			throw new Exception("La compañía de seguros del paciente no puede estar vacía");
		}

		if (patient.getPolicyValidity() == null || patient.getPolicyValidity().trim().isEmpty()) {
			throw new Exception("La fecha de inicio de la póliza no puede estar vacía");
		}

		if (patient.getPolicyEndDate() == null || patient.getPolicyEndDate().trim().isEmpty()) {
			throw new Exception("La fecha de finalización de la póliza no puede estar vacía");
		}

		try {
			LocalDate validity = LocalDate.parse(patient.getPolicyValidity().trim());
			LocalDate endDate = LocalDate.parse(patient.getPolicyEndDate().trim());

			if (endDate.isBefore(validity)) {
				throw new Exception("La fecha de finalización de la póliza debe ser posterior a la fecha de inicio");
			}
		} catch (DateTimeParseException e) {
			throw new Exception("Las fechas de la póliza deben tener formato yyyy-MM-dd");
		}
	}

	private void validateColombianDocumentAndPhone(Patient patient) throws Exception {
		long patientDocument = patient.getDocument();
		String document = String.valueOf(patientDocument);

		if (patientDocument <= 0 || !document.matches("[1-9][0-9]{5,9}")) {
			throw new Exception("El documento debe tener entre 6 y 10 dígitos numéricos y no puede iniciar en 0");
		}

		long phoneNumber = patient.getPhoneNumber();
		String phone = String.valueOf(phoneNumber);

		if (phoneNumber <= 0 || !phone.matches("(3[0-9]{9}|60[1-8][0-9]{7})")) {
			throw new Exception("El teléfono debe ser: celular de 10 dígitos que empiece por 3 o fijo nacional que empiece por 60");
		}
	}
		
		public Patient findByUsername(String username) throws Exception {
			return patientPort.findByUsername(username);
		}

	public Patient findByEmail(String email) throws Exception {
		return patientPort.findByEmail(email);
	}

	public Patient findPatientByDocument(Patient patient) throws Exception {
		return patientPort.findByDocument(patient);
	}

		public Patient save(Patient patient) throws Exception {
			validateColombianDocumentAndPhone(patient);
			return patientPort.save(patient);
		}


	public void deletePatient(Patient patient) throws Exception {
		Patient existing = patientPort.findById(patient);

		if (existing == null) {
			throw new Exception("El paciente no existe");
		} else {
			patientPort.deleteById(patient);
			System.out.println("Paciente eliminado correctamente: " + existing.getFullName());
		}

	}

	public java.util.List<Patient> getAllPatients() throws Exception {
		return patientPort.findAll();
	}
}
