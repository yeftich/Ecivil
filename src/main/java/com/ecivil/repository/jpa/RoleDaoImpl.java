package com.ecivil.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ecivil.model.Role;
import com.ecivil.repository.RoleDao;

@Repository
public class RoleDaoImpl implements RoleDao {
    
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Role getRole(String name) throws DataAccessException {
		Query query = this.em.createQuery("SELECT DISTINCT role FROM Role role WHERE role.role = :name");
		query.setParameter("name", name);
		return (Role) query.getSingleResult();
	}

}
