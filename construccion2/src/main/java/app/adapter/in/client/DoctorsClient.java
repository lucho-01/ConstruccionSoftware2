package app.adapter.in.client;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import app.adapter.in.builder.MedicalRecordBuilder;
import app.adapter.in.builder.OrderBuilder;
import app.adapter.in.builder.PatientBuilder;
import app.application.usercases.DoctorsUseCase;
import app.domain.model.MedicalRecord;
import app.domain.model.Order;
import app.domain.model.Patient;
import app.domain.model.enums.Gender;

@Controller
public class DoctorsClient {
	private static final String MENU = "Ingrese una de las opciones \n 1. Para crear orden \n 2. Para crear registro medico \n 3. Para actualizar registro medico \n 4. Para buscar historia clinica \n 5. Para salir ";
	private static Scanner reader = new Scanner(System.in);
	
	@Autowired
	private DoctorsUseCase doctorsUseCase;
	@Autowired
	private PatientBuilder patientBuilder;
	@Autowired
	private MedicalRecordBuilder medicalRecordBuilder;
	@Autowired
	private OrderBuilder OrderBuilder;
	
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
				Order order= readInfoFromOrder();
				doctorsUseCase.createOrder(order);
				return true;
			}
			case "2":{
				MedicalRecord medicalRecord= readInfoFromMedicalRecord();
				doctorsUseCase.createMedicalRecord(medicalRecord);
				return true;
			}
			case "3":{
				MedicalRecord medicalRecord= readInfoFromMedicalRecord();
				doctorsUseCase.updateMedicalRecord(medicalRecord);
				return true;
			}
			
			case "4":{
				Patient patient= readInfoFromPatient();
				doctorsUseCase.searchMedicalRecord(patient);
				return true;
			}
			
			case "5": {
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
	
	private Order readInfoFromOrder() throws Exception {
		System.out.println("ingrese los medicamentos");
		String medication = reader.nextLine();
		System.out.println("ingrese el procedimiento");
		String procedures = reader.nextLine();
		System.out.println("ingrese la ayuda diagnostica");
		String diagnosticAids = reader.nextLine();	
		
		return OrderBuilder.build(medication, procedures, diagnosticAids);
	}	
	
	private MedicalRecord readInfoFromMedicalRecord() throws Exception {
		System.out.println("ingrese el nombre del doctor");
		String doctorName = reader.nextLine();
		System.out.println("ingrese el documento del doctor");
		String doctorDocument = reader.nextLine();
		System.out.println("ingrese los sintomas del paciente");
		String symptomatology = reader.nextLine();	
		System.out.println("ingrese el motivo de la consulta");
		String reasonConsultation = reader.nextLine();
		System.out.println("ingrese el diagnostico");
		String diagnosis = reader.nextLine();	
		System.out.println("ingrese el nombre del paciente");
		String patientName = reader.nextLine();
		System.out.println("ingrese la fecha");
		String date = reader.nextLine();
		
		return medicalRecordBuilder.build(doctorName, symptomatology, reasonConsultation, diagnosis, date, doctorDocument, patientName);
				
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

}
