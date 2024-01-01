package com.blog.service;

import com.blog.payload.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto,int postId);
	
	void delete(Integer commentId);
}
