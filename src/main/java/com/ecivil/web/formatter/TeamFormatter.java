package com.ecivil.web.formatter;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import com.ecivil.model.team.Team;
import com.ecivil.service.TeamService;

/**
 * @author Milan 
 *	15 мая 2014 г.  -  13:51:31
 *
 */
public class TeamFormatter implements Formatter<Team>{
	private final TeamService teamService;

	@Autowired
	public TeamFormatter(TeamService teamService) {
		this.teamService = teamService;
	}

	@Override
	public String print(Team team, Locale locale) {
		return team.getName() + ((team.getType() != null) ? "  - " + team.getType().getName().toUpperCase() : "") ;
	}

	@Override
	public Team parse(String text, Locale locale) throws ParseException {
		List<Team> teams = this.teamService.getAllTeams();
		for (Team team : teams) {
			String type = team.getName() + ( (team.getType() != null) ? "  - " + team.getType().getName().toUpperCase() : "");
			if ( type.equals(text)) {
				return team;
			}
		}

		throw new ParseException("team not found: " + text, 0);
	}
}
