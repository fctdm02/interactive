/**
 * 
 */
package com.eprize.montana.common.model.lookandfeel.content;

import java.util.Map;
import java.util.TreeMap;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.lookandfeel.PromotionLookAndFeel;

/**
 * 
 * @author tmyers
 * 
 */
public abstract class AbstractPromotionCopy extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long promotionCopyId;

	/* */
	protected String languageCode;
	
	/* */
	protected String countryCode;

	/* */
	protected String copyKey;
	
	/* */
	protected PromotionLookAndFeel promotionLookAndFeel;
	
	/**
	 * 
	 */
	public AbstractPromotionCopy() {
	}

	/**
	 * @return the promotionCopyId
	 */
	public Long getPromotionCopyId() {
		return promotionCopyId;
	}

	/**
	 * @param promotionCopyId the promotionCopyId to set
	 */
	public void setPromotionCopyId(Long promotionCopyId) {
		this.promotionCopyId = promotionCopyId;
	}

	/**
	 * @return the languageCode
	 */
	public String getLanguageCode() {
		return languageCode;
	}

	/**
	 * @param languageCode the languageCode to set
	 */
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	/**
	 * @return the copyKey
	 */
	public String getCopyKey() {
		return copyKey;
	}

	/**
	 * @param copyKey the copyKey to set
	 */
	public void setCopyKey(String copyKey) {
		this.copyKey = copyKey;
	}

	/**
	 * @return the promotionLookAndFeel
	 */
	public PromotionLookAndFeel getPromotionLookAndFeel() {
		return promotionLookAndFeel;
	}

	/**
	 * @param promotionLookAndFeel the promotionLookAndFeel to set
	 */
	public void setPromotionLookAndFeel(PromotionLookAndFeel promotionLookAndFeel) {
		this.promotionLookAndFeel = promotionLookAndFeel;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.promotionCopyId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		StringBuilder sb = new StringBuilder();
		if (this.languageCode != null && !this.languageCode.isEmpty()) {
			sb.append(this.languageCode);
			sb.append(".");
		}
		
		if (this.countryCode != null) {
			sb.append(this.countryCode);
			sb.append(".");
		}
		
		sb.append(this.copyKey);
		return sb.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#validate()
	 */
	public Map<String, String> validate() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();
		if (this.copyKey == null || this.copyKey.isEmpty()) {
			validationErrors.put("copyKey", "copyKey cannot be null or empty");
		}		
		return validationErrors;
	}
}