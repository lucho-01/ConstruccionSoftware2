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
}
