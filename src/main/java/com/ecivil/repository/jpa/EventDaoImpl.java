package com.ecivil.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ecivil.model.enums.EEventStatus;
import com.ecivil.model.event.Event;
import com.ecivil.repository.EventDao;

/**
 * @author Milan 
 *	16 мая 2014 г.  -  19:04:30
 *
 */
@Repository
public class EventDaoImpl implements EventDao{

	@PersistenceContext
	private EntityManager em;

	@Override
	public void saveEvent(Event event) throws DataAccessException {
		if (event.isNew()) {
			this.em.persist(event);
		} else {
			this.em.merge(event);
		}
	}

	@Override
	public Event findEventById(int eventId) throws DataAccessException {
		Query query = this.em
				.createQuery("SELECT DISTINCT event FROM Event event WHERE event.id = :eventId");
		query.setParameter("eventId", eventId);
		return (Event) query.getSingleResult();
	}

	@Override
	public void closeEvent(int eventId) throws DataAccessException {
		Query query = this.em.createNamedQuery("updateFreshnessNativeSQL")
				.setParameter("freshness", EEventStatus.Closed.inGreek())
				.setParameter("eventId", eventId);

			query.executeUpdate();		
	}
}
