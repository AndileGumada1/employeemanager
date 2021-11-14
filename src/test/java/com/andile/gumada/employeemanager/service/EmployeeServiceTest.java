package com.andile.gumada.employeemanager.service;

import com.andile.gumada.employeemanager.model.Employee;
import com.andile.gumada.employeemanager.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    EmployeeService employeeService;

    @BeforeEach
    void initUseCase() {
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    void adding_new_employee_into_database_test() {

        // providing knowledge
        when(employeeRepository.save(any(Employee.class))).thenReturn(completeEmployee());

        //
        Employee savedUser = employeeRepository.save(completeEmployee());

        //then
       assertThat(savedUser.getName()).isNotNull();


    }
    @Test
    public void     employee_exists_in_database_success_test() {

        //Given
        Employee user = new Employee();


        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(user);

        // providing knowledge
        when(employeeRepository.findAll()).thenReturn(employeeList);

        //Then
        List<Employee> fetchedUsers = employeeService.findAllEmployees();
        Assertions.assertThat(fetchedUsers.size()).isGreaterThan(0);
    }

    public Employee completeEmployee(){
        return Employee.builder()
        .name("andile")
        .email("gumada")
        .phone("0737186095")
                .empCode(String.valueOf(UUID.randomUUID()))
        .imageUrl("")
                .jobTitle("Junior Java developer")
                .build();
    }
}