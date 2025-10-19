package app.adapter.in.validators;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class MedicalAppointmentValidator extends SimpleValidator{
	
	public Date dateValidator(String value) throws Exception{
		 stringValidator("fecha de la cita", value);
		 return Date.valueOf(value);
	}
}
