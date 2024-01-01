package com.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.payload.CategoryDto;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.payload.UserDto;
import com.blog.service.FileService;
import com.blog.service.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public PostDto createPost(@Valid @RequestBody PostDto postDto,@PathVariable int userId,@PathVariable int categoryId)
	{
		return postService.createPost(postDto, userId, categoryId);
	}
	
	@GetMapping("/user/{userId}/posts")
	public List<PostDto> getByUser(@PathVariable int userId)
	{
		return postService.getPostByUser(userId);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public List<PostDto> getByCategory(@PathVariable int categoryId)
	{
		return postService.getPostByCategory(categoryId);
	}
	
	//get all posts
	@GetMapping("/posts")
	public PostResponse getAll(
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "10-",required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = "postID",required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "ASC",required = false)String sortDir
			)
	{
		return postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
	}
	
	//get  posts by id
	@GetMapping("/{postId}/posts")
	public PostDto getPostByID(@PathVariable int postId)
	{
		return postService.getPostById(postId);
	}
	
	//update
	@PutMapping("/{postId}/posts")
	public PostDto updatePost(@Valid @RequestBody PostDto postDto,@PathVariable int postId)
	{
		return postService.updatePost(postDto, postId);
	}	
	
	//delete
	@DeleteMapping("/{postId}/posts")
	public PostDto deletePost(@PathVariable int postId)
	{
		return postService.getPostById(postId);
	}
	
	//search
	@GetMapping("/posts/search/{keywords}")
	public List<PostDto> searchPost(@PathVariable String keywords)
	{
		return postService.searchPosts(keywords);
	}
	
	//upload image
	@PostMapping("/post/image/upload/{postId}")
	public PostDto uploadImage(@RequestParam("image")MultipartFile file ,@PathVariable("postId") int postId) throws IOException
	{
		String fileName = fileService.uploadImage(path, file);
			
		PostDto postDto = postService.getPostById(postId);
		postDto.setImageName(fileName);
		PostDto postDtoRes = postService.updatePost(postDto, postId);
		return postDtoRes;
	}
	
	//download image
		@GetMapping(value="/profiles/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
		public void downloadImage(HttpServletResponse response,
				@PathVariable("imageName") String imageName) throws IOException
		{
			InputStream resource = fileService.getResource(path, imageName);
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			StreamUtils.copy(resource, response.getOutputStream());
		}
}
