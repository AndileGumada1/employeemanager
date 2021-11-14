package com.andile.gumada.employeemanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andile.gumada.employeemanager.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	void deleteEmployeeById(Long id);

	Optional<Employee> findEmployeeById(Long id);

	Optional<Employee> findByEmail(String email);
}
