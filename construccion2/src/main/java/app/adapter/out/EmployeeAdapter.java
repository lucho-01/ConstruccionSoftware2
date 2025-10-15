package app.adapter.out;

import org.springframework.stereotype.Repository;

import app.domain.model.Employee;
import app.domain.port.EmployeePort;


@Repository
public class EmployeeAdapter implements EmployeePort {

	@Override
	public Employee findByDocument(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findById(Employee doctor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee deleteById(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findByEmployeeName(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee update(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Employee employee) throws Exception {
		System.out.println("Se guardo al empleado");
		
	}

}
