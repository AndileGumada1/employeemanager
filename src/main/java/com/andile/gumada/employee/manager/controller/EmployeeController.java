package com.andile.gumada.employee.manager.controller;

import com.andile.gumada.employee.manager.model.Employee;
import com.andile.gumada.employee.manager.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private  final EmployeeService employeeService;
    private HttpHeaders httpHeaders = new HttpHeaders();

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    /**
     * @return
     */
    @GetMapping(path = "/all",produces = {MediaType.APPLICATION_JSON_VALUE},consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployee(){
        setClientHeaders();
        List<Employee> employeeList = employeeService.findAllEmployees();
        log.info("Request to list all employees {}:",employeeList);
        return  employeeList;
    }


    /**
     *This end-point used to get the employee by id
     * @param id
     **/
    @GetMapping(path = "/find/{id}",produces = "application/json")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        Employee employee = employeeService.findEmployeeById(id);
        log.info("Request for finding employee by id {} :",id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     * @param employee
     * @param name
     * @param phone
     * @return
     */
    @PostMapping(path = "/add",consumes = "application/json", produces = "application/json",params = "name+phone")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee,
                                                @RequestParam String name,
                                                @RequestParam String phone){
        Employee newEmployee = employeeService.addEmployee(employee,name,phone);
        log.info("Request for adding a new employee {}",employee,name,phone);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    /**
     * This end-point is used for updating employee
     * @param employee represents employee to be updated
     * @return updated employee object
     */
    @PutMapping(path = "/update",produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee updateEmployee = employeeService.updateEmployee(employee);
        log.info("Request to update employee {}:",employee);
        return updateEmployee;
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping(path = "/delete/{id}",produces = "application/json")
    public ResponseEntity<List<?>> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        log.info("Request to delete employee by id: {}",id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * method used to set client headers
     */
    private void setClientHeaders() {
        String token = new Random().toString();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization",""+token);
    }

}

