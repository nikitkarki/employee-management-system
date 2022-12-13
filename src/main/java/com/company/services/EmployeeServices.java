package com.company.services;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.company.dto.EmployeeDto;
import com.company.model.Employee;

public interface EmployeeServices {

	Page<Employee> findAll(Principal principal, Integer page_number, Integer page_size, String sortBy);

	ResponseEntity<?> save(EmployeeDto employeeDto, Principal principal);

	ResponseEntity<?> delete(Long id, Principal principal);

	Employee findById(Long id, Principal principal);

	Employee editEmployee(Long id, Principal principal,Employee employee);

}
