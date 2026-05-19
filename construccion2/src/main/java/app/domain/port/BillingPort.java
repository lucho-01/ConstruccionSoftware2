package app.domain.port;

import app.domain.model.Billing;
import app.domain.model.Patient;

public interface BillingPort {
	
	public void save(Billing billing) throws Exception;
	public Billing findById(Billing billing) throws Exception;
	public Billing findByPatientDocument(Billing billing) throws Exception;
	public java.util.List<Billing> findAll() throws Exception;
	public void update(Billing billing) throws Exception;
	public void deleteById(Billing billing) throws Exception;
}
