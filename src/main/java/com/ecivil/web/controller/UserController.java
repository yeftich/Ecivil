package com.ecivil.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecivil.model.Location;
import com.ecivil.model.team.Team;
import com.ecivil.model.user.User;
import com.ecivil.model.user.UserTeam;
import com.ecivil.service.RoleService;
import com.ecivil.service.TeamService;
import com.ecivil.service.UserService;
import com.ecivil.util.ConstantUtil;

@Controller
@SessionAttributes({ "user", "aUserTeam" })
public class UserController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	private final UserService userService;

	private final TeamService teamService;
	
	private final RoleService roleService;

	@Autowired
	public UserController(UserService userService, TeamService teamService, RoleService roleService) {
		this.userService = userService;
		this.teamService = teamService;
		this.roleService = roleService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("teamList")
	public Collection<Team> populateTeamList() {
		return this.teamService.getAllTeams();
	}

	@RequestMapping(value = "/users/new", method = RequestMethod.GET)
	public String initCreationForm(Map<String, Object> model) {
		logger.debug("initCreationForm for new user");

		User user = new User();
		model.put("user", user);
		return "users/createOrUpdateUserForm";
	}

	@RequestMapping(value = "/users/new", method = RequestMethod.POST)
	public String processCreationForm(@ModelAttribute("user") User user, Map<String, Object> model) {
		logger.debug("proccessCreationForm for new user");
		
		User dbUser = this.userService.getUser(user.getLogin());
		
		if (dbUser != null) {
			user.setLogin(null);
			model.put("user", user);
			model.put("message", "User name already exists!");
			return "users/createOrUpdateUserForm";
		} else {
			this.userService.createUser(user);
			
			return "redirect:/users/" + user.getId();
		}
	}

	// @RequestMapping(value = "/users/find", method = RequestMethod.GET)
	// public String initFindForm(Map<String, Object> model) {
	// model.put("user", new User());
	// return "users/findUsers";
	// }

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView listAllUsers() {
		ModelAndView modelAndView = new ModelAndView("users/usersList");
		modelAndView.addObject("itemList",
				(List<User>) this.userService.getAllUsers());
		return modelAndView;
	}

	// @RequestMapping(value = "/users/findUser", method = RequestMethod.GET)
	// public String processFindForm(@ModelAttribute("user") User user,
	// BindingResult result, Map<String, Object> model) {
	// List<User> results = new ArrayList<User>();
	//
	// // allow parameterless GET request for /users to return all records
	// if (user.getLogin() == null) {
	// results = (List<User>) this.userService.getAllUsers();
	// if (results.isEmpty()) {
	// // no users found
	// model.put("itemList", results);
	// return "users/usersList";
	// }
	// } else {
	// // find user by login
	// User userFromDB = this.userService.getUser(user.getLogin());
	// if (userFromDB != null) {
	// results.add(userFromDB);
	// } else {
	// // Searched user not found
	// result.rejectValue("login", "notFound", "not found");
	// return "users/findUsers";
	// }
	// }
	//
	// if (results.size() > 1) {
	// // multiple users found
	// model.put("itemList", results);
	// return "users/usersList";
	// } else {
	// // 1 user found
	// user = results.get(0);
	// return "redirect:/users/" + user.getId();
	// }
	// }

	@RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.GET)
	public String initUpdateUserForm(@PathVariable("userId") int userId,
			Model model) {
		User user = this.userService.findUserById(userId);
		model.addAttribute("user", user);
		return "users/createOrUpdateUserForm";
	}

	@RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.PUT)
	public String processUpdateUserForm(@PathVariable("userId") int userId,
			@ModelAttribute("user") @Valid User user, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "users/createOrUpdateUserForm";
		} else {
			user.setId(userId);
			this.userService.updateUser(user);
			// this.userService.createUser(user);
			status.setComplete();
			return "redirect:/users/{userId}";
		}
	}

	@RequestMapping(value = "/users/{userId}/delete", method = RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable Integer userId) {
		ModelAndView modelAndView = new ModelAndView("redirect:/users");
		userService.deleteUser(userId);
		modelAndView.addObject("itemList",
				(List<User>) this.userService.getAllUsers());
		String message = "User was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	/**
	 * Custom handler for displaying an user.
	 * 
	 * @param userId
	 *            the ID of the user to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@RequestMapping("/users/{userId}")
	public ModelAndView showUser(@PathVariable("userId") int userId) {
		ModelAndView mav = new ModelAndView("users/userDetails");
		mav.addObject("user", this.userService.findUserById(userId));
		return mav;
	}

	// showing the page where team administrator can mange the members of his
	// team
	@RequestMapping(value = "/users/manage", method = RequestMethod.GET)
	public String manageUserForm(Map<String, Object> model) {
		logger.debug("initManageForm for menaging users");

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String login = auth.getName(); // get logged in username
		logger.debug("User " + login
				+ " is trying to manage users in its teams");

		List<Team> teams = teamService.getManagedTeams(login);
		List<UserTeam> userTeams = new ArrayList<UserTeam>();
		for (Team team : teams) {
			userTeams.addAll(team.getUserTeams());
		}
		model.put("userTeams", userTeams);
		return "users/manageUsers";
	}

	// verifying the user as a team member
	@RequestMapping(value = "/users/{userId}/teams/{teamId}/verify")
	public String verifyMember(@PathVariable("userId") int userId,
			@PathVariable("teamId") int teamId) {

		userService.verifyUser(userId, teamId);
		logger.debug("USER VERIFIED SUCCESSFULLY!");

		return "redirect:/users/manage";

	}
	
	// verifying user with email link clicked by user
	@RequestMapping(value = "/confirm/{uuid}/email")
	public ModelAndView confirmEmail(@PathVariable("uuid") String uuid) {
		ModelAndView model = new ModelAndView("email-confirm");
		logger.debug("UUID is " + uuid);
		User user = this.userService.getUserByUuid(uuid);
		StringBuilder message = new StringBuilder();
		message.append("Your email address ");
		if(user != null ) {
			message.append("is confirmed successfully! You can use ecivil application as a member.");
			user.setVerified(true);
			user.setRole(this.roleService.getRole(ConstantUtil.ROLE_MEMBER));
			this.userService.updateUser(user);
			logger.debug("USERS EMAIL CONFIRMED");
		}
		else {
			message.append("cant be confirmed!");
			logger.debug("USERS EMAIL NOT CONFIRMED");
		}
		model.addObject("message", message.toString());
		return model;
	}
	
	// adding a responsibility to a team member
	@RequestMapping(value = "/users/{userId}/teams/{teamId}/responsibility/add", method = RequestMethod.GET)
	public String initResponsibilityForm(@PathVariable("userId") int userId,
			@PathVariable("teamId") int teamId, Model model) {
		logger.debug("initResponsibilityForm for adding responsibility to a team member");

		User user = this.userService.findUserById(userId);
		UserTeam uTeam = null;
		for (UserTeam userTeam : user.getUserTeams()) {
			if (userTeam.getTeam().getId() == teamId) {
				uTeam = userTeam;
				break;
			}
		}
		model.addAttribute("aUserTeam", uTeam);

		return "users/addResponsibilityForm";
	}

	@RequestMapping(value = "/users/responsibility/add", method = RequestMethod.POST)
	public String processResponsibilityForm(
			@ModelAttribute("aUserTeam") UserTeam userteam, BindingResult result) {

		if (result.hasErrors()) {
			return "users/addResponsibilityForm";
		} else {
			logger.debug("userId =  " + userteam.getUser().getId()
					+ " teamId = " + userteam.getTeam().getId()
					+ " responsibility = " + userteam.getResponsibility());

			userService.addUserResponsibility(userteam.getUser().getId(),
					userteam.getTeam().getId(), userteam.getResponsibility());
		}

		logger.debug("RESPONSIBILITY ADDED SUCCESSFULLY!");

		return "redirect:/users/manage";
	}

	// removing member from the team and setting his role to MEMBER in case that
	// he is not involved in any other team
	@RequestMapping(value = "/users/{userId}/teams/{teamId}/remove")
	public String removeMember(@PathVariable("userId") int userId,
			@PathVariable("teamId") int teamId) {

		userService.removeUserFromTeam(userId, teamId);

		logger.debug("USER REMOVED FROM TEAM SUCCESSFULLY!");

		return "redirect:/users/manage";

	}
	
	//saving a current location of user that is logged in 
	// called with ajax
	 @RequestMapping(value="/users/{userName}/locations/lat/{lat}/lon/{lon}/save", 
			 method=RequestMethod.GET)
	 public@ResponseBody
	  String saveUsersCurrentLocation(@PathVariable String userName, @PathVariable double lat, @PathVariable double lon) {
		 logger.debug("SAVING LOCATION " + lat + ", " + lon + " FOR USER " + userName );
		
		 this.userService.saveUserLocation(userName, lat, lon);
		
		 logger.debug("LOCATION SUCCESSFULLY SAVED");
		 
		 return "LOCATION OF USER " + userName + " SAVED";
	 }
	 
	 
	
}
