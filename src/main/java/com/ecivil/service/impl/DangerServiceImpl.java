package com.ecivil.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecivil.model.enums.EEventStatus;
import com.ecivil.model.event.Danger;
import com.ecivil.repository.DangerDao;
import com.ecivil.service.DangerService;

/**
 * @author Milan 
 *	17 мая 2014 г.  -  16:52:53
 *
 */
@Service
public class DangerServiceImpl implements DangerService{

	@Autowired
	private DangerDao dangerDao;

	@Override
	@Transactional
	public void saveDanger(Danger danger) throws DataAccessException {
		dangerDao.saveDanger(danger);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Danger> getAllDangers() throws DataAccessException {
		return (List<Danger>) dangerDao.getAllDangers();
	}

	@Override
	@Transactional
	public Danger findDangerById(int dangerId) throws DataAccessException {
		return dangerDao.findDangerById(dangerId);
	}

	@Override
	@Transactional
	public void deleteDanger(int dangerId) throws DataAccessException {
		dangerDao.deleteDanger(dangerId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Danger> getDangersByFreshness(EEventStatus freshness)
			throws DataAccessException {
		return dangerDao.getDangersByFreshness(freshness);
	}

}
