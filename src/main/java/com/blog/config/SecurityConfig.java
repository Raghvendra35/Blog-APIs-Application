package com.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blog.security.CustomUserDetailService;
import com.blog.security.JwtAuthenticationEntryPoint;
import com.blog.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig 
{
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint; 
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	
	
	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		 http.csrf()
		.disable()
		.authorizeHttpRequests() 
		//.antMatchers()
		.requestMatchers("/api/v1/auth/**").permitAll()
		//this is for API Documentations
		.requestMatchers("/v3/api-docs").permitAll()
		.requestMatchers(HttpMethod.GET).permitAll()
	    .anyRequest()
		.authenticated()
		.and()
		
		//Step 6 here  next step inside AuthController 
		//if Unathorize person will accsess API then this ExceptionHandling will be genarate and call method inside jwtAuthenticationEntryPoint  
		.exceptionHandling()
		.authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
		.and() //now Set State management Policy(stateless)
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		
		
		
		
		
		
		http.authenticationProvider(daoAuthenticationProvider());
		//http.addFilterBefore(null, null);
		
		//here we will get object of DefaultSecurityFilterChain  
		DefaultSecurityFilterChain build=http.build();
		
		return build;
		
	}
	
//	protected void configure(HttpSecurity http) throws Exception
//	{
//		//method chaining
//		
//		http.
//		csrf().disable()
//		.authorizeRequests()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.httpBasic();
//	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        
        return provider;
	}
	
	//Now we have to tell in configuration file we want Basic Authentication and we have to do authentication with database
	//here we will override one method configure that takes authentication builder
    //@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
    	auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder()
    {
      return new BCryptPasswordEncoder();		
    }

    
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception
//    {
//    	
//    	return super.authenticationManagerBean();
//    }
    
    
    
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception
    {
    	
    	return configuration.getAuthenticationManager();
    }
    
    
    
    
    
    
}


















