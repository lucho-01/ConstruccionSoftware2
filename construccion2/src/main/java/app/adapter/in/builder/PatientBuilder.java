/*package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.EmployeeValidator;
import app.adapter.in.validators.PatientValidator;
import app.domain.model.Employee;
import app.domain.model.Patient;

@Component
public class PatientBuilder {
	@Autowired
	private PatientValidator patientValidator;
	@Autowired
	private EmployeeValidator employeeValidator;
	
	public Patient build(String fullName, String doctorDocument, String document, String email, String phoneNumber, String address, String gender, String birthdate, String weigth, String size) throws Exception{
		Patient patient = new Patient();
		Employee doctor = new Employee();
		patient.setFullName(patientValidator.fullNameValidator(fullName));
		patient.setDocument(patientValidator.documentValidator(document));
		patient.setEmail(patientValidator.emailValidator(email));
		patient.setAddress(patientValidator.addressValidator(address));
		patient.setPhoneNumber(patientValidator.phoneNumberValidator(phoneNumber));
		patient.setGender(patientValidator.genderValidator(gender));
		patient.setBirthdate(patientValidator.birthdateValidator(birthdate));
		patient.setWeigth(patientValidator.weigthValidator(weigth));
		patient.setSize(patientValidator.sizeValidator(size));
		doctor.setDocument(employeeValidator.documentValidator(doctorDocument));
		patient.setDoctor(doctor);
		
		return patient;
		
	}
}
*/