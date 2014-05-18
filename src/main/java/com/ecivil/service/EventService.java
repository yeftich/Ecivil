package com.ecivil.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.event.Accident;
import com.ecivil.model.event.Action;
import com.ecivil.model.event.Danger;
import com.ecivil.model.event.Event;

/**
 * @author Milan 
 *	16 мая 2014 г.  -  18:51:08
 *
 */
public interface EventService {

	void closeEvent(int eventId);
		
}
