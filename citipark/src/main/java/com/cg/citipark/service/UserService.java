package com.cg.citipark.service;

import org.springframework.stereotype.Service;

import com.cg.citipark.beans.User;

@Service
public interface UserService {
	public User addUser(User user);
	public User getUserByEmail(String email);
	 public User getUserById(int userId);
	public boolean deleteUserById(int userId);
	public User updateUser(User user);
	

}
