package com.ecivil.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.enums.EEventStatus;
import com.ecivil.model.event.Accident;

/**
 * @author Milan 
 *	17 мая 2014 г.  -  12:06:54
 *
 */
public interface AccidentService {

	public List<Accident> getAllAccidents() throws DataAccessException;

	Accident findAccidentById(int accidentId) throws DataAccessException;

	void saveAccident(Accident accident) throws DataAccessException;

	public void deleteAccident(int accidentId)  throws DataAccessException;
	
	public List<Accident> getAccidentsByFreshness(EEventStatus freshness) throws DataAccessException;

}
