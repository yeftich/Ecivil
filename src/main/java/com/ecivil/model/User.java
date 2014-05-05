package com.ecivil.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Milan
 * 
 *         Simple JavaBean domain object representing an ecivil user
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "login", length = 45)
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
	protected String firstName;

	@Column(name = "last_name")
	@NotEmpty
	protected String lastName;

	@Column(name = "telephone")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;

	@Column(name = "enabled", columnDefinition = "TINYINT(1) DEFAULT 1")
	@NotEmpty
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean enabled = true;

	@Column(name = "verified")
	@NotEmpty
	private Boolean verified;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_name", referencedColumnName = "login") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
	private Role role;

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

	//
	// public Integer getId() {
	// return id;
	// }
	//
	// public void setId(Integer id) {
	// this.id = id;
	// }
	//
	// public String getLogin() {
	// return login;
	// }
	//
	// public void setLogin(String login) {
	// this.login = login;
	// }
	//
	// public String getPassword() {
	// return password;
	// }
	//
	// public void setPassword(String password) {
	// this.password = password;
	// }
	//
	// public Role getRole() {
	// return role;
	// }
	//
	// public void setRole(Role role) {
	// this.role = role;
	// }

}
