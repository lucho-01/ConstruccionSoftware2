package app.adapter.in.rest.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.adapter.rest.response.AdminPatientResponse;
import app.adapter.rest.response.EmergencyContactResponse;
import app.adapter.rest.response.MedicalAppointmentResponse;
import app.application.usercases.AdministratorUseCase;
import app.domain.model.Billing;
import app.domain.model.Employee;

@RestController
@RequestMapping({"/api/informationSupport", "/api/information-support"})
@PreAuthorize("hasRole('INFORMATIONSUPPORT')")
public class InformationSupportController {

    @Autowired
    private AdministratorUseCase administratorUseCase;

    @GetMapping("/patients")
    public ResponseEntity<List<AdminPatientResponse>> getAllPatients() {
        try {
            return ResponseEntity.ok(administratorUseCase.getAllPatients().stream()
                    .map(AdminPatientResponse::new)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            return ResponseEntity.ok(administratorUseCase.getAllEmployees());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<MedicalAppointmentResponse>> getAllAppointments() {
        try {
            return ResponseEntity.ok(administratorUseCase.getAllAppointments().stream()
                    .map(MedicalAppointmentResponse::new)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/billings")
    public ResponseEntity<List<Billing>> getAllBillings() {
        try {
            return ResponseEntity.ok(administratorUseCase.getAllBillings());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/emergency-contacts")
    public ResponseEntity<List<EmergencyContactResponse>> getAllEmergencyContacts() {
        try {
            return ResponseEntity.ok(administratorUseCase.getAllEmergencyContacts().stream()
                    .map(EmergencyContactResponse::new)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
