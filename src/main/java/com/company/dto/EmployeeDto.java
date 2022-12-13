package com.company.dto;

import java.util.List;

import com.company.model.Designation;

import lombok.Data;

@Data
public class EmployeeDto {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private List<Designation> designation;
}
