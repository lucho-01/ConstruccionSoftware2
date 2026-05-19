package app.domain.port;

import app.domain.model.Order;

public interface OrderPort {
	public Order findById(Order order)throws Exception;
	public Order save(Order order)throws Exception;
	public java.util.List<Order> findAll() throws Exception;
	public java.util.List<Order> findByPatientDocument(long document) throws Exception;
	public Order update(Order order) throws Exception;
	public Order deleteById(Order order) throws Exception;
}
