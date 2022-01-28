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

    /**
     * method to add an employee
     * @param employee
     * @return
     */
    public Employee addEmployee(Employee employee,String name, String phone) {
        //set a default empCode
        employee.setEmpCode(UUID.randomUUID().toString());
        employee.setName(name);
        employee.setPhone(phone);

        return employeeRepository.save(employee);
    }

    /**
     * method used to list all the employees
     * @return List objects
     */
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * method used to update employee
     * @param employee represents the employee object
     * @return Employee object
     */
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * method used to find an Employee by id
     * @param id represents employeeId
     * @return Employee object
     */
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by " + id + "id not found"));
    }

    /**
     * method used to delete an employee
     * @param id
     */
    public void deleteEmployee(Long id) {
        employeeRepository.deleteEmployeeById(id);
    }

}
