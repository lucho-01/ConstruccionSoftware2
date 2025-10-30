package app.infrastructure.mapper;

import org.springframework.stereotype.Component;
import app.domain.model.Order;
import app.infrastructure.entities.OrderEntity;

@Component
public class OrderMapper {

    // === Domain → Entity ===
    public static OrderEntity toEntity(Order order) {
        if (order == null) return null;

        OrderEntity entity = new OrderEntity();
        entity.setId(order.getOrderId());
        entity.setMedications(order.getMedications());
        entity.setProcedure(order.getProcedure());
        entity.setDiagnosticAid(order.getDiagnosticAid());
        entity.setPatient(PatientMapper.toEntity(order.getPatient()));
        

        return entity;
    }

    // === Entity → Domain ===
    public static Order toDomain(OrderEntity entity) {
        if (entity == null) return null;

        Order order = new Order();
        order.setOrderId(entity.getId());
        order.setMedications(entity.getMedications());
        order.setProcedure(entity.getProcedure());
        order.setDiagnosticAid(entity.getDiagnosticAid());
        order.setPatient(PatientMapper.toDomain(entity.getPatient()));

        return order;
    }
}
