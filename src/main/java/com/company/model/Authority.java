package com.company.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "authority")
@Data
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String authority;

	@OneToOne(mappedBy = "authority", cascade = { CascadeType.ALL })
	@JsonBackReference
	private Users user;

	public Authority() {

	}

	public Authority(String authority) {
		this.authority = authority;
	}
}
