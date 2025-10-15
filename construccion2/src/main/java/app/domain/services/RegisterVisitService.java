package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.RegisterVisit;
import app.domain.port.RegisterVisitPort;
@Service
public class RegisterVisitService {
	@Autowired
    private RegisterVisitPort registerVisitPort;

    public void registerVisit(RegisterVisit registerVisit) throws Exception {
        if (registerVisit.getPatientId() == null) {
            throw new Exception("Debe asociar la visita a un paciente.");
        }
        if (registerVisit.getBloodPressure() == null) {
            throw new Exception("Debe registrar la presión arterial.");
        }
        if (registerVisit.getTemperature() <= 0) {
            throw new Exception("Debe registrar una temperatura válida.");
        }
        if (registerVisit.getPulse() <=0) {
            throw new Exception("Debe registrar un valor valido.");
        }
        if (registerVisit.getOxygenLevel() <=0) {
            throw new Exception("Debe registrar un valor valido.");
        }

        registerVisitPort.save(registerVisit);
    }
}
