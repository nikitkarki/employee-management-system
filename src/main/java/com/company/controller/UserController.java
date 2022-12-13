package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.dto.UsersDto;
import com.company.services.UserServices;

@Controller
public class UserController {

	@Autowired
	private UserServices userServices;

	@ModelAttribute("user")
	public UsersDto usersDto() {
		return new UsersDto();
	}

	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}

	@PostMapping(value = "/user/save")
	@ResponseBody
	public ResponseEntity<?> saveUser(@ModelAttribute("user") UsersDto usersDto) {
		return new ResponseEntity<>(userServices.save(usersDto), HttpStatus.OK);
	}
}
