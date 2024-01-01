package com.blog.service.impl;

import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.model.Comment;
import com.blog.model.Post;
import com.blog.payload.CommentDto;
import com.blog.repo.CommentRepo;
import com.blog.repo.PostRepo;
import com.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, int postId) {
		Comment comment=modelMapper.map(commentDto, Comment.class);
		Post post = postRepo.findById(postId).get();
		comment.setPost(post);
		Comment saveComment = commentRepo.save(comment);
		return modelMapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void delete(Integer commentId) {
		Comment comment = commentRepo.findById(commentId).get();
		commentRepo.delete(comment);
		
	}

}
