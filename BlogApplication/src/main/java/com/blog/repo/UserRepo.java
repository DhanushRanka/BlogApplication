package com.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
