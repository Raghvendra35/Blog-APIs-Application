package com.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Post 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	private String title;
	@Column(length =10000)
	private String content;
	private String imageName;
	private Date addDate;
	
    @ManyToOne
	private User user;
    
	@ManyToOne
	private Category category;

	@OneToMany(mappedBy ="post",cascade = CascadeType.ALL)
	private Set<Comment> comments=new HashSet<>();
	
	
	
	






	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Post(int postId, String title, String content, String imageName, Date addDate, User user, Category category,
			Set<Comment> comments) {
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








	public Set<Comment> getComments() {
		return comments;
	}


	public void setComments(Set<Comment> comments) {
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





	public User getUser() {
		return user;
	}





	public void setUser(User user) {
		this.user = user;
	}





	public Category getCategory() {
		return category;
	}





	public void setCategory(Category category) {
		this.category = category;
	}

	
	
	
	
	
	
	
	

}















