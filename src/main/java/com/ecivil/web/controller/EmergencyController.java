package com.ecivil.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.wimpi.telnetd.io.terminal.ansi;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.ecivil.model.MapInfo;
import com.ecivil.model.enums.EEventStatus;
import com.ecivil.model.event.Accident;
import com.ecivil.model.event.Action;
import com.ecivil.model.event.Danger;
import com.ecivil.model.event.Emergency;
import com.ecivil.model.event.Event;
import com.ecivil.service.AccidentService;
import com.ecivil.service.DangerService;
import com.ecivil.service.EmergencyService;
import com.ecivil.service.EventService;
import com.ecivil.service.UserService;
import com.ecivil.util.DateTimeFormatBuilder;

/**
 * @author Milan 18 мая 2014 г. - 0:34:19
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
	private DateTimeFormatBuilder dateTimeFormatBuilder;

	@Autowired
	public EmergencyController(EmergencyService emergencyService,
			DangerService dangerService, AccidentService accidentService,
			EventService eventService) {
		this.emergencyService = emergencyService;
		this.dangerService = dangerService;
		this.eventService = eventService;
		this.accidentService = accidentService;
		this.dateTimeFormatBuilder = new DateTimeFormatBuilder();
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(value = "/emergencys/{emergencyId}/verify", method = RequestMethod.GET)
	public String verifyEmergency(@PathVariable("emergencyId") int emergencyId) {

		this.eventService.verifyEvent(emergencyId);

		logger.debug("EMERGENCY VERIFIED id = " + emergencyId);

		return "redirect:/index";
	}
	
	@RequestMapping(value = "/emergencys/{emergencyId}/unverify", method = RequestMethod.GET)
	public String unVerifyEmergency(@PathVariable("emergencyId") int emergencyId) {

		this.eventService.unVerifyEvent(emergencyId);
		
		logger.debug("EMERGENCY UNVERIFIED id = " + emergencyId);

		return "redirect:/index";
	}

	@RequestMapping(value = "/emergencys/{emergencyId}/close", method = RequestMethod.GET)
	public ModelAndView closeEmergency(
			@PathVariable("emergencyId") int emergencyId) {

		Emergency emergency = this.emergencyService
				.findEmergencyById(emergencyId);

		for (Action action : emergency.getActions()) {
			this.eventService.closeEvent(action.getId());
		}

		this.eventService.closeEvent(emergency.getId());

		logger.debug("EMERGENCY CLOSED --> " + emergency.getFreshness());
		return new ModelAndView("redirect:/index");

	}

	// AJAX
/*	@RequestMapping(value = "/ajax/messages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Message getMessageJSON() {
		logger.debug("AJAX GET MESSAGES");
		return new Message("This is test " + new Date().toString());
	}
	*/
	
	// AJAX
	// get emergency with all its actions
	@RequestMapping(value = "/ajax/event/{eventId}/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<MapInfo> getEmergencyJSON(@PathVariable("eventId") int eventId) {
		logger.debug("AJAX GET EMERGENCY with id " + eventId);

		Map<Date, Action> sortedMap = new TreeMap<Date, Action>(
				Collections.reverseOrder());
		List<MapInfo> mapInfos = new ArrayList<MapInfo>();
		Emergency emergency = this.emergencyService.findEmergencyById(eventId);
		if (emergency != null) {
			
			DateTime emStartTime = emergency.getCreatedDateTime();
			logger.debug("EMERGENCY: " + emergency.getTextDescription());
			String emType = "";
			String type = "";
			Accident accident = null;
			Danger danger = null;
			if(emergency instanceof Accident) {
				accident = this.accidentService.findAccidentById(emergency.getId());
//				Accident a = (Accident) emergency;
				emType = accident.getType();
				type = "Accident";
			}
			else {
				danger = this.dangerService.findDangerById(emergency.getId());
				emType = danger.getType();
				type = "Danger";
			}
			
/*			String type = (emergency instanceof Accident ? "Accident"
					: "Danger");
*/			

			// used for sending JSON result
			String latitude = "0";
			String longitude = "0";
			DateTime now = new DateTime();
			Period emDuration = new Period(emStartTime, now);
			String started = this.dateTimeFormatBuilder
					.periodToString(emDuration);
			logger.debug("Emergency started " + started);
			if (emergency.hasValidLocation()) {
				latitude = emergency.getLocation().getLatitude().toString();
				longitude = emergency.getLocation().getLongitude().toString();
			}
			MapInfo mapinfo = new MapInfo(emergency.getId().toString(),
					latitude, longitude, type, emType, emergency.getTextDescription(),
					started, emergency.getOwner().getLogin(),
					emergency.getCertification());
			mapInfos.add(mapinfo);

			if(accident != null) {
				for(Action action : accident.getActions()) {
					sortedMap.put(action.getCreatedDateTime().toDate(), action);
				}
			}
			else if(danger != null) {
				for(Action action : danger.getActions()) {
					sortedMap.put(action.getCreatedDateTime().toDate(), action);
				}
			}

			for (Map.Entry<Date, Action> entry : sortedMap.entrySet()) {
				// if it is institution green marker else blue marker
				Action action = entry.getValue();
				String volType = (action.getOwner().getRole().isInstitution() ? "actionInstIcon"
						: "actionVolIcon");
				String helping = "Yes";
				if(action.getOwner().getRole().isMemeber()) {
					helping = "No";
				}
				logger.debug("HELPING: " + helping);
				MapInfo mInfo = new MapInfo(action.getId().toString(), action
						.getOwner().getCurrent_location().getLatitude()
						.toString(), action.getOwner().getCurrent_location()
						.getLongitude().toString(), volType, helping);
				logger.debug("owners location: latlon --> "
						+ action.getOwner().getCurrent_location().getLatitude()
								.toString()
						+ ","
						+ action.getOwner().getCurrent_location()
								.getLongitude().toString());
				mInfo.setDescription(action.getTextDescription());
				mInfo.setOwner(action.getOwner().getLogin());
				Period actPeriod = new Period(action.getCreatedDateTime(), now);
				mInfo.setStarted(this.dateTimeFormatBuilder
						.periodToString(actPeriod));
				mapInfos.add(mInfo);
			}

		}

		return mapInfos;
	}

	// AJAX
	@RequestMapping(value = "/ajax/index", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<MapInfo> allEmergenciesJSON() {
		logger.debug("AJAX GET ALL EMERGENCIES");
		List<MapInfo> mapInfos = new ArrayList<MapInfo>();
		List<Emergency> emergencies = this.emergencyService.getAllEmergencys();
		if (emergencies != null) {
//			logger.debug("EMERGENCIES: " + emergencies.toString());
			for (Emergency emergency : emergencies) {		
				String type = (emergency instanceof Accident ? "Accident"
						: "Danger");
				if (emergency.hasValidLocation()) {
					MapInfo mapinfo = new MapInfo(emergency.getId().toString(),
							emergency.getLocation().getLatitude().toString(),
							emergency.getLocation().getLongitude().toString(),
							type, "None");
					mapInfos.add(mapinfo);
				}
			}
		}
		return mapInfos;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView listAllEmergencys(Principal principal) {
		logger.debug("/index listAllEmergenys method called!");
		ModelAndView modelAndView = new ModelAndView(
				"emergencys/emergencysList");
		if (principal != null && principal.getName() != null) {
			modelAndView.addObject("userLogin", principal.getName());
		} else {
			modelAndView.addObject("userLogin", "ANONYMOUS_USER");
		}

		// Map<DateTime, Emergency> sortedMap = new TreeMap<DateTime,
		// Emergency>(Collections.reverseOrder());

		List<Accident> accidents = (List<Accident>) this.accidentService
				.getAccidentsByFreshness(EEventStatus.Active);
		List<Danger> dangers = (List<Danger>) this.dangerService
				.getDangersByFreshness(EEventStatus.Active);

		/*
		 * for (Danger danger : dangers) { DateTime date = null; int i = 1;
		 * while(sortedMap.containsKey(danger.getCreatedDateTime())) { date =
		 * danger.getCreatedDateTime().plusMillis(i++);
		 * danger.setCreatedDateTime(date.plusMillis(1)); }
		 * sortedMap.put(danger.getCreatedDateTime(), danger); } for(Accident
		 * accident : accidents) { DateTime date = null; int i = 1;
		 * while(sortedMap.containsKey(accident.getCreatedDateTime())) { date =
		 * accident.getCreatedDateTime().plusMillis(i++);
		 * accident.setCreatedDateTime(date.plusMillis(1)); }
		 * sortedMap.put(accident.getCreatedDateTime(), accident); }
		 */
		List<Emergency> itemList = new ArrayList<Emergency>(accidents.size()
				+ dangers.size());

		itemList.addAll(accidents);
		itemList.addAll(dangers);

		modelAndView.addObject("itemList", itemList);
		return modelAndView;
	}

	@RequestMapping(value = "/emergencys/{emergencyId}/edit", method = RequestMethod.GET)
	public String initUpdateEmergencyForm(
			@PathVariable("emergencyId") int emergencyId, Model model) {
		Emergency emergency = this.emergencyService
				.findEmergencyById(emergencyId);
		logger.debug("EMERGENCYIS " + emergency.getTextDescription());
		model.addAttribute("emergency", emergency);
		return "emergencys/createOrUpdateEmergencyForm";
	}

	@RequestMapping(value = "/emergencys/{emergencyId}/edit", method = RequestMethod.PUT)
	public String processUpdateEmergencyForm(
			@PathVariable("emergencyId") int emergencyId,
			@ModelAttribute("emergency") Emergency emergency,
			BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return "emergencys/createOrUpdateEmergencyForm";
		} else {
			if (emergency.getId() == null) {
				emergency.setId(emergencyId);
			}
			this.emergencyService.updateEmergency(emergency);
			status.setComplete();
			return "redirect:/index";
		}
	}
}
