package com.ecivil.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecivil.model.Team;
import com.ecivil.repository.TeamDao;
import com.ecivil.service.TeamService;

@Service  
public class TeamServiceImpl implements TeamService{
	
	@Autowired  
	 private TeamDao teamDao; 

	
	@Override
	@Transactional(readOnly = true)
	public Team getTeamByName(String name) throws DataAccessException {
		return teamDao.getTeamByName(name);
	}

	@Override
	@Transactional
	public void saveTeam(Team team) throws DataAccessException {
		teamDao.saveTeam(team);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Team> getAllTeams() throws DataAccessException{
		return (List<Team>) teamDao.getAllTeams();
	}
	
	@Override
	@Transactional
	public Team findTeamById(int teamId) throws DataAccessException {
		return teamDao.findTeamById(teamId);
	}

}
