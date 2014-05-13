package com.ecivil.model;

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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Milan
 *
 *
 */
@Entity
@Table(name = "teams")
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_id")
    private Integer id;
	
    @Column(name = "team_name", unique = true)
    @NotEmpty
    private String name;	

	@Column(name = "team_address")
	@NotEmpty
	private String address;
	
	@Column(name = "team_telephone")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;
	
	@Column(name = "team_email")
	@NotEmpty
	private String email;
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "teams_users", joinColumns = @JoinColumn(name = "teamid", referencedColumnName = "team_id", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "userid", referencedColumnName = "user_id", insertable = false, updatable = false))
    private Set<User> users = new HashSet<User>();

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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}
	
	
	
}
