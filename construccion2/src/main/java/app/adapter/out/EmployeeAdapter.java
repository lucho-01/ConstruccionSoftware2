package app.adapter.out;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import app.domain.model.Employee;
import app.domain.port.EmployeePort;
import app.infrastructure.entities.EmployeeEntity;
import app.infrastructure.mapper.EmployeeMapper;
import app.infrastructure.repository.EmployeeRepository;


@Repository
public class EmployeeAdapter implements EmployeePort {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee findByDocument(Employee employee) throws Exception {
		EmployeeEntity employeeEntity = employeeRepository.findByDocument(String.valueOf(employee.getDocument()));
		return EmployeeMapper.toDomain(employeeEntity);
	}

	@Override
	public Employee findById(Employee employee) throws Exception {
		Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employee.getId());
		return employeeEntity.map(EmployeeMapper::toDomain).orElse(null);
	}

	@Override
	public Employee deleteById(Employee employee) throws Exception {
	    Optional<EmployeeEntity> optionalEntity = employeeRepository.findById(employee.getId());
	    if (optionalEntity.isPresent()) {
	        EmployeeEntity entityToDelete = optionalEntity.get();
	        Employee deletedEmployee = EmployeeMapper.toDomain(entityToDelete);
	        try {
	            employeeRepository.delete(entityToDelete);
	            employeeRepository.flush();
	        } catch (DataIntegrityViolationException e) {
	            throw new Exception("No se puede eliminar porque este empleado tiene registros asignados.");
	        } catch (RuntimeException e) {
	            if (isIntegrityViolation(e)) {
	                throw new Exception("No se puede eliminar porque este empleado tiene registros asignados.");
	            }
	            throw e;
	        }
	        return deletedEmployee;
	    } else {
	        throw new Exception("No se encontró un empleado con ID: " + employee.getId());
	    }
	}

	private boolean isIntegrityViolation(Throwable throwable) {
	    Throwable current = throwable;
	    while (current != null) {
	        String className = current.getClass().getName();
	        if (className.contains("ConstraintViolationException")
	                || className.contains("DataIntegrityViolationException")) {
	            return true;
	        }
	        current = current.getCause();
	    }
	    return false;
	}


	@Override
	public Employee findByEmployeeName(Employee employee) throws Exception {
		EmployeeEntity employeeEntity = employeeRepository.findByFullName(employee.getFullName());
		return EmployeeMapper.toDomain(employeeEntity);
	}

	@Override
	public Employee update(Employee employee) throws Exception {
		Optional<EmployeeEntity> existingEntityOpt = employeeRepository.findById(employee.getId());
        if (existingEntityOpt.isPresent()) {      	
            EmployeeEntity existingEntity = existingEntityOpt.get();
            existingEntity.setFullName(employee.getFullName());
            existingEntity.setEmail(employee.getEmail());
            existingEntity.setDocument(String.valueOf(employee.getDocument()));
            existingEntity.setBirthdate(employee.getBirthdate());
            existingEntity.setAddress(employee.getAddress());
            existingEntity.setPhoneNumber(String.valueOf(employee.getPhoneNumber()));
            existingEntity.setUserName(employee.getUserName());
            existingEntity.setRole(employee.getRole());
            if (employee.getPassword() != null && !employee.getPassword().isBlank()) {
                existingEntity.setPassword(employee.getPassword());
            }
            EmployeeEntity updatedEntity = employeeRepository.save(existingEntity);
            return EmployeeMapper.toDomain(updatedEntity);
        } else {
            throw new Exception("No se pudo actualizar. Empleado no encontrado con id: " + employee.getId());
        }
    }

	@Override
	public void save(Employee employee) throws Exception {
		EmployeeEntity employeeEntity = EmployeeMapper.toEntity(employee);
		
		employeeEntity.setId(null);
		
		employeeRepository.save(employeeEntity);
		System.out.println("Empleado guardado correctamente: " + employeeEntity.getFullName());
	}

	@Override
	public java.util.List<Employee> findAll() throws Exception {
		return employeeRepository.findAll().stream()
			.map(EmployeeMapper::toDomain)
			.collect(Collectors.toList());
	}

	@Override
	public Employee findByUserName(Employee employee) throws Exception {
		EmployeeEntity employeeEntity = employeeRepository.findByUserName(employee.getUserName());
		return EmployeeMapper.toDomain(employeeEntity);
	}

}
