package com.ecivil.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.Team;


public interface TeamDao {
	
	public List<Team> getAllTeams() throws DataAccessException;

	public void saveTeam(Team team);

	public Team findTeamById(int teamId);

	public Team getTeamByName(String name);
}
