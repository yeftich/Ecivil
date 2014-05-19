package com.ecivil.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ecivil.model.event.Action;
import com.ecivil.repository.ActionDao;

/**
 * @author Milan 
 *	19 мая 2014 г.  -  11:50:46
 *
 */
@Repository
public class ActionDaoImpl implements ActionDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Action> getAllActions() throws DataAccessException {
		Query query = this.em.createQuery("from Action ORDER BY createdDateTime desc");
		return query.getResultList();
	}

	@Override
	public void saveAction(Action action) throws DataAccessException {
		if (action.isNew()) {
			this.em.persist(action);
		} else {
			this.em.merge(action);
		}
	}

	@Override
	public Action findActionById(int actionId) throws DataAccessException {
		Query query = this.em
				.createQuery("SELECT DISTINCT action FROM Action action WHERE action.id = :actionId");
		query.setParameter("actionId", actionId);
		return (Action) query.getSingleResult();
	}

	@Override
	public void deleteAction(int actionId) throws DataAccessException {
		this.em.remove(findActionById(actionId));
	}


}
