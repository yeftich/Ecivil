package com.ecivil.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.event.Emergency;

/**
 * @author Milan 
 *	18 мая 2014 г.  -  1:05:51
 *
 */
public interface EmergencyService {
	
	public List<Emergency> getAllEmergencys() throws DataAccessException;

	Emergency findEmergencyById(int emergencyId) throws DataAccessException;

//	void updateEmergency(Emergency emergency) throws DataAccessException;
}
