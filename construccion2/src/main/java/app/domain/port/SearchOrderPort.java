package app.domain.port;

import java.util.List;

import app.domain.model.Order;

public interface SearchOrderPort {
	
	public List<Order> findByOrder(Order order)throws Exception;
}
