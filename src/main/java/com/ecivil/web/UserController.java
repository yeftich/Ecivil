package com.ecivil.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecivil.model.User;
import com.ecivil.service.RoleService;
import com.ecivil.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	private final RoleService roleService;

	@Autowired
	public UserController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}
	
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

	@RequestMapping(value = "/users/new", method = RequestMethod.GET)
	public String initCreationForm(Map<String, Object> model) {
		logger.debug("initCreationForm for new user");
		
		User user = new User();
		model.put("user", user);
		return "users/createOrUpdateUserForm";
	}

	@RequestMapping(value = "/users/new", method = RequestMethod.POST)
	public String processCreationForm(@Valid User user, BindingResult result,
			SessionStatus status) {
		logger.debug("proccessCreationForm for new user");
		
		if (result.hasErrors()) {
			return "users/createOrUpdateUserForm";
		} else {
			if(user.getRole() == null) {
				user.setRole(roleService.getDefaultRole());
			}
			
			this.userService.saveUser(user);
			status.setComplete();
			return "redirect:/users/" + user.getId();
		}
	}

	@RequestMapping(value = "/users/find", method = RequestMethod.GET)
	public String initFindForm(Map<String, Object> model) {
		model.put("user", new User());
		return "users/findUsers";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String processFindForm(User user, BindingResult result,
			Map<String, Object> model) {
		List<User> results = new ArrayList<User>();

		// allow parameterless GET request for /users to return all records
		if (user.getLogin() == null) {
			results = (List<User>) this.userService.getAllUsers();
			if (results.isEmpty()) {
				// no users found
				model.put("selections", results);
				return "users/usersList";
			}
		} else {
			// find user by login
			User userFromDB = this.userService.getUser(user.getLogin());
			if (userFromDB != null) {
				results.add(userFromDB);
			}
			else {
				// Searched user not found
				result.rejectValue("login", "notFound", "not found");
				return "users/findUsers";
			}
		}

		if (results.size() > 1) {
			// multiple users found
			model.put("selections", results);
			return "users/usersList";
		} else {
			// 1 user found
			user = results.get(0);
			return "redirect:/users/" + user.getId();
		}
	}

	@RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.GET)
	public String initUpdateUserForm(@PathVariable("userId") int userId,
			Model model) {
		User user = this.userService.findUserById(userId);
		model.addAttribute(user);
		return "users/createOrUpdateUserForm";
	}

	@RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.PUT)
	public String processUpdateUserForm(User user,
			BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return "users/createOrUpdateUserForm";
		} else {
			this.userService.saveUser(user);
			status.setComplete();
			return "redirect:/users/{userId}";
		}
	}
	
    /**
     * Custom handler for displaying an user.
     *
     * @param userId the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/users/{userId}")
    public ModelAndView showUser(@PathVariable("userId") int userId) {
        ModelAndView mav = new ModelAndView("users/userDetails");
        mav.addObject(this.userService.findUserById(userId));
        return mav;
    }
}
