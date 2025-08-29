package app.domain.port;

import app.domain.model.employee;

public interface EmployeePort {
	public employee findByDocument(employee employee) throws Exception;
	public employee findByEmployeeName(employee employee) throws Exception;
	public void save(employee employee) throws Exception;
	
}
