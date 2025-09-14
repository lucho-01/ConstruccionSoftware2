package app.domain.port;

import app.domain.model.Order;

public interface OrderPort {
	public Order findById(Order order)throws Exception;
	public void save(Order order)throws Exception;
}
