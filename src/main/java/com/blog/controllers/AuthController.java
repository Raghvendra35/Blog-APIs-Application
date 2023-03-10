package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exceptions.APIException;
import com.blog.payloads.JwtAuthRequest;
import com.blog.payloads.JwtAuthResponse;
import com.blog.payloads.UserDto;
import com.blog.security.CustomUserDetailService;
import com.blog.security.JwtTokenHelper;
import com.blog.services.UserService;

  //step -7
@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController 
{
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private CustomUserDetailService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	
	
	
	
	@PostMapping("/login")                 //loginAPI or createToken()
	public ResponseEntity<JwtAuthResponse> createToken(
			                            @RequestBody JwtAuthRequest request) throws Exception
	{
		System.out.println("Inside Controller");
		  System.out.println(request.getUsername());
		  System.out.println(request.getPassword());
    
	this.authenticaticate(request.getUsername(), request.getPassword());		
	  System.out.println("Inside Controller");
	  System.out.println(request.getUsername());
	  System.out.println(request.getPassword());
	
	
		//Generate Token
	UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
	String token=this.jwtTokenHelper.generateToken(userDetails);
	
	JwtAuthResponse response=new JwtAuthResponse();
	response.setToken(token);
	return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	
	}




	private void authenticaticate(String username, String password) throws Exception
	{
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
		
		//here username and password will chect that is correct of not
		try
		{
		this.authenticationManager.authenticate(authenticationToken);
		}catch(BadCredentialsException e)
		{
			System.out.println("Invalid Username and Password !!! ");
		    throw new APIException("Invalid usename and password"); 
		}
		
	
	}
	
	
	//Register new user API
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto)
	{
	  UserDto registeredUser=this.userService.registerNewUser(userDto);
	
	  return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
	}
	

}
















