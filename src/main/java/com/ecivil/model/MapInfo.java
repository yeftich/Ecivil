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
	
	public MapInfo() {
		
	}

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
	
	
	
}
