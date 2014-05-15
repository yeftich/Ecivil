package com.ecivil.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ecivil.model.User;
import com.ecivil.repository.UserDao;

@Repository
public class UserDaoImpl implements UserDao {
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public User getUser(String login) throws DataAccessException {
		Query query = this.em
				.createQuery("SELECT DISTINCT user FROM User user WHERE user.login = :login");
		query.setParameter("login", login);
		return (User) query.getSingleResult();
	}

	@Override
	public void insertUser(User user) throws DataAccessException {
		logger.debug("INSERTING USER with username " + user.getLogin());
		this.em.persist(user);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() throws DataAccessException {
		Query query = this.em.createQuery("from User");
		return query.getResultList();
	}

	@Override
	public User findUserById(int userId) throws DataAccessException {
		Query query = this.em
				.createQuery("SELECT DISTINCT user FROM User user WHERE user.id = :userId");
		query.setParameter("userId", userId);
		return (User) query.getSingleResult();
	}

	@Override
	public void updateUser(User user) throws DataAccessException {
		logger.debug("UPDATING USER with username " + user.getLogin() + " and id " + user.getId());
		this.em.merge(user);
	}

	@Override
	public void deleteUser(int userId) throws DataAccessException {
			this.em.remove(findUserById(userId));
	}

}
