package app.adapter.out;

import org.springframework.stereotype.Service;

import app.domain.model.Billing;
import app.domain.port.BillingPort;

@Service
public class BillingAdapter implements BillingPort {

	@Override
	public Billing findByDocument(Billing billing) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Billing billing) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
