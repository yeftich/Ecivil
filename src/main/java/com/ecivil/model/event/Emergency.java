package com.ecivil.model.event;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.ecivil.model.user.User;

/**
 *
 * @author Milan
 */
@Entity
//@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "EMERGENCIES")
@PrimaryKeyJoinColumn(name="EVENT_ID")
public abstract class Emergency extends Event implements Serializable {
    
    private static final long serialVersionUID = 7992585374396213691L;
    
    public Emergency() {
    	super();
    }
    
//    
//    
//	public Emergency(int id, DateTime createdDateTime, String place,
//			String textDescription, User owner, String freshness,
//			String certification) {
//		super(id, createdDateTime, place, textDescription, owner, freshness,
//				certification);
//		// TODO Auto-generated constructor stub
//	}

	@OneToMany(fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL, mappedBy = "emergency")
    private Set<Action> actions;
	
	public Set<Action> getActions() {
		return actions;
	}

	public void setActions(Set<Action> actions) {
		this.actions = actions;
	}

	@Override
	public String toString() {
		return "Emergency [actions=" + actions + ", isNew()=" + isNew()
				+ ", getId()=" + getId() + ", getCreatedDateTime()="
				+ getCreatedDateTime() + ", getPlace()=" + getPlace()
				+ ", getTextDescription()=" + getTextDescription()
				+ ", getOwner()=" + getOwner() + ", getFreshness()="
				+ getFreshness() + ", getCertification()=" + getCertification()
				+ "]";
	}
	
	
    
}
