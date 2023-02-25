package com.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.payloads.CommentDto;
import com.blog.payloads.PostDto;
import com.blog.repository.CommentRepo;
import com.blog.repository.PostRepository;
import com.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService
{
    @Autowired
	private PostRepository postRepo;
	
    @Autowired
    private CommentRepo commentRepo;
    
    @Autowired
    private ModelMapper modelMapper;
    
	
    
    
    @Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) 
	{
	 
	 Post post=this.postRepo.findById(postId).get();
	 Comment comment=this.modelMapper.map(commentDto, Comment.class);
	 
	 comment.setPost(post);
	 Comment savedCom=this.commentRepo.save(comment);
	 
		return this.modelMapper.map(savedCom, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) 
	{
	
		Comment com=this.commentRepo.findById(commentId).get();  	
		this.commentRepo.delete(com);
	}

}
