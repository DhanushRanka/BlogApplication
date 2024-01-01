package com.blog.payload;

import org.hibernate.validator.constraints.NotEmpty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {
	
	private int id;
	
	@NotEmpty
	private String name;
	
	@Email(message = "Email is not valid")
	@Size(min = 4)
	private String email;
	
	@NotEmpty
	@Size(min = 4,max = 10,message = "The message must be minimum of 4 char and maximum of 10")
	private String password;
	
	@NotNull
	private String about;

	public UserDto() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

}
