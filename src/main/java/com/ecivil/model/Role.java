package com.ecivil.model;

import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

/**
 * @author Milan
 * 
 *         Simple JavaBean domain object representing a user's role
 */
@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@GeneratedValue
	 @Column(name = "role_id")
	private Integer id;

	private String role;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
