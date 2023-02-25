package com.blog.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//Step-4 (JWT)
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
	//@Autowired
	//private UserDetails userDetails;

	@Autowired
    private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//@Autowired
	//private CustomUserDetailService userDetailsService;
	
	
	
	//when API will hit then this method will be execute  first check token and  then API will be accessed 

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		
     //1. Get JWT token from request
	  	
	//when we will send token in url/Header mai Authorization key mai bhejege
	//token is coming in header
                                                //Authorization is a key		
     String requestTokenHeader=request.getHeader("Authorization");		
		
     //token format is like- Bearer 233432rtyrte
    
     System.out.println(requestTokenHeader);
	
     // After getting token we will find two things from token
     // 1. username  2. password
     // username aayega kaha se -> JwtTokenHelper has a method
 
     String username=null;
     
     //here we will find actual token
     //Beare 233432rtyrte  -> Beare ye extra hai  || ye actual token 233432rtyrte
     //token variable mai  233432rtyrte ye assign karege
     String token=null;
                                                     // token Starts with Beare
     if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer"))
     {
    	token=requestTokenHeader.substring(7);     //Beare 233432rtyrte
                                                  // 1234567 now we will get String after seven ->this token is actual token
       try
       {
    	username=this.jwtTokenHelper.getUsernameFormToken(token);
       }catch(IllegalArgumentException e)
       {
    	   System.out.println("Unable to get Jwt Token");
       }catch(ExpiredJwtException e)
       {
    	System.out.println("Jwt token has expired");   
       }catch(MalformedJwtException e)
       {
    	 System.out.println("Invalid Jwt");   
       }
    	//these exception can come when i will get Token
    	
     }else
     {
    	 System.out.println("jwt Token does not begin with Beare");
     }
     
      
     
     //Here We have got the Token
     //step 2
     
     //Validate
     
     //username is null and SecurityContext is a null then validate token and then set in Authentication
     // because SpringSecurity is not authentication any one now Validate and then set Spring Security
     
      if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
      {	 
    	  
  UserDetails userDetails= this.userDetailsService.loadUserByUsername(username);
      
        if(this.jwtTokenHelper.validateToken(token, userDetails))
        {
          //if it's giving true that mean everthing is working correct
          //Validation done
         //Set Authentication
        	
        	//setAuthentication(Object) -> Pass the object of Authentication
        	//Here We create a object of Authentication we will pass some Details
        	//then craete SecurityContextHolder then call setAuthentication(SetAuthentication)  
        	
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
        		new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        	
   	usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        	
    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
          	
        	
        }else
        {
        	System.out.println("Invald Jwt Token ");
        }
    	  
      }else
      {
          System.out.println("username is null or context is not null");	  
      }
    	  
	// if everthing is correct the forward  the request
      filterChain.doFilter(request, response);
	}
	

}


















