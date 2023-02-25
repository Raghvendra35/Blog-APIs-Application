package com.blog.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//These are steps related to Jwt
//step1 Add Depenedency (JWT)
//Step2 Create Class JwtAuthenticationEntryPoint(JWT) 

@Component // so that we can autowired this
public class JwtAuthenticationEntryPoint  implements AuthenticationEntryPoint
{

	//this method will execute when unauthorize person are trying to access the API
	//here we will sent error unauthorize with the help of httpResponse
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException
	{
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied !!!");  // this status we will send as well as we can send message
		
	}
	
	//complete Step 2

}
