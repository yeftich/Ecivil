package com.ecivil.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.enums.EEventStatus;
import com.ecivil.model.event.Danger;

/**
 * @author Milan 
 *	17 мая 2014 г.  -  16:55:11
 *
 */
public interface DangerDao {

	public List<Danger> getAllDangers() throws DataAccessException;

	public void saveDanger(Danger danger) throws DataAccessException;

	public Danger findDangerById(int dangerId) throws DataAccessException;

	public void deleteDanger(int dangerId) throws DataAccessException;

	public List<Danger> getDangersByFreshness(EEventStatus freshness) throws DataAccessException;
	
}
