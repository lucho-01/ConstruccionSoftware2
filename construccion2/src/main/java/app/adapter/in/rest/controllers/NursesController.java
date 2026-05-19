package app.adapter.in.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.application.usercases.NursesUseCase;
import app.domain.model.Order;
import app.domain.model.Patient;
import app.domain.model.RegisterVisit;
import app.adapter.rest.request.PatientRequest;

@RestController
@RequestMapping("/api/nurses")
public class NursesController {

    @Autowired
    private NursesUseCase nursesUseCase;

    @PostMapping("/orders/search")
    public ResponseEntity<?> searchOrder(@RequestBody Order order) {
        try {
            List<Order> found = nursesUseCase.searchOrder(order);
            return ResponseEntity.ok(found);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al buscar orden médica: " + e.getMessage());
        }
    }

    @PostMapping("/register-visit")
    public ResponseEntity<String> registerVisit(@RequestBody RegisterVisit registerVisit) {
        try {
            nursesUseCase.registerVisit(registerVisit);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Visita registrada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al registrar visita: " + e.getMessage());
        }
    }

    @GetMapping("/visits")
    public ResponseEntity<?> getAllVisits() {
        try {
            return ResponseEntity.ok(nursesUseCase.getAllVisits());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al obtener visitas: " + e.getMessage());
        }
    }

    @GetMapping("/visits/patient/{patientDocument}")
    public ResponseEntity<?> getVisitByPatient(@PathVariable long patientDocument) {
        try {
            return ResponseEntity.ok(nursesUseCase.searchVisitByPatient(patientDocument));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al buscar visita: " + e.getMessage());
        }
    }

    @PutMapping("/visits/{id}")
    public ResponseEntity<String> updateVisit(@PathVariable Long id, @RequestBody RegisterVisit registerVisit) {
        try {
            registerVisit.setId(id);
            nursesUseCase.updateVisit(registerVisit);
            return ResponseEntity.ok("Visita actualizada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar visita: " + e.getMessage());
        }
    }

    @DeleteMapping("/visits/{id}")
    public ResponseEntity<String> deleteVisit(@PathVariable Long id) {
        try {
            RegisterVisit registerVisit = new RegisterVisit();
            registerVisit.setId(id);
            nursesUseCase.deleteVisit(registerVisit);
            return ResponseEntity.ok("Visita eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar visita: " + e.getMessage());
        }
    }

    @PostMapping("/patients/search")
    public ResponseEntity<?> searchPatient(@RequestBody PatientRequest patientRequest) {
        try {
            Patient patient = new Patient();
            patient.setDocument(patientRequest.getDocument());
            java.util.List<Patient> found = nursesUseCase.searchPatient(patient);
            return ResponseEntity.ok(found);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al buscar paciente: " + e.getMessage());
        }
    }
}
