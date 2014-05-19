package com.ecivil.model.event;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.ecivil.model.Location;
import com.ecivil.model.enums.EEventStatus;
import com.ecivil.model.enums.EVerification;
import com.ecivil.model.user.User;

/**
 *
 * @author Milan
 */

@NamedNativeQueries({
	@NamedNativeQuery(name = "updateCertificationNativeSQL", query = "update events set certification = :certification where EVENT_ID = :eventId"),
	@NamedNativeQuery(name = "updateFreshnessNativeSQL", query = "update events set freshness = :freshness where EVENT_ID = :eventId") })
@Entity
@Table(name = "EVENTS")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Event implements Serializable {
	
    private static final long serialVersionUID = 4039035883350026078L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID")
    private Integer id;
    
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS")
	@Column(name = "CREATED_DATE_TIME", nullable = false)
    private DateTime createdDateTime;
	
	
    @Column(name = "PLACE")
    private String place;
    
    @Lob
    @Size(max = 32700)
    @Column(name = "TEXT_DESCRIPTION")
    private String textDescription;
    
    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private User owner;
    
    @Column(name = "FRESHNESS", nullable = false)
    private String freshness;
    
    @Column(name = "CERTIFICATION", nullable = false)
    private String certification;
    
    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location lacation;
    
    public Event() {
    	this.createdDateTime = new DateTime();
    	this.freshness = EEventStatus.Active.inGreek();
    	this.certification = EVerification.Unverified.inGreek();
    }

//	public Event(Integer id, DateTime createdDateTime, String place,
//			String textDescription, User owner, String freshness,
//			String certification) {
//		super();
//		this.id = id;
//		this.createdDateTime = createdDateTime;
//		this.place = place;
//		this.textDescription = textDescription;
//		this.owner = owner;
//		this.freshness = freshness;
//		this.certification = certification;
//	}




	public boolean isNew() {
		if (this.id == null) {
			return true;
		} else {
			return false;
		}
	}
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(DateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTextDescription() {
		return textDescription;
	}

	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getFreshness() {
		return freshness;
	}

	public void setFreshness(String freshness) {
		this.freshness = freshness;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

}
