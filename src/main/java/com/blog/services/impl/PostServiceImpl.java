package com.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.repository.CategoryRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService
{
	
	@Autowired
    private PostRepository postRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
    private CategoryRepository categoryRepo;	
	
	
	
	
	
	
	
	// Create 
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) 
	{
	  
	  User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryId));	
	  Category cate=this.categoryRepo.findById(categoryId).get();
	  
	  Post post=this.modelMapper.map(postDto, Post.class);
	  post.setImageName("default.png");
	  post.setAddDate(new Date());
	  post.setUser(user);
	  post.setCategory(cate);
	  
	  Post newPost=this.postRepo.save(post);
	   
	  return this.modelMapper.map(newPost, PostDto.class);
	}

	
	
	
	


	//Get Single Post
	@Override
	public PostDto getPostById(Integer postId)
	{
	Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id", postId));
	
	PostDto postDto=this.modelMapper.map(post, PostDto.class);
	
	return postDto;
	}

	
	
	
	
	
	
	
	
	
	//Get All Post
	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir)
	{
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}else
		{
			sort=Sort.by(sortBy).descending();
		}
		
	Pageable p=PageRequest.of(pageNumber, pageSize, sort);//here we are getting object of Pageable from PageRequest(object) and of()method se 	
		
	Page<Post> pagePost=this.postRepo.findAll(p); // when we'll pass object of Pageable then find All method will return Page(not List) Of Post 
	 
	List<Post> allPost= pagePost.getContent();//here we aill call getContent() it will give list of post 
	 
	 
            //here we will get one by one post in map(post) then convert in postDto using modelMapper	 
	List<PostDto> postDto=allPost.stream().map((post)
			 ->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	
	PostResponse postResponse=new PostResponse();
	
	postResponse.setContent(postDto);
	postResponse.setPageNumber(pagePost.getNumber()); //pagePost will give pageNumber(it will give current slice
	postResponse.setPageSize(pagePost.getSize()); 
	postResponse.setTotalElements(pagePost.getTotalElements());
	postResponse.setTotalpages(pagePost.getTotalPages());
	postResponse.setLastPage(pagePost.isLast());
	
	return postResponse;
	}

	
	
	
	
	
	
	
	
	
	//Delete Post by Id
	@Override
	public void deletePost(Integer postId) 
	{
	Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","post id",postId));
	   this.postRepo.delete(post);
	
	}

	
	
	
	
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId)
	{
	 Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","post id",postId));	
	
	 post.setTitle(postDto.getTitle());
	 post.setContent(postDto.getContent());
	 post.setImageName(postDto.getImageName());
	 
	 Post upatedPost=this.postRepo.save(post);
	
	 PostDto postDto2=this.modelMapper.map(upatedPost, PostDto.class);
	 return postDto2;
	}
	
	
	




	@Override
	public List<PostDto> getPostByCategory(Integer categoryId)
	{
		// if category id is not present then throw error ResourceNot Found Exception
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()
				-> new ResourceNotFoundException("category","id",categoryId));
		 
	  List<Post> posts=this.postRepo.findByCategory(cat);
	 
	  //Here we are converting post into postDto
	  List<PostDto> listPostDto=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)
			  ).collect(Collectors.toList());
	  
	  System.out.println(listPostDto);
	  return listPostDto;
	}



	
	
	@Override
	public List<PostDto> getPostByUser(Integer userId)
	{
	
	User user=this.userRepo.findById(userId).orElseThrow(()-> 
	                     new ResourceNotFoundException("user", "user id", userId));	
	
   List<Post>  postUser=this.postRepo.findByUser(user);
	
   List<PostDto> postDto=postUser.stream().map((post)->this.modelMapper.map(post, PostDto.class)
                          ).collect(Collectors.toList());
  
   System.out.println(postDto);
   return postDto;
	}




   // Searching 
	@Override
	public List<PostDto> searchPosts(String keyword) 
	{
	 
	 List<Post> posts=this.postRepo.findByTitleContaining(keyword);
	                           //stream api
	 List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	 return postDtos;
	}




}























