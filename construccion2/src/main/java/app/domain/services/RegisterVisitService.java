package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Patient;
import app.domain.model.RegisterVisit;
import app.domain.port.PatientPort;
import app.domain.port.RegisterVisitPort;
@Service
public class RegisterVisitService {
	@Autowired
    private RegisterVisitPort registerVisitPort;
	@Autowired
	private PatientPort patientPort;

    public void registerVisit(RegisterVisit registerVisit) throws Exception {
        validateVisit(registerVisit, true);
        registerVisitPort.save(registerVisit);
    }

    public java.util.List<RegisterVisit> getAllVisits() throws Exception {
        return registerVisitPort.findAll();
    }

    public RegisterVisit findByPatientDocument(long document) throws Exception {
        return registerVisitPort.findByPatientDocument(document);
    }

    public void updateVisit(RegisterVisit registerVisit) throws Exception {
        if (registerVisit.getId() == null || registerVisitPort.findById(registerVisit) == null) {
            throw new Exception("La visita no existe.");
        }
        validateVisit(registerVisit, false);
        registerVisitPort.update(registerVisit);
    }

    public void deleteVisit(RegisterVisit registerVisit) throws Exception {
        if (registerVisit.getId() == null || registerVisitPort.findById(registerVisit) == null) {
            throw new Exception("La visita no existe.");
        }
        registerVisitPort.deleteById(registerVisit);
    }

    private void validateVisit(RegisterVisit registerVisit, boolean requirePatient) throws Exception {
        Patient patient = null;
        if (registerVisit.getPatient() != null) {
            patient = patientPort.findByDocument(registerVisit.getPatient());
        }
        if (requirePatient && patient == null) {
            throw new Exception("Debe asociar la visita a un paciente registrado.");
        }
        if (patient != null) {
            registerVisit.setPatient(patient);
        }

        // 🔹 Validar presión arterial (en mmHg)
        if (registerVisit.getBloodPressure() == 0 || registerVisit.getBloodPressure() < 50 || registerVisit.getBloodPressure() > 250) {
            throw new Exception("Debe registrar una presión arterial válida (entre 50 y 250 mmHg).");
        }

        // 🔹 Validar temperatura corporal (en °C)
        if (registerVisit.getTemperature() == 0 || registerVisit.getTemperature() < 30 || registerVisit.getTemperature() > 45) {
            throw new Exception("Debe registrar una temperatura válida (entre 30°C y 45°C).");
        }

        // 🔹 Validar frecuencia cardíaca (en pulsaciones por minuto)
        if (registerVisit.getPulse() == 0 || registerVisit.getPulse() < 30 || registerVisit.getPulse() > 220) {
            throw new Exception("Debe registrar un pulso válido (entre 30 y 220 bpm).");
        }

        // 🔹 Validar nivel de oxígeno en sangre (SpO2 en %)
        if (registerVisit.getOxygenLevel() == 0 || registerVisit.getOxygenLevel() < 70 || registerVisit.getOxygenLevel() > 100) {
            throw new Exception("Debe registrar un nivel de oxígeno válido (entre 70% y 100%).");
        }

    }
}
