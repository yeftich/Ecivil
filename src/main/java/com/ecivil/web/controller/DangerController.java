package com.ecivil.web.controller;

import java.util.Collection;
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

import com.ecivil.model.enums.EAccidentType;
import com.ecivil.model.enums.EDangerType;
import com.ecivil.model.event.Danger;
import com.ecivil.model.event.Danger;
import com.ecivil.model.user.User;
import com.ecivil.service.DangerService;
import com.ecivil.service.UserService;

/**
 * @author Milan 
 *	17 мая 2014 г.  -  16:31:11
 *
 */
@Controller
@SessionAttributes({"danger"})
public class DangerController {

	private static final Logger logger = LoggerFactory
			.getLogger(DangerController.class);

	private final DangerService dangerService;
	private final UserService userService;

	@Autowired
	public DangerController(DangerService dangerService, UserService userService) {
		this.dangerService = dangerService;
		this.userService = userService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("dangerTypes")
	public Map<String, String> populateDangerTypesList() {
		return EDangerType.getSelections() ;
	}
	
	@ModelAttribute("defaultType")
	public String populateDefaultType() {
		return EDangerType.defaultInGreek();
	}
	
	@RequestMapping(value = "/dangers/new", method = RequestMethod.GET)
	public String initCreationDangerForm(Map<String, Object> model) {
		logger.debug("initCreationDangerForm for new danger");

		Danger danger = new Danger();
		model.put("danger", danger);
		return "dangers/createOrUpdateDangerForm";
	}
	
	@RequestMapping(value = "/dangers/new", method = RequestMethod.POST)
	public String processCreationDangerForm(@ModelAttribute("danger") Danger danger, BindingResult result,
			SessionStatus status) {
		logger.debug("proccessCreationForm for new danger");

		if (result.hasErrors()) {
			return "dangers/createOrUpdateDangerForm";
		} else {
			User owner = userService.getLoggedInUser();
			danger.setOwner(owner);
			this.dangerService.saveDanger(danger);
			status.setComplete();
			return "redirect:/dangers/" + danger.getId();
		}
	}

	@RequestMapping(value = "/dangers", method = RequestMethod.GET)
	public ModelAndView listAllDangers() {
		ModelAndView modelAndView = new ModelAndView("dangers/dangersList");
		modelAndView.addObject("itemList",	(List<Danger>) this.dangerService.getAllDangers());
		return modelAndView;
	}

	@RequestMapping(value = "/dangers/{dangerId}/edit", method = RequestMethod.GET)
	public String initUpdateDangerForm(@PathVariable("dangerId") int dangerId,
			Model model) {
		Danger danger = this.dangerService.findDangerById(dangerId);
		model.addAttribute("danger", danger);
		return "dangers/createOrUpdateDangerForm";
	}

	@RequestMapping(value = "/dangers/{dangerId}/edit", method = RequestMethod.PUT)
	public String processUpdateDangerForm(@PathVariable("dangerId") int dangerId, @ModelAttribute("danger") Danger danger, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "dangers/createOrUpdateDangerForm";
		} else {
			if(danger.getId() == null) {
				danger.setId(dangerId);
			}
			this.dangerService.saveDanger(danger);
			status.setComplete();
			return "redirect:/dangers/{dangerId}";
		}
	}

	@RequestMapping("/dangers/{dangerId}")
	public ModelAndView showDanger(@PathVariable("dangerId") int dangerId) {
		ModelAndView mav = new ModelAndView("dangers/dangerDetails");
		mav.addObject(this.dangerService.findDangerById(dangerId));
		return mav;
	}
	

}
