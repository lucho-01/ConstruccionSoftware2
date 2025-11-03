package app.adapter.in.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.application.usercases.DoctorsUseCase;
import app.domain.model.MedicalRecord;
import app.domain.model.Order;
import app.domain.model.Patient;

@RestController
@RequestMapping("/api/doctors")
public class DoctorsController {

    @Autowired
    private DoctorsUseCase doctorsUseCase;

    @PostMapping("/orders")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        try {
            doctorsUseCase.createOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(" Orden médica creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(" Error al crear la orden médica: " + e.getMessage());
        }
    }

    @PostMapping("/medical-records")
    public ResponseEntity<String> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        try {
            doctorsUseCase.createMedicalRecord(medicalRecord);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(" Registro médico creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(" Error al crear el registro médico: " + e.getMessage());
        }
    }

    @PutMapping("/medical-records/{id}")
    public ResponseEntity<String> updateMedicalRecord(
            @PathVariable Long id,
            @RequestBody MedicalRecord medicalRecord) {
        try {
            medicalRecord.setId(id);
            doctorsUseCase.updateMedicalRecord(medicalRecord);
            return ResponseEntity.ok("Registro médico actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar el registro médico: " + e.getMessage());
        }
    }

    @GetMapping("/medical-records/{patientDocument}")
    public ResponseEntity<?> getMedicalRecordByPatient(@PathVariable long patientDocument) {
        try {
            Patient patient = new Patient();
            patient.setDocument(patientDocument);
            return ResponseEntity.ok(doctorsUseCase.searchMedicalRecord(patient));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al obtener historial médico: " + e.getMessage());
        }
    }
}
