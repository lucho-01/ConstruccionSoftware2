package app.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import app.infrastructure.entities.EmployeeEntity;

@Repository	
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{

	EmployeeEntity findByDocument(String document);

	EmployeeEntity findByFullName(String fullName);
	


}
