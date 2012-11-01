package com.eprize.montana.common.model.user;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.eprize.montana.common.MontanaCommonConstants;
import com.eprize.montana.common.MontanaCommonHelper;
import com.eprize.montana.common.model.location.MailingAddress;
import com.eprize.montana.common.model.promotion.Promotion;

/**
 * 
 * @author Tom.Myers
 *
 */
public class RegistrantTest implements MontanaCommonConstants {
	
	protected Promotion promotion;
	protected Registrant registrant;
	
	@Before
	public void setUp() throws Exception {
		
		promotion = MontanaCommonHelper.createTestPromotion();
		
		int registrantIndex = 1;		
		registrant = MontanaCommonHelper.createTestRegistrant(promotion, registrantIndex);
	}

	@Test
	public void test_validate_NoArgConstructor() throws Exception {
		registrant = new Registrant();
		Map<String, String> validationErrors = registrant.validate();
		Assert.assertNotNull(validationErrors);
		Assert.assertEquals("number of validation errors is incorrect", Integer.toString(2), Integer.toString(validationErrors.size()));
		Assert.assertEquals("validation errors are incorrect", "{emailAddress=Cannot be null, promotion=Cannot be null}", validationErrors.toString());
	}
	
	@Test
	public void test_setMailingAddress() throws Exception {

		// Get the promotion into the live state.
		MontanaCommonHelper.configureAndLaunchTestSweepstakesPromotionEndDrawingToLiveState(promotion);
		
		// Go through the promotion workflow as a first time user.
		int registrantIndex = 1;
		String emailAddress = registrantOriginalEmailAddressPrefix + registrantIndex + registrantOriginalEmailAddressSuffix;
		Assert.assertNotNull("Registrant wasn't added to promotion properly", promotion.getRegistrantByEmailAddressNullIfNotExists(emailAddress));

		// Add their first visit.
		registrant.createRegistrantVisit(ipAddressPrefix + registrantIndex, userAgent);
				
		MailingAddress mailingAddress = MontanaCommonHelper.createMailingAddressForRegistrant(
			registrant, 
			street1, 
			street2, 
			city);
			
		Assert.assertNotNull("mailing address is null", mailingAddress);
		Assert.assertEquals("Mailing address is incorrect", mailingAddress, registrant.getMailingAddress());
	}
	
	@Test
	public void test_getMailingAddressAsString() throws Exception {
		
		// Get the promotion into the live state.
		MontanaCommonHelper.configureAndLaunchTestSweepstakesPromotionEndDrawingToLiveState(promotion);
		
		// Go through the promotion workflow as a first time user.
		int registrantIndex = 1;
		String emailAddress = registrantOriginalEmailAddressPrefix + registrantIndex + registrantOriginalEmailAddressSuffix;
		Assert.assertNotNull("Registrant wasn't added to promotion properly", promotion.getRegistrantByEmailAddressNullIfNotExists(emailAddress));

		// Add their first visit.
		registrant.createRegistrantVisit(ipAddressPrefix + registrantIndex, userAgent);
				
		MailingAddress mailingAddress = MontanaCommonHelper.createMailingAddressForRegistrant(
			registrant, 
			street1, 
			street2, 
			city);
			
		Assert.assertNotNull("mailing address is null", mailingAddress);
		StringBuffer sb = new StringBuffer();
		sb.append(street1);
		sb.append(" ");
		if (street2 != null && !street2.isEmpty()) {
			sb.append(street2);
			sb.append(" ");
		}
		sb.append(city);
		sb.append(" ");
		sb.append(stateAbbreviation);
		sb.append(" ");
		sb.append(zipcode);
		sb.append(" ");
		sb.append(countryAbbreviation);
		String expected = sb.toString();
		Assert.assertEquals("Mailing address as formatted string is incorrect", expected, registrant.getMailingAddress().getMailingAddressAsString());
	}
	
}