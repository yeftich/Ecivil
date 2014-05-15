package com.ecivil.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecivil.model.user.User;
import com.ecivil.repository.RoleDao;
import com.ecivil.repository.TeamDao;
import com.ecivil.repository.UserDao;
import com.ecivil.service.UserService;

@Service  
public class UserServiceImpl implements UserService{

	@Autowired  
	 private UserDao userDao; 
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private TeamDao teamDao;
	
	@Override
	@Transactional(readOnly = true)
	public User getUser(String login) throws DataAccessException {
		return userDao.getUser(login);
	}

	@Override
	@Transactional
	public void createUser(User user) throws DataAccessException {
		if(user.getRole() == null ) {
			user.setRole(roleDao.getDefaultRole());
		}
		userDao.insertUser(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getAllUsers() throws DataAccessException  {
		return (List<User>) userDao.getAllUsers();
	}

	@Override
	@Transactional
	public User findUserById(int userId) throws DataAccessException {
		return userDao.findUserById(userId);
	}

	@Override
	@Transactional
	public void updateUser(User user) throws DataAccessException {
		if(user.getId() != null) {
			userDao.updateUser(user);
		}
	}

	@Override
	@Transactional
	public void deleteUser(int userId) throws DataAccessException {
			userDao.deleteUser(userId);
	}

	@Override
	@Transactional
	public void verifyUser(int userId, int teamId) throws DataAccessException {
		userDao.verifyUser(userId, teamId);
	}

}
