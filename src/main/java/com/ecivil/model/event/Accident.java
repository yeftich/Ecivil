/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecivil.model.event;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.ecivil.model.user.User;

/**
 *
 * @author Milan
 */
@Entity
@Table(name = "ACCIDENTS")
@PrimaryKeyJoinColumn(name="EVENT_ID")
public class Accident extends Emergency implements Serializable {

    private static final long serialVersionUID = -2013605926157154427L;
    
    @Column(name = "ACCIDENT_TYPE")
    private String type;
    
    public Accident() {
        super();
    }
    
//    
//
//	public Accident(int id, DateTime createdDateTime, String place,
//			String textDescription, User owner, String freshness,
//			String certification, String type) {
//		super(id, createdDateTime, place, textDescription, owner, freshness,
//				certification);
//		this.type = type;
//	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
}
