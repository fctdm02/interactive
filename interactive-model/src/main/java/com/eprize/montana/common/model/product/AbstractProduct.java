/**
 * 
 */
package com.eprize.montana.common.model.product;

import java.util.Map;
import java.util.TreeMap;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.IDomainObject;
import com.eprize.montana.common.model.promotion.Promotion;

/**
 * 
 * @author tmyers
 * 
 */
public abstract class AbstractProduct extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long productId;
	
	/* */
	protected String name;

	/* */
	protected String description;
	
	/* */
	protected Promotion promotion;
	
	/**
	 * 
	 */
	public AbstractProduct() {
		this.name = this.getClass().getSimpleName();
	}
	
	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return this.productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the promotion
	 */
	public Promotion getPromotion() {
		return this.promotion;
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
		
	   return this.productId;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
   public String getNaturalIdentity() {
	   
	   StringBuilder sb = new StringBuilder();
	   if (this.promotion != null) {
		   sb.append(this.promotion.getNaturalIdentity());
		   sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
	   }
	   sb.append(this.name);
	   return sb.toString();
   }

    /*
     * (non-Javadoc)
     * @see com.eprize.montana.common.model.DomainObject#validate()
     */
	public Map<String, String> validate() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();

		if (this.name == null || this.name.isEmpty()) {
			validationErrors.put("product name", "Cannot be empty");
		}
		
		return validationErrors;
	}
}
