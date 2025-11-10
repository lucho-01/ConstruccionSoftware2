package app.adapter.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Order;
import app.domain.port.OrderPort;
import app.infrastructure.entities.OrderEntity;
import app.infrastructure.entities.PatientEntity;
import app.infrastructure.mapper.OrderMapper;
import app.infrastructure.repository.OrderRepository;
import app.infrastructure.repository.PatientRepository;

@Service
public class OrderAdapter implements OrderPort {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private OrderMapper orderMapper; 

    @Override
    public Order findById(Order order) throws Exception {
        OrderEntity orderEntity = orderRepository.findById(order.getOrderId());
        return orderMapper.toDomain(orderEntity); 
    }

    @Override
    public void save(Order order) throws Exception {
        OrderEntity orderEntity = orderMapper.toEntity(order);

        PatientEntity patient = patientRepository.findById(order.getPatient().getId())
            .orElseThrow(() -> new Exception("Paciente no encontrado"));
        orderEntity.setPatient(patient);
        orderRepository.save(orderEntity);
    }

}
