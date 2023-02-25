package com.blog.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Role 
{
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roolId;
	
	private String name;

	@ManyToMany
	private Set<User> user=new HashSet<>();
	
	
	
	

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Role(int roolId, String name, Set<User> user) {
		super();
		this.roolId = roolId;
		this.name = name;
		this.user = user;
	}





	public int getRoolId() {
		return roolId;
	}





	public void setRoolId(int roolId) {
		this.roolId = roolId;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public Set<User> getUser() {
		return user;
	}





	public void setUser(Set<User> user) {
		this.user = user;
	}
	
	
	
}
