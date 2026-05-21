package app.adapter.in.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import app.adapter.rest.request.EmployeeRequest;
import app.application.usercases.HumanResourseUseCase;
import app.domain.model.Employee;
import app.domain.model.enums.Role;

import java.util.List;

@RestController
@RequestMapping("/api/humanResource")
@PreAuthorize("hasRole('HUMANRESOURCES')")
public class HumanResourseController {

    @Autowired
    private HumanResourseUseCase humanResourseUseCase;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            return ResponseEntity.ok(humanResourseUseCase.getAllEmployees());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeRequest request) {
        try {
            humanResourseUseCase.createEmployee(buildEmployee(request));
            return ResponseEntity.status(HttpStatus.CREATED).body("Empleado creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear empleado: " + e.getMessage());
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest request) {
        try {
            Employee employee = buildEmployee(request);
            employee.setId(id);
            humanResourseUseCase.updateEmployee(employee);
            return ResponseEntity.ok("Empleado actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar empleado: " + e.getMessage());
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        try {
            Employee employee = new Employee();
            employee.setId(id);
            humanResourseUseCase.deleteEmployee(employee);
            return ResponseEntity.ok("Empleado eliminado exitosamente");
        } catch (Exception e) {
            if ("No se puede eliminar porque este empleado tiene registros asignados.".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar empleado: " + e.getMessage());
        }
    }

    private Employee buildEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setFullName(request.getFullName());
        employee.setDocument(request.getDocument());
        employee.setEmail(request.getEmail());
        employee.setBirthdate(request.getBirthdate());
        employee.setAddress(request.getAddress());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setUserName(request.getUserName());
        employee.setPassword(request.getPassword());
        employee.setAge(request.getAge());
        employee.setRole(Role.fromCodeOrName(request.getRoleType()));
        return employee;
    }
}
