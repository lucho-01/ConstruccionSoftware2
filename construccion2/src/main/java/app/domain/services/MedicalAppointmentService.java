package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.MedicalAppointment;
import app.domain.port.MedicalAppointmentPort;

@Service
public class MedicalAppointmentService {

    @Autowired
    private MedicalAppointmentPort medicalAppointmentPort;

    public java.util.List<MedicalAppointment> getAllAppointments() throws Exception {
        return medicalAppointmentPort.findAll();
    }

    public void createMedicalAppointment(MedicalAppointment appointment) throws Exception {
        if (appointment == null || appointment.getDate() == null) {
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

    public void updateMedicalAppointment(MedicalAppointment appointment) throws Exception {
        if (appointment == null || appointment.getAppointmentId() <= 0) {
            throw new Exception("La cita debe incluir un id válido");
        }

        if (appointment.getDate() == null) {
            throw new Exception("La fecha de la cita no puede estar vacía");
        }

        MedicalAppointment existing = medicalAppointmentPort.findById(appointment);
        if (existing == null) {
            throw new Exception("La cita médica no existe");
        }

        medicalAppointmentPort.update(appointment);
    }

    public void deleteMedicalAppointment(MedicalAppointment appointment) throws Exception {
        if (appointment == null || appointment.getAppointmentId() <= 0) {
            throw new Exception("La cita debe incluir un id válido");
        }

        MedicalAppointment existing = medicalAppointmentPort.findById(appointment);
        if (existing == null) {
            throw new Exception("La cita médica no existe");
        }

        medicalAppointmentPort.deleteById(appointment);
    }
}
