package app.adapter.in.rest.controllers;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.adapter.in.validators.MedicalAppointmentValidator;
import app.adapter.rest.request.MedicalAppointmentRequest;
import app.adapter.rest.response.DoctorResponse;
import app.adapter.rest.response.MedicalAppointmentResponse;
import app.adapter.rest.response.MedicalRecordResponse;
import app.adapter.rest.response.PatientResponseDto;
import app.domain.model.Employee;
import app.domain.model.MedicalAppointment;
import app.domain.model.Patient;
import app.domain.model.enums.Role;
import app.domain.services.EmployeeService;
import app.domain.services.MedicalAppointmentService;
import app.domain.services.MedicalRecordService;
import app.domain.services.PatientServices;

@RestController
@RequestMapping("/api/patient")
@PreAuthorize("hasRole('PATIENT')")
public class PatientController {

    @Autowired
    private PatientServices patientServices;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MedicalAppointmentService medicalAppointmentService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private MedicalAppointmentValidator medicalAppointmentValidator;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Principal principal) {
        try {
            Patient patient = getAuthenticatedPatient(principal);
            return ResponseEntity.ok(toPatientResponse(patient));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al obtener el perfil: " + e.getMessage());
        }
    }

    @GetMapping("/doctors")
    public ResponseEntity<?> getDoctors() {
        try {
            List<DoctorResponse> doctors = employeeService.getAllEmployees().stream()
                    .filter(employee -> employee.getRole() == Role.DOCTORS)
                    .map(DoctorResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(doctors);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al obtener doctores: " + e.getMessage());
        }
    }

    @GetMapping("/appointments")
    public ResponseEntity<?> getMyAppointments(Principal principal) {
        try {
            Patient patient = getAuthenticatedPatient(principal);
            return ResponseEntity.ok(medicalAppointmentService.getAppointmentsByPatient(patient.getDocument()).stream()
                    .map(MedicalAppointmentResponse::new)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al obtener citas: " + e.getMessage());
        }
    }

    @PostMapping("/appointments")
    public ResponseEntity<String> createAppointment(Principal principal, @RequestBody MedicalAppointmentRequest request) {
        try {
            Patient patient = getAuthenticatedPatient(principal);
            MedicalAppointment appointment = new MedicalAppointment();
            appointment.setPatient(patient);
            appointment.setDoctor(resolveDoctor(request));
            appointment.setDate(medicalAppointmentValidator.appointmentDateTimeValidator(
                    request.getDate(),
                    request.getTime(),
                    request.getDateTime()));

            medicalAppointmentService.createMedicalAppointment(appointment);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Cita médica agendada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al agendar la cita: " + e.getMessage());
        }
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<String> deleteAppointment(Principal principal, @PathVariable Long id) {
        try {
            Patient patient = getAuthenticatedPatient(principal);
            boolean belongsToPatient = medicalAppointmentService.getAppointmentsByPatient(patient.getDocument()).stream()
                    .anyMatch(appointment -> appointment.getAppointmentId() == id);
            if (!belongsToPatient) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("No puedes cancelar una cita que no te pertenece.");
            }

            MedicalAppointment appointment = new MedicalAppointment();
            appointment.setAppointmentId(id);
            medicalAppointmentService.deleteMedicalAppointment(appointment);
            return ResponseEntity.ok("Cita médica cancelada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al cancelar la cita: " + e.getMessage());
        }
    }

    @GetMapping("/medical-records")
    public ResponseEntity<?> getMyMedicalRecords(Principal principal) {
        try {
            Patient patient = getAuthenticatedPatient(principal);
            return ResponseEntity.ok(medicalRecordService.getByPatientDocument(patient.getDocument()).stream()
                    .map(MedicalRecordResponse::new)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al obtener historial médico: " + e.getMessage());
        }
    }

    private Patient getAuthenticatedPatient(Principal principal) throws Exception {
        if (principal == null || principal.getName() == null) {
            throw new Exception("No se encontró una sesión de paciente válida.");
        }

        Patient patient = patientServices.findByUsername(principal.getName());
        if (patient == null) {
            throw new Exception("No se encontró el paciente autenticado.");
        }
        return patient;
    }

    private Employee resolveDoctor(MedicalAppointmentRequest request) throws Exception {
        if (request.getDoctor() == null || request.getDoctor().getDocument() <= 0) {
            throw new Exception("Debes seleccionar un doctor válido.");
        }

        Employee doctorLookup = new Employee();
        doctorLookup.setDocument(request.getDoctor().getDocument());
        Employee doctor = employeeService.findByDocument(doctorLookup);
        if (doctor == null || doctor.getId() <= 0 || doctor.getRole() != Role.DOCTORS) {
            throw new Exception("No se encontró un doctor válido con ese documento.");
        }
        return doctor;
    }

    private PatientResponseDto toPatientResponse(Patient patient) {
        return new PatientResponseDto(
                patient.getId(),
                patient.getFullName(),
                patient.getEmail(),
                patient.getUserName(),
                patient.getPhoneNumber(),
                patient.getAddress(),
                null,
                patient.getGender(),
                patient.getDocument(),
                patient.getWeigth(),
                patient.getSize());
    }
}
