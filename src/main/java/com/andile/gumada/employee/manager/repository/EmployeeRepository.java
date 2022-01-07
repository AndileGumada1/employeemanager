package com.andile.gumada.employee.manager.repository;

import java.util.Optional;

import com.andile.gumada.employee.manager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	void deleteEmployeeById(Long id);

	Optional<Employee> findEmployeeById(Long id);

	Optional<Employee> findByEmail(String email);
}
