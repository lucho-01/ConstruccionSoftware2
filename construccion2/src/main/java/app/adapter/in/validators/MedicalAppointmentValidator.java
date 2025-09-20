package app.adapter.in.validators;

import java.sql.Date;

public class MedicalAppointmentValidator extends SimpleValidator{
	
	public String nameDoctorValidator(String value) throws Exception{
		return stringValidator("nombre del doctor", value);
	}
	
	public String namePatientValidator(String value) throws Exception{
		return stringValidator("nombre del paciente", value);
	}
	
	public Date dateValidator(String value) throws Exception{
		return dateValidator("ffecha de la cita", value);
	}
}
