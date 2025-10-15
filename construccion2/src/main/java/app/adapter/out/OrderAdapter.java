package app.adapter.out;

import org.springframework.stereotype.Service;

import app.domain.model.Order;
import app.domain.port.OrderPort;
@Service
public class OrderAdapter implements OrderPort{

	@Override
	public Order findById(Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Order order) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
