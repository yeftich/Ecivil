package com.ecivil.service;

import java.util.List;

import com.ecivil.model.User;

public interface UserService {
	public User getUser(String login);

	public void saveUser(User user);

	public List<User> getAllUsers();

	public User findUserById(int userId);  
}
