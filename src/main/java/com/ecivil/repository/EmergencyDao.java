package com.ecivil.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.event.Emergency;


/**
 * @author Milan 
 *	18 мая 2014 г.  -  1:11:11
 *
 */
public interface EmergencyDao {

	public List<Emergency> getAllEmergencys() throws DataAccessException;

	public Emergency findEmergencyById(int emergencyId) throws DataAccessException;

	public void updateEmergency(Emergency emergency) throws DataAccessException;

}
