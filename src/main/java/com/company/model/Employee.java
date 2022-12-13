package com.company.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;

	private String phone;

	@Temporal(TemporalType.DATE)
	private Date createdDate;

	private String createdBy;

	@Temporal(TemporalType.DATE)
	private Date editedDate;

	private String editedBy;

	@OneToMany(targetEntity = Designation.class, cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	@JsonManagedReference
	private List<Designation> designation;

	public Employee() {

	}

	public Employee(Long id) {
		this.id = id;
	}

}
