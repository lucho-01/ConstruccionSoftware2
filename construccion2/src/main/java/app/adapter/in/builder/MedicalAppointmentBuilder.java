package app.adapter.in.builder;

import app.adapter.in.validators.MedicalAppointmentValidator;
import app.domain.model.MedicalAppointment;

public class MedicalAppointmentBuilder {

	private MedicalAppointmentValidator medicalAppointmentValidator;;
	
	public MedicalAppointment build(String nameDoctor, String namePatient, String date) throws Exception{
		MedicalAppointment medicalAppointment = new MedicalAppointment();
		medicalAppointment.setNameDoctor(medicalAppointmentValidator.nameDoctorValidator(nameDoctor));
		medicalAppointment.setNamepatient(medicalAppointmentValidator.namePatientValidator(namePatient));
		medicalAppointment.setDate(medicalAppointmentValidator.dateValidator(date));	
		
		return medicalAppointment;
		
	}
}
