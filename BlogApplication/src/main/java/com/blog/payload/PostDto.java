package com.blog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog.model.Category;
import com.blog.model.Comment;
import com.blog.model.User;


public class PostDto {
	
	private String title; 
	
	private String content; 
	
	private String imageName; 
	
	private Date addDate;
	
	private CategoryDto category;
	
	private Set<CommentDto>comments;
	
	private UserDto user;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

//	public Category getCategory() {
//		return category;
//	}
//
//	public void setCategory(Category category) {
//		this.category = category;
//	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategoryDto(CategoryDto category) {
		this.category = category;
	}
	
	public Set<CommentDto> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
	
	public UserDto getUser() {
		return user;
	}

	public void setUserDto(UserDto user) {
		this.user = user;
	}
	
}
