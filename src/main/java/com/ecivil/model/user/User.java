package com.ecivil.model.user;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.ecivil.model.Location;
import com.ecivil.model.Role;
import com.ecivil.model.enums.EVerification;
import com.ecivil.model.event.Event;
import com.ecivil.model.team.Team;

/**
 * @author Milan
 * 
 *         Simple JavaBean domain object representing an ecivil user
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 4318644128187461064L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;

	@Column(name = "login", length = 45, unique = true)
	@NotEmpty
	private String login;

	@Column(name = "password", length = 45)
	@NotEmpty
	private String password;

	@Column(name = "google_account")
	private String googleAccount;

	@Column(name = "city")
	@NotEmpty
	private String city;

	@Column(name = "first_name")
	@NotEmpty
	private String firstName;

	@Column(name = "last_name")
	@NotEmpty
	private String lastName;

	@Column(name = "telephone")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;

	@Column(name = "enabled", columnDefinition = "TINYINT(1) DEFAULT 1")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean enabled = true;

	@Column(name = "verified")
	private Boolean verified = false;

	@Column(name = "skill")
	private String skill;

	@Column(name = "blood_group")
	private String bloodGroup;

	@Column(name = "address")
	private String address;

	@OneToOne()
	@Cascade({ CascadeType.SAVE_UPDATE })
	@JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "userid", referencedColumnName = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "roleid", referencedColumnName = "role_id") })
	private Role role;

	@OneToMany(fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL, mappedBy = "owner")
    private Set<Event> events;
	
    @ManyToOne
    @JoinColumn(name = "CURRENT_LOCATION")
    private Location current_location;
	
//	// temporal list of teams that user choose to sign up
//	@Transient
//	private List<Team> teams = new ArrayList<Team>();
	
//	@ManyToMany(mappedBy = "users")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.user", cascade=javax.persistence.CascadeType.ALL)
	private Set<UserTeam> userTeams = new HashSet<UserTeam>(0);

	public boolean isNew() {
		if (this.id == null) {
			return true;
		} else {
			return false;
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGoogleAccount() {
		return googleAccount;
	}

	public void setGoogleAccount(String googleAccount) {
		this.googleAccount = googleAccount;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Location getCurrent_location() {
		return current_location;
	}

	public void setCurrent_location(Location current_location) {
		this.current_location = current_location;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<UserTeam> getUserTeams() {
		return userTeams;
	}

	public void setUserTeams(Set<Team> teams) {
		for(Team team : teams) {
			if(!containsTeam(team)) {
				UserTeam userTeam = new UserTeam(EVerification.Unverified.inGreek());
				userTeam.setUser(this);
				userTeam.setTeam(team);
				this.userTeams.add(userTeam);
			}
		}
	}
	
	private boolean containsTeam(Team team) {
		for(UserTeam userTeam : userTeams) {
			if(userTeam.getTeam().getId() == team.getId()) {
				return true;
			}
		}
		return false;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	
}
