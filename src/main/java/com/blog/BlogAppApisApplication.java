package com.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.config.AppConstants;
import com.blog.entities.Role;
import com.blog.repository.RoleRepository;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner 
{
    @Autowired
	private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepo;
	
	public static void main(String[] args) 
	{
		SpringApplication.run(BlogAppApisApplication.class, args);
	
	     System.out.println("Running.....");
	}

	
	
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}



	// implements CommendLineRunner Interface for password encoder
	//which password we have to encode that one we will pass here 
	@Override
	public void run(String... args) throws Exception 
	{
		System.out.print(this.passwordEncoder.encode("123"));
		
		try
		{
		Role role=new Role();
		role.setRoolId(AppConstants.ADMIN_USER);
		role.setName("ADMIN_USER");
		
		Role role2=new Role();
		role2.setRoolId(AppConstants.NORMAL_USER);
		role2.setName("NORMAL_USER");
		
		List<Role> roles=List.of(role,role2);
		
		List<Role> result=this.roleRepo.saveAll(roles);
		
		result.forEach(res->
		{
		 System.out.print(res.getName());
		});
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
}












