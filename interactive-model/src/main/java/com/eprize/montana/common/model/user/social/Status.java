/**
 *
 * 
 */
package com.eprize.montana.common.model.user.social;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Tom.Myers
 *
 */
@XmlRootElement
public class Status implements Serializable {

	/* */
	private static final long serialVersionUID = 1L;

	/* */
	protected String status = "TODO: TDM: Implement to return the real status of the service.";
	
	/**
	 * 
	 */
	public Status() {
		
	}

	/**
	 * @return the status
	 */
	@XmlAttribute
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append(this.getClass().getSimpleName());
		sb.append(": status=");
		sb.append(this.status);
		sb.append("}");
		return sb.toString();
	}
}