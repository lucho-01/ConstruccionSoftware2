package app.domain.port;

import app.domain.model.Employee;

public interface EmployeePort {
	public Employee findByDocument(Employee employee) throws Exception;
	public Employee findById(Employee employee) throws Exception;
	public Employee deleteById(Employee employee) throws Exception;
	public Employee findByUserName(Employee employee) throws Exception;
	public Employee findByEmployeeName(Employee employee) throws Exception;
	public Employee update(Employee employee) throws Exception;
	public void save(Employee employee) throws Exception;

	
}
