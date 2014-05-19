package com.ecivil.model.event;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Milan
 */
@Entity
@Table(name = "ACTIONS")
@PrimaryKeyJoinColumn(name="EVENT_ID")
public class Action extends Event implements Serializable {
	
    private static final long serialVersionUID = -380668906770537216L;

	public Action() {
		super();
	}

    @ManyToOne
    @JoinColumn(name = "EMERGENCY_ID")
    private Emergency emergency;
    
    @Column(name = "TOOL")
    private String tool;

	public Emergency getEmergency() {
		return emergency;
	}

	public void setEmergency(Emergency emergency) {
		this.emergency = emergency;
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}
}
