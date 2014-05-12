package com.ecivil.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.User;

public interface UserDao {
	public User getUser(String login) throws DataAccessException;

	public void saveUser(User user) throws DataAccessException;  
	
	public List<User> getAllUsers() throws DataAccessException;

	public User findUserById(int userId) throws DataAccessException;
	
}
