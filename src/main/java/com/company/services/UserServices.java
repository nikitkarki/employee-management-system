package com.company.services;

import org.springframework.http.ResponseEntity;

import com.company.dto.UsersDto;

public interface UserServices {

	ResponseEntity<?> save(UsersDto usersDto);

}
