package app.adapter.in.validators;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

@Component
public class MedicalAppointmentValidator extends SimpleValidator{
	
	public LocalDateTime appointmentDateTimeValidator(String date, String time, String dateTime) throws Exception {
		if (dateTime != null && !dateTime.trim().isEmpty()) {
			return dateValidator(dateTime.trim());
		}

		stringValidator("fecha de la cita", date);

		if (date.contains("T")) {
			return dateValidator(date.trim());
		}

		stringValidator("hora de la cita", time);
		try {
			return LocalDate.parse(date.trim()).atTime(LocalTime.parse(time.trim()));
		} catch (DateTimeParseException e) {
			throw new Exception("La cita debe tener fecha yyyy-MM-dd y hora HH:mm");
		}
	}

	public LocalDateTime dateValidator(String value) throws Exception{
		stringValidator("fecha de la cita", value);
		try {
			return LocalDateTime.parse(value);
		} catch (DateTimeParseException ignored) {
			try {
				return LocalDate.parse(value).atStartOfDay();
			} catch (DateTimeParseException e) {
				throw new Exception("La fecha de la cita debe tener formato yyyy-MM-dd o yyyy-MM-ddTHH:mm");
			}
		}
	}
}
