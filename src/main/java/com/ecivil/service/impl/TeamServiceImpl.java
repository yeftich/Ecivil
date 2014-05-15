package com.ecivil.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecivil.model.Role;
import com.ecivil.model.Team;
import com.ecivil.model.TeamType;
import com.ecivil.model.User;
import com.ecivil.repository.RoleDao;
import com.ecivil.repository.TeamDao;
import com.ecivil.repository.UserDao;
import com.ecivil.service.TeamService;
import com.ecivil.util.ConstantUtil;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional(readOnly = true)
	public Team getTeamByName(String name) throws DataAccessException {
		return teamDao.getTeamByName(name);
	}

	@Override
	@Transactional
	public void saveTeam(Team team) throws DataAccessException {
		// if we are doing updating, then need to check if administrator has been changed
		if(!team.isNew()) {
			Team oldTeam = teamDao.findTeamById(team.getId());
			if(oldTeam.getAdmin() != null ) {
				if(team.getAdmin() != null && oldTeam.getAdmin().getId() == team.getAdmin().getId()) {
					// do nothing
				}
				else {
					// administrator changed, we change ROLE of old administrator to MEMBER
					User oldAdmin = oldTeam.getAdmin();
					oldAdmin.setRole(roleDao.getDefaultRole());
					userDao.updateUser(oldAdmin);
				}
			}
		}
		User admin = team.getAdmin();
		Role role = null;
		if(team.getType().isInstitutional()) {
			role = roleDao.getRole(ConstantUtil.ROLE_INSTITUTIONS_ADMIN);
		}
		else {
			role = roleDao.getRole(ConstantUtil.ROLE_VOLUNTEERS_ADMIN);
		}
		admin.setRole(role);
		userDao.updateUser(admin);
		teamDao.saveTeam(team);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Team> getAllTeams() throws DataAccessException {
		return (List<Team>) teamDao.getAllTeams();
	}

	@Override
	@Transactional
	public Team findTeamById(int teamId) throws DataAccessException {
		return teamDao.findTeamById(teamId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TeamType> getAllTeamTypes() throws DataAccessException {
		return (List<TeamType>) teamDao.getAllTeamTypes();
	}

	@Override
	@Transactional
	public void deleteTeam(int teamId) throws DataAccessException {
		teamDao.deleteTeam(teamId);
	}

}
