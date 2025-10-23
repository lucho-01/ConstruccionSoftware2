package app.domain.port;

import app.domain.model.Billing;
import app.domain.model.Patient;

public interface BillingPort {
	
	public void save(Billing billing) throws Exception;
	public Billing findByPatientDocument(Billing billing) throws Exception;
}
