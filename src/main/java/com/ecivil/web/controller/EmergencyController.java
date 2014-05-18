package com.ecivil.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.wimpi.telnetd.io.terminal.ansi;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ecivil.model.enums.EEventStatus;
import com.ecivil.model.event.Accident;
import com.ecivil.model.event.Action;
import com.ecivil.model.event.Danger;
import com.ecivil.model.event.Emergency;
import com.ecivil.model.event.Event;
import com.ecivil.service.AccidentService;
import com.ecivil.service.DangerService;
import com.ecivil.service.EmergencyService;
import com.ecivil.service.EmergencyService;
import com.ecivil.service.EventService;
import com.ecivil.service.UserService;

/**
 * @author Milan 
 *	18 мая 2014 г.  -  0:34:19
 *
 */
@Controller
@SessionAttributes("emergency")
public class EmergencyController {
	private static final Logger logger = LoggerFactory
			.getLogger(EmergencyController.class);

	private final DangerService dangerService;
	private final EventService eventService;
	private final EmergencyService emergencyService;
	private final AccidentService accidentService;
	
	@Autowired
	public EmergencyController(EmergencyService emergencyService, DangerService dangerService, AccidentService accidentService, EventService eventService) {
		this.emergencyService = emergencyService;
		this.dangerService = dangerService;
		this.eventService = eventService;
		this.accidentService = accidentService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping(value = "/emergencys/{emergencyId}/close", method = RequestMethod.GET)
	public ModelAndView closeEmergency(@PathVariable("emergencyId") int emergencyId) {
		
		Emergency emergency = this.emergencyService.findEmergencyById(emergencyId);
		
		for(Action action : emergency.getActions()) {
			this.eventService.closeEvent(action.getId());
		}
		
		this.eventService.closeEvent(emergency.getId());
		
		logger.debug("EMERGENCY CLOSED --> " + emergency.getFreshness() );
		return new ModelAndView("redirect:/emergencys");
		
	}
	
	@RequestMapping(value = "/emergencys", method = RequestMethod.GET)
	public ModelAndView listAllEmergencys(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("emergencys/emergencysList");
		if(principal != null && principal.getName() != null) {
			modelAndView.addObject("userLogin", principal.getName());
		}
		else {
			modelAndView.addObject("userLogin", "ANONYMOUS_USER");
		}
			
		Map<DateTime, Emergency> sortedMap = new TreeMap<DateTime, Emergency>(Collections.reverseOrder());
		
		List<Accident> accidents = (List<Accident>) this.accidentService.getAccidentsByFreshness(EEventStatus.Active);
		List<Danger> dangers = (List<Danger>) this.dangerService.getDangersByFreshness(EEventStatus.Active);
		
		for (Danger danger : dangers) {
			DateTime date = null;
			int i = 1;
			while(sortedMap.containsKey(danger.getCreatedDateTime())) {
				date = danger.getCreatedDateTime().plusMillis(i++);
				danger.setCreatedDateTime(date.plusMillis(1));
			}
			sortedMap.put(danger.getCreatedDateTime(), danger);
		}
		for(Accident accident : accidents) {
			DateTime date = null;
			int i = 1;
			while(sortedMap.containsKey(accident.getCreatedDateTime())) {
				date = accident.getCreatedDateTime().plusMillis(i++);
				accident.setCreatedDateTime(date.plusMillis(1));
			}
			sortedMap.put(accident.getCreatedDateTime(), accident);
		}
		
		List<Emergency> itemList = new ArrayList<Emergency>(sortedMap.values());
		
		modelAndView.addObject("itemList", itemList);
		return modelAndView;
	}
}