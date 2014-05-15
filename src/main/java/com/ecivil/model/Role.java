package com.ecivil.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import com.ecivil.util.ConstantUtil;

/**
 * @author Milan
 * 
 *         Simple JavaBean domain object representing a user's role
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable  {
	
	private static final long serialVersionUID = -1977800691596341296L;

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
	
	public boolean isAdmin() {
		return this.role.equals(ConstantUtil.ROLE_ADMIN);
	}
	
	public boolean isAdminVolunteer() {
		return this.role.equals(ConstantUtil.ROLE_VOLUNTEERS_ADMIN);
	}

	public boolean isAdminInstitution() {
		return this.role.equals(ConstantUtil.ROLE_INSTITUTIONS_ADMIN);
	}

	public boolean isVolunteer() {
		return this.role.equals(ConstantUtil.ROLE_VOLUNTEER);
	}

	public boolean isInstitution() {
		return this.role.equals(ConstantUtil.ROLE_INSTITUTION);
	}

	public boolean isMemeber() {
		return this.role.equals(ConstantUtil.ROLE_MEMBER);
	}

}
