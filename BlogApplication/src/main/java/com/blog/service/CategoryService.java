package com.blog.service;

import java.util.List;

import com.blog.payload.CategoryDto;


public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto category,int categoryId);

	CategoryDto getCategoryById(int categoryId);
	
	List<CategoryDto> getAllCategory();
	
	void deleteCategory(int categoryId);
	
}
