/**
 *
 * 
 */
package com.eprize.montana.common.model.user.social;

import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author Tom.Myers
 *
 * TODO: Once the proof-of-concept is done, wire this class into Registrant 
 * (i.e. a Registrant has 0 or more SocialConnections) and make this class extend DomainObject.
 *
 */
public class SocialUserConnection {
		
	
	/* TODO: TDM: this will be the persistent identity.  The natural identity will be a combination 
	 * of parent registrant natural identity and provider name
	 */
	//private Long socialUserConnectionId
	
	
	/* */
	protected String providerName = "";
	
	/* */
	protected String accessTokenKey = "";
	
	/* */
	protected String accessTokenSecret = "";
	
	/* */
	protected String userId = "";

	/* */
	protected String userName = "";
	
	/* */
	protected String userFullName = "";
	
	/* */
	protected String profilePictureUrl = "";
	
	/* */
	protected Set<SocialFriend> socialFriends = new TreeSet<SocialFriend>();

	/* */
	protected Set<SocialPhotoAlbum> socialPhotoAlbums = new TreeSet<SocialPhotoAlbum>();
	
	/**
	 * 
	 */
	public SocialUserConnection() {
		
	}

	/**
	 * @param providerName
	 */
	public SocialUserConnection(String providerName) {
		this.setProviderName(providerName);
	}
	
	/**
	 * @return the providerName
	 */
	public String getProviderName() {
		return providerName;
	}

	/**
	 * @param providerName the providerName to set
	 */
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	/**
	 * @return the accessTokenKey
	 */
	public String getAccessTokenKey() {
		return accessTokenKey;
	}

	/**
	 * @param accessTokenKey the accessTokenKey to set
	 */
	public void setAccessTokenKey(String accessTokenKey) {
		this.accessTokenKey = accessTokenKey;
	}

	/**
	 * @return the accessTokenSecret
	 */
	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	/**
	 * @param accessTokenSecret the accessTokenSecret to set
	 */
	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userFullName
	 */
	public String getUserFullName() {
		return userFullName;
	}

	/**
	 * @param userFullName the userFullName to set
	 */
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	/**
	 * @return the profilePictureUrl
	 */
	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	/**
	 * @param profilePictureUrl the profilePictureUrl to set
	 */
	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}

	/**
	 * @return the socialFriends
	 */
	public Set<SocialFriend> getSocialFriends() {
		return socialFriends;
	}

	/**
	 * @param socialFriends the socialFriends to set
	 */
	public void setSocialFriends(Set<SocialFriend> socialFriends) {
		this.socialFriends = socialFriends;
	}
	
	/**
	 * 
	 * @param socialFriend
	 * @return
	 */
	public boolean addSocialFriend(SocialFriend socialFriend) {
		return this.socialFriends.add(socialFriend);
	}
	
	/**
	 * 
	 * @param socialFriend
	 * @return
	 */
	public boolean removeSocialFriend(SocialFriend socialFriend) {
		return this.socialFriends.remove(socialFriend);
	}
	
	/**
	 * @return the socialPhotoAlbums
	 */
	public Set<SocialPhotoAlbum> getSocialAlbums() {
		return socialPhotoAlbums;
	}

	/**
	 * @param socialPhotoAlbums the socialPhotoAlbums to set
	 */
	public void setSocialAlbums(Set<SocialPhotoAlbum> socialPhotoAlbums) {
		this.socialPhotoAlbums = socialPhotoAlbums;
	}
	
	/**
	 * 
	 * @param socialPhotoAlbum
	 * @return
	 */
	public boolean addSocialAlbum(SocialPhotoAlbum socialPhotoAlbum) {
		return this.socialPhotoAlbums.add(socialPhotoAlbum);
	}
	
	/**
	 * 
	 * @param socialPhotoAlbum
	 * @return
	 */
	public boolean removeSocialAlbum(SocialPhotoAlbum socialPhotoAlbum) {
		return this.socialPhotoAlbums.remove(socialPhotoAlbum);
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
		sb.append(": providerName=");
		sb.append(this.providerName);
		sb.append(", userId=");
		sb.append(this.userId);
		sb.append(", userName=");
		sb.append(this.userName);
		sb.append(", accessTokenKey=");
		sb.append(this.accessTokenKey);
		sb.append(", accessTokenSecret=");
		sb.append(this.accessTokenSecret);		
		sb.append("}");
		return sb.toString();
	}
}