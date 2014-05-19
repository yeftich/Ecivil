package com.ecivil.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.event.Action;

/**
 * @author Milan 19 мая 2014 г. - 11:49:55
 * 
 */
public interface ActionDao {
	
	public List<Action> getAllActions() throws DataAccessException;

	public void saveAction(Action action) throws DataAccessException;

	public Action findActionById(int actionId) throws DataAccessException;

	public void deleteAction(int actionId) throws DataAccessException;
}
