package com.eprize.montana.common;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Set;

import junit.framework.Assert;

import com.eprize.montana.common.model.DomainObjectFactory;
import com.eprize.montana.common.model.client.Client;
import com.eprize.montana.common.model.location.Country;
import com.eprize.montana.common.model.location.MailingAddress;
import com.eprize.montana.common.model.location.StateOrProvince;
import com.eprize.montana.common.model.location.ZipOrPostalCode;
import com.eprize.montana.common.model.prize.AbstractPrize;
import com.eprize.montana.common.model.prize.awarding.WinningGamePlayResult;
import com.eprize.montana.common.model.prize.entry.GamePlayEntry;
import com.eprize.montana.common.model.prize.pool.AbstractPrizePool;
import com.eprize.montana.common.model.prize.pool.sweepstakes.SweepstakesPrizeDrawing;
import com.eprize.montana.common.model.prize.pool.sweepstakes.SweepstakesPrizePool;
import com.eprize.montana.common.model.product.prized.AbstractPrizedProduct;
import com.eprize.montana.common.model.product.prized.instantwin.AbstractInstantWinProduct;
import com.eprize.montana.common.model.product.prized.sweepstakes.SweepstakesProduct;
import com.eprize.montana.common.model.promotion.Promotion;
import com.eprize.montana.common.model.promotion.state.AbstractPromotionState;
import com.eprize.montana.common.model.promotion.state.LivePromotionState;
import com.eprize.montana.common.model.promotion.time.NonProductionModeTimekeeper;
import com.eprize.montana.common.model.registration.RegistrantVisit;
import com.eprize.montana.common.model.user.AccountCoordinator;
import com.eprize.montana.common.model.user.Registrant;

/**
 * 
 * @author Tom.Myers
 *
 */
public class MontanaCommonHelper implements MontanaCommonConstants {

	/**
	 * @param registrant
	 */
	public static MailingAddress createMailingAddressForRegistrant(
		Registrant registrant,
		String street1,
		String street2,
		String city) {
		
		Country country = MontanaCommonHelper.createTestCountry();
		StateOrProvince stateOrProvince = MontanaCommonHelper.createTestStateOrProvince();
		ZipOrPostalCode zipOrPostalCode = MontanaCommonHelper.createTestZipOrPostalCode();
		
		country.addStateOrProvince(stateOrProvince);
		stateOrProvince.setCountry(country);
		stateOrProvince.addZipOrPostalCode(zipOrPostalCode);
		zipOrPostalCode.setStateOrProvince(stateOrProvince);
		
		MailingAddress mailingAddress = DomainObjectFactory.createMailingAddress(
			street1, 
			street2, 
			city, 
			zipOrPostalCode);
		
		registrant.setMailingAddress(mailingAddress);
		
		return mailingAddress;
	}

	/**
	 * 
	 * @return
	 */
	public static ZipOrPostalCode createTestZipOrPostalCode() {
		
		ZipOrPostalCode zipOrPostalCode = new ZipOrPostalCode();
		
		zipOrPostalCode.setZipOrPostalCodeValue(zipcode);
				
		return zipOrPostalCode;
	}
	
