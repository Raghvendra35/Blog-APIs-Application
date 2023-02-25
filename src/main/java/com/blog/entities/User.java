package com.blog.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   // @Column(name="userId")
	private int UserId;
	private String name;
	private String email;
	private String password;
	private String about;
	
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Post> posts=new ArrayList<>();

	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@JoinTable(name = "user_role", joinColumns= @JoinColumn(name ="user", referencedColumnName = "id"),
	                  //             inverseJoinColumns=@JoinColumn(name="role", referencedColumnName="id" ))
	private Set<Role> roles=new HashSet<>();
	
	
	
	
	
	
	


	public User(int userId, String name, String email, String password, String about, List<Post> posts,
			Set<Role> roles) {
		super();
		UserId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.posts = posts;
		this.roles = roles;
	}




	public int getUserId() {
		return UserId;
	}


	public void setUserId(int userId) {
		UserId = userId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}




	public Set<Role> getRoles() {
		return roles;
	}




	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
	 //here we have to return collection of GrantedAuthority but we have roles so we'll fetch role one by one
	// and change in GrantedAuthority with the help of stream api
		
	List<SimpleGrantedAuthority> authorities=this.roles.stream().map((role)-> 
		                                   new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
	
	return authorities;
	}




	@Override
	public String getUsername()
	{
	// here we will return username in mycase email is username	
		return this.email;
	}




	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}




	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}




	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}




	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



	
	
	

	
}
