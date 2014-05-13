package com.ecivil.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.Team;


public interface TeamService {

	public List<Team> getAllTeams() throws DataAccessException;

	Team getTeamByName(String name) throws DataAccessException;

	Team findTeamById(int teamId) throws DataAccessException;

	void saveTeam(Team team) throws DataAccessException;
}
