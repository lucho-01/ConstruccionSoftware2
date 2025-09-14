package app.domain.services;

import java.util.List;

import app.domain.model.Order;
import app.domain.port.OrderPort;
import app.domain.port.SearchOrderPort;

public class SearchOrderService {
	private OrderPort orderPort;
	private SearchOrderPort searchOrderPort;

	public List<Order> search(Order order) throws Exception {
		order = orderPort.findById(order);
		if (order == null) {
			throw new Exception("debe consultar una orden de un paciente registrado");
		}
		return searchOrderPort.findByOrder(order);

	}
}
