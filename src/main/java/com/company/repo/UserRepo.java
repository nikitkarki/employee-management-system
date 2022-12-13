package com.company.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.company.model.Users;

public interface UserRepo extends JpaRepository<Users, Long> {

	@Query(nativeQuery = true, value = "select * from user where user_name=?")
	Optional<Users> findByUserName(String username);
}
