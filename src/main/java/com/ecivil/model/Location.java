package com.ecivil.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Milan 18 мая 2014 г. - 18:35:46
 * 
 */
@Entity
@Table(name = "locations")
public class Location implements Serializable {

	private static final long serialVersionUID = -6738532815097670776L;

	@Id
	@GeneratedValue
	@Column(name = "LOCATION_ID")
	private Integer id;
	
	@Column(name = "LATITUDE")
	private Double latitude;
	
	@Column(name = "LONGITUDE")
	private Double longitude;

	public Location() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
