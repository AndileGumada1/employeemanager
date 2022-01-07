package com.andile.gumada.employee.manager.controller;

import com.andile.gumada.employee.manager.model.Employee;
import com.andile.gumada.employee.manager.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void endpoint_should_get_all_employees_() throws Exception {

        //Given
        List<Employee> employeeList = Arrays.asList(
                new Employee(1L,"andile", "gumada", "Junior Java Developer","0614723334","users/andile/downloads/images",120.2,"9bf2f645-70c0-4a48-a788-81869dece495")

        );
        //When
        when(service.findAllEmployees()).thenReturn(employeeList);

        //Then
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:9090/api/employee/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}]"));
    }

    @Test
    void endpoint_should_add_new_employee()  throws Exception {

            given(service.addEmployee(completeEmployee())).willReturn(completeEmployee());

            mockMvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:9090/api/employee/add")
                                .content(mapper.writeValueAsString(completeEmployee()))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Employee Save/update Done"));
    }
    /**
     *This is a complete Employee Request
     * @return Employee object
     **/
    public Employee completeEmployee(){
        return Employee.builder()
                .id(1L)
                .name("andile")
                .email("gumada")
                .phone("0737186095")
                .empCode(String.valueOf(UUID.randomUUID()))
                .imageUrl("")
                .jobTitle("Junior Java developer")
                .build();
    }
}