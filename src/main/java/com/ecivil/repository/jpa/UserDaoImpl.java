package com.ecivil.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ecivil.model.enums.EVerification;
import com.ecivil.model.user.User;
import com.ecivil.repository.UserDao;

@Repository
public class UserDaoImpl implements UserDao {
	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public User getUser(String login) throws DataAccessException {
		Query query = this.em
				.createQuery("SELECT DISTINCT user FROM User user WHERE user.login = :login");
		query.setParameter("login", login);

		List<User> results = query.getResultList();
		if (!results.isEmpty()) {
			return results.get(0);
		} else {
			return null;
		}
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
		this.em.merge(user);
	}

	@Override
	public void deleteUser(int userId) throws DataAccessException {
		this.em.remove(findUserById(userId));
	}

	@Override
	public void verifyUser(int userId, int teamId) throws DataAccessException {
		logger.debug("VERIFYING USER with userId " + userId
				+ " for team with teamId " + teamId);

		// String sqlScript = "UPDATE user_team SET STATUS=\'"+
		// Verifications.Verified +
		// "\' WHERE USERID=" + userId + " AND TEAMID=" + teamId;

		Query query = this.em.createNamedQuery("updateStatusNativeSQL")
				.setParameter("status", EVerification.Verified.inGreek())
				.setParameter("userId", userId).setParameter("teamId", teamId);

		query.executeUpdate();
	}

	@Override
	public void addUserResponsibility(int userId, int teamId, String responsStr)
			throws DataAccessException {
		logger.debug("ADDING RESPONSIBILITY " + responsStr
				+ " to USER with userId " + userId + " and team with teamId "
				+ teamId);

		Query query = this.em.createNamedQuery("updateResponsibilityNativeSQL")
				.setParameter("responsStr", responsStr)
				.setParameter("userId", userId).setParameter("teamId", teamId);

		query.executeUpdate();
	}

	@Override
	public void removeUserFromTeam(int userId, int teamId)
			throws DataAccessException {
		logger.debug("REMOVING USER with userId " + userId
				+ " from team with teamId " + teamId);

		Query query = this.em.createNamedQuery("removeUserFromTeamNativeSQL")
				.setParameter("userId", userId).setParameter("teamId", teamId);

		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUuid(String uuid) throws DataAccessException {
		Query query = this.em
				.createQuery("SELECT DISTINCT user FROM User user WHERE user.uuid = :uuid");
		query.setParameter("uuid", uuid);

		List<User> results = query.getResultList();
		if (!results.isEmpty()) {
			return results.get(0);
		} else {
			return null;
		}
	}

}
