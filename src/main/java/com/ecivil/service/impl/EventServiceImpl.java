package com.ecivil.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecivil.model.enums.EEventStatus;
import com.ecivil.model.event.Accident;
import com.ecivil.model.event.Danger;
import com.ecivil.model.event.Event;
import com.ecivil.model.event.Event;
import com.ecivil.model.user.User;
import com.ecivil.repository.EventDao;
import com.ecivil.service.EventService;

/**
 * @author Milan 16 мая 2014 г. - 18:55:35
 * 
 */
@Service
public class EventServiceImpl implements EventService{

	@Autowired
	private EventDao eventDao;

	@Override
	@Transactional
	public void closeEvent(int eventId) {
		this.eventDao.closeEvent(eventId);
	}

	@Override
	@Transactional
	public void verifyEvent(int eventId) {
		this.eventDao.verifyEvent(eventId);
	}
	
	@Override
	@Transactional
	public void unVerifyEvent(int eventId) {
		this.eventDao.unVerifyEvent(eventId);
	}


	@Override
	@Transactional
	public void deleteEvent(int eventId) {
		this.eventDao.deleteEvent(eventId);
	}

}
