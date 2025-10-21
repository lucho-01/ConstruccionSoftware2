package app.infrastructure.mapper;

import org.springframework.stereotype.Component;
import app.domain.model.Order;
import app.infrastructure.entities.OrderEntity;

@Component
public class OrderMapper {

    private final PatientMapper patientMapper;

    public OrderMapper(PatientMapper patientMapper) {
        this.patientMapper = patientMapper;
    }

    // === Domain → Entity ===
    public OrderEntity toEntity(Order order) {
        if (order == null) return null;

        OrderEntity entity = new OrderEntity();
        entity.setOrderId(order.getOrderId());
        entity.setMedications(order.getMedications());
        entity.setProcedure(order.getProcedure());
        entity.setDiagnosticAid(order.getDiagnosticAid());
        entity.setPatient(patientMapper.toEntity(order.getPatient()));

        return entity;
    }

    // === Entity → Domain ===
    public Order toDomain(OrderEntity entity) {
        if (entity == null) return null;

        Order order = new Order();
        order.setOrderId(entity.getOrderId());
        order.setMedications(entity.getMedications());
        order.setProcedure(entity.getProcedure());
        order.setDiagnosticAid(entity.getDiagnosticAid());
        order.setPatient(patientMapper.toDomain(entity.getPatient()));

        return order;
    }
}
