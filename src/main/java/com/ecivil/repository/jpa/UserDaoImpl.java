package com.ecivil.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ecivil.model.User;
import com.ecivil.repository.UserDao;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager em;

	@Override
    public User getUser(String login) throws DataAccessException {
		 Query query = this.em.createQuery("SELECT DISTINCT user FROM User user WHERE user.login = :login");
	        query.setParameter("login", login );
	        return (User)query.getSingleResult();
	}

	@Override
	public void saveUser(User user) throws DataAccessException {
//    	if (user.isNew()) {
    		this.em.persist(user);     		
//    	}
//    	else {
//    		this.em.merge(user);    
//    	}	
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() throws DataAccessException {
		Query query = this.em.createQuery("from User");
		return query.getResultList();
	}

	@Override
	public User findUserById(int userId) throws DataAccessException {
		Query query = this.em.createQuery("SELECT DISTINCT user FROM User user WHERE user.id = :userId");
        query.setParameter("userId", userId );
        return (User)query.getSingleResult();
	}

}
