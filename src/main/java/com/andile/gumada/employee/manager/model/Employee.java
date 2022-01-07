package com.andile.gumada.employee.manager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "employee")
public class Employee implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Name cannot be null")
	private String name;

	private String email;

	private String jobTitle;


	private String phone;

	private String imageUrl;

	@NotNull(message = "Amount cannot be empty")
	@DecimalMin(value = "0.0",message = "Amount cannot be negative")
	private Double salary;

	private String empCode;
}
