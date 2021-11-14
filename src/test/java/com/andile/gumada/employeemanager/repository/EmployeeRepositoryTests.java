package com.andile.gumada.employeemanager.repository;

import com.andile.gumada.employeemanager.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;



@ExtendWith(MockitoExtension.class)// enabling the mockito extension.
@DataJpaTest//Annotation that will prepare spring data JPA. It will enable entity-based save, fetch, and other environments.
@TestPropertySource(locations = "classpath:application-junit.properties")
class EmployeeRepositoryTests {


    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach//Here before execution start, we can initialize some employees
    void initUseCase() {
        List<Employee> employeeList = Arrays.asList(
                new Employee(1L, "andile", "andile.gumada@xgile.com", "junior developer", "0737186090", "", "9bf2f645-70c0-4a48-a788-81869dece495"));
        employeeRepository.saveAll(employeeList);
    }

    @AfterEach// After execution of all cases we remove the data
    public void destroyAll(){
        employeeRepository.deleteAll();
    }


    @Test
    void should_find_all_list_of_employees_test() {

        //Given and When are combined here
        List<Employee> employeeList = employeeRepository.findAll();

        //Then we can execute assertions to verify or correct output
        assertThat(employeeList.size()).isGreaterThanOrEqualTo(0);
    }

}