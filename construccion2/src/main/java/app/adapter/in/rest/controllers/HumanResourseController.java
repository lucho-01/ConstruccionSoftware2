package app.adapter.in.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import app.application.usercases.HumanResourseUseCase;
import app.domain.model.Employee;

@RestController
@RequestMapping("/api/humanResource")
public class HumanResourseController {

    @Autowired
    private HumanResourseUseCase humanResourseUseCase;

    @PostMapping("employees")
    public String createEmployee(@RequestBody Employee employee) {
        try {
            humanResourseUseCase.createEmployee(employee);
            return "Empleado creado exitosamente";
        } catch (Exception e) {
            return "Error al crear empleado: " + e.getMessage();
        }
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        try {
            employee.setId(id);
            humanResourseUseCase.updateEmployee(employee);
            return "Empleado actualizado exitosamente";
        } catch (Exception e) {
            return "Error al actualizar empleado: " + e.getMessage();
        }
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        try {
            Employee employee = new Employee();
            employee.setId(id);
            humanResourseUseCase.deleteEmployee(employee);
            return "Empleado eliminado exitosamente";
        } catch (Exception e) {
            return "Error al eliminar empleado: " + e.getMessage();
        }
    }
   
}
