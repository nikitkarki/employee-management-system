package com.company.dto;

import java.util.List;

import com.company.model.Authority;

import lombok.Data;

@Data
public class UsersDto {

	private Long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private List<Authority> role;
}
