package com.blog.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.blog.payload.UserDto;
import com.blog.service.CategoryService;
import com.blog.service.FileService;
import com.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	
	
	//create category
	@PostMapping("/")
	public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		return categoryService.createCategory(categoryDto);
	}
	
	//update
	@PutMapping("/{categoryId}")
	public CategoryDto updateUser(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId")int categoryId)
	{
		return categoryService.updateCategory(categoryDto, categoryId);
	}
	
	//delete
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("categoryId")int categoryId)
	{
		categoryService.deleteCategory(categoryId);
	}
	
	//getAll
	@GetMapping("/")
	public List<CategoryDto> getAllUser()
	{
		return categoryService.getAllCategory();
	}
	
	//getSingleUser
	@GetMapping("/{categoryId}")
	public CategoryDto getSingleUser(@PathVariable("categoryId") int categoryId)
	{
		return categoryService.getCategoryById(categoryId);
	}
	
	
	
}
