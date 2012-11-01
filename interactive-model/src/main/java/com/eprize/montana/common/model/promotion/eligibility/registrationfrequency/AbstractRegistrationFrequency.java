package com.eprize.montana.common.model.promotion.eligibility.registrationfrequency;

import java.util.Map;
import java.util.TreeMap;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Weeks;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.promotion.Promotion;
import com.eprize.montana.common.model.promotion.eligibility.PromotionEligibility;
import com.eprize.montana.common.model.registration.RegistrantVisit;
import com.eprize.montana.common.model.user.Registrant;

/**
 * 
 * @author Tom.Myers
 *
 */
public abstract class AbstractRegistrationFrequency extends DomainObject {
	
	/* */
	private static final long serialVersionUID = 1L;
	
	/** */
	public static final String ONE_TIME_ONLY = "ONE_TIME_ONLY";
	
	/** */
	public static final String DAILY = "DAILY";
	
	/** */
	public static final String WEEKLY = "WEEKLY";
	
	/** */
	public static final String MONTHLY = "MONTHLY";
	
	/* */
	private Long registrationFrequencyId;
	
	/* */
	private String value;	
	
	/* */
	private PromotionEligibility promotionEligibility;

	/**
	 *
	 */
	public AbstractRegistrationFrequency() {		
		
	}
	
	/**
	 * @return the registrationFrequencyId
	 */
	public Long getRegistrationFrequencyId() {
		return registrationFrequencyId;
	}

	/**
	 * @param registrationFrequencyId the registrationFrequencyId to set
	 */
	public void setRegistrationFrequencyId(Long registrationFrequencyId) {
		this.registrationFrequencyId = registrationFrequencyId;
	}
	
	/**
	 * @param value
	 */
	public AbstractRegistrationFrequency(String value) {
		this.value = value;
	}
			
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the promotionEligibility
	 */
	public PromotionEligibility getPromotionEligibility() {
		return promotionEligibility;
	}

	/**
	 * @param promotionEligibility the promotionEligibility to set
	 */
	public void setPromotionEligibility(PromotionEligibility promotionEligibility) {
		this.promotionEligibility = promotionEligibility;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.registrationFrequencyId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		return this.value;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#validate()
	 */
	public Map<String, String> validate() {

		Map<String, String> validationErrors = new TreeMap<String, String>();
		
		if (this.value == null) {
			validationErrors.put("value", "Cannot be null");
		} else if (this.value.isEmpty()) {
			validationErrors.put("value", "Cannot be empty");
		} else if (!this.value.equals(ONE_TIME_ONLY)
				&& !this.value.equals(DAILY)
				&& !this.value.equals(WEEKLY)
				&& !this.value.equals(MONTHLY)) {
			validationErrors.put("value", "Unsupported value: " +  value);
		}
		
		return validationErrors;
	}
	
	/**
	 * 
	 * @param registrantVisitList
	 * @return
	 */
	public boolean isEligibleForRegistration(Registrant registrant) {

		RegistrantVisit mostRecentGamePlayVisit = registrant.getMostRecentGamePlayRegistrantVisit();
		if (mostRecentGamePlayVisit != null) {

			// Get the second to last registration visit before the current one (which just happened) and check against the registration frequency limit.
			DateMidnight priorMostRecentRegistrantGamePlayVisitDate = new DateMidnight(mostRecentGamePlayVisit.getRegistrationDate().getTime());
			
			Promotion promotion = this.promotionEligibility.getPromotion();
			DateTime currentdate = new DateTime(promotion.getTimekeeper().getCurrentTimeInMillis());

			if (this.getValue().equals(ONE_TIME_ONLY)) {
				
				return false; // If here, then the registrant has already registered and is in a "login" (or "identification" state).
				// That is, the number of visits for the registrant at this point is greater than 1, and this type of registration frequency
				// is one time only.
				
			} else if (this.getValue().equals(DAILY)) {
				
				Days days = Days.daysBetween(priorMostRecentRegistrantGamePlayVisitDate, currentdate);
				if (days.getDays() < 1) {
					return false;
				}
				
			} else if (this.getValue().equals(WEEKLY)) {

				Weeks weeks = Weeks.weeksBetween(priorMostRecentRegistrantGamePlayVisitDate, currentdate);
				if (weeks.getWeeks() < 1) {
					return false;
				}
				
			} else if (this.getValue().equals(MONTHLY)) {

				Months months = Months.monthsBetween(priorMostRecentRegistrantGamePlayVisitDate, currentdate);
				if (months.getMonths() < 1) {
					return false;
				}
			}
		}
		
		return true;
	}
}