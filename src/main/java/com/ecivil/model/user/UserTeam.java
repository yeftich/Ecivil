package com.ecivil.model.user;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.DateTime;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import com.ecivil.model.enums.EVerification;
import com.ecivil.model.team.Team;

/**
 * @author Milan 15 мая 2014 г. - 11:11:11
 * 
 */
@NamedNativeQueries({
		@NamedNativeQuery(name = "updateStatusNativeSQL", query = "update user_team set status = :status where USERID = :userId and TEAMID = :teamId"),
		@NamedNativeQuery(name = "removeUserFromTeamNativeSQL", query = "DELETE FROM user_team WHERE USERID = :userId and TEAMID = :teamId"),
		@NamedNativeQuery(name = "updateResponsibilityNativeSQL", query = "update user_team set RESPONSIBILITY = :responsStr where USERID = :userId and TEAMID = :teamId") })
@Entity
@Table(name = "user_team")
@AssociationOverrides({
		@AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "USERID")),
		@AssociationOverride(name = "pk.team", joinColumns = @JoinColumn(name = "TEAMID")) })
// this class is a record of members partipication in a team
public class UserTeam implements Serializable {

	private static final long serialVersionUID = -5038112734736813355L;

	private UserTeamId pk = new UserTeamId();
	private DateTime createdDate;
	private String status;
	private String responsibility;

	public UserTeam() {
		this.createdDate = new DateTime();
	}

	public UserTeam(String status) {
		super();
		this.createdDate = new DateTime();
		this.status = status;
	}

	@EmbeddedId
	public UserTeamId getPk() {
		return pk;
	}

	public void setPk(UserTeamId pk) {
		this.pk = pk;
	}

	@Transient
	public User getUser() {
		return getPk().getUser();
	}

	public void setUser(User user) {
		getPk().setUser(user);
	}

	@Transient
	public Team getTeam() {
		return getPk().getTeam();
	}

	public void setTeam(Team team) {
		getPk().setTeam(team);
	}

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS")
	@Column(name = "CREATED_DATE", nullable = false)
	public DateTime getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "STATUS", nullable = false)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "RESPONSIBILITY")
	public String getResponsibility() {
		return this.responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	@Transient
	public boolean isVerified() {
		return this.status.equals(EVerification.Verified.inGreek());
	}

}
