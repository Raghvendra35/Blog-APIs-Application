package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer>
{

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category categoory);
	
	// For Searching
	List<Post> findByTitleContaining(String title);// here concept of like type in Mysql
	
	// If we want search by Name then change only Title place 
	//like---> List<Post>  findByNameContanining(String name);
}
