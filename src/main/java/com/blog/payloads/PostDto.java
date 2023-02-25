package com.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog.entities.Category;
import com.blog.entities.Comment;
import com.blog.entities.User;


public class PostDto
{
	private int postId;
	private String title;
	private String content;
	private String imageName;
	private Date addDate;

	private UserDto user;
	private CategoryDto category;
	
	//when we fatch post then comment will come automatically
	private Set<CommentDto> comments=new HashSet<>();

	//there are two ways of getting id of user and category
	
	//this is first way 
	
	//private User userId;
    //private Category categoryI;
	
	//second way to get Id from url so now here we will get id frome url; 



	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<CommentDto> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}

	public PostDto(int postId, String title, String content, String imageName, Date addDate, UserDto user,
			CategoryDto category, Set<CommentDto> comments) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addDate = addDate;
		this.user = user;
		this.category = category;
		this.comments = comments;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	
	

	
}
