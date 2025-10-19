package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.MedicalAppointment;
import app.domain.port.MedicalAppointmentPort;
@Service
public class MedicalAppointmentService {
	@Autowired
	private MedicalAppointmentPort medicalAppointmentPort;

    public void createMedicalAppointment(MedicalAppointment appointment) throws Exception {
    	
    	if (appointment.getPatient() == null) {
    	    throw new Exception("El nombre del paciente no puede estar vacío");
    	}

    	if (appointment.getDoctor() == null) {
    	    throw new Exception("El nombre del doctor no puede estar vacío");
    	}

    	if (appointment.getDate() == null) {
    	    throw new Exception("La fecha de la cita no puede estar vacía");
    	}
        
        if (!medicalAppointmentPort.isDoctorAvailable(appointment.getDoctor(), appointment.getDate())) {
            throw new Exception("El doctor ya tiene una cita agendada para esa fecha y hora.");
        }

        if (!medicalAppointmentPort.isPatientAvailable(appointment.getPatient(), appointment.getDate())) {
            throw new Exception("El paciente ya tiene una cita agendada para esa fecha y hora.");
        }
        medicalAppointmentPort.save(appointment);
    }
}
