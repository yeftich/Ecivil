package com.ecivil.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecivil.model.User;
import com.ecivil.repository.UserDao;
import com.ecivil.service.UserService;

@Service  
public class UserServiceImpl implements UserService{

	@Autowired  
	 private UserDao userDao; 
	
	@Override
	@Transactional(readOnly = true)
	public User getUser(String login) throws DataAccessException {
		return userDao.getUser(login);
	}

	@Override
	@Transactional
	public void saveUser(User user) throws DataAccessException {
		userDao.saveUser(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getAllUsers() throws DataAccessException  {
		return (List<User>) userDao.getAllUsers();
	}

	@Override
	@Transactional(readOnly = true)
	public User findUserById(int userId) throws DataAccessException {
		return userDao.findUserById(userId);
	}

}
