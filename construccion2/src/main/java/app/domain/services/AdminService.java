package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.port.EmployeePort;

@Service
public class AdminService {

    @Autowired
    private EmployeePort employeePort;

    public void createEmployee(Employee employee) throws Exception {
        if (employeePort.findByDocument(employee) != null) {
            throw new Exception("Ya existe un empleado con esa cédula");
        }
        employeePort.save(employee);
    }

    public void deleteEmployee(Employee employee) throws Exception {
        Employee existing = employeePort.findById(employee);
        if (existing == null) {
            throw new Exception("El empleado no existe");
        }
        employeePort.deleteById(employee);
    }

    public void updateEmployee(Employee employee) throws Exception {
        Employee existing = employeePort.findById(employee);
        if (existing == null) {
            throw new Exception("El empleado no existe");
        }
        employeePort.update(employee);
    }
}
