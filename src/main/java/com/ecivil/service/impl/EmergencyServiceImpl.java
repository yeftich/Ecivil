package com.ecivil.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecivil.model.event.Emergency;
import com.ecivil.repository.AccidentDao;
import com.ecivil.repository.DangerDao;
import com.ecivil.repository.EmergencyDao;
import com.ecivil.service.EmergencyService;

/**
 * @author Milan 
 *	18 мая 2014 г.  -  1:06:33
 *
 */
@Service
public class EmergencyServiceImpl implements EmergencyService{

	@Autowired
	private EmergencyDao emergencyDao;
	
	@Autowired
	private AccidentDao accidentDao;
	
	@Autowired
	private DangerDao dangerDao;


	@Override
	@Transactional(readOnly = true)
	public List<Emergency> getAllEmergencys() throws DataAccessException {
		return (List<Emergency>) emergencyDao.getAllEmergencys();
	}

	@Override
	@Transactional
	public Emergency findEmergencyById(int emergencyId) throws DataAccessException {
		return this.emergencyDao.findEmergencyById(emergencyId);
	}
	
	@Override
	@Transactional
	public void updateEmergency(Emergency emergency) throws DataAccessException {
		emergencyDao.updateEmergency(emergency);
	}
	
//	@Override
//	@Transactional
//	public Emergency findEmergencyById(int emergencyId) throws DataAccessException {
//		
//		Emergency emergency = this.accidentDao.findAccidentById(emergencyId);
//		if(emergency != null) {
//			return emergency;
//		}
//		else {
//			return this.dangerDao.findDangerById(emergencyId);
//		}
//	}



}
