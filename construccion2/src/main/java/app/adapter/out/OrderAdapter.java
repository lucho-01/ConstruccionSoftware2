package app.adapter.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
    public Order save(Order order) throws Exception {
        OrderEntity orderEntity = orderMapper.toEntity(order);

        PatientEntity patient = patientRepository.findById(order.getPatient().getId())
            .orElseThrow(() -> new Exception("Paciente no encontrado"));
        orderEntity.setPatient(patient);
        return orderMapper.toDomain(orderRepository.save(orderEntity));
    }

    @Override
    public java.util.List<Order> findAll() throws Exception {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public java.util.List<Order> findByPatientDocument(long document) throws Exception {
        return orderRepository.findByPatient_Document(document).stream()
                .map(orderMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Order update(Order order) throws Exception {
        OrderEntity existingEntity = orderRepository.findById(order.getOrderId());
        if (existingEntity == null) {
            throw new Exception("No se encontró la orden médica con id: " + order.getOrderId());
        }

        existingEntity.setMedications(order.getMedications());
        existingEntity.setProcedure(order.getProcedure());
        existingEntity.setDiagnosticAid(order.getDiagnosticAid());

        if (order.getPatient() != null && order.getPatient().getDocument() > 0) {
            PatientEntity patient = patientRepository.findByDocument(order.getPatient().getDocument());
            if (patient == null) {
                throw new Exception("Paciente no encontrado");
            }
            existingEntity.setPatient(patient);
        }

        return orderMapper.toDomain(orderRepository.save(existingEntity));
    }

    @Override
    public Order deleteById(Order order) throws Exception {
        OrderEntity entityToDelete = orderRepository.findById(order.getOrderId());
        if (entityToDelete == null) {
            return null;
        }

        Order deletedOrder = orderMapper.toDomain(entityToDelete);
        orderRepository.delete(entityToDelete);
        return deletedOrder;
    }
}
