package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.MedicalAppointmentValidator;
import app.domain.model.MedicalAppointment;

@Component
public class MedicalAppointmentBuilder {
	@Autowired
	private MedicalAppointmentValidator medicalAppointmentValidator;;
	
	public MedicalAppointment build(String doctorName, String patientName, String date) throws Exception{
		MedicalAppointment medicalAppointment = new MedicalAppointment();
		medicalAppointment.setDoctor(medicalAppointmentValidator.doctorNameValidator(doctorName, null));
		medicalAppointment.setPatient(medicalAppointmentValidator.patientNameValidator(patientName, null));
		medicalAppointment.setDate(medicalAppointmentValidator.dateValidator(date));	
		
		return medicalAppointment;
		
	}
}
