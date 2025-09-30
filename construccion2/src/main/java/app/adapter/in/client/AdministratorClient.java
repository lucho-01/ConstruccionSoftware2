package app.adapter.in.client;

import java.util.Scanner;

import app.adapter.in.builder.BillingBuilder;
import app.adapter.in.builder.EmergencyContactBuilder;
import app.adapter.in.builder.MedicalAppointmentBuilder;
import app.adapter.in.builder.PatientBuilder;
import app.application.usercases.AdministratorUseCase;
import app.domain.model.Billing;
import app.domain.model.EmergencyContact;
import app.domain.model.MedicalAppointment;
import app.domain.model.Patient;
import app.domain.model.enums.Gender;

public class AdministratorClient {

	private static final String MENU = "Ingrese una de las opciones \n 1. Para crear paciente \n 2. Para actualizar paciente \n 3. Para crear cita medica\n 4. Para crear factura \n 5. Para crear contacto de emergencia \n 5. Para salir";
	private static Scanner reader = new Scanner(System.in);
	
	private AdministratorUseCase administratorUseCase;
	private PatientBuilder patientBuilder;
	private MedicalAppointmentBuilder medicalAppointmentBuilder;
	private BillingBuilder billingBuilder;
	private EmergencyContactBuilder emergencyContactBuilder;
	
	public void session() {
		boolean session = true;
		while (session) {
			session = menu();
		}
	}
	
	private boolean menu() {
		try {
			System.out.println(MENU);
			String option= reader.nextLine();
			switch (option) {
			case "1":{
				Patient patient= readInfoFromPatient();
				administratorUseCase.createPatient(patient);
				return true;
			}
			case "2":{
				Patient patient= readInfoFromPatient();
				administratorUseCase.updatePatient(patient);
				return true;
			}
			case "3":{
				MedicalAppointment medicalAppointment= readInfoFromMedicalAppointment();
				administratorUseCase.createAppointment(medicalAppointment);
				return true;
			}
			
			case "4":{
				Billing billing= readInfoFromBilling();
				administratorUseCase.createBilling(billing);
				return true;
			}
			
			case "5":{
				EmergencyContact emergencyContact= readInfoFromEmergencyContact();
				administratorUseCase.createEmergencyContact(emergencyContact);
				return true;
			}
			
			case "6": {
				System.out.println("hasta luego \n cerrando sesion");
				return false;
			}
				
			default: {
				System.out.println("ingrese una opcion valida");
				return true;
			
			}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return true;
		}
	}
	
private Patient readInfoFromPatient() throws Exception {
		

		System.out.println("ingrese el nombre completo del paciente");
		String fullName = reader.nextLine();
		System.out.println("ingrese la cedula del paciente");
		String document = reader.nextLine();
		System.out.println("ingrese el email del paciente");
		String email = reader.nextLine();
		System.out.println("ingrese la fecha de nacimiento del paciente");
		String birthDate = reader.nextLine();
		System.out.println("ingrese la direccion del paciente");
		String address = reader.nextLine();
		System.out.println("ingrese el numero de telefono del paciente");
		String phoneNumber = reader.nextLine();
		System.out.println("ingrese lel peso del paciente");
		String weigth = reader.nextLine();
		System.out.println("ingrese el tamaño del paciente");
		String size = reader.nextLine();
	    System.out.println("Ingrese el genero del empleado (MASCULINO, FEMENINO, OTRO)");
	    String genderInput = reader.nextLine().toUpperCase();
	    Gender gender = Gender.valueOf(genderInput);
	    
	    return patientBuilder.build(fullName, document, email, phoneNumber, address, genderInput, birthDate, weigth, size);
	    		
	}	

private MedicalAppointment readInfoFromMedicalAppointment() throws Exception {
	

	System.out.println("ingrese el nombre del paciente");
	String patientName = reader.nextLine();
	System.out.println("ingrese el nombre del doctor");
	String doctorName = reader.nextLine();
	System.out.println("ingrese la fecha de la cita");
	String date = reader.nextLine();
	
	return medicalAppointmentBuilder.build(doctorName, patientName, date);
    		
	}	

private Billing readInfoFromBilling() throws Exception {
	

	System.out.println("ingrese el nombre del paciente");
	String patientName = reader.nextLine();
	System.out.println("ingrese el nombre del doctor");
	String doctorName= reader.nextLine();
	System.out.println("ingrese el documento del paciente");
	String patientDocument = reader.nextLine();
	System.out.println("ingrese el numero de poliza del paciente");
	String policyNumber = reader.nextLine();
	System.out.println("ingrese la edad del paciente");
	String patientAge = reader.nextLine();
	System.out.println("ingrese el nombre de la compañia de seguros del paciente");
	String insuranceCompanyName = reader.nextLine();
	System.out.println("ingrese la validacion de la poliza del paciente");
	String policyValidity = reader.nextLine();
	System.out.println("ingrese la fecha de finalizacion de la poliza del paciente");
	String policyEndDate = reader.nextLine();
	
	return billingBuilder.build(patientName, doctorName, patientDocument, patientAge, policyNumber, insuranceCompanyName, policyValidity, policyEndDate); 
	
	}

private EmergencyContact readInfoFromEmergencyContact() throws Exception {
	System.out.println("ingrese el nombre del contacto de emergencia");
	String name = reader.nextLine();
	System.out.println("ingrese el apellido del contacto de emergencia");
	String lastName = reader.nextLine();
	System.out.println("ingrese el numero de telefono");
	String phoneNumber = reader.nextLine();	
	
	return emergencyContactBuilder.build(name, lastName, phoneNumber);
}
}
