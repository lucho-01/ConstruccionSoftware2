package app.domain.port;

import app.domain.model.Billing;
import app.domain.model.Patient;

public interface BillingPort {
	
	public Billing findByDocument(Billing billing) throws Exception;
	public void save(Billing billing) throws Exception;
}
