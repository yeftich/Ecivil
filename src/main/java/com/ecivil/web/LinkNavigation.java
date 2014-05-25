package com.ecivil.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ecivil.model.enums.EEventStatus;
import com.ecivil.model.event.Accident;
import com.ecivil.model.event.Danger;
import com.ecivil.model.event.Emergency;
import com.ecivil.service.AccidentService;
import com.ecivil.service.DangerService;

@Controller
public class LinkNavigation {

	private final DangerService dangerService;
	private final AccidentService accidentService;

	
	@Autowired
	public LinkNavigation(DangerService dangerService, AccidentService accidentService) {
		this.dangerService = dangerService;
		this.accidentService = accidentService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homePage(Principal principal) {
		ModelAndView modelAndView = new ModelAndView(
				"redirect:/index");
/*		if (principal != null && principal.getName() != null) {
			modelAndView.addObject("userLogin", principal.getName());
		} else {
			modelAndView.addObject("userLogin", "ANONYMOUS_USER");
		}

		Map<DateTime, Emergency> sortedMap = new TreeMap<DateTime, Emergency>(
				Collections.reverseOrder());

		List<Accident> accidents = (List<Accident>) this.accidentService
				.getAccidentsByFreshness(EEventStatus.Active);
		List<Danger> dangers = (List<Danger>) this.dangerService
				.getDangersByFreshness(EEventStatus.Active);

		for (Danger danger : dangers) {
			DateTime date = null;
			int i = 1;
			while (sortedMap.containsKey(danger.getCreatedDateTime())) {
				date = danger.getCreatedDateTime().plusMillis(i++);
				danger.setCreatedDateTime(date.plusMillis(1));
			}
			sortedMap.put(danger.getCreatedDateTime(), danger);
		}
		for (Accident accident : accidents) {
			DateTime date = null;
			int i = 1;
			while (sortedMap.containsKey(accident.getCreatedDateTime())) {
				date = accident.getCreatedDateTime().plusMillis(i++);
				accident.setCreatedDateTime(date.plusMillis(1));
			}
			sortedMap.put(accident.getCreatedDateTime(), accident);
		}

		List<Emergency> itemList = new ArrayList<Emergency>(sortedMap.values());

		modelAndView.addObject("itemList", itemList);*/
		return modelAndView;
	}

}