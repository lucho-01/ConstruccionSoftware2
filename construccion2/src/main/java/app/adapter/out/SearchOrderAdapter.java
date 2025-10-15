package app.adapter.out;

import java.util.List;

import org.springframework.stereotype.Service;

import app.domain.model.Order;
import app.domain.port.SearchOrderPort;

@Service
public class SearchOrderAdapter implements SearchOrderPort {

	@Override
	public List<Order> findByOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
