package com.company.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.dto.EmployeeDto;
import com.company.model.Employee;
import com.company.services.EmployeeServices;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeServices employeeServices;

	@ModelAttribute("employee")
	public EmployeeDto employeeDto() {
		return new EmployeeDto();
	}

	@GetMapping("/add_employee")
	public String addEmployee() {
		return "add_employee";
	}

	@GetMapping("/all/{page_number}/{page_size}/{sortBy}")
	public String listAll(Principal principal, @PathVariable Integer page_number, @PathVariable Integer page_size,
			@PathVariable String sortBy, Model model) {
		Page<Employee> employee = employeeServices.findAll(principal, page_number, page_size, sortBy);
		model.addAttribute("listEmployee", employee.getContent());
		model.addAttribute("totalPages", employee.getTotalPages());
		model.addAttribute("totalItems", employee.getTotalElements());
		model.addAttribute("sortField", sortBy);
		return "employee_list";
	}

	@PostMapping(value = "/employee/save")
	@ResponseBody
	public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDto employeeDto, Principal principal) {
		return new ResponseEntity<>(employeeServices.save(employeeDto, principal), HttpStatus.OK);
	}

	@DeleteMapping("/delete/employee/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteEmployee(@PathVariable Long id, Principal principal) {
		return new ResponseEntity<>(employeeServices.delete(id, principal), HttpStatus.OK);
	}

	@GetMapping("/employee/{id}")
	public String findById(@PathVariable Long id, Principal principal, Model model) {
		Employee employee = employeeServices.findById(id, principal);
		model.addAttribute("editEmployee", employee);
		return "edit_page";
	}

	@PutMapping("/edit/employee/{id}")
	@ResponseBody
	public Employee editEmployee(Principal principal, @PathVariable Long id, @RequestBody Employee employee) {
		Employee employeeEdit = employeeServices.editEmployee(id, principal, employee);
		return employeeEdit;
	}
}
