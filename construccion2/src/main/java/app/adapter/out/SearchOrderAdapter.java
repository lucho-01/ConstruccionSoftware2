package app.adapter.out;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Order;
import app.domain.port.SearchOrderPort;
import app.infrastructure.entities.OrderEntity;
import app.infrastructure.mapper.OrderMapper;
import app.infrastructure.repository.OrderRepository;

@Service
public class SearchOrderAdapter implements SearchOrderPort {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> findByOrder(Order order) throws Exception {
        List<OrderEntity> entities = orderRepository.findByPatient_Document(order.getPatient().getDocument());

        if (entities == null || entities.isEmpty()) {
            throw new Exception("No se encontraron órdenes médicas para el paciente con documento: " 
                    + order.getPatient().getDocument());
        }

        return entities.stream()
                .map(orderMapper::toDomain)
                .collect(Collectors.toList());
    }
}
