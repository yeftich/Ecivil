package com.ecivil.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.user.User;

public interface UserDao {
	public User getUser(String login) throws DataAccessException;

	public void insertUser(User user) throws DataAccessException;  
	
	public List<User> getAllUsers() throws DataAccessException;

	public User findUserById(int userId) throws DataAccessException;
	
	public void updateUser(User user) throws DataAccessException;

	public void deleteUser(int userId) throws DataAccessException;

	public void verifyUser(int userId, int teamId) throws DataAccessException;

	public void addUserResponsibility(int userId, int teamId, String responsStr) throws DataAccessException;

	public void removeUserFromTeam(int userId, int teamId) throws DataAccessException;

	
}
