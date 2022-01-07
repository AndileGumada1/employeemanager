package com.andile.gumada.employee.manager.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.andile.gumada.employee.manager.exception.UserNotFoundException;
import com.andile.gumada.employee.manager.model.Employee;
import com.andile.gumada.employee.manager.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.*;

import static java.util.Objects.isNull;

@Service
@Transactional
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //add an employee
    public Employee addEmployee(Employee employee) {

        //validate the request
        validateRequest(employee);
        employee.setEmpCode(UUID.randomUUID().toString());

        return employeeRepository.save(employee);
    }

    //list all the employees
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    //find an Employee by id
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by " + id + "id not found"));
    }

    //delete an employee
    public void deleteEmployee(Long id) {
        employeeRepository.deleteEmployeeById(id);
    }

    /**
     * Validate the request
     **/
    public void validateRequest(Employee request) {

        if (isNull(request))
            throw new ConstraintViolationException("Request cannot be null", null);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Employee>> violations = validator.validate(request);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Missing required fields", violations);
        }
        if (request.getSalary() <= 0 ) {
            throw new ConstraintViolationException("Salary invalid", null);
        }

    }
}
