package com.andile.gumada.employee.manager;

import com.andile.gumada.employee.manager.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeManagerApplicationTests {


	@Test
	void method_to_test_delete_employee() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:9090/api/employees/delete/{id}";

		URI uri = new URI(baseUrl);
		Map<String,String> map = new HashMap<>();

		restTemplate.delete( baseUrl,map);
	}

	@Test
	void method_to_test_get_all_employees() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:9090/api/employees/add";

		URI uri = new URI(baseUrl);

		ResponseEntity<String> response = restTemplate.postForEntity(uri,completeEmployee(),String.class);
		Assertions.assertEquals(201,response.getStatusCodeValue());
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
