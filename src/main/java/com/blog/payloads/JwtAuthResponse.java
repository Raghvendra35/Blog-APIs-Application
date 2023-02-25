package com.blog.payloads;


// Step -5

public class JwtAuthResponse
{
 //here we will return Token
// here we can also return when this token generated
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	} 

   
	//next Step inside SecurityConfig.class

}
