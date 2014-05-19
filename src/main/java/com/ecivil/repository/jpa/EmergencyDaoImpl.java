package com.ecivil.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ecivil.model.event.Emergency;
import com.ecivil.model.user.User;
import com.ecivil.repository.EmergencyDao;

/**
 * @author Milan 
 *	18 мая 2014 г.  -  1:12:04
 *
 */
@Repository
public class EmergencyDaoImpl implements EmergencyDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Emergency> getAllEmergencys() throws DataAccessException {
		Query query = this.em.createQuery("from Emergency");
		return query.getResultList();
	}

	@Override
	public Emergency findEmergencyById(int emergencyId)
			throws DataAccessException {
		Query query = this.em
				.createQuery("SELECT DISTINCT emergency FROM Emergency emergency WHERE emergency.id = :emergencyId");
		query.setParameter("emergencyId", emergencyId);
		return (Emergency) query.getSingleResult();
	}

	@Override
	public void updateEmergency(Emergency emergency) throws DataAccessException {
			this.em.merge(emergency);
	}

		
		
		//		String query = "SELECT * FROM events left JOIN accidents ON events.event_id=accidents.event_id left join dangers on events.event_id=dangers.event_id";
//		Connection cnn = em.unwrap(java.sql.Connection.class);  
//		Statement st;
//		List<Emergency> emergencyList = new ArrayList<Emergency>();
//		try {
//			
//			st = cnn.createStatement();
//			ResultSet rs = st.executeQuery(query);
//			while(rs.next()) {
//				if(rs.getString("accident_type") != null) {
//					Accident emergency = new Accident(	rs.getInt("event_id"), new DateTime(rs.getDate("created_date_and_time")), rs.getString("place"), rs.getString("text_description"), rs.getInt("owner_id"), rs.getString("freshness"), rs.getString("certification"), rs.getString("accident_type"))
//				}
//				rs.getString("danger_type");
//				emergencyList.add(emergency);
//				
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
		
		
}
