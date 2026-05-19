package app.domain.port;

import app.domain.model.Billing;
import app.domain.model.Employee;

public interface AdminPort {
    
    public void save(Employee employee) throws Exception;
	public Billing findByEmployeeDocument(Employee employee) throws Exception;
}
