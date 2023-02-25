package com.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>
{

	// this method will be use in login time
	//emailId will be use as username
	Optional<User> findByEmail(String email);
	
	
	
	
	
	
}
