package com.ecivil.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.team.Team;
import com.ecivil.model.team.TeamType;


public interface TeamService {

	public List<Team> getAllTeams() throws DataAccessException;

	Team getTeamByName(String name) throws DataAccessException;

	Team findTeamById(int teamId) throws DataAccessException;

	void saveTeam(Team team) throws DataAccessException;

	public List<TeamType> getAllTeamTypes()  throws DataAccessException;

	public void deleteTeam(int teamId)  throws DataAccessException;
	
	public List<Team> getManagedTeams(String login) throws DataAccessException;
	
}
