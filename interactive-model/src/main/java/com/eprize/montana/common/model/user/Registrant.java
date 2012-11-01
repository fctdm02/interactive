/**
 * 
 */
package com.eprize.montana.common.model.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Years;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.location.MailingAddress;
import com.eprize.montana.common.model.promotion.Promotion;
import com.eprize.montana.common.model.registration.BirthDate;
import com.eprize.montana.common.model.registration.EmailAddress;
import com.eprize.montana.common.model.registration.MobilePhoneNumber;
import com.eprize.montana.common.model.registration.RegistrantVisit;

/**
 * 
 * @author tmyers
 * 
 */
public class Registrant extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;

	/** */
	public static final String MALE = "MALE";

	/** */
	public static final String FEMALE = "FEMALE";
	
	/* */
	private Long registrantId;
	
	/* */
	private String firstName;

	/* */
	private String lastName;
	
	/* */
	private EmailAddress emailAddress;
		
	/* */
	private MailingAddress mailingAddress;
	
	/* */
	private BirthDate birthDate;
		
	/* */
	private MobilePhoneNumber mobilePhoneNumber;
	
	/* */
	private String gender;
	
	/* */
	private boolean unsubscribe;
	
	/* */
	private boolean primaryOptIn;
	
	/* */
	private Registrant referringRegistrant;

	/* */
	private Set<RegistrantVisit> registrantVisits = new TreeSet<RegistrantVisit>();
	
	/* */
	private Promotion promotion;
	
	/**
	 * 
	 */
	public Registrant() {
		
	}
	
	/**
	 * @return the registrantId
	 */
	public Long getRegistrantId() {
		return this.registrantId;
	}

	/**
	 * @param registrantId the registrantId to set
	 */
	public void setRegistrantId(Long registrantId) {
		this.registrantId = registrantId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the emailAddress
	 */
	public EmailAddress getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(EmailAddress emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
	 * @return the mailingAddress
	 */
	public MailingAddress getMailingAddress() {
		return mailingAddress;
	}

	/**
	 * @param mailingAddress the mailingAddress to set
	 */
	public void setMailingAddress(MailingAddress mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	/**
	 * @return the birthDate
	 */
	public BirthDate getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(BirthDate birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the mobilePhoneNumber
	 */
	public MobilePhoneNumber getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	/**
	 * @param mobilePhoneNumber the mobilePhoneNumber to set
	 */
	public void setMobilePhoneNumber(MobilePhoneNumber mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	/**
	 * @return the unsubscribe
	 */
	public boolean isUnsubscribe() {
		return unsubscribe;
	}

	/**
	 * @param unsubscribe the unsubscribe to set
	 */
	public void setUnsubscribe(boolean unsubscribe) {
		this.unsubscribe = unsubscribe;
	}

	/**
	 * @return the primaryOptIn
	 */
	public boolean isPrimaryOptIn() {
		return primaryOptIn;
	}

	/**
	 * @param primaryOptIn the primaryOptIn to set
	 */
	public void setPrimaryOptIn(boolean primaryOptIn) {
		this.primaryOptIn = primaryOptIn;
	}

	/**
	 * @return the referringRegistrant
	 */
	public Registrant getReferringRegistrant() {
		return referringRegistrant;
	}

	/**
	 * @param referringRegistrant the referringRegistrant to set
	 */
	public void setReferringRegistrant(Registrant referringRegistrant) {
		this.referringRegistrant = referringRegistrant;
	}

	/**
	 * @return the registrantVisits
	 */
	public Set<RegistrantVisit> getRegistrantVisits() {
		return registrantVisits;
	}

	/**
	 * @param registrantVisits the registrantVisits to set
	 */
	public void setRegistrantVisits(Set<RegistrantVisit> registrantVisits) {
		this.registrantVisits = registrantVisits;
	}

	/**
	 * 
	 * @param ipAddress
	 * @param userAgent
	 * @param registrant
	 */
	public void createRegistrantVisit(
		String ipAddress,
		String userAgent) {
		
		RegistrantVisit registrantVisit = new RegistrantVisit();
		java.sql.Date currentDate = this.getPromotion().getTimekeeper().getCurrentDate();
		registrantVisit.setRegistrationDate(currentDate);
		
		registrantVisit.setIpAddress(ipAddress);
		registrantVisit.setUserAgent(userAgent);
		registrantVisit.setRegistrantVisitIndex(Integer.valueOf(this.registrantVisits.size()));
		
		this.addRegistrantVisit(registrantVisit);
	}
	
	/**
	 * 
	 * @param registrantVisit
	 */
	public void addRegistrantVisit(RegistrantVisit registrantVisit) {
		registrantVisit.setRegistrant(this);
		this.registrantVisits.add(registrantVisit);
	}
	
	/**
	 * 
	 * @return
	 */
	public RegistrantVisit getMostRecentVisit() {
		
		// TODO: TDM: Change the natural ordering for registrant visits to be by identification date.
		Object[] registrantVisitArray = this.registrantVisits.toArray();
		
		RegistrantVisit mostRecentVisit = null;
		
		if (registrantVisitArray.length > 0) {
			mostRecentVisit = (RegistrantVisit)registrantVisitArray[registrantVisitArray.length-1];
		}
		
		return mostRecentVisit;
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

	/**
	 * 
	 * @return
	 */
	public int getAge() {
		
		DateMidnight birthdate = new DateMidnight(
			this.birthDate.getBirthYear().intValue(), 
			this.birthDate.getBirthMonth().intValue(), 
			this.birthDate.getBirthDay().intValue());
		
		DateTime currentdate = new DateTime(this.promotion.getTimekeeper().getCurrentTimeInMillis());

		Years age = Years.yearsBetween(birthdate, currentdate);
		return age.getYears();
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		
	   return this.registrantId;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
   public String getNaturalIdentity() {
	   
		StringBuilder sb = new StringBuilder();
		sb.append(this.promotion.getNaturalIdentity());
		sb.append(NATURAL_IDENTITY_DELIMITER);
		sb.append(this.emailAddress.getNaturalIdentity());
		return sb.toString();
   }

    /*
     * (non-Javadoc)
     * @see com.eprize.montana.common.model.DomainObject#validate()
     */
    public Map<String, String> validate() {

		Map<String, String> validationErrors = new TreeMap<String, String>();
		
		if (this.promotion == null) {
			validationErrors.put("promotion", "Cannot be null");
		}
		
		if (this.emailAddress == null) {
			validationErrors.put("emailAddress", "Cannot be null");
		}
		
		return validationErrors;
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<String> getUniqueIpAddressesForRegistrantVisits() {
		
		Set<String> uniqueIpAddressesForRegistrantVisits = new HashSet<String>();
		
		Iterator<RegistrantVisit> registrantVisitIterator = this.registrantVisits.iterator();
		while (registrantVisitIterator.hasNext()) {
		
			RegistrantVisit registrantVisit = registrantVisitIterator.next();
			String ipAddress = registrantVisit.getIpAddress();
			uniqueIpAddressesForRegistrantVisits.add(ipAddress);
		}
		
		return uniqueIpAddressesForRegistrantVisits;
	}
	
	
	/**
	 * TODO: TDM: Investigate refactoring visits as a list instead of a set, as we already have an index member in place for them.
	 * 
	 * @return
	 */
	public List<RegistrantVisit> getRegistrantVisitsAsList() {
	
		List<RegistrantVisit> registrantVisitList = new ArrayList<RegistrantVisit>();
		registrantVisitList.addAll(this.registrantVisits);
		
		return registrantVisitList;
	}
	
	/**
	 * 
	 * @return The most recent registrant visit that is associated with a game play entry, null otherwise.
	 */
	public RegistrantVisit getMostRecentGamePlayRegistrantVisit() {
		
		RegistrantVisit mostRecentGamePlayRegistrantVisit = null;
		java.sql.Date mostRecentGamePlayEntryDate = null;
		Iterator<RegistrantVisit> iterator = this.registrantVisits.iterator();
		while (iterator.hasNext()) {
			
			RegistrantVisit registrantVisit = iterator.next();
			if (registrantVisit.getGamePlayEntry() != null) {
				java.sql.Date entryDate = registrantVisit.getGamePlayEntry().getEntryDate();
				if (mostRecentGamePlayEntryDate == null || entryDate.after(mostRecentGamePlayEntryDate)) {
					
					mostRecentGamePlayEntryDate = entryDate;
					mostRecentGamePlayRegistrantVisit = registrantVisit;
				}
			}
		}
		
		return mostRecentGamePlayRegistrantVisit;
	}
}