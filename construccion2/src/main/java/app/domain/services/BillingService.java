package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Billing;
import app.domain.port.BillingPort;
@Service
public class BillingService {
	@Autowired
	private BillingPort billingPort;
    public void createBilling(Billing billing) throws Exception {

        if (billingPort.findByDocument(billing)==null) {
            throw new Exception("El paciente no existe");
        }

        billingPort.save(billing);
    }
}
