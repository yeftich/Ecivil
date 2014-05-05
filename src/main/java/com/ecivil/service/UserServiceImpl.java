package com.ecivil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecivil.model.User;
import com.ecivil.repository.UserDao;

@Service  
@Transactional  
public class UserServiceImpl implements UserService{

	@Autowired  
	 private UserDao userDao; 
	
	@Override
	public User getUser(String login) {
		return userDao.getUser(login);
	}

}
