package com.blog.payloads;

public class JwtAuthRequest 
{
                  // here username will consider as email_id
	private String username;
	private String password;
	
	
	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
