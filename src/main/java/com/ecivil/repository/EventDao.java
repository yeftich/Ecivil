package com.ecivil.repository;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.event.Event;

/**
 * @author Milan 
 *	16 мая 2014 г.  -  18:57:15
 *
 */
public interface EventDao {

	public Event findEventById(int eventId) throws DataAccessException;
	
	void saveEvent(Event event) throws DataAccessException;

	public void closeEvent(int eventId) throws DataAccessException;

	public void verifyEvent(int eventId)throws DataAccessException;

	void deleteEvent(int eventId) throws DataAccessException;


}
