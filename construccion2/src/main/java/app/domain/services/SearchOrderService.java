package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Order;
import app.domain.port.OrderPort;
import app.domain.port.SearchOrderPort;
@Service
public class SearchOrderService {
	@Autowired
	private OrderPort orderPort;
	@Autowired
	private SearchOrderPort searchOrderPort;

	public List<Order> search(Order order) throws Exception {
		order = orderPort.findById(order);
		if (order == null) {
			throw new Exception("debe consultar una orden de un paciente registrado");
		}
		return searchOrderPort.findByOrder(order);

	}
}
