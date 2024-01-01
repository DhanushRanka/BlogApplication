package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.payload.UserDto;
@Service
public interface UserService {
	
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto user,int userId);

	UserDto getUserById(int userId);
	
	List<UserDto> getAllUser();
	
	void deleteUser(int userId);
}
