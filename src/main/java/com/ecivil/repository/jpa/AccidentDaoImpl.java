package com.ecivil.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ecivil.model.enums.EEventStatus;
import com.ecivil.model.event.Accident;
import com.ecivil.repository.AccidentDao;

/**
 * @author Milan 
 *	17 мая 2014 г.  -  16:21:34
 *
 */
@Repository
public class AccidentDaoImpl implements AccidentDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Accident> getAllAccidents() throws DataAccessException {
		Query query = this.em.createQuery("from Accident ORDER BY createdDateTime desc");
		return query.getResultList();
	}

	@Override
	public void saveAccident(Accident accident) throws DataAccessException {
		if (accident.isNew()) {
			this.em.persist(accident);
		} else {
			this.em.merge(accident);
		}
	}

	@Override
	public Accident findAccidentById(int accidentId) throws DataAccessException {
		Query query = this.em
				.createQuery("SELECT DISTINCT accident FROM Accident accident WHERE accident.id = :accidentId");
		query.setParameter("accidentId", accidentId);
		return (Accident) query.getSingleResult();
	}

	@Override
	public void deleteAccident(int accidentId) throws DataAccessException {
		this.em.remove(findAccidentById(accidentId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Accident> getAccidentsByFreshness(EEventStatus freshness) throws DataAccessException {
		Query query = this.em
				.createQuery("SELECT DISTINCT accident FROM Accident accident WHERE accident.freshness = :freshness");
		query.setParameter("freshness", freshness.inGreek());
		return query.getResultList();
	}

}
