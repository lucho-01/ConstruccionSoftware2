package app.adapter.in.client;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import app.adapter.in.builder.EmployeeBuilder;
import app.application.usercases.HumanResourseUseCase;
import app.domain.model.Employee;
@Controller
public class HumanResourseClient {
	private static final String MENU = "Ingrese una de las opciones \n 1. Para crear empleado \n 2. Para actualizar empleado \n 3. Para eliminar empleado \n 4. Para salir ";
	private static Scanner reader = new Scanner(System.in);
	@Autowired
	private HumanResourseUseCase humanResourseUseCase;
	@Autowired
	private EmployeeBuilder employeeBuilder;
	
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
				Employee employee= readInfoFromEmployee();
				humanResourseUseCase.createEmployee(employee);
				return true;
			}
			case "2":{
				Employee employee= readInfoFromEmployee();
				humanResourseUseCase.updateEmployee(employee);
				return true;
			}
			case "3":{
				Employee employee= readInfoFromEmployee();
				humanResourseUseCase.deleteEmployee(employee);
				return true;
			}
			
			case "4": {
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
	
	private Employee readInfoFromEmployee() throws Exception {
		System.out.println("ingrese el nombre completo del empleado");
		String fullName = reader.nextLine();
		System.out.println("ingrese la cedula del empleado");
		String document = reader.nextLine();
		System.out.println("ingrese el email del empleado");
		String email = reader.nextLine();
		System.out.println("ingrese la fecha de nacimiento del empleado");
		String birthDate = reader.nextLine();
		System.out.println("ingrese la direccion del empleado");
		String address = reader.nextLine();
		System.out.println("ingrese el numero de telefono del empleado");
		String phoneNumber = reader.nextLine();
		System.out.println("ingrese el nombre de de usuario");
		String userName = reader.nextLine();
		System.out.println("ingrese la contraseña");
		String password = reader.nextLine();
		System.out.println("ingrese la edad del empleado");
		String age = reader.nextLine();
	    System.out.println("Ingrese el rol del empleado (ADMIN, DOCTOR, ENFERMERA, etc.)");
	    String roleInput = reader.nextLine().toUpperCase();
	    return employeeBuilder.build(fullName, document,email, birthDate, address, phoneNumber, userName, password,age, roleInput);
	    		
		
	}
}
