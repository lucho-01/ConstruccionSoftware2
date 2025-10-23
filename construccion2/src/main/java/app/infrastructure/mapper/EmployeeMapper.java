package app.infrastructure.mapper;

import org.springframework.stereotype.Component;

import app.domain.model.Employee;
import app.infrastructure.entities.EmployeeEntity;

@Component
public class EmployeeMapper {

    public static EmployeeEntity toEntity(Employee employee) {
        if (employee == null) return null;

        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(employee.getId());
        entity.setDocument(String.valueOf(employee.getDocument()));
        entity.setFullName(employee.getFullName());
        entity.setEmail(employee.getEmail());
        entity.setAddress(employee.getAddress());
        entity.setPhoneNumber(String.valueOf(employee.getPhoneNumber()));
        entity.setRole(employee.getRole());
        entity.setUserName(employee.getUserName());
        entity.setPassword(employee.getPassword());

        try {
            if (employee.getBirthdate() != null && !employee.getBirthdate().isEmpty()) {
                entity.setBirthdate(employee.getBirthdate());
            }
        } catch (Exception e) {
            System.err.println("Error al convertir birthdate: " + e.getMessage());
        }

        return entity;
    }

    public static Employee toDomain(EmployeeEntity entity) {
        if (entity == null) return null;

        Employee employee = new Employee();
        employee.setId(entity.getId());
        employee.setDocument(Long.parseLong(entity.getDocument()));
        employee.setFullName(entity.getFullName());
        employee.setEmail(entity.getEmail());
        employee.setAddress(entity.getAddress());
        employee.setPhoneNumber(Long.parseLong(entity.getPhoneNumber() != null ? entity.getPhoneNumber() : "0"));
        employee.setRole(entity.getRole());
        employee.setUserName(entity.getUserName());
        employee.setPassword(entity.getPassword());

        if (entity.getBirthdate() != null) {
            employee.setBirthdate(entity.getBirthdate().toString());
        }

        return employee;
    }
}
