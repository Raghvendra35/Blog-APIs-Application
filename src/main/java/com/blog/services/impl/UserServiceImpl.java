package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.exceptions.*;
import com.blog.config.AppConstants;
import com.blog.entities.Role;
import com.blog.entities.User;
import com.blog.payloads.UserDto;
import com.blog.repository.RoleRepository;
import com.blog.repository.UserRepository;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService
{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper; 
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepo;

	
	
	
	
	@Override
	public UserDto createUser(UserDto userDto) 
	{
		
		User user=this.dtoToUser(userDto);
	    User savedUser=this.userRepository.save(user);
	    return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) 
	{                                           //if the Id is present then throw the exception
		User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id", userId));
		
		//Here we are getting userId and UserDto object
		//Now We will pass userId and find User Object
		//then we will update value
		//here we will get value from UserDto and save in user 
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser=this.userRepository.save(user);
		
		UserDto userDto2=this.userToDto(updatedUser);
		return userDto2;
		
		
	}

	@Override
	public UserDto getUserById(Integer userId)
	{
		User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id", userId));
				
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers()
	{
		List<User> users=this.userRepository.findAll();
		
		// here we are converting user into userDto
		//We have two ways to convert userList into userDto
		//1. forEach  2. Map
		
	List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
	return userDtos;
	
	}

	@Override
	public void deleteUser(Integer userId)
	{
	User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id", userId));
	
	this.userRepository.delete(user);
			
	}
	
		
	
	public User dtoToUser(UserDto  userDto)
	{
		
//		User user=new User();
//		user.setUserId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
//		
		// We are using ModelMapper library so we don't need to do manually this things
		
		// we have to pass two things 
		//1 Source (kis object ko conver krna hai
		//2 kis class ke Object mai convert krna hai
		
		User user=this.modelMapper.map(userDto, User.class);
		
		return user;
	}
	
	
	
	

	public UserDto  userToDto(User user)
	{
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getUserId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getName());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		
    UserDto	userDto=this.modelMapper.map(user, UserDto.class);
		
		return userDto;
	}

	
	
	
	
	//this is used for logging 
	@Override
	public UserDto registerNewUser(UserDto userDto)
	{
	   User user=this.modelMapper.map(userDto, User.class);	
	   //Encode Password
	   user.setPassword(this.passwordEncoder.encode(user.getPassword()));
	  
	   //Define the Roles
	  Role role=this.roleRepo.findById(AppConstants.NORMAL_USER).get();
	  
	  user.getRoles().add(role);
	  
	  User newUser=this.userRepository.save(user);
	  
	  return this.modelMapper.map(newUser, UserDto.class);
	}
	
	
	
	
	
	
	
	
	
	
	

}
