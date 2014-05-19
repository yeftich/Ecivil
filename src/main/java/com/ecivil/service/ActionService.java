package com.ecivil.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.event.Action;

/**
 * @author Milan 
 *	19 мая 2014 г.  -  11:18:36
 *
 */
public interface ActionService {
	
	public List<Action> getAllActions() throws DataAccessException;

	Action findActionById(int actionId) throws DataAccessException;

	void saveAction(Action action) throws DataAccessException;

	public void deleteAction(int actionId)  throws DataAccessException;
}
