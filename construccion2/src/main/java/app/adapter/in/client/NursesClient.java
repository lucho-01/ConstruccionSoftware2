package app.adapter.in.client;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import app.adapter.in.builder.OrderBuilder;
import app.adapter.in.builder.PatientBuilder;
import app.adapter.in.builder.RegisterVisitBuilder;
import app.application.usercases.NursesUseCase;
import app.domain.model.Order;
import app.domain.model.Patient;
import app.domain.model.RegisterVisit;
import app.domain.model.enums.Gender;
@Controller
public class NursesClient {

	private static final String MENU = "Ingrese una de las opciones \n 1. Para registrar visita \n 2. Para buscar paciente \n 3. Para buscar orden \n 4. Para Salir ";
	private static Scanner reader = new Scanner(System.in);
	@Autowired
	private NursesUseCase nursesUseCase;
	@Autowired
	private PatientBuilder patientBuilder;
	@Autowired
	private OrderBuilder orderBuilder;
	@Autowired
	private RegisterVisitBuilder registerVisitBuilder;
	
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
				RegisterVisit registerVisit= readInfoFromRegisterVisit();
				nursesUseCase.registerVisit(registerVisit);
				return true;
			}
			case "2":{
				Patient patient= readInfoFromPatient();
				nursesUseCase.searchPatient(patient);
				return true;
			}
			case "3":{
				Order order= readInfoFromOrder();
				nursesUseCase.searchOrder(order);
				return true;
			}
			
			case "4":{
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
	
	private RegisterVisit readInfoFromRegisterVisit() throws Exception {
		System.out.println("ingrese la presion arterial");
		String bloodPressure = reader.nextLine();
		System.out.println("ingrese la temperatura");
		String temperature = reader.nextLine();
		System.out.println("ingrese el pulso");
		String pulse = reader.nextLine();	
		System.out.println("ingrese el nivel de oxigeno");
		String oxygenLevel = reader.nextLine();	
		System.out.println("ingrese el medicamento");
		String medicatios = reader.nextLine();	
		System.out.println("ingrese el procedimineto");
		String procedures = reader.nextLine();	
		System.out.println("ingrese la ayuda diagnostica");
		String diagnosticAid = reader.nextLine();	
		
		return registerVisitBuilder.build(bloodPressure, oxygenLevel, pulse, temperature, medicatios, procedures, diagnosticAid);
	}
	
	private Order readInfoFromOrder() throws Exception {
		System.out.println("ingrese los medicamentos");
		String medication = reader.nextLine();
		System.out.println("ingrese el procedimiento");
		String procedures = reader.nextLine();
		System.out.println("ingrese la ayuda diagnostica");
		String diagnosticAids = reader.nextLine();	
		System.out.println("ingrese la identificación del paciente");
		String patientId = reader.nextLine();
		
		return orderBuilder.build(medication, procedures, diagnosticAids, patientId);
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
	    System.out.println("Ingrese el genero del empleado (MALE, FEMALE, OTHER)");
	    String genderInput = reader.nextLine().toUpperCase();
	    Gender gender = Gender.valueOf(genderInput);
	    System.out.println("ingrese el documento del doctor");
	    String doctor = reader.nextLine();
	    
	    return patientBuilder.build(fullName, doctor, document, email, phoneNumber, address, genderInput, birthDate, weigth, size);
	    		
	}	
}
