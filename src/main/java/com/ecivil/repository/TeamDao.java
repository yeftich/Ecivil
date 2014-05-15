package com.ecivil.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.Team;
import com.ecivil.model.TeamType;


public interface TeamDao {
	
	public List<Team> getAllTeams() throws DataAccessException;

	public void saveTeam(Team team) throws DataAccessException;

	public Team findTeamById(int teamId) throws DataAccessException;

	public Team getTeamByName(String name) throws DataAccessException;

	public List<TeamType> getAllTeamTypes() throws DataAccessException;

	public void deleteTeam(int teamId) throws DataAccessException;
}
