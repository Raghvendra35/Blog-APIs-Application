package com.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.APIResponse;
import com.blog.payloads.UserDto;
import com.blog.services.UserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
	private UserService userService;
	
    
    //Create User -Post
	@PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
	
	 UserDto createdUserDto=this.userService.createUser(userDto);	
     return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

	
	@PutMapping("/{userId}") // path uri varaible
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId )
	{
	 UserDto updatedUser=this.userService.updateUser(userDto, userId);
	
	 return ResponseEntity.ok(updatedUser);
	}
	
	
//	@DeleteMapping("/{userId}")
//	public ResponseEntity<?> deteleUser(@PathVariable ("userId") int uId)
//	{
//		this.userService.deleteUser(uId);
//		return ResponseEntity.ok(Map.of("Mesaage", "User Deleted Successfully"));
//	}
	
	//Or
	
	
	
	//This Role can be played by ADMIN
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deteleUser(@PathVariable ("userId") int uId)
	{
		this.userService.deleteUser(uId);
		return new ResponseEntity(new APIResponse("User deleted Successfully", true), HttpStatus.OK);
		
	}
	
	
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser()
	{
		return ResponseEntity.ok(this.userService.getAllUsers());
		
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable int userId)
	{
		return ResponseEntity.ok(this.userService.getUserById(userId));
		
	}
	
	
	
	
	
	
	
	
}









































