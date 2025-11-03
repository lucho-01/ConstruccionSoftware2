package app.adapter.out;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.model.MedicalAppointment;
import app.domain.model.Patient;
import app.domain.port.MedicalAppointmentPort;
import app.infrastructure.entities.MedicalAppointmentEntity;
import app.infrastructure.mapper.MedicalAppointmentMapper;
import app.infrastructure.repository.MedicalAppointmentRepository;

@Service
public class MedicalAppointmentAdapter implements MedicalAppointmentPort {

    @Autowired
    private MedicalAppointmentRepository appointmentRepository;

    @Autowired
    private MedicalAppointmentMapper appointmentMapper;

    @Override
    public MedicalAppointment findById(MedicalAppointment appointment) throws Exception {
        Optional<MedicalAppointmentEntity> appointmentEntityOpt =
                appointmentRepository.findById(appointment.getAppointmentId());

        if (appointmentEntityOpt.isPresent()) {
            return appointmentMapper.toDomain(appointmentEntityOpt.get());
        } else {
            throw new Exception("No se encontró la cita médica con id: " + appointment.getAppointmentId());
        }
    }

    @Override
    public boolean isDoctorAvailable(Employee doctor, Date date) throws Exception {
        Long doctorId = doctor.getId();
        if (doctorId == null) {
            throw new Exception("El doctor no tiene un ID asignado.");
        }

        boolean exists = appointmentRepository.existsByDoctorIdAndDate(doctorId, date.toLocalDate());
        return !exists;
    }

    @Override
    public boolean isPatientAvailable(Patient patient, Date date) throws Exception {
        Long patientId = patient.getId();
        if (patientId == null) {
            throw new Exception("El paciente no tiene un ID asignado.");
        }

        boolean exists = appointmentRepository.existsByPatientIdAndDate(patientId, date.toLocalDate());
        return !exists;
    }

    @Override
    public void save(MedicalAppointment appointment) throws Exception {
        MedicalAppointmentEntity entity = appointmentMapper.toEntity(appointment);
        entity.setAppointmentId(null); 
        appointmentRepository.save(entity);
        System.out.println("Cita médica guardada correctamente para el doctor " + appointment.getDoctor().getFullName() + " y el paciente " + appointment.getPatient().getFullName());
    }
}
