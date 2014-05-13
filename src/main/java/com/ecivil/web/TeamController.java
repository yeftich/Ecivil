package com.ecivil.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ecivil.model.Team;
import com.ecivil.service.RoleService;
import com.ecivil.service.TeamService;

@Controller
public class TeamController {
	private static final Logger logger = LoggerFactory
			.getLogger(TeamController.class);

	private final TeamService teamService;

	@Autowired
	public TeamController(TeamService teamService, RoleService roleService) {
		this.teamService = teamService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(value = "/teams/new", method = RequestMethod.GET)
	public String initCreationForm(Map<String, Object> model) {
		logger.debug("initCreationForm for new team");

		Team team = new Team();
		model.put("team", team);
		return "teams/createOrUpdateTeamForm";
	}

	@RequestMapping(value = "/teams/new", method = RequestMethod.POST)
	public String processCreationForm(@Valid Team team, BindingResult result,
			SessionStatus status) {
		logger.debug("proccessCreationForm for new team");

		if (result.hasErrors()) {
			return "teams/createOrUpdateTeamForm";
		} else {
			this.teamService.saveTeam(team);
			status.setComplete();
			return "redirect:/teams/" + team.getId();
		}
	}

	@RequestMapping(value = "/teams/find", method = RequestMethod.GET)
	public String initFindForm(Map<String, Object> model) {
		model.put("team", new Team());
		return "teams/findTeams";
	}

	@RequestMapping(value = "/teams", method = RequestMethod.GET)
	public String processFindForm(Team team, BindingResult result,
			Map<String, Object> model) {
		List<Team> results = new ArrayList<Team>();

		// allow parameterless GET request for /teams to return all records
		if (team.getName() == null) {
			results = (List<Team>) this.teamService.getAllTeams();
		} else {
			// find team by name
			Team teamFromDB = this.teamService.getTeamByName(team.getName());
			if (teamFromDB != null) {
				results.add(teamFromDB);
			}
			else {
				result.rejectValue("name", "notFound", "not found");
				return "teams/findTeams";
			}
		}
		model.put("selections", results);
		return "teams/teamsList";
	}

	@RequestMapping(value = "/teams/{teamId}/edit", method = RequestMethod.GET)
	public String initUpdateTeamForm(@PathVariable("teamId") int teamId,
			Model model) {
		Team team = this.teamService.findTeamById(teamId);
		model.addAttribute(team);
		return "teams/createOrUpdateTeamForm";
	}

	@RequestMapping(value = "/teams/{teamId}/edit", method = RequestMethod.PUT)
	public String processUpdateTeamForm(Team team, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "teams/createOrUpdateTeamForm";
		} else {
			this.teamService.saveTeam(team);
			status.setComplete();
			return "redirect:/teams/{teamId}";
		}
	}

	/**
	 * Custom handler for displaying an team.
	 * 
	 * @param teamId
	 *            the ID of the team to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@RequestMapping("/teams/{teamId}")
	public ModelAndView showTeam(@PathVariable("teamId") int teamId) {
		ModelAndView mav = new ModelAndView("teams/teamDetails");
		mav.addObject(this.teamService.findTeamById(teamId));
		return mav;
	}

}
