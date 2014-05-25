package com.ecivil.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecivil.model.event.Accident;
import com.ecivil.model.event.Danger;
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
	@Transactional
	public List<Emergency> getAllEmergencys() throws DataAccessException {
		List<Accident> accidents = this.accidentDao.getAllAccidents();
		List<Danger> dangers = this.dangerDao.getAllDangers();
		int size = 0;
		if(accidents != null && dangers != null) {
			size = accidents.size() + dangers.size();
		}
		List<Emergency> emergencies = new ArrayList<Emergency>(size);

		if(accidents != null) {
			emergencies.addAll(accidents);
		}

		if(dangers != null) {
			emergencies.addAll(dangers);
		}
		
		return emergencies;
/*		return (List<Emergency>) emergencyDao.getAllEmergencys();*/
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
