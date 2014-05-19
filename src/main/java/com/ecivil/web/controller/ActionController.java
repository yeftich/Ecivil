package com.ecivil.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ecivil.model.event.Action;
import com.ecivil.model.event.Action;
import com.ecivil.model.event.Emergency;
import com.ecivil.model.user.User;
import com.ecivil.service.ActionService;
import com.ecivil.service.EmergencyService;
import com.ecivil.service.UserService;

/**
 * @author Milan 
 *	19 мая 2014 г.  -  11:15:05
 *
 */
@Controller
@SessionAttributes("action")
public class ActionController {

	private static final Logger logger = LoggerFactory
			.getLogger(ActionController.class);
	
	private final ActionService actionService;
	private final UserService userService;
	private final EmergencyService emergencyService;

	@Autowired
	public ActionController(ActionService actionService, UserService userService, EmergencyService emergencyService) {
		this.actionService = actionService;
		this.userService = userService;
		this.emergencyService = emergencyService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(value = "/emergencys/{emergencyId}/action/new", method = RequestMethod.GET)
	public String initCreationActionForm(Map<String, Object> model) {
		logger.debug("initCreationActionForm for new action");

		Action action = new Action();
		model.put("action", action);
		return "actions/createOrUpdateActionForm";
	}
	
	@RequestMapping(value = "/emergencys/{emergencyId}/action/new", method = RequestMethod.POST)
	public String processCreationActionForm(@ModelAttribute("action") Action action, BindingResult result,
			SessionStatus status, @PathVariable("emergencyId") int emergencyId) {
		logger.debug("proccessCreationForm for new action");

		if (result.hasErrors()) {
			return "actions/createOrUpdateActionForm";
		} else {
			User owner = this.userService.getLoggedInUser();
			action.setOwner(owner);
			Emergency emergency = this.emergencyService.findEmergencyById(emergencyId);
			action.setEmergency(emergency);
			this.actionService.saveAction(action);
			status.setComplete();
			return "redirect:/actions/" + action.getId();
		}
	}

	@RequestMapping(value = "/actions/{actionId}/edit", method = RequestMethod.GET)
	public String initUpdateActionForm(@PathVariable("actionId") int actionId,
			Model model) {
		Action action = this.actionService.findActionById(actionId);
		model.addAttribute("action", action);
		return "actions/createOrUpdateActionForm";
	}

	@RequestMapping(value = "/actions/{actionId}/edit", method = RequestMethod.PUT)
	public String processUpdateActionForm(@PathVariable("actionId") int actionId, @ModelAttribute("action") Action action, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "actions/createOrUpdateActionForm";
		} else {
			if(action.getId() == null) {
				action.setId(actionId);
			}
			this.actionService.saveAction(action);
			status.setComplete();
			return "redirect:/actions/{actionId}";
		}
	}

	@RequestMapping("/actions/{actionId}")
	public ModelAndView showAction(@PathVariable("actionId") int actionId) {
		ModelAndView mav = new ModelAndView("actions/actionDetails");
		mav.addObject(this.actionService.findActionById(actionId));
		return mav;
	}
}
