/**
 *
 * 
 */
package com.eprize.montana.common.model.user.social;

import java.util.Set;
import java.util.TreeSet;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.eprize.montana.common.model.user.social.SocialPhoto;

/**
 * 
 * @author Tom.Myers
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class SocialPhotoAlbum {

	/* */
	@JsonProperty("id") 
	protected String id;
	
	/* */
	@JsonProperty("name") 
	protected String name;
	
	/* */
	@JsonProperty("link") 
	protected String link;

	/* */
	@JsonProperty("photos") 
	protected Set<SocialPhoto> photos = new TreeSet<SocialPhoto>();
	
	/**
	 * 
	 */
	@JsonCreator
	public SocialPhotoAlbum() {
		
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param link
	 * @param photos
	 */
	@JsonCreator
	public SocialPhotoAlbum(
		String id, 
		String name, 
		String link,
		Set<SocialPhoto> photos) {
		this.id = id;
		this.name = name;
		this.link = link;
		this.photos = photos;
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

	/**
	 * @return the photos
	 */
	public Set<SocialPhoto> getPhotos() {
		return photos;
	}

	/**
	 * @param photos the photos to set
	 */
	public void setPhotos(Set<SocialPhoto> photos) {
		this.photos = photos;
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
		sb.append(", photos=");
		sb.append(this.photos);
		sb.append("}");
		return sb.toString();
	}
}