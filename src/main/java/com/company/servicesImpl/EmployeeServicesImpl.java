package com.company.servicesImpl;

import java.security.Principal;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.dto.EmployeeDto;
import com.company.model.Designation;
import com.company.model.Employee;
import com.company.model.Users;
import com.company.repo.DesignationRepo;
import com.company.repo.EmployeeRepo;
import com.company.repo.UserRepo;
import com.company.services.EmployeeServices;

@Service
public class EmployeeServicesImpl implements EmployeeServices {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private DesignationRepo designationRepo;

	@Override
	public Page<Employee> findAll(Principal principal, Integer page_number, Integer page_size, String sortBy) {
		Pageable pageable = PageRequest.of(page_number - 1, page_size, Sort.by(sortBy));
		Page<Employee> employee = employeeRepo.findAll(pageable);
		return employee;
	}

	@Override
	public ResponseEntity<?> save(EmployeeDto employeeDto, Principal principal) {
		Users user = userRepo.findByUserName(principal.getName()).get();
		Employee employee = mapper.map(employeeDto, Employee.class);
		employee.setCreatedDate(new Date());
		employee.setCreatedBy(user.getUserName());
		employeeRepo.save(employee);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> delete(Long id, Principal principal) {
		Employee employee = employeeRepo.findById(id).get();
		employeeRepo.delete(employee);
		return new ResponseEntity<>("Employee deleted!!", HttpStatus.OK);
	}

	@Override
	public Employee findById(Long id, Principal principal) {
		Employee employee = employeeRepo.findById(id).get();
		return employee;
	}

	@Override
	public Employee editEmployee(Long id, Principal principal, Employee employee) {
		Users user = userRepo.findByUserName(principal.getName()).get();
		Employee employeeEdit = employeeRepo.findById(id).get();
		employeeEdit.setFirstName(employee.getFirstName());
		employeeEdit.setLastName(employee.getLastName());
		employeeEdit.setEmail(employee.getEmail());
		employeeEdit.setPhone(employee.getPhone());
		employeeEdit.setEditedDate(new Date());
		employeeEdit.setEditedBy(user.getUserName());
		designationRepo.deleteByEmployeeId(id);
		employee.getDesignation().forEach(e -> {
			Designation designationNew = new Designation();
			designationNew.setTitle(e.getTitle());
			designationNew.setEmployee(new Employee(id));
			designationRepo.save(designationNew);
		});
		employeeRepo.save(employeeEdit);
		return employeeEdit;
	}

}
