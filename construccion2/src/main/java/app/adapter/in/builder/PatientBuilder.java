package app.adapter.in.builder;

import app.adapter.in.validators.PatientValidator;
import app.domain.model.Patient;

public class PatientBuilder {

	private PatientValidator patientValidator;
	
	public Patient build(String fullName, String document, String email, String phoneNumber, String address, String gender, String birthdate, String weigth, String size) throws Exception{
		Patient patient = new Patient();
		patient.setFullName(patientValidator.fullNameValidator(fullName));
		patient.setDocument(patientValidator.documentValidator(document));
		patient.setEmail(patientValidator.emailValidator(email));
		patient.setAddress(patientValidator.addressValidator(address));
		patient.setPhoneNumber(patientValidator.phoneNumberValidator(phoneNumber));
		patient.setGender(patientValidator.genderValidator(gender));
		patient.setBirthdate(patientValidator.birthdateValidator(birthdate));
		patient.setWeigth(patientValidator.weigthValidator(weigth));
		patient.setSize(patientValidator.sizeValidator(size));
		
		return patient;
		
	}
}
