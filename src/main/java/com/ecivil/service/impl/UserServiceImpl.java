package com.ecivil.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecivil.model.Role;
import com.ecivil.model.user.User;
import com.ecivil.model.user.UserTeam;
import com.ecivil.repository.RoleDao;
import com.ecivil.repository.TeamDao;
import com.ecivil.repository.UserDao;
import com.ecivil.service.UserService;
import com.ecivil.util.ConstantUtil;

@Service
public class UserServiceImpl implements UserService {

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
		if (user.getRole() == null) {
			Role role = null;
			if (user.getUserTeams().isEmpty()) {
				role = roleDao.getDefaultRole();
			} else {
				for (UserTeam userTeam : user.getUserTeams()) {
					if (userTeam.getTeam().getType().isInstitutional()) {
						role = roleDao.getRole(ConstantUtil.ROLE_INSTITUTION);
						break;
					}
				}
				if (role == null) {
					role = roleDao.getRole(ConstantUtil.ROLE_VOLUNTEER);
				}
			}
			user.setRole(role);
		}
		userDao.insertUser(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getAllUsers() throws DataAccessException {
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
		if (user.getId() != null) {
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

	@Override
	@Transactional
	public void addUserResponsibility(int userId, int teamId, String responsStr)
			throws DataAccessException {
		userDao.addUserResponsibility(userId, teamId, responsStr);
	}

	@Override
	@Transactional
	public void removeUserFromTeam(int userId, int teamId)
			throws DataAccessException {

		User user = userDao.findUserById(userId);

		if (user.getUserTeams().size() == 1) {
			// user is not member of other teams
			// we should change his role to ROLE_MEMBER
			user.setRole(roleDao.getRole(ConstantUtil.ROLE_MEMBER));
			userDao.updateUser(user);
		}

		userDao.removeUserFromTeam(userId, teamId);
	}

	@Override
	public User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		return getUser(login);
	}

}
