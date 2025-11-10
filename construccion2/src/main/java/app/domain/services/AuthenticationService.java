package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.usercases.exceptions.BusinessException;
import app.domain.model.Employee;
import app.domain.model.User;
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
    

    public TokenResponse authenticate(AuthCredentials credentials) throws Exception{
        Employee employee= this.getUserByUsername(credentials.getUsername());
        this.validatePassword(credentials.getPassword(), employee.getPassword());
        return authenticationPort.authenticate(credentials, String.valueOf(employee.getRole()));
    }

    private Employee getUserByUsername(String username)  throws Exception{
    	Employee employee= new Employee();
    	employee.setUserName(username);
        employee = employeePort.findByUserName(employee);
        if (employee == null) {
            throw new BusinessException("Usuario no encontrado");
        }
        return employee;
    }

    private void validatePassword(String inputPassword, String storedPassword) throws Exception {
        if(inputPassword == null || inputPassword.isEmpty()) {
            throw new BusinessException("La contraseña no puede estar vacía");
        }
        if (!inputPassword.equals(storedPassword)) {
            throw new BusinessException("Contraseña incorrecta");
        }
    }
}
