package com.andile.gumada.employee.manager.controller;

import com.andile.gumada.employee.manager.model.Employee;
import com.andile.gumada.employee.manager.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@ResponseBody
@RequiredArgsConstructor
public class ApplicationController {


    private final EmployeeService employeeService;

    @RequestMapping(path = "employees",produces = "application/json",method = RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee,
                                                @RequestParam String name){
        Employee newEmployee = employeeService.addEmployee(employee,name);
        log.info("Request for adding a new employee {}",employee,name);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
}
