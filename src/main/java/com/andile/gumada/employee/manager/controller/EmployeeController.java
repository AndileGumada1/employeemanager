package com.andile.gumada.employee.manager.controller;

import com.andile.gumada.employee.manager.model.Employee;
import com.andile.gumada.employee.manager.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    /**
     * @return List of objects
     */
    @GetMapping(path = "/",produces = {MediaType.APPLICATION_JSON_VALUE},consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployee(){
        List<Employee> employeeList = employeeService.findAllEmployees();
        log.info("Request to list all employees {}:",employeeList);
        return  employeeList;
    }


    /**
     *This end-point used to get the employee by id
     * @param id
     **/
    @GetMapping(path = "/{id}",produces = "application/json")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        Employee employee = employeeService.findEmployeeById(id);
        log.info("Request for finding employee by id {} :",id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     * @param employee
     * @param name
     * @return
     */
    @PostMapping(path = "/",consumes = "application/json", produces = "application/json",params = "name=andile",headers = "User-Agent=PostmanRuntime/7.20.0")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee,
                                                @RequestParam String name,
                                                @RequestHeader("User-Agent") String userAgent){
        //create a multiValueMap
        MultiValueMap<String,String> headers = new HttpHeaders();
        headers.add("User-Agent",userAgent);
        headers.add("Andile","andile");

        Employee newEmployee = employeeService.addEmployee(employee,name);

        log.info("Request for adding a new employee {}",employee,name,userAgent);
        return new ResponseEntity<>(newEmployee, headers,HttpStatus.CREATED);
    }

    /**
     * This end-point is used for updating employee
     * @param employee represents employee to be updated
     * @return updated employee object
     */
    @PutMapping(path = "/",produces = "application/json")
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
    @DeleteMapping(path = "/{id}",produces = "application/json")
    public ResponseEntity<List<?>> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        log.info("Request to delete employee by id: {}",id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

