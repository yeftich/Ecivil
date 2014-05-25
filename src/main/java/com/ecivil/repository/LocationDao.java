package com.ecivil.repository;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.Location;

/**
 * @author Milan 
 *	23 мая 2014 г.  -  15:54:18
 *
 */
public interface LocationDao {
	
	Location getLocationByLatLon(double lat, double lon) throws DataAccessException;
	
	Location updateLocation(Location location) throws DataAccessException;
	
	void insertLocation(Location location) throws DataAccessException;

}
