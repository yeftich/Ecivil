package com.ecivil.model.user;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.ecivil.model.team.Team;

/**
 * @author Milan 
 *	15 мая 2014 г.  -  11:30:38
 *
 */
@Embeddable
public class UserTeamId  implements Serializable{
	
	private static final long serialVersionUID = 5546271272486904749L;
	
	private User user;
    private Team team;
 
	@ManyToOne
	public User getUser() {
		return user;
	}
 
	public void setUser(User user) {
		this.user = user;
	}
 
	@ManyToOne
	public Team getTeam() {
		return team;
	}
 
	public void setTeam(Team team) {
		this.team = team;
	}
 
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        UserTeamId that = (UserTeamId) o;
 
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (team != null ? !team.equals(that.team) : that.team != null)
            return false;
 
        return true;
    }
 
    public int hashCode() {
        int result;
        result = (user != null ? user.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        return result;
    }
 
}
