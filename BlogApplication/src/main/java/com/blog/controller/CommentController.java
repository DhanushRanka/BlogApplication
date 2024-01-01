package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.model.Comment;
import com.blog.model.Post;
import com.blog.payload.CommentDto;
import com.blog.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@PostMapping("post/{postId}/comments")
	public CommentDto createComment(CommentDto commentDto,@PathVariable("postId") int postId) {
		
		return commentService.createComment(commentDto, postId);
	}
	
	@DeleteMapping("comments/{commentId}")
	public void delete(@PathVariable("commentId")Integer commentId) {
		commentService.delete(commentId);
	}
	
}
