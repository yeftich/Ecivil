package com.ecivil.web.controller;

import java.util.List;
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

import com.ecivil.model.Location;
import com.ecivil.model.enums.EAccidentType;
import com.ecivil.model.event.Accident;
import com.ecivil.model.user.User;
import com.ecivil.service.AccidentService;
import com.ecivil.service.UserService;

/**
 * @author Milan 16 мая 2014 г. - 19:10:37
 * 
 */
@Controller
@SessionAttributes({ "accident" })
public class AccidentController {

	private static final Logger logger = LoggerFactory
			.getLogger(AccidentController.class);

	private final AccidentService accidentService;
	private final UserService userService;

	@Autowired
	public AccidentController(AccidentService accidentService,
			UserService userService) {
		this.accidentService = accidentService;
		this.userService = userService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("accidentTypes")
	public Map<String, String> populateAccidentTypesList() {
		return EAccidentType.getSelections();
	}

	@ModelAttribute("defaultType")
	public String populateDefaultType() {
		return EAccidentType.defaultInGreek();
	}

	@RequestMapping(value = "/accidents/new", method = RequestMethod.GET)
	public String initCreationAccidentForm(Map<String, Object> model) {
		logger.debug("initCreationAccidentForm for new accident");

		Accident accident = new Accident();
		User owner = userService.getLoggedInUser();
		logger.debug("LOGGED IN USER IS " + owner.getLastName()
				+ " and his location is " + owner.getCurrent_location());
		if (owner.hasValidLocation()) {
			Location loc = new Location();
			loc.setLatitude(owner.getCurrent_location().getLatitude());
			loc.setLongitude(owner.getCurrent_location().getLongitude());
			accident.setLocation(loc);
		}
		accident.setOwner(owner);
		accident.setType(EAccidentType.defaultInGreek());
		Accident savedAccident = this.accidentService.saveAccident(accident);
		model.put("accident", savedAccident);
		model.put("accidentIsNew", new Boolean(true));
		logger.debug("NEW ACCIDENT SAVED with id " + savedAccident.getId()
				+ " Created by " + owner.getLastName()
				+ " and his location is " + owner.getCurrent_location());
		return "accidents/createOrUpdateAccidentForm";
	}

	@RequestMapping(value = "/accidents/new", method = RequestMethod.POST)
	public String processCreationAccidentForm(
			@ModelAttribute("accident") Accident accident,
			BindingResult result, SessionStatus status) {
		logger.debug("proccessCreationForm for new accident");

		if (result.hasErrors()) {
			return "accidents/createOrUpdateAccidentForm";
		} else {
			this.accidentService.saveAccident(accident);
			status.setComplete();
			return "redirect:/accidents/" + accident.getId();
		}
	}

	@RequestMapping(value = "/accidents", method = RequestMethod.GET)
	public ModelAndView listAllAccidents() {
		ModelAndView modelAndView = new ModelAndView("accidents/accidentsList");
		modelAndView.addObject("itemList",
				(List<Accident>) this.accidentService.getAllAccidents());
		return modelAndView;
	}

	@RequestMapping(value = "/accidents/{accidentId}/edit", method = RequestMethod.GET)
	public String initUpdateAccidentForm(
			@PathVariable("accidentId") int accidentId, Model model) {
		Accident accident = this.accidentService.findAccidentById(accidentId);
		model.addAttribute("accident", accident);
		return "accidents/createOrUpdateAccidentForm";
	}

	@RequestMapping(value = "/accidents/{accidentId}/edit", method = RequestMethod.PUT)
	public String processUpdateAccidentForm(
			@PathVariable("accidentId") int accidentId,
			@ModelAttribute("accident") Accident accident,
			BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return "accidents/createOrUpdateAccidentForm";
		} else {
			if (accident.getId() == null) {
				accident.setId(accidentId);
			}
			this.accidentService.saveAccident(accident);
			status.setComplete();
			return "redirect:/accidents/{accidentId}";
		}
	}

	@RequestMapping("/accidents/{accidentId}")
	public ModelAndView showAccident(@PathVariable("accidentId") int accidentId) {
		ModelAndView mav = new ModelAndView("accidents/accidentDetails");
		mav.addObject(this.accidentService.findAccidentById(accidentId));
		return mav;
	}

}
