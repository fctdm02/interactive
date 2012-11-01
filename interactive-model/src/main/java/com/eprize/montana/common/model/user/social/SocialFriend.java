/**
 *
 * 
 */
package com.eprize.montana.common.model.user.social;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Tom.Myers
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class SocialFriend {

	/* */
	@JsonProperty("id") 
	protected String id;
	
	/* */
	@JsonProperty("name") 
	protected String name;
	
	/* */
	@JsonProperty("link") 
	protected String link;

	/**
	 * 
	 */
	@JsonCreator
	public SocialFriend() {
		
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param link
	 */
	@JsonCreator
	public SocialFriend(
		String id, 
		String name, 
		String link) {
		this.id = id;
		this.name = name;
		this.link = link;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
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
		sb.append(": id=");
		sb.append(this.id);
		sb.append(", name=");
		sb.append(this.name);
		sb.append(", link=");
		sb.append(this.link);
		sb.append("}");
		return sb.toString();
	}
}