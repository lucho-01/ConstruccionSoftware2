package app.domain.services;

import app.domain.model.MedicalAppointment;
import app.domain.port.MedicalAppointmentPort;

public class MedicalAppointmentService {
	
	private MedicalAppointmentPort medicalAppointmentPort;

    public void createMedicalAppointment(MedicalAppointment appointment) throws Exception {
        
        if (!medicalAppointmentPort.isDoctorAvailable(appointment.getDoctor(), appointment.getDateTime())) {
            throw new Exception("El doctor ya tiene una cita agendada para esa fecha y hora.");
        }

        if (!medicalAppointmentPort.isPatientAvailable(appointment.getPatient(), appointment.getDateTime())) {
            throw new Exception("El paciente ya tiene una cita agendada para esa fecha y hora.");
        }
        medicalAppointmentPort.save(appointment);
    }
}
