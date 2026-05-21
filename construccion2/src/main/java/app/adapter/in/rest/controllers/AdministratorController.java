package app.adapter.in.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.adapter.in.validators.MedicalAppointmentValidator;
import app.adapter.rest.request.EmployeeRequest;
import app.adapter.rest.request.MedicalAppointmentRequest;
import app.adapter.rest.request.PatientRequest;
import app.adapter.rest.response.AdminPatientResponse;
import app.adapter.rest.response.EmergencyContactResponse;
import app.adapter.rest.response.MedicalAppointmentResponse;
import app.application.usercases.AdministratorUseCase;
import app.domain.model.Billing;
import app.domain.model.EmergencyContact;
import app.domain.model.Employee;
import app.domain.model.MedicalAppointment;
import app.domain.model.Patient;
import app.domain.model.enums.Gender;
import java.util.List;
import java.util.stream.Collectors;

import app.domain.model.enums.Role;

@RestController
@RequestMapping("/api/administrator")
@PreAuthorize("hasRole('ADMINISTRATOR')")
public class AdministratorController {

    @Autowired
    private AdministratorUseCase administratorUseCase;

    @Autowired
    private MedicalAppointmentValidator medicalAppointmentValidator;



    @GetMapping("/patients")
    public ResponseEntity<List<AdminPatientResponse>> getAllPatients() {
        try {
            return ResponseEntity.ok(administratorUseCase.getAllPatients().stream()
                    .map(AdminPatientResponse::new)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @PostMapping("/patients")
    public ResponseEntity<String> createPatient(@RequestBody PatientRequest patientRequest) {
        try {
            Patient patient = new Patient();
            patient.setFullName(patientRequest.getFullName());
            patient.setDocument(patientRequest.getDocument());
            patient.setEmail(patientRequest.getEmail());
            patient.setPhoneNumber(patientRequest.getPhoneNumber());
            patient.setAddress(patientRequest.getAddress());
            patient.setBirthdate(patientRequest.getBirthdate());
            if (patientRequest.getGender() != null && !patientRequest.getGender().isEmpty()) {
                patient.setGender(Gender.valueOf(patientRequest.getGender().toUpperCase()));
            }
            patient.setWeigth(patientRequest.getWeigth());
            patient.setSize(patientRequest.getSize());
            patient.setPolicyNumber(patientRequest.getPolicyNumber());
            patient.setInsuranceCompanyName(patientRequest.getInsuranceCompanyName());
            patient.setPolicyValidity(patientRequest.getPolicyValidity());
            patient.setPolicyEndDate(patientRequest.getPolicyEndDate());
            if (patientRequest.getDoctorDocument() > 0) {
                Employee doctor = new Employee();
                doctor.setDocument(patientRequest.getDoctorDocument());
                patient.setDoctorDocument(doctor);
            }
            if (patientRequest.getUserName() != null && !patientRequest.getUserName().isEmpty()) {
                patient.setUserName(patientRequest.getUserName());
            } else {
                patient.setUserName(String.valueOf(patientRequest.getDocument()));
            }
            if (patientRequest.getPassword() != null && !patientRequest.getPassword().isEmpty()) {
                patient.setPassword(patientRequest.getPassword());
            } else {
                patient.setPassword(String.valueOf(patientRequest.getDocument()));
            }
            administratorUseCase.createPatient(patient);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Paciente creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear el paciente: " + e.getMessage());
        }
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody PatientRequest patientRequest) {
        try {
            Patient patient = new Patient();
            patient.setId(id);
            patient.setFullName(patientRequest.getFullName());
            patient.setDocument(patientRequest.getDocument());
            patient.setEmail(patientRequest.getEmail());
            patient.setPhoneNumber(patientRequest.getPhoneNumber());
            patient.setAddress(patientRequest.getAddress());
            patient.setBirthdate(patientRequest.getBirthdate());
            if (patientRequest.getGender() != null && !patientRequest.getGender().isEmpty()) {
                patient.setGender(Gender.valueOf(patientRequest.getGender().toUpperCase()));
            }
            patient.setWeigth(patientRequest.getWeigth());
            patient.setSize(patientRequest.getSize());
            patient.setPolicyNumber(patientRequest.getPolicyNumber());
            patient.setInsuranceCompanyName(patientRequest.getInsuranceCompanyName());
            patient.setPolicyValidity(patientRequest.getPolicyValidity());
            patient.setPolicyEndDate(patientRequest.getPolicyEndDate());
            if (patientRequest.getDoctorDocument() > 0) {
                Employee doctor = new Employee();
                doctor.setDocument(patientRequest.getDoctorDocument());
                patient.setDoctorDocument(doctor);
            }
            administratorUseCase.updatePatient(patient);
            return ResponseEntity.ok("Paciente actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar el paciente: " + e.getMessage());
        }
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        try {
            Patient patient = new Patient();
            patient.setId(id);
            administratorUseCase.deletePatient(patient);
            return ResponseEntity.ok("Paciente eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar el paciente: " + e.getMessage());
        }
    }

    @PostMapping("/appointments")
    public ResponseEntity<String> createAppointment(@RequestBody MedicalAppointmentRequest request) {
        try {
            MedicalAppointment appointment = buildAppointmentFromRequest(request);
            administratorUseCase.createAppointment(appointment);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Cita médica creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear la cita médica: " + e.getMessage());
        }
    }

    @PutMapping("/appointments/{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable Long id, @RequestBody MedicalAppointmentRequest request) {
        try {
            MedicalAppointment appointment = buildAppointmentFromRequest(request);
            appointment.setAppointmentId(id);
            administratorUseCase.updateAppointment(appointment);
            return ResponseEntity.ok("Cita médica actualizada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar la cita médica: " + e.getMessage());
        }
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        try {
            MedicalAppointment appointment = new MedicalAppointment();
            appointment.setAppointmentId(id);
            administratorUseCase.deleteAppointment(appointment);
            return ResponseEntity.ok("Cita médica eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar la cita médica: " + e.getMessage());
        }
    }
    
    @PostMapping("/billings")
    public ResponseEntity<String> createBilling(@RequestBody Billing billing) {
        try {
            administratorUseCase.createBilling(billing);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Factura creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear la factura: " + e.getMessage());
        }
    }

    @PutMapping("/billings/{id}")
    public ResponseEntity<String> updateBilling(@PathVariable Long id, @RequestBody Billing billing) {
        try {
            billing.setId(id);
            administratorUseCase.updateBilling(billing);
            return ResponseEntity.ok("Factura actualizada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar la factura: " + e.getMessage());
        }
    }

    @DeleteMapping("/billings/{id}")
    public ResponseEntity<String> deleteBilling(@PathVariable Long id) {
        try {
            Billing billing = new Billing();
            billing.setId(id);
            administratorUseCase.deleteBilling(billing);
            return ResponseEntity.ok("Factura eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar la factura: " + e.getMessage());
        }
    }

    @PostMapping("/emergency-contacts")
    public ResponseEntity<String> createEmergencyContact(@RequestBody EmergencyContact emergencyContact) {
        try {
            administratorUseCase.createEmergencyContact(emergencyContact);
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
            administratorUseCase.updateEmergencyContact(emergencyContact);
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
            administratorUseCase.deleteEmergencyContact(emergencyContact);
            return ResponseEntity.ok("Contacto de emergencia eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar el contacto de emergencia: " + e.getMessage());
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<java.util.List<Employee>> getAllEmployees() {
        try {
            return ResponseEntity.ok(administratorUseCase.getAllEmployees());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/appointments")
    public ResponseEntity<java.util.List<MedicalAppointmentResponse>> getAllAppointments() {
        try {
            return ResponseEntity.ok(administratorUseCase.getAllAppointments().stream()
                    .map(MedicalAppointmentResponse::new)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/billings")
    public ResponseEntity<java.util.List<Billing>> getAllBillings() {
        try {
            return ResponseEntity.ok(administratorUseCase.getAllBillings());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/emergency-contacts")
    public ResponseEntity<java.util.List<EmergencyContactResponse>> getAllEmergencyContacts() {
        try {
            return ResponseEntity.ok(administratorUseCase.getAllEmergencyContacts().stream()
                    .map(EmergencyContactResponse::new)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeRequest request) {
        try {
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

            administratorUseCase.createEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Empleado creado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear el empleado: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear el empleado: " + e.getMessage());
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable long id, @RequestBody EmployeeRequest request) {
        try {
            Employee employee = new Employee();
            employee.setId(id);
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

            administratorUseCase.updateEmployee(employee);
            return ResponseEntity.ok("Empleado actualizado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar el empleado: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar el empleado: " + e.getMessage());
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        try {
            Employee employee = new Employee();
            employee.setId(id);
            administratorUseCase.deleteEmployee(employee);
            return ResponseEntity.ok("Empleado eliminado exitosamente");
        } catch (Exception e) {
            if ("No se puede eliminar porque este empleado tiene registros asignados.".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar el empleado: " + e.getMessage());
        }
    }

    private MedicalAppointment buildAppointmentFromRequest(MedicalAppointmentRequest request) throws Exception {
        MedicalAppointment appointment = new MedicalAppointment();
        appointment.setDoctor(request.getDoctor());
        appointment.setPatient(request.getPatient());
        appointment.setDate(medicalAppointmentValidator.appointmentDateTimeValidator(
                request.getDate(),
                request.getTime(),
                request.getDateTime()));

        if (appointment.getDoctor() == null || appointment.getDoctor().getDocument() <= 0) {
            throw new Exception("El doctor de la cita debe incluir un documento válido.");
        }
        if (appointment.getPatient() == null || appointment.getPatient().getDocument() <= 0) {
            throw new Exception("El paciente de la cita debe incluir un documento válido.");
        }

        Employee doctorLookup = new Employee();
        doctorLookup.setDocument(appointment.getDoctor().getDocument());
        Employee resolvedDoctor = administratorUseCase.findEmployeeByDocument(doctorLookup);
        if (resolvedDoctor == null || resolvedDoctor.getId() <= 0) {
            throw new Exception("No se encontró un doctor con el documento proporcionado.");
        }
        appointment.setDoctor(resolvedDoctor);

        Patient patientLookup = new Patient();
        patientLookup.setDocument(appointment.getPatient().getDocument());
        Patient resolvedPatient = administratorUseCase.findPatientByDocument(patientLookup);
        if (resolvedPatient == null || resolvedPatient.getId() <= 0) {
            throw new Exception("No se encontró un paciente con el documento proporcionado.");
        }
        appointment.setPatient(resolvedPatient);

        return appointment;
    }
}
