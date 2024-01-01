package com.blog.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.model.Category;
import com.blog.payload.CategoryDto;
import com.blog.repo.CategoryRepo;
import com.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		Category savedCategory = categoryRepo.save(category);
		return modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category category = modelMapper.map(categoryDto, Category.class);
		Optional<Category> opCategory = categoryRepo.findById(categoryId);
		
		opCategory.get().setCategoryTitle(categoryDto.getCategoryTitle());
		opCategory.get().setCategoryDescription(categoryDto.getCategoryDescription());
		Category savedCategory = categoryRepo.save(opCategory.get());
		return modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {
		Optional<Category> opCategory = categoryRepo.findById(categoryId);
		Category category = opCategory.get();
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepo.findAll();
		List<CategoryDto> categoryDto = categories.stream().map(category->modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return categoryDto;
	}

	@Override
	public void deleteCategory(int categoryId) {
		Optional<Category> opCategory = categoryRepo.findById(categoryId);
		Category category = opCategory.get();
		categoryRepo.delete(category);
	}

}
