package com.blog.service;

import java.util.List;

import com.blog.model.Category;
import com.blog.model.User;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;


public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
    PostDto updatePost(PostDto post,int postId);

    PostDto getPostById(int postId);
	
    PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	void deletePost(int postId);
	
	List<PostDto> getPostByCategory(Integer category);
	
	List<PostDto> getPostByUser(Integer user);
	
	List<PostDto> searchPosts(String keyword);
}
