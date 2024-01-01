package com.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.model.Category;
import com.blog.model.Post;
import com.blog.model.User;
import com.blog.payload.CategoryDto;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.payload.UserDto;
import com.blog.repo.CategoryRepo;
import com.blog.repo.PostRepo;
import com.blog.repo.UserRepo;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	PostRepo postRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		Post post = modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddDate(new Date());
		Optional<User> opUser = userRepo.findById(userId);
		Optional<Category> opCategory = categoryRepo.findById(categoryId);
		UserDto userDto = modelMapper.map(opUser.get(), UserDto.class);
		CategoryDto categoryDto = modelMapper.map(opCategory.get(), CategoryDto.class);
		post.setUser(opUser.get());
		post.setCategory(opCategory.get());
		
		Post postSaved = postRepo.save(post);
		
		return modelMapper.map(postSaved, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		
		Post post = modelMapper.map(postDto, Post.class);
		Optional<Post> postOp = postRepo.findById(postId);
		postOp.get().setTitle(post.getTitle());
		postOp.get().setCategory(post.getCategory());
		Post postSaved = postRepo.save(post);
		
		return modelMapper.map(postSaved, PostDto.class);
	}

	@Override
	public PostDto getPostById(int postId) {
		Post post = postRepo.findById(postId).get();
//		List<PostDto> postDtos = posts.stream().map(post->modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
//		int pageSize=5;
//		int pageNumber=2;
		Sort sort=null;
		
		if(sortDir.equalsIgnoreCase("asc"))
			sort=Sort.by(sortBy).ascending();
		else
			sort=Sort.by(sortBy).descending();
		Pageable p=PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> pageOfPost = postRepo.findAll(p);
		 
		List<Post> posts=pageOfPost.getContent();
		List<PostDto> postDtos = posts.stream().map(post->modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pageNumber);
		postResponse.setPageSize(pageSize);
		postResponse.setTotalElements(pageOfPost.getTotalElements());
		postResponse.setTotlPages(pageOfPost.getTotalPages());
		postResponse.setLastPage(pageOfPost.isLast());
		
		return postResponse;
	}

	@Override
	public void deletePost(int postId) {
		Post post = postRepo.findById(postId).get();

		postRepo.delete(post);
		
	}

	@Override
	public List<PostDto> getPostByCategory(Integer category) {
		Category category1=categoryRepo.findById(category).get();
		List<Post> posts = postRepo.findByCategory(category1);
		
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer user) {
	
		User user1=userRepo.findById(user).get();
		List<Post> posts = postRepo.findByUser(user1);
		
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = postRepo.findByTitleContaining(keyword);
		
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

}
