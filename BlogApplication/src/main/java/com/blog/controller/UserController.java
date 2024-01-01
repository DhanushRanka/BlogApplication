package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.UserDto;
import com.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;
	//create user	
	@PostMapping("/")
	public UserDto createUser(@Valid @RequestBody UserDto userDto)
	{
		return userService.createUser(userDto);
	}
	
	//update
	@PutMapping("/{userId}")
	public UserDto updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId")int userId)
	{
		return userService.updateUser(userDto,userId);
	}
	
	//delete
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId")int userId)
	{
		userService.deleteUser(userId);
	}
	
	//getAll
	@GetMapping("/")
	public List<UserDto> getAllUser()
	{
		return userService.getAllUser();
	}
	
	//getSingleUser
	@GetMapping("/{userId}")
	public UserDto getSingleUser(@PathVariable("userId") int userId)
	{
		return userService.getUserById(userId);
	}
	
}
