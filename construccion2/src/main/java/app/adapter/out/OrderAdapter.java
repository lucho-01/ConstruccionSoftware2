package app.adapter.out;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Order;
import app.domain.port.OrderPort;
import app.infrastructure.entities.OrderEntity;
import app.infrastructure.mapper.OrderMapper;
import app.infrastructure.repository.OrderRepository;
@Service
public class OrderAdapter implements OrderPort{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order findById(Order order) throws Exception {
		OrderEntity orderEntity = orderRepository.findById(order.getOrderId());
		return OrderMapper.toDomain(orderEntity);
	}

	@Override
	public void save(Order order) throws Exception {
		OrderEntity orderEntity = OrderMapper.toEntity(order);
		
		orderEntity.setId(null);
		orderRepository.save(orderEntity);

		System.out.println("Se ha guardado una orden.");
	}

}
