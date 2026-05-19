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
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        try {
            Order createdOrder = doctorsUseCase.createOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(createdOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(" Error al crear la orden médica: " + e.getMessage());
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrders() {
        try {
            return ResponseEntity.ok(doctorsUseCase.getAllOrders());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al obtener órdenes médicas: " + e.getMessage());
        }
    }

    @GetMapping("/orders/patient/{patientDocument}")
    public ResponseEntity<?> getOrdersByPatient(@PathVariable long patientDocument) {
        try {
            return ResponseEntity.ok(doctorsUseCase.searchOrdersByPatient(patientDocument));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al buscar órdenes médicas: " + e.getMessage());
        }
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable long id, @RequestBody Order order) {
        try {
            order.setOrderId(id);
            doctorsUseCase.updateOrder(order);
            return ResponseEntity.ok("Orden médica actualizada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar la orden médica: " + e.getMessage());
        }
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable long id) {
        try {
            Order order = new Order();
            order.setOrderId(id);
            doctorsUseCase.deleteOrder(order);
            return ResponseEntity.ok("Orden médica eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar la orden médica: " + e.getMessage());
        }
    }

    @PostMapping("/medical-records")
    public ResponseEntity<?> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        try {
            MedicalRecord createdRecord = doctorsUseCase.createMedicalRecord(medicalRecord);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(createdRecord);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(" Error al crear el registro médico: " + e.getMessage());
        }
    }

    @GetMapping("/medical-records")
    public ResponseEntity<?> getAllMedicalRecords() {
        try {
            return ResponseEntity.ok(doctorsUseCase.getAllMedicalRecords());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al obtener registros médicos: " + e.getMessage());
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

    @DeleteMapping("/medical-records/{id}")
    public ResponseEntity<String> deleteMedicalRecord(@PathVariable Long id) {
        try {
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setId(id);
            doctorsUseCase.deleteMedicalRecord(medicalRecord);
            return ResponseEntity.ok("Registro médico eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar el registro médico: " + e.getMessage());
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
