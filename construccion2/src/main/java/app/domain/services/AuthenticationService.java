package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.application.usercases.exceptions.BusinessException;
import app.domain.model.Employee;
import app.domain.model.Patient;
import app.domain.model.auth.AuthCredentials;
import app.domain.model.auth.TokenResponse;
import app.domain.port.AuthenticationPort;
import app.domain.port.EmployeePort;

@Service
public class AuthenticationService {
    
    @Autowired
    private AuthenticationPort authenticationPort;
    
    @Autowired
    private EmployeePort employeePort;

    @Autowired
    private PatientServices patientServices;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    public TokenResponse authenticate(AuthCredentials credentials) throws Exception{
        // Intentar autenticar como empleado primero
        try {
            Employee employee = this.getEmployeeByUsername(credentials.getUsername());
            this.validatePassword(credentials.getPassword(), employee.getPassword());
            return authenticationPort.authenticate(credentials, String.valueOf(employee.getRole()), employee.getFullName());
        } catch (Exception e) {
            // Si no es empleado, intentar como paciente
            Patient patient = this.getPatientByUsername(credentials.getUsername());
            this.validateEncodedPassword(credentials.getPassword(), patient.getPassword());
            
            // Crear credenciales con rol de paciente
            AuthCredentials patientCredentials = new AuthCredentials();
            patientCredentials.setUsername(credentials.getUsername());
            patientCredentials.setPassword(credentials.getPassword());
            
            return authenticationPort.authenticate(patientCredentials, "PATIENT", patient.getFullName());
        }
    }

    private Employee getEmployeeByUsername(String username) throws Exception {
        Employee employee = new Employee();
        employee.setUserName(username);
        employee = employeePort.findByUserName(employee);
        if (employee == null) {
            throw new BusinessException("Usuario no encontrado");
        }
        return employee;
    }

    private Patient getPatientByUsername(String username) throws Exception {
        Patient patient = patientServices.findByUsername(username);
        if (patient == null) {
            throw new BusinessException("Usuario no encontrado");
        }
        return patient;
    }

    private void validatePassword(String inputPassword, String storedPassword) throws Exception {
        if(inputPassword == null || inputPassword.isEmpty()) {
            throw new BusinessException("La contraseña no puede estar vacía");
        }
        if (!inputPassword.equals(storedPassword)) {
            throw new BusinessException("Contraseña incorrecta");
        }
    }

    private void validateEncodedPassword(String inputPassword, String storedPassword) throws Exception {
        if(inputPassword == null || inputPassword.isEmpty()) {
            throw new BusinessException("La contraseña no puede estar vacía");
        }
        if (!passwordEncoder.matches(inputPassword, storedPassword)) {
            throw new BusinessException("Contraseña incorrecta");
        }
    }
}
