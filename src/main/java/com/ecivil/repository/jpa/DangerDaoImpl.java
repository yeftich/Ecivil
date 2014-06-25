package com.ecivil.repository.jpa;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ecivil.model.enums.EEventStatus;
import com.ecivil.model.event.Action;
import com.ecivil.model.event.Danger;
import com.ecivil.repository.DangerDao;

/**
 * @author Milan 17 мая 2014 г. - 16:56:16
 * 
 */
@Repository
public class DangerDaoImpl implements DangerDao {

	private static final Logger logger = LoggerFactory
			.getLogger(DangerDaoImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Danger> getAllDangers() throws DataAccessException {
		Query query = this.em
				.createQuery("from Danger ORDER BY createdDateTime desc");
		return query.getResultList();
	}

	@Override
	public Danger saveDanger(Danger danger) throws DataAccessException {
		Danger savedDanger = this.em.merge(danger);
		if (danger.isNew()) {
			this.em.flush();
		}
		return savedDanger;

		/*
		 * if (danger.isNew()) { this.em.persist(danger); } else {
		 * this.em.merge(danger); }
		 */
	}

	@SuppressWarnings("unchecked")
	@Override
	public Danger findDangerById(int dangerId) throws DataAccessException {
		Query query = this.em
				.createQuery("SELECT DISTINCT danger FROM Danger danger WHERE danger.id = :dangerId");
		query.setParameter("dangerId", dangerId);
		List<Danger> result = query.getResultList();
		Danger danger = null;
		if (result != null && !result.isEmpty()) {
			danger = result.get(0);
			Set<Action> actions = danger.getActions();
			for (Action action : actions) {
				logger.debug("action loaded with id " + action.getId());
			}
		}
		return danger;
	}

	@Override
	public void deleteDanger(int dangerId) throws DataAccessException {
		this.em.remove(findDangerById(dangerId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Danger> getDangersByFreshness(EEventStatus freshness)
			throws DataAccessException {
		Query query = this.em
				.createQuery("SELECT DISTINCT danger FROM Danger danger WHERE danger.freshness = :freshness");
		query.setParameter("freshness", freshness.inGreek());
		return query.getResultList();
	}

}
