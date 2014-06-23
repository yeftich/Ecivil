package com.ecivil.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecivil.model.event.Accident;
import com.ecivil.model.event.Action;
import com.ecivil.repository.ActionDao;
import com.ecivil.service.ActionService;

/**
 * @author Milan 19 мая 2014 г. - 11:47:41
 * 
 */
@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionDao actionDao;

	@Override
	@Transactional
	public void saveAction(Action action) throws DataAccessException {
		actionDao.saveAction(action);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Action> getAllActions() throws DataAccessException {
		return (List<Action>) actionDao.getAllActions();
	}

	@Override
	@Transactional
	public Action findActionById(int actionId) throws DataAccessException {
		return actionDao.findActionById(actionId);
	}

	@Override
	@Transactional
	public void deleteAction(int actionId) throws DataAccessException {
		actionDao.deleteAction(actionId);
	}
	
}
