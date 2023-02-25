package com.blog.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.payloads.PostResponse;

public class AppConstants 
{

	public static final String PAGE_NUMBER="0"; //constants value
    public static final String PAGE_SIZE="5";
    public static final String SORT_BY="postId";
    public static final String SORT_DIR="asc";

    public static final Integer ADMIN_USER=501;
    public static final Integer NORMAL_USER=502;
}
    
    // ALL Constants Of Project make here then use  

    //How to use 
    //These all static variable so we can access by Class Name
    
    
    
    //Example 
    
	// Get all Post
//	@GetMapping("/posts")
//	public ResponseEntity<PostResponse> getAllPost(
//			@RequestParam(value="pageNumber", defaultValue="0", required= false)Integer pageNumber,
//			@RequestParam(value="pageSize", defaultValue="5", required=false)Integer pageSize,
//			@RequestParam(value="sortBy", defaultValue="title", required=false)String sortBy,
//			@RequestParam(value="sortDir", defaultValue="asc", required=false)String sortDir)
//	
//	
//	
//	{
//	PostResponse postResponse=this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
//	return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
//	}
//	
    
    
        // AFTER Use This class
    
//	// Get all Post
//	@GetMapping("/posts")
//	public ResponseEntity<PostResponse> getAllPost(
//			@RequestParam(value="pageNumber", defaultValue=AppConstatans.PAGE_NUMBER, required= false)Integer pageNumber,
//			@RequestParam(value="pageSize", defaultValue=AppConstatans.PAGE_SIZE, required=false)Integer pageSize,
//			@RequestParam(value="sortBy", defaultValue=AppConstatans.SORT_BY, required=false)String sortBy,
//			@RequestParam(value="sortDir", defaultValue=AppConstatans.SORT_DIR, required=false)String sortDir)
//	
//	
//	
//	{
//	PostResponse postResponse=this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
//	return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
//	}
//	
    


