package com.ecivil.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ecivil.model.Team;
import com.ecivil.model.TeamType;
import com.ecivil.repository.TeamDao;

@Repository
public class TeamDaoImpl implements TeamDao {

    @PersistenceContext
    private EntityManager em;
    
	@Override
	@SuppressWarnings("unchecked")
	public List<Team> getAllTeams() throws DataAccessException {
		Query query = this.em.createQuery("from Team");
		return query.getResultList();
	}

	@Override
	public void saveTeam(Team team) {
    	if (team.isNew()) {
    		this.em.persist(team);     		
    	}
    	else {
    		this.em.merge(team);    
    	}			
	}

	@Override
	public Team findTeamById(int teamId) {
		Query query = this.em.createQuery("SELECT DISTINCT team FROM Team team WHERE team.id = :teamId");
        query.setParameter("teamId", teamId );
        return (Team)query.getSingleResult();
	}

	@Override
	public Team getTeamByName(String name) {
		 Query query = this.em.createQuery("SELECT DISTINCT team FROM Team team WHERE team.name = :name");
	        query.setParameter("name", name );
	        return (Team)query.getSingleResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<TeamType> getAllTeamTypes() throws DataAccessException {
		Query query = this.em.createQuery("from TeamType");
		return query.getResultList();
	}

	@Override
	public void deleteTeam(int teamId) throws DataAccessException {
		this.em.remove(findTeamById(teamId));
	}
}
