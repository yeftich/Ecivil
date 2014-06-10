package com.ecivil.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.enums.EEventStatus;
import com.ecivil.model.event.Danger;

/**
 * @author Milan 
 *	17 мая 2014 г.  -  16:50:14
 *
 */
public interface DangerService {

	public List<Danger> getAllDangers() throws DataAccessException;

	Danger findDangerById(int dangerId) throws DataAccessException;

	Danger saveDanger(Danger danger) throws DataAccessException;

	public void deleteDanger(int dangerId)  throws DataAccessException;

	public List<Danger> getDangersByFreshness(EEventStatus active) throws DataAccessException;
	
}
