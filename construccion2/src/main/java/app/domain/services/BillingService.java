package app.domain.services;

import app.domain.model.Billing;
import app.domain.port.BillingPort;

public class BillingService {
	
	private BillingPort billingPort;
    public void createBilling(Billing billing) throws Exception {

        if (billingPort.findByDocument(billing)==null) {
            throw new Exception("El paciente no existe");
        }

        billingPort.save(billing);
    }
}
