package com.ecivil.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecivil.model.enums.EEventStatus;
import com.ecivil.model.event.Accident;
import com.ecivil.repository.AccidentDao;
import com.ecivil.service.AccidentService;

/**
 * @author Milan 
 *	17 мая 2014 г.  -  12:09:26
 *
 */
@Service
public class AccidentServiceImpl implements AccidentService{

	@Autowired
	private AccidentDao accidentDao;

	@Override
	@Transactional
	public void saveAccident(Accident accident) throws DataAccessException {
		accidentDao.saveAccident(accident);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accident> getAllAccidents() throws DataAccessException {
		return (List<Accident>) accidentDao.getAllAccidents();
	}

	@Override
	@Transactional
	public Accident findAccidentById(int accidentId) throws DataAccessException {
		return accidentDao.findAccidentById(accidentId);
	}

	@Override
	@Transactional
	public void deleteAccident(int accidentId) throws DataAccessException {
		accidentDao.deleteAccident(accidentId);
	}


	@Override
	@Transactional(readOnly = true)
	public List<Accident> getAccidentsByFreshness(EEventStatus freshness) throws DataAccessException {
		return (List<Accident>) accidentDao.getAccidentsByFreshness(freshness);
	}

}

