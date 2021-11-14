package com.andile.gumada.employeemanager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.andile.gumada.employeemanager.exception.UserNotFoundException;
import com.andile.gumada.employeemanager.model.Employee;
import com.andile.gumada.employeemanager.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeService  {
	

	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	//add an employee
	public Employee addEmployee(Employee employee) {
		employee.setEmpCode(UUID.randomUUID().toString());
		return employeeRepository.save(employee);
	}
	//list all the employees
	public List<Employee> findAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	//find an Employee by id
	public Employee findEmployeeById(Long id) {
		return employeeRepository.findEmployeeById(id)
				.orElseThrow(() -> new UserNotFoundException("User by " +id+ "id not found"));
	}
	//delete an employee
	public void deleteEmployee(Long id) {
		employeeRepository.deleteEmployeeById(id);
	}
}
