package app.application.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import app.application.usercases.exceptions.BusinessException;
import app.domain.model.Patient;
import app.domain.services.PatientServices;

@Component
public class RegisterPatientUseCase {
    @Autowired
    private PatientServices patientServices;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Patient registerPatient(Patient patient) throws Exception {
        // Validar que el paciente no exista
        if (patientServices.findByUsername(patient.getUserName()) != null) {
            throw new BusinessException("El nombre de usuario ya está registrado");
        }

        if (patientServices.findByEmail(patient.getEmail()) != null) {
            throw new BusinessException("El email ya está registrado");
        }

        // Encriptar la contraseña
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));

        // Guardar el paciente
        return patientServices.save(patient);
    }
}