	/**
	 * @param zipOrPostalCode
	 * @return
	 */
	public static StateOrProvince createTestStateOrProvince() {
		
		StateOrProvince stateOrProvince = new StateOrProvince();
		
		stateOrProvince.setStateName(stateName);
		stateOrProvince.setStateAbbreviation(stateAbbreviation);
		
		return stateOrProvince;
	}
	/**
	 * 
	 * @return
	 */
	public static Country createTestCountry() {
		
		Country country = new Country();
		
		country.setCountryAbbreviation(countryAbbreviation);
		country.setCountryCode(countryCode);
		country.setCountryName(countryName);
		
		return country;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static AccountCoordinator createTestAccountCoordinator() throws Exception {

		return DomainObjectFactory.createAccountCoordinator(
			accountCoordinatorUsername, 
			accountCoordinatorPassword, 
			accountCoordinatorPasswordVerify, 
			team);
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Client createTestClient() throws Exception {
		
		return DomainObjectFactory.createClient(
			shortName, 
			legalName, 
			legalMailingAddress, 
			objective, 
			licenseNumber, 
			taxId);
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Promotion createTestPromotion() throws Exception {
	
		AccountCoordinator managingAccountCoordinator = MontanaCommonHelper.createTestAccountCoordinator();
		
		Client client = MontanaCommonHelper.createTestClient();
		
		Promotion promotion = DomainObjectFactory.createPromotion(
			managingAccountCoordinator, 
			client, 
			promotionName,
			projectNumber);
		
		promotion.setTimekeeper(new NonProductionModeTimekeeper());
		
		return promotion;
	}
	
	/**
	 * 
	 * @param promotion
	 * @throws Exception
	 */
	public static void addBaseDetailsToTestPromotion(Promotion promotion) throws Exception {
		
		promotion.setLaunchDateFromConceptDate(10);
		promotion.setEndDateFromLaunchDate(90);
		promotion.setArchiveDateFromEndDate(60);
		promotion.setOptOutUrl("http://www.eprize.com/montana/optOutUrl?promo=" + URLEncoder.encode(promotion.getName(), "UTF-8"));
		promotion.setProjectNumber(projectNumber);
		DomainObjectFactory.createMicrositeDeliveryChannel(promotion);
	}

	/**
	 * 
	 * @param promotion
	 */
	public static void configureAndLaunchTestSweepstakesPromotionEndDrawingToLiveState(Promotion promotion) throws Exception {
	
		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);
		MontanaCommonHelper.addConfiguredSweepstakesProductToTestPromotion(promotion);
		promotion.setHasBeenDeployed(Boolean.TRUE);
		promotion.getTimekeeper().setTimeToLaunchDate();
		AbstractPromotionState promotionState = promotion.evaluatePromotionState();
		Assert.assertEquals("Promotion State is incorrect", new LivePromotionState(), promotionState);
	}
	
	/**
	 * @param promotion
	 * @param oddsForInstantWinAwarding
	 */
	public static void configureAndLaunchTestOddsBasedInstantWinToLiveState(Promotion promotion, int oddsForInstantWinAwarding) throws Exception {
	
		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);
		MontanaCommonHelper.addConfiguredOddsBasedInstantWinProductToTestPromotion(promotion, oddsForInstantWinAwarding);
		promotion.setHasBeenDeployed(Boolean.TRUE);
		promotion.getTimekeeper().setTimeToLaunchDate();
		AbstractPromotionState promotionState = promotion.evaluatePromotionState();
		Assert.assertEquals("Promotion State is incorrect", new LivePromotionState(), promotionState);
	}
	
	/**
	 * 
	 * @param promotion
	 * @throws Exception
	 */
	public static void addConfiguredSweepstakesProductToTestPromotion(Promotion promotion) throws Exception {

		SweepstakesProduct sweepstakesProduct = DomainObjectFactory.createSweepstakesProduct(promotion);

		AbstractPrize prize = createDigitalPrize();
		
		DomainObjectFactory.createSweepstakesPrizePoolWithPromotionConclusionDrawing(
			defaultSweepstakesPrizePoolName,
		    sweepstakesProduct, 
		    prize);
	}

	/**
	 * 
	 * @param promotion
	 * @param oddsForInstantWinAwarding
	 * @throws Exception
	 */
	public static void addConfiguredOddsBasedInstantWinProductToTestPromotion(Promotion promotion, int oddsForInstantWinAwarding) throws Exception {

		java.sql.Date constrainStartDate = promotion.getLaunchDate();
		java.sql.Date constrainEndDate = promotion.getEndDate();
		
		AbstractInstantWinProduct instantWinProduct = DomainObjectFactory.createRandomInstantWinProduct(promotion);

		AbstractPrize prize = createDigitalPrize();
		
		DomainObjectFactory.createInstantWinPrizePoolWithOddsBasedAwarding(
			defaultInstantWinPrizePoolName, 
			instantWinProduct, 
			prize, 
			constrainStartDate, 
			constrainEndDate,
			oddsForInstantWinAwarding);
	}
	
	/**
	 * 
	 * @return
	 */
	public static AbstractPrize createDigitalPrize() {
		
		return DomainObjectFactory.createDigitalPrize(
			prizeName, 
			prizeDescription, 
			quantity, 
			arv, 
			arvCurrency, 
			tenNineNineParty, 
			affidavitRequired, 
			isGrandPrize, 
			sendWinningEmail, 
			allowDifferentPrizeTypeWins, 
			numberOfRepickRounds, 
			individualWinLimit, 
			digitalCode);		
	}
	
	/**
	 * 
	 * @param promotion
	 * @param registrantIndex
	 * @return
	 */
	public static Registrant createTestRegistrant(Promotion promotion, int registrantIndex) {
		
		String emailAddress = registrantOriginalEmailAddressPrefix + registrantIndex + registrantOriginalEmailAddressSuffix;
		
		Registrant registrant = promotion.getRegistrantByEmailAddressNullIfNotExists(emailAddress);
		Assert.assertNull("Registrant found, when there shouldn't be any in newly built promotion", registrant);
		registrant = DomainObjectFactory.createRegistrantNoAddressNonReferredForPromotion(
			registrantFirstName, 
			registrantLastName + registrantIndex, 
			emailAddress, 
			birthMonth, 
			birthDay, 
			birthYear, 
			registrantGender, 
			originalMobilePhoneNumber, 
			unsubscribe, 
			primaryOptIn, 
			promotion);
		Assert.assertNotNull("Registrant wasn't added to promotion properly", promotion.getRegistrantByEmailAddressNullIfNotExists(emailAddress));
		
		return registrant;
	}

	/**
	 * @param promotion
	 * @param numberOfRegistrants
	 * @param numberOfRegistrationsPerRegistrant
	 * @param numberOfDaysToIncrementPerRegistration
	 */
	public static void performRegistrationsForProduct(
		Promotion promotion,
		AbstractPrizedProduct product,
		int numberOfRegistrants,
		int numberOfRegistrationsPerRegistrant,
		int numberOfDaysToIncrementPerRegistration) {
		
		java.sql.Date launchDate = promotion.getLaunchDate();
		for (int i=0; i < numberOfRegistrants; i++) {
			
			// Go through the promotion workflow as a first time user.
			promotion.getTimekeeper().setTimeToDate(launchDate);
			Registrant registrant = MontanaCommonHelper.createTestRegistrant(promotion, i);
			Assert.assertNotNull("Registrant wasn't added to promotion properly", registrant);
			
			for (int j=0; j < numberOfRegistrationsPerRegistrant; j++) {

				// Add visits (i.e. registrations)
				registrant.createRegistrantVisit(ipAddressPrefix + i, userAgent);
						
				// Get a game entry (sweeps entry) for this qualifying visit (since the registration frequency is once per day)
				promotion.getRegistrantGamePlayEntriesForProduct(product, registrant);
				/*
				Set<GamePlayEntry> entries = promotion.getRegistrantGamePlayEntriesForProduct(product, registrant);
				if (entries.size() > 0) {
					System.out.println(promotion.getTimekeeper().getCurrentDate() + ": user: " + i + ", visit: " + j + ": num entries: " + entries.size());	
				}
				*/
 								
				// Jump ahead one day because we are set to allow only one registration per day.
				java.sql.Date futureDate = promotion.getTimekeeper().getDateFromDatePlusNumberOfDays(launchDate, j + numberOfDaysToIncrementPerRegistration);
				promotion.getTimekeeper().setTimeToDate(futureDate);
			}
		}
	}

	/**
	 * 
	 * @param promotion
	 * @param swProduct
	 * @param numberOfRegistrants
	 * @param numberOfRegistrationsPerRegistrant
	 * @param expectedGamePlayEntries
	 * @param expectedWinningGamePlayEntries
	 */
	public static void fastForwardSweepstakesToConclusionDateDrawingAndValidate(
		Promotion promotion,
		SweepstakesProduct swProduct,
		int numberOfRegistrants,
		int numberOfRegistrationsPerRegistrant,
		int expectedGamePlayEntries,
		int expectedWinningGamePlayEntries) {
		
		
		// Fast forward to the end of the promotion and have the promotion perform the drawing (by virtue of the "evaluate" method)
		Iterator<AbstractPrizePool> prizePoolIterator = swProduct.getPrizePools().iterator();
		while (prizePoolIterator.hasNext()) {
			
			AbstractPrizePool prizePool = prizePoolIterator.next();
			Iterator<SweepstakesPrizeDrawing> sweepstakesPrizeDrawingsIterator = ((SweepstakesPrizePool)prizePool).getSweepstakesPrizeDrawings().iterator();
			
			while (sweepstakesPrizeDrawingsIterator.hasNext()) {
				
				SweepstakesPrizeDrawing sweepstakesPrizeDrawing = sweepstakesPrizeDrawingsIterator.next();
				
				java.sql.Date promotionConclusionDrawingDate = sweepstakesPrizeDrawing.getDrawingDate();
				promotion.getTimekeeper().setTimeToDate(promotionConclusionDrawingDate);
				promotion.evaluatePromotionState();
			}
		}

		// Verify expected number of registrants.
		Set<Registrant> completedStateRegistrants = promotion.getRegistrants();
		Assert.assertEquals("Number of registrants for promotion is incorrect", Integer.toString(numberOfRegistrants), Integer.toString(completedStateRegistrants.size()));
		Iterator<Registrant> completedStateRegistrantIterator = completedStateRegistrants.iterator();
		while (completedStateRegistrantIterator.hasNext()) {

			Registrant registrant = completedStateRegistrantIterator.next();
			Set<RegistrantVisit> registrantVisits = registrant.getRegistrantVisits();
			
			// Verify expected number of registrations per registrant.
			Assert.assertEquals("Number of registrant visits for registrant: [" + registrant + "] is incorrect", Integer.toString(numberOfRegistrationsPerRegistrant), Integer.toString(registrantVisits.size()));
		}
				
		// Verify expected number of game play entries.
		Set<GamePlayEntry> gamePlayEntries = swProduct.getGamePlayEntries();
		Assert.assertEquals("Game play entries size is incorrect", Integer.toString(expectedGamePlayEntries), Integer.toString(gamePlayEntries.size()));
		
		// Verify expected number of winning game play entries.
		Set<WinningGamePlayResult> winningGamePlayResults = promotion.getWinningGamePlayResults();
		Assert.assertEquals("Win list size is incorrect", Integer.toString(expectedWinningGamePlayEntries), Integer.toString(winningGamePlayResults.size()));
	}
}