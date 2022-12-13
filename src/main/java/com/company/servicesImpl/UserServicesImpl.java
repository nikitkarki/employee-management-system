package com.company.servicesImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.dto.UsersDto;
import com.company.model.Authority;
import com.company.model.Users;
import com.company.repo.UserRepo;
import com.company.services.UserServices;

@Service
public class UserServicesImpl implements UserServices {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<?> save(UsersDto usersDto) {
		Users user = mapper.map(usersDto, Users.class);
		user.setPassword(passwordEncoder.encode(usersDto.getPassword()));
		user.setAuthority(new Authority("USER"));
		userRepo.save(user);
		return new ResponseEntity<>("User Registered Successfully", HttpStatus.OK);
	}

}
