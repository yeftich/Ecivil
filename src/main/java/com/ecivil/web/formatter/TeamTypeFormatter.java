package com.ecivil.web.formatter;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import com.ecivil.model.team.TeamType;
import com.ecivil.service.TeamService;

/**
 * @author Milan 
 *	14 мая 2014 г.  -  13:23:58
 *
 */
public class TeamTypeFormatter implements Formatter<TeamType>{
	private final TeamService teamService;


    @Autowired
    public TeamTypeFormatter(TeamService teamService) {
        this.teamService = teamService;
    }
    
	@Override
	public String print(TeamType teamType, Locale locale) {
		return teamType.getName();
	}

	@Override
	public TeamType parse(String text, Locale locale) throws ParseException {
		   List<TeamType> teamTypes = this.teamService.getAllTeamTypes();
	        for (TeamType type : teamTypes) {
	            if (type.getName().equals(text)) {
	                return type;
	            }
	        }
	        throw new ParseException("type not found: " + text, 0);
	}

}
