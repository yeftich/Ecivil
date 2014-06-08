package com.ecivil.model;

/**
 * @author Milan 
 *	25 мая 2014 г.  -  15:57:09
 *
 */
public class MapInfo {
	private String id;
	private String lat;
	private String lon;
	private String type;
	private String description;
	private String started;
	private String owner;
	private String verified;
	
	public MapInfo() {
		
	}

	// used like JSON for getting event with actions
	public MapInfo(String id, String lat, String lon, String type,
			String description, String started, String owner, String verified) {
		super();
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.type = type;
		this.description = description;
		this.started = started;
		this.owner = owner;
		this.verified = verified;
	}

	// used li JSON for getting all emergencies with ajax
	public MapInfo(String id, String lat, String lon, String type) {
		super();
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.type = type;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStarted() {
		return started;
	}

	public void setStarted(String started) {
		this.started = started;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}
	
}
