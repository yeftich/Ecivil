package com.ecivil.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.User;

public interface UserService {
	public User getUser(String login) throws DataAccessException;

	public void saveUser(User user) throws DataAccessException;

	public List<User> getAllUsers() throws DataAccessException;

	public User findUserById(int userId) throws DataAccessException;  
}
