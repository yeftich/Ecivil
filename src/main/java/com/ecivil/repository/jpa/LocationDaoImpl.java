package com.ecivil.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ecivil.model.Location;
import com.ecivil.model.user.User;
import com.ecivil.repository.LocationDao;

/**
 * @author Milan 23 мая 2014 г. - 15:58:45
 * 
 */
@Repository
public class LocationDaoImpl implements LocationDao {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public Location getLocationByLatLon(double lat, double lon)
			throws DataAccessException {
		Query query = this.em
				.createQuery("SELECT DISTINCT location FROM Location location WHERE location.latitude = :lat AND location.longitude =:lon");
		query.setParameter("lat", lat);
		query.setParameter("lon", lon);

		List<Location> results = query.getResultList();
		if (!results.isEmpty()) {
			return results.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Location updateLocation(Location location)
			throws DataAccessException {
		logger.debug("UPDATING LOCATION with id " +location.getId() + " and  lat, lon " + location.getLatitude() + ", " + location.getLongitude());
		return this.em.merge(location);
	}

	@Override
	public void insertLocation(Location location) throws DataAccessException {
		logger.debug("INSERTING LOCATION with lat, lon " + location.getLatitude() + ", " + location.getLongitude());
		this.em.persist(location);
	}

}
