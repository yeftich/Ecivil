package com.ecivil.repository;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.User;

public interface UserDao {
	public User getUser(String login) throws DataAccessException;  
}
