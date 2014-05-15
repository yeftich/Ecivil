package com.ecivil.model.team;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "team_type")
public class TeamType implements Serializable {
	
	private static final long serialVersionUID = -4953758628869995005L;
	private final static String INSTITUTIONAL_TYPE = "INSTITUTIONAL";
	
	@Id
	@Column(name = "tt_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "tt_name")
	private String name;
	
	public boolean isInstitutional() {
		return this.name.equals(INSTITUTIONAL_TYPE);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

}
