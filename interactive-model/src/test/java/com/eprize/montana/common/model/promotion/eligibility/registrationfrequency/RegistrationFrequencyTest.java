package com.eprize.montana.common.model.promotion.eligibility.registrationfrequency;

import org.junit.Before;
import org.junit.Test;

import com.eprize.montana.common.MontanaCommonConstants;
import com.eprize.montana.common.MontanaCommonHelper;
import com.eprize.montana.common.model.product.prized.sweepstakes.SweepstakesProduct;
import com.eprize.montana.common.model.promotion.Promotion;

/**
 * 
 * @author Tom.Myers
 *
 */
public class RegistrationFrequencyTest implements MontanaCommonConstants {
	
	protected Promotion promotion;
	protected java.sql.Date launchDate;
	protected SweepstakesProduct swProduct;
	
	@Before
	public void setUp() throws Exception {
		
		promotion = MontanaCommonHelper.createTestPromotion();
		MontanaCommonHelper.configureAndLaunchTestSweepstakesPromotionEndDrawingToLiveState(promotion);
		launchDate = promotion.getLaunchDate();
		swProduct = promotion.getSweepstakesProduct();
	}

	@Test
	public void test_oneTimeOnlyRegistrationFrequency() throws Exception {

		promotion.getPromotionEligibility().setRegistrationFrequency(new OneTimeOnlyRegistrationFrequency());
		
		int numberOfRegistrants = 200;
		int numberOfRegistrationsPerRegistrant = 5;
		int numberOfDaysToIncrementPerRegistration = 1;
				
		MontanaCommonHelper.performRegistrationsForProduct(
			promotion,
			swProduct,
			numberOfRegistrants,
			numberOfRegistrationsPerRegistrant,
			numberOfDaysToIncrementPerRegistration);

		int expectedGamePlayEntries = 200;
		int expectedWinningGamePlayEntries = 1;
		MontanaCommonHelper.fastForwardSweepstakesToConclusionDateDrawingAndValidate(
			promotion, 
			swProduct,
			numberOfRegistrants,
			numberOfRegistrationsPerRegistrant,
			expectedGamePlayEntries,
			expectedWinningGamePlayEntries);
	}
	
	@Test
	public void test_dailyRegistrationFrequency() throws Exception {

		promotion.getPromotionEligibility().setRegistrationFrequency(new DailyRegistrationFrequency());
		
		int numberOfRegistrants = 200;
		int numberOfRegistrationsPerRegistrant = 5;
		int numberOfDaysToIncrementPerRegistration = 1;
				
		MontanaCommonHelper.performRegistrationsForProduct(
			promotion,
			swProduct,
			numberOfRegistrants,
			numberOfRegistrationsPerRegistrant,
			numberOfDaysToIncrementPerRegistration);

		int expectedGamePlayEntries = 1000;
		int expectedWinningGamePlayEntries = 1;
		MontanaCommonHelper.fastForwardSweepstakesToConclusionDateDrawingAndValidate(
			promotion, 
			swProduct,
			numberOfRegistrants,
			numberOfRegistrationsPerRegistrant,
			expectedGamePlayEntries,
			expectedWinningGamePlayEntries);
	}
	
	@Test
	public void test_weeklyRegistrationFrequency() throws Exception {

		promotion.getPromotionEligibility().setRegistrationFrequency(new WeeklyRegistrationFrequency());
		
		int numberOfRegistrants = 100;
		int numberOfRegistrationsPerRegistrant = 10;
		int numberOfDaysToIncrementPerRegistration = 1;
				
		MontanaCommonHelper.performRegistrationsForProduct(
			promotion,
			swProduct,
			numberOfRegistrants,
			numberOfRegistrationsPerRegistrant,
			numberOfDaysToIncrementPerRegistration);

		// Since we are pumping registrations once a day for 10 days and we have weekly, then each registrant should have exactly 2 game play entries.
		int expectedGamePlayEntries = 2 * numberOfRegistrants;
		int expectedWinningGamePlayEntries = 1;
		MontanaCommonHelper.fastForwardSweepstakesToConclusionDateDrawingAndValidate(
			promotion, 
			swProduct,
			numberOfRegistrants,
			numberOfRegistrationsPerRegistrant,
			expectedGamePlayEntries,
			expectedWinningGamePlayEntries);
	}
	
	@Test
	public void test_monthlyRegistrationFrequency() throws Exception {

		promotion.getPromotionEligibility().setRegistrationFrequency(new MonthlyRegistrationFrequency());
		
		int numberOfRegistrants = 100;
		int numberOfRegistrationsPerRegistrant = 90;
		int numberOfDaysToIncrementPerRegistration = 1;
				
		MontanaCommonHelper.performRegistrationsForProduct(
			promotion,
			swProduct,
			numberOfRegistrants,
			numberOfRegistrationsPerRegistrant,
			numberOfDaysToIncrementPerRegistration);

		// Since we are pumping registrations once a day for 90 days and we have weekly, then each registrant should have exactly 3 game play entries.
		int expectedGamePlayEntries = 3 * numberOfRegistrants;
		int expectedWinningGamePlayEntries = 1;
		MontanaCommonHelper.fastForwardSweepstakesToConclusionDateDrawingAndValidate(
			promotion, 
			swProduct,
			numberOfRegistrants,
			numberOfRegistrationsPerRegistrant,
			expectedGamePlayEntries,
			expectedWinningGamePlayEntries);
	}
}