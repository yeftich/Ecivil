package com.ecivil.model.team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;

import com.ecivil.model.user.User;
import com.ecivil.model.user.UserTeam;


/**
 * @author Milan
 *
 *
 */
@Entity
@Table(name = "teams")
public class Team implements Serializable {
	
	private static final long serialVersionUID = 8740649794332028426L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_id")
    private Integer id;
	
    @Column(name = "team_name", unique = true)
    private String name;	

	@Column(name = "team_address")
	private String address;
	
	@Column(name = "team_telephone")
	/*@Digits(fraction = 0, integer = 10)*/
	private String telephone;
	
	@Column(name = "team_email")
	private String email;
	
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "teams_users", joinColumns = @JoinColumn(name = "teamid", referencedColumnName = "team_id", insertable = false, updatable = false),
//            inverseJoinColumns = @JoinColumn(name = "userid", referencedColumnName = "user_id", insertable = false, updatable = false))
//    private Set<User> users = new HashSet<User>();
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.team")
	private Set<UserTeam> userTeams = new HashSet<UserTeam>(0);
    
    @ManyToOne
    @JoinColumn(name = "t_type")
    private TeamType type;
    
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

	public Team() {
	}
	
	public boolean isNew() {
		if(this.id == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public Set<UserTeam> getUserTeams() {
		return this.userTeams;
	}
 
	public void setUserTeams(Set<UserTeam> userTeams) {
		this.userTeams = userTeams;
	}
//	public Set<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}
//	
//	public void addUser(User user) {
//		this.users.add(user);
//	}

	public TeamType getType() {
		return type;
	}

	public void setType(TeamType type) {
		this.type = type;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public List<User> getTeamMembers() {
		List<User> members = new ArrayList<User>();
		
		for(UserTeam userTeam : userTeams) {
			members.add(userTeam.getUser());
		}
		
		return members;
	}
	
	
	
	
}
