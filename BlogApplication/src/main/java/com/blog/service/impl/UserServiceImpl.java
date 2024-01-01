package com.blog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.model.User;
import com.blog.payload.UserDto;
import com.blog.repo.UserRepo;
import com.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user=this.dtoToUser(userDto);
		User saveUser = userRepo.save(user);
		
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {
		
		Optional<User> opUser = this.userRepo.findById(userId);
		User user =opUser.get();
		
		User dtoToUser = this.dtoToUser(userDto);
		user.setId(dtoToUser.getId());
		user.setEmail(dtoToUser.getEmail());
		user.setName(dtoToUser.getName());
		user.setPassword(dtoToUser.getPassword());
		
		user = this.userRepo.save(user);
		return this.userToDto(user);
	}

	@Override
	public UserDto getUserById(int userId) {
		Optional<User> opUser = this.userRepo.findById(userId);
		User user =opUser.get();
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {

		List<User>li=this.userRepo.findAll();
		List<UserDto>lis=new ArrayList<>();
		
		for(User u:li)
		{
			lis.add(this.userToDto(u));
		}
		return lis;
	}

	@Override
	public void deleteUser(int userId) {

		Optional<User> opUser = this.userRepo.findById(userId);
		User user =opUser.get();
		
		this.userRepo.delete(user);
	}

	private User dtoToUser(UserDto userDto)
	{
		User user=this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setEmail(userDto.getEmail());
//		user.setName(userDto.getName());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
	
	private UserDto userToDto(User user)
	{
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setEmail(user.getEmail());
//		userDto.setName(user.getName());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}
}
