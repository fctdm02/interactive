/**
 * 
 */
package com.eprize.montana.common.model.prize;

import java.util.Set;
import java.util.TreeSet;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.IDomainObject;
import com.eprize.montana.common.model.promotion.Promotion;

/**
 * 
 * @author tmyers
 * 
 */
public class PrizeGroup extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;

	/* */
	private Long prizeGroupId;
	
	/* */
	private String name;

	/* */
	private Integer groupWinLimit;
	
	/* */
	private Set<AbstractPrize> prizes = new TreeSet<AbstractPrize>();

	/* */
	private Promotion promotion;
	
	/**
	 * 
	 */
	public PrizeGroup() {
		
	}
	
	/**
	 * @return the prizeGroupId
	 */
	public Long getPrizeGroupId() {
		return prizeGroupId;
	}

	/**
	 * @param prizeGroupId the prizeGroupId to set
	 */
	public void setPrizeGroupId(Long prizeGroupId) {
		this.prizeGroupId = prizeGroupId;
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
	 * @return the groupWinLimit
	 */
	public Integer getGroupWinLimit() {
		return groupWinLimit;
	}

	/**
	 * @param groupWinLimit the groupWinLimit to set
	 */
	public void setGroupWinLimit(Integer groupWinLimit) {
		this.groupWinLimit = groupWinLimit;
	}

	/**
	 * @return the prizes
	 */
	public Set<AbstractPrize> getPrizes() {
		return prizes;
	}

	/**
	 * @param prizes the prizes to set
	 */
	public void setPrizes(Set<AbstractPrize> prizes) {
		this.prizes = prizes;
	}

	/**
	 * @return the promotion
	 */
	public Promotion getPromotion() {
		return promotion;
	}

	/**
	 * @param promotion the promotion to set
	 */
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.prizeGroupId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.promotion.getNaturalIdentity());
		sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
		sb.append(this.name);
		return sb.toString();
	}
}