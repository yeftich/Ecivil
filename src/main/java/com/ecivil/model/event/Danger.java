/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecivil.model.event;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Milan
 */
@Entity
@Table(name = "DANGERS")
@PrimaryKeyJoinColumn(name="EVENT_ID")
public class Danger extends Emergency implements Serializable {
    
    private static final long serialVersionUID = 8313672807322565623L;
    
    @Column(name = "DANGER_TYPE")
    private String type;
    
    public Danger() {
    	super();
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
    
}
