/**
 * 
 */
package com.eprize.montana.common.model.user;

import java.util.Set;
import java.util.TreeSet;

import com.eprize.montana.common.model.promotion.Promotion;


/**
 * 
 * @author tmyers
 * 
 */
public class AccountCoordinator extends AuthenticatingUser {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private String team;
	
	/* */
	private Set<Promotion> managedPromotions = new TreeSet<Promotion>();
		
	/**
	 * 
	 */
	public AccountCoordinator() {
		
	}
	
	/**
	 * 
	 * @param accountCoordinatorUsername
	 * @param encodedPassword
	 * @param team
	 */
	public AccountCoordinator(
		String username, 
		String encodedPassword, 
		String team) {
		this.setUsername(username);
		this.setEncodedPassword(encodedPassword);
		this.setTeam(team);
	}

	/**
	 * @return the team
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(String team) {
		this.team = team;
	}
	
	/**
	 * @return the managedPromotions
	 */
	public Set<Promotion> getManagedPromotions() {
		return managedPromotions;
	}

	/**
	 * @param managedPromotions the managedPromotions to set
	 */
	public void setManagedPromotions(Set<Promotion> managedPromotions) {
		this.managedPromotions = managedPromotions;
	}
	
	/**
	 * 
	 * @param promotion
	 * @return
	 */
	public boolean addManagedPromotion(Promotion promotion) {
		promotion.setManagingAccountCoordinator(this);
		return this.managedPromotions.add(promotion);
	}
	
	/**
	 * 
	 * @param promotion
	 * @return
	 */
	public boolean removeManagedPromotion(Promotion promotion) {
		promotion.setManagingAccountCoordinator(null);
		return this.managedPromotions.remove(promotion);
	}

	/**
	 * A super user is able to create/update/delete other account coordinators.
	 * @return
	 */
	public boolean isSuperUser() { 
		return false;
	}
}