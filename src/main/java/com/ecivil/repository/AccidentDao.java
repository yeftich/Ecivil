package com.ecivil.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.enums.EEventStatus;
import com.ecivil.model.event.Accident;

/**
 * @author Milan 
 *	17 мая 2014 г.  -  16:20:04
 *
 */
public interface AccidentDao {
	
	public List<Accident> getAllAccidents() throws DataAccessException;

	public void saveAccident(Accident accident) throws DataAccessException;

	public Accident findAccidentById(int accidentId) throws DataAccessException;

	public void deleteAccident(int accidentId) throws DataAccessException;

	public List<Accident> getAccidentsByFreshness(EEventStatus freshness) throws DataAccessException;

}
