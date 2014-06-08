package com.ecivil.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ecivil.model.event.Accident;
import com.ecivil.model.event.Action;
import com.ecivil.model.event.Event;
import com.ecivil.service.AccidentService;
import com.ecivil.service.ActionService;
import com.ecivil.service.DangerService;
import com.ecivil.service.EmergencyService;
import com.ecivil.service.EventService;

/**
 * @author Milan 
 *	08 июня 2014 г.  -  12:03:16
 *
 */
@Controller
public class EventController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(EventController.class);

	private final EventService eventService;
	private final DangerService dangerService;
	private final ActionService actionService;
	private final AccidentService accidentService;
	
	@Autowired
	public EventController(EventService eventService, DangerService dangerService, AccidentService accidentService,
			ActionService actionService) {
		this.eventService = eventService;
		this.dangerService = dangerService;
		this.actionService = actionService;
		this.accidentService = accidentService;
	}

	
	@RequestMapping(value = "/events/{eventType}/{eventId}/delete", method = RequestMethod.GET)
	public ModelAndView deleteEvent(@PathVariable Integer eventId, @PathVariable String eventType) {
		ModelAndView modelAndView = new ModelAndView("redirect:/" + eventType);

		List<? extends Event> itemList = null;
		
		logger.debug("DELETING event with id " + eventId + " and type " + eventType);
		
		this.eventService.deleteEvent(eventId);
		
		if(eventType.equals("actions")) {
			itemList = this.actionService.getAllActions();
			modelAndView.addObject("itemList",itemList);
			logger.debug("action deleted");
		}
		else if(eventType.equals("accidents")) {
			itemList = this.accidentService.getAllAccidents();
			modelAndView.addObject("itemList",itemList);
			logger.debug("accident deleted");
		}
		else if(eventType.equals("dangers")) {
			itemList = this.dangerService.getAllDangers();
			modelAndView.addObject("itemList",itemList);
			logger.debug("danger deleted");
		}
				
		logger.debug("FINISHED DELETING");
		return modelAndView;
	}
}
