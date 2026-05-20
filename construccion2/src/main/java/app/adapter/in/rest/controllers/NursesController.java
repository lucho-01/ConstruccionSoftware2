package app.adapter.in.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.application.usercases.NursesUseCase;
import app.adapter.rest.response.EmergencyContactResponse;
import app.domain.model.EmergencyContact;
import app.domain.model.Order;
import app.domain.model.Patient;
import app.domain.model.RegisterVisit;
import app.adapter.rest.request.PatientRequest;
import java.util.stream.Collectors;

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

    @PostMapping("/emergency-contacts")
    public ResponseEntity<String> createEmergencyContact(@RequestBody EmergencyContact emergencyContact) {
        try {
            nursesUseCase.createEmergencyContact(emergencyContact);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Contacto de emergencia creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear el contacto de emergencia: " + e.getMessage());
        }
    }

    @PutMapping("/emergency-contacts/{id}")
    public ResponseEntity<String> updateEmergencyContact(@PathVariable Long id, @RequestBody EmergencyContact emergencyContact) {
        try {
            emergencyContact.setId(id);
            nursesUseCase.updateEmergencyContact(emergencyContact);
            return ResponseEntity.ok("Contacto de emergencia actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar el contacto de emergencia: " + e.getMessage());
        }
    }

    @DeleteMapping("/emergency-contacts/{id}")
    public ResponseEntity<String> deleteEmergencyContact(@PathVariable Long id) {
        try {
            EmergencyContact emergencyContact = new EmergencyContact();
            emergencyContact.setId(id);
            nursesUseCase.deleteEmergencyContact(emergencyContact);
            return ResponseEntity.ok("Contacto de emergencia eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar el contacto de emergencia: " + e.getMessage());
        }
    }

    @GetMapping("/emergency-contacts")
    public ResponseEntity<?> getAllEmergencyContacts() {
        try {
            return ResponseEntity.ok(nursesUseCase.getAllEmergencyContacts().stream()
                    .map(EmergencyContactResponse::new)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al obtener contactos de emergencia: " + e.getMessage());
        }
    }
}
