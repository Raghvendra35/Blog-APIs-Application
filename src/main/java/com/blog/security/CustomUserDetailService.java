package com.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repository.UserRepository;

@Component
public class CustomUserDetailService implements UserDetailsService
{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		// Loading user from database by username
		
	User user=this.userRepo.findByEmail(username).orElseThrow(()
				-> new ResourceNotFoundException("User","email :"+username,0));
	
	
	// here we are getting user but we have to return UserDetails so we will implements UserDetails in User Entity class
	//UserDetils interface has a method to use spring security so we will enable some method return type in entity class
	// isEnabled() bydefault is return type is return false se we will change return true;
	// isCredentialsNonExpired type change true  etc
	
	//we have implented UserDetails class in User entity so now we can return user
	
	return user;
	}
	

}







