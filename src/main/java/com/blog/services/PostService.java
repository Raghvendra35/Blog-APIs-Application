package com.blog.services;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.payloads.UserDto;

public interface PostService 
{

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	PostDto getPostById(Integer postId);
	
	//Get all Post
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);
	
	void deletePost(Integer postId);
	
	
	//Get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//Get All Post by User
	List<PostDto> getPostByUser(Integer userId);
	
	
	
	//Seacrh Post
	List<PostDto> searchPosts(String keyword);


	
	
	
	
	
	
	
	

}
