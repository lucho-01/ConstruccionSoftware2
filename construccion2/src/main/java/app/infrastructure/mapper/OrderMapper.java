package app.infrastructure.mapper;

import org.springframework.stereotype.Component;
import app.domain.model.Order;
import app.infrastructure.entities.OrderEntity;

@Component
public class OrderMapper {

    public OrderEntity toEntity(Order order) {
        if (order == null) return null;
        OrderEntity entity = new OrderEntity();
        if (order.getOrderId() > 0) {
            entity.setId(order.getOrderId());
        }
        entity.setMedications(order.getMedications());
        entity.setProcedure(order.getProcedure());
        entity.setDiagnosticAid(order.getDiagnosticAid());
        return entity;
    }

    public Order toDomain(OrderEntity entity) {
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


