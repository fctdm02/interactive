package com.eprize.montana.common;

import com.eprize.montana.common.model.lookandfeel.PromotionViewConstants;
import com.eprize.montana.common.model.user.Registrant;

/**
 * 
 * @author Tom.Myers
 *
 */
public interface MontanaCommonConstants {
	
	// Country info
	String countryCode = "US";
	String countryName = "United States";
	String countryAbbreviation = "US";
	
	// State or Province info
	String stateName = "Michigan";
	String stateAbbreviation = "MI";
	
	// Zip or Postal Code info
	String zipcode = "48067";
	
	// Address info
	String street1 = "325 E. Seventh St.";
	String street2 = "";
	String city = "Royal Oak";
	
	// Account Coordinator info
	String accountCoordinatorUsername = "ac_user";
	String accountCoordinatorPassword = "password";
	String accountCoordinatorPasswordVerify = "password";
	String team = "West";
	
	// Client Administrator info
	String clientAdminUsername = "portal_user";
	String clientAdminPssword = "reverse";
	String clientAdminPasswordVerify = "reverse";
	boolean termsAndConditionsAccepted = true;
	
	// Client info
	String shortName = "acme";
	String legalName = "Acme Corporation";
	String legalMailingAddress = "123 Acme Way, Detroit MI 48201";
	String objective = "To sell more widgets";
	String licenseNumber = "123";
	String taxId = "456";

	// Promotion info
	String promotionName = "Promo Name";
	Long projectNumber = Long.valueOf(123l);
	
	// Prize pool info
	String defaultSweepstakesPrizePoolName = "default sweepstakes prize pool";
	String defaultInstantWinPrizePoolName = "default instant win prize pool";
	
	// Instant win awarding info
	int defaultOddsForInstantWinOddsAwarding = 1; 
	
	// Prize info
	String prizeName = "ACME Digital Prize"; 
	String prizeDescription = "This is the description for ACME Digital Prize";
	Integer quantity = Integer.valueOf(1);
	Double arv = Double.valueOf(100.0);
	String arvCurrency = PromotionViewConstants.USD_CURRENCY;
	String tenNineNineParty = "ePrize LLC";
	Boolean affidavitRequired = Boolean.FALSE;
	Boolean isGrandPrize = Boolean.FALSE;
	Boolean sendWinningEmail = Boolean.TRUE;
	Boolean allowDifferentPrizeTypeWins = Boolean.TRUE;
	Integer numberOfRepickRounds = Integer.valueOf(0);
	Integer individualWinLimit = Integer.valueOf(1);
	String digitalCode = "ABCDE";
	
	// Registrant info
	String registrantOriginalEmailAddressPrefix = "tmyers";
	String registrantOriginalEmailAddressSuffix = "@yahoo.com";
	String registrantFirstName = "Tom";
	String registrantLastName = "Myers";
	int birthMonth = 3;
    int birthDay = 30;
	int birthYear = 1969;
	String registrantGender = Registrant.MALE;
	String originalMobilePhoneNumber = "(313) 555-1212";
	boolean unsubscribe = false;
	boolean primaryOptIn = true;

	
	// Registrant Visit Info
	String ipAddressPrefix = "192.168.";
	String userAgent = "Mozilla 12.0";
		
}