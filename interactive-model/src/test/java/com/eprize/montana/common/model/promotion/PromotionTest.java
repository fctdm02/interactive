package com.eprize.montana.common.model.promotion;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.eprize.montana.common.MontanaCommonConstants;
import com.eprize.montana.common.MontanaCommonHelper;
import com.eprize.montana.common.model.DomainObjectFactory;
import com.eprize.montana.common.model.exception.ObjectNotFoundException;
import com.eprize.montana.common.model.prize.PrizeGroup;
import com.eprize.montana.common.model.prize.awarding.WinningGamePlayResult;
import com.eprize.montana.common.model.prize.entry.GamePlayEntry;
import com.eprize.montana.common.model.prize.pool.AbstractPrizePool;
import com.eprize.montana.common.model.prize.pool.sweepstakes.SweepstakesPrizeDrawing;
import com.eprize.montana.common.model.prize.pool.sweepstakes.SweepstakesPrizePool;
import com.eprize.montana.common.model.product.AbstractProduct;
import com.eprize.montana.common.model.product.prized.AbstractPrizedProduct;
import com.eprize.montana.common.model.product.prized.instantwin.AbstractInstantWinProduct;
import com.eprize.montana.common.model.product.prized.instantwin.CasinoInstantWinProduct;
import com.eprize.montana.common.model.product.prized.instantwin.RandomInstantWinProduct;
import com.eprize.montana.common.model.product.prized.instantwin.ShuffleInstantWinProduct;
import com.eprize.montana.common.model.product.prized.instantwin.SlotMachineInstantWinProduct;
import com.eprize.montana.common.model.product.prized.sweepstakes.SweepstakesProduct;
import com.eprize.montana.common.model.promotion.state.AbstractPromotionState;
import com.eprize.montana.common.model.promotion.state.ArchivedPromotionState;
import com.eprize.montana.common.model.promotion.state.CompletedPromotionState;
import com.eprize.montana.common.model.promotion.state.ConceptPromotionState;
import com.eprize.montana.common.model.promotion.state.DraftPromotionState;
import com.eprize.montana.common.model.promotion.state.LivePromotionState;
import com.eprize.montana.common.model.promotion.state.PendingPromotionState;
import com.eprize.montana.common.model.registration.RegistrantVisit;
import com.eprize.montana.common.model.user.Registrant;

/**
 * 
 * @author Tom.Myers
 *
 */
public class PromotionTest implements MontanaCommonConstants {
	
	protected Promotion promotion;
	
	@Before
	public void setUp() throws Exception {
		
		promotion = MontanaCommonHelper.createTestPromotion();
	}

	@Test
	public void test_validate_NoArgConstructor() throws Exception {
		promotion = new Promotion();
		Map<String, String> validationErrors = promotion.validate();
		Assert.assertNotNull(validationErrors);
		Assert.assertEquals("number of validation errors is incorrect", Integer.toString(3), Integer.toString(validationErrors.size()));
		Assert.assertEquals("validation errors are incorrect", "{client=Cannot be null, managingAccountCoordinator=Cannot be null, name=Cannot be null or empty}", validationErrors.toString());
	}
	
	@Test
	public void test_addSweepstakesProduct() throws Exception {
		SweepstakesProduct product = DomainObjectFactory.createSweepstakesProduct(promotion); 
		this.verifyAddProduct(product);
	}
	
	@Test
	public void test_addCasinoInstantWinProduct() throws Exception {
		CasinoInstantWinProduct product = DomainObjectFactory.createCasinoInstantWinProduct(promotion);
		this.verifyAddProduct(product);
	}

	@Test
	public void test_addRandomInstantWinProduct() throws Exception {
		RandomInstantWinProduct product = DomainObjectFactory.createRandomInstantWinProduct(promotion);
		this.verifyAddProduct(product);
	}
	
	@Test
	public void test_addShuffleInstantWinProduct() throws Exception {
		ShuffleInstantWinProduct product = DomainObjectFactory.createShuffleInstantWinProduct(promotion);
		this.verifyAddProduct(product);
	}
	
	@Test
	public void test_addSlotMachineInstantWinProduct() throws Exception {
		SlotMachineInstantWinProduct product = DomainObjectFactory.createSlotMachineInstantWinProduct(promotion);
		this.verifyAddProduct(product);
	}

	@Test(expected=IllegalStateException.class)
	public void test_addProduct_IllegalStateException() throws Exception {
		SweepstakesProduct product = DomainObjectFactory.createSweepstakesProduct(promotion);
		DomainObjectFactory.createSweepstakesProduct(promotion);
		this.verifyAddProduct(product);
	}
	
	@Test
	public void test_getProductByProductName() throws Exception {
		SweepstakesProduct product = DomainObjectFactory.createSweepstakesProduct(promotion);
		String productName = product.getName();
		AbstractProduct actualProduct = promotion.getProductByProductName(productName);
		Assert.assertNotNull("Product is null", actualProduct);
		Assert.assertEquals("getProductByProductName returned incorrect product", product, actualProduct);
	}

	@Test(expected=ObjectNotFoundException.class)
	public void test_getProductByProductName_ObjectNotFoundException() throws Exception {
		SweepstakesProduct product = DomainObjectFactory.createSweepstakesProduct(promotion);
		String productName = product.getName();
		promotion.removeProduct(productName);
		promotion.getProductByProductName(productName);
	}
	
	@Test
	public void test_getPrizedProducts() throws Exception {
		SweepstakesProduct swProduct = DomainObjectFactory.createSweepstakesProduct(promotion);
		AbstractInstantWinProduct iwProduct = DomainObjectFactory.createCasinoInstantWinProduct(promotion);
		Set<AbstractPrizedProduct> prizedProducts = promotion.getPrizedProducts();
		Assert.assertNotNull(prizedProducts);
		Assert.assertEquals("prizedProducts size is incorrect", Integer.toString(2), Integer.toString(prizedProducts.size()));
		Assert.assertTrue("Sweepstakes product not found in promotion", prizedProducts.contains(swProduct));
		Assert.assertTrue("Instant Win product not found in promotion", prizedProducts.contains(iwProduct));
	}
	
	@Test
	public void test_setProducts() throws Exception {
		Set<AbstractProduct> products = promotion.getProducts();
		promotion.setProducts(products);
		Assert.assertEquals("products getter/setter incorrect", products, promotion.getProducts());
	}
	
	@Test
	public void test_removeProduct() throws Exception {
		SweepstakesProduct swProduct = DomainObjectFactory.createSweepstakesProduct(promotion);
		AbstractInstantWinProduct iwProduct = DomainObjectFactory.createCasinoInstantWinProduct(promotion);
		Set<AbstractPrizedProduct> prizedProducts = promotion.getPrizedProducts();
		
		String productName = iwProduct.getName();
		promotion.removeProduct(productName);
		
		prizedProducts = promotion.getPrizedProducts();
		Assert.assertEquals("prizedProducts size is incorrect", Integer.toString(1), Integer.toString(prizedProducts.size()));
		Assert.assertTrue("Sweepstakes product not found in promotion", prizedProducts.contains(swProduct));
	}
	
	@Test(expected=ObjectNotFoundException.class)
	public void test_removeProduct_ObjectNotFoundException() throws Exception {
		DomainObjectFactory.createSweepstakesProduct(promotion);
		DomainObjectFactory.createCasinoInstantWinProduct(promotion);
		
		String productName = "dummy_product_name";
		promotion.removeProduct(productName);
	}
	
	@Test
	public void test_getPrizeGroups() throws Exception {
		Set<PrizeGroup> prizeGroups = promotion.getPrizeGroups();
		Assert.assertNotNull("prize groups is null", prizeGroups);
	}
	
	@Test
	public void test_setPrizeGroups() throws Exception {
		Set<PrizeGroup> expectedPrizeGroups = promotion.getPrizeGroups();
		promotion.setPrizeGroups(expectedPrizeGroups);
		Set<PrizeGroup> actualPrizeGroups = promotion.getPrizeGroups();
		Assert.assertNotNull("prize groups is null", actualPrizeGroups);
		Assert.assertEquals("prize groups is incorrect", expectedPrizeGroups.toString(), actualPrizeGroups.toString());
	}

	private void verifyAddProduct(AbstractProduct product) throws Exception {
		
		Assert.assertNotNull("Product is null", product);
		Promotion actualParentPromotion = product.getPromotion();
		Assert.assertEquals("Product parent promotion is incorrect", promotion, actualParentPromotion);
	}
	
	@Test
	public void test_validateBasePromotionDetails() throws Exception {
		
		// STEP 1: Arrange.
		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);
		
		// STEP 2: Act.
		Map<String, String> validationErrors = promotion.validateBasePromotionDetails();
				
		// STEP 3: Assert.
		Assert.assertEquals("Base Promotion Details validation warnings is incorrect", "{}", validationErrors.toString());
	}

	@Test
	public void test_validateBasePromotionDetails_nullConceptDate() throws Exception {
		
		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);

		promotion.setConceptDate(null);
		
		Map<String, String> validationErrors = promotion.validateBasePromotionDetails();
		
		Assert.assertFalse("Base Promotion Details validation warnings is incorrect", validationErrors.isEmpty());
		Assert.assertEquals("Base Promotion Details validation warnings is incorrect", Integer.toString(1), Integer.toString(validationErrors.size()));
	}

	@Test
	public void test_validateBasePromotionDetails_nullLaunchDate() throws Exception {
		
		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);

		promotion.setLaunchDate(null);
		
		Map<String, String> validationErrors = promotion.validateBasePromotionDetails();
		
		Assert.assertFalse("Base Promotion Details validation warnings is incorrect", validationErrors.isEmpty());
		Assert.assertEquals("Base Promotion Details validation warnings is incorrect", Integer.toString(1), Integer.toString(validationErrors.size()));
	}

	@Test
	public void test_validateBasePromotionDetails_nullEndDate() throws Exception {
		
		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);

		promotion.setEndDate(null);
		
		Map<String, String> validationErrors = promotion.validateBasePromotionDetails();
		
		Assert.assertFalse("Base Promotion Details validation warnings is incorrect", validationErrors.isEmpty());
		Assert.assertEquals("Base Promotion Details validation warnings is incorrect", Integer.toString(1), Integer.toString(validationErrors.size()));
	}

	@Test
	public void test_validateBasePromotionDetails_nullArchiveDate() throws Exception {
		
		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);

		promotion.setArchiveDate(null);
		
		Map<String, String> validationErrors = promotion.validateBasePromotionDetails();
		
		Assert.assertFalse("Base Promotion Details validation warnings is incorrect", validationErrors.isEmpty());
		Assert.assertEquals("Base Promotion Details validation warnings is incorrect", Integer.toString(1), Integer.toString(validationErrors.size()));
	}

	@Test
	public void test_validateBasePromotionDetails_invalidConceptDate() throws Exception {
		
		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);

		promotion.setConceptDate(promotion.getLaunchDate());
		
		Map<String, String> validationErrors = promotion.validateBasePromotionDetails();
		
		Assert.assertFalse("Base Promotion Details validation warnings is incorrect", validationErrors.isEmpty());
		Assert.assertEquals("Base Promotion Details validation warnings is incorrect", Integer.toString(1), Integer.toString(validationErrors.size()));
	}
	
	@Test
	public void test_validateBasePromotionDetails_invalidLaunchDate() throws Exception {
		
		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);

		promotion.setLaunchDate(promotion.getConceptDate());
		
		Map<String, String> validationErrors = promotion.validateBasePromotionDetails();
		
		Assert.assertFalse("Base Promotion Details validation warnings is incorrect", validationErrors.isEmpty());
		Assert.assertEquals("Base Promotion Details validation warnings is incorrect", Integer.toString(1), Integer.toString(validationErrors.size()));
	}

	@Test
	public void test_validateBasePromotionDetails_invalidEndDate() throws Exception {
		
		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);

		promotion.setEndDate(promotion.getConceptDate());
		
		Map<String, String> validationErrors = promotion.validateBasePromotionDetails();
		
		Assert.assertFalse("Base Promotion Details validation warnings is incorrect", validationErrors.isEmpty());
		Assert.assertEquals("Base Promotion Details validation warnings is incorrect", Integer.toString(3), Integer.toString(validationErrors.size()));
	}

	@Test
	public void test_validateBasePromotionDetails_invalidArchiveDate() throws Exception {
		
		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);

		promotion.setArchiveDate(promotion.getConceptDate());
		
		Map<String, String> validationErrors = promotion.validateBasePromotionDetails();
		
		Assert.assertFalse("Base Promotion Details validation warnings is incorrect", validationErrors.isEmpty());
		Assert.assertEquals("Base Promotion Details validation warnings is incorrect", Integer.toString(4), Integer.toString(validationErrors.size()));
	}
	
	@Test
	public void getTimezone() throws Exception {
		
		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);
		
		promotion.setTimezone(Promotion.DEFAULT_TIMEZONE_AMERICA_NEW_YORK);
		
		String timezone = promotion.getTimezone();
		Assert.assertEquals("timezone is incorrect", Promotion.DEFAULT_TIMEZONE_AMERICA_NEW_YORK, timezone);
	}

	@Test
	public void setTimezone() throws Exception {
		
		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);
		
		promotion.setTimezone(Promotion.TIMEZONE_AMERICA_CHICAGO);
		
		String timezone = promotion.getTimezone();
		Assert.assertEquals("timezone is incorrect", Promotion.TIMEZONE_AMERICA_CHICAGO, timezone);
		
		TimeZone expectedTimeZone = TimeZone.getTimeZone(Promotion.TIMEZONE_AMERICA_CHICAGO);
		TimeZone actualTimeZone = promotion.getTimekeeper().getTimeZone();
		Assert.assertNotNull(expectedTimeZone);
		Assert.assertNotNull(actualTimeZone);
		Assert.assertEquals("Timezone object is incorrect", expectedTimeZone, actualTimeZone);
	}
	
	
	
	@Test
	public void test_getPercentageComplete() throws Exception {
		
		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);
		MontanaCommonHelper.addConfiguredSweepstakesProductToTestPromotion(promotion);

		float percentageComplete = promotion.getPercentageComplete();
		
		Assert.assertTrue("percentageComplete is incorrect", percentageComplete > 0);
	}
	
	@Test
	public void test_getPromotionState_conceptState() throws Exception {
		
		AbstractPromotionState promotionState = promotion.getPromotionState();
		
		Assert.assertEquals("Promotion State is incorrect", new ConceptPromotionState(), promotionState);
	}
	
	@Test
	public void test_evaluatePromotionState_draftState() throws Exception {

		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);
		MontanaCommonHelper.addConfiguredSweepstakesProductToTestPromotion(promotion);

		
		AbstractPromotionState promotionState = promotion.evaluatePromotionState();
		
		
		Assert.assertEquals("Promotion State is incorrect", new DraftPromotionState(), promotionState);
	}
	
	@Test
	public void test_evaluatePromotionState_pendingState() throws Exception {

		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);
		MontanaCommonHelper.addConfiguredSweepstakesProductToTestPromotion(promotion);

		promotion.setHasBeenDeployed(Boolean.TRUE);
		
		AbstractPromotionState promotionState = promotion.evaluatePromotionState();
		
		Assert.assertEquals("Promotion State is incorrect", new PendingPromotionState(), promotionState);
	}	

	@Test
	public void test_evaluatePromotionState_liveState() throws Exception {

		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);
		MontanaCommonHelper.addConfiguredSweepstakesProductToTestPromotion(promotion);

		promotion.setHasBeenDeployed(Boolean.TRUE);

		promotion.getTimekeeper().setTimeToLaunchDate();
		
		AbstractPromotionState promotionState = promotion.evaluatePromotionState();
		
		Assert.assertEquals("Promotion State is incorrect", new LivePromotionState(), promotionState);
	}
	
	@Test
	public void test_evaluatePromotionState_completedState() throws Exception {

		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);
		MontanaCommonHelper.addConfiguredSweepstakesProductToTestPromotion(promotion);

		promotion.setHasBeenDeployed(Boolean.TRUE);

		promotion.getTimekeeper().setTimeToEndDate();
		
		AbstractPromotionState promotionState = promotion.evaluatePromotionState();
		
		Assert.assertEquals("Promotion State is incorrect", new CompletedPromotionState(), promotionState);
	}
	
	@Test
	public void test_evaluatePromotionState_archivedState() throws Exception {

		MontanaCommonHelper.addBaseDetailsToTestPromotion(promotion);
		MontanaCommonHelper.addConfiguredSweepstakesProductToTestPromotion(promotion);

		promotion.setHasBeenDeployed(Boolean.TRUE);

		promotion.getTimekeeper().setTimeToArchiveDate();
		
		AbstractPromotionState promotionState = promotion.evaluatePromotionState();
		
		Assert.assertEquals("Promotion State is incorrect", new ArchivedPromotionState(), promotionState);
	}
	
	@Test
	public void test_evaluatePromotionState_live_sweepsGamePlay_trivial() throws Exception {

		// Get the promotion into the live state.
		MontanaCommonHelper.configureAndLaunchTestSweepstakesPromotionEndDrawingToLiveState(promotion);
		
		// Go through the promotion workflow as a first time user.
		int registrantIndex = 1;
		Registrant registrant = MontanaCommonHelper.createTestRegistrant(promotion, registrantIndex);
		Assert.assertNotNull("Registrant wasn't added to promotion properly", registrant);

		// Add their first visit.
		registrant.createRegistrantVisit(ipAddressPrefix + registrantIndex, userAgent);
				
		// Get a game entry (sweeps entry) for this qualifying visit (since the registration frequency is once per day)
		SweepstakesProduct swProduct = promotion.getSweepstakesProduct();
		Set<GamePlayEntry> entries = promotion.getRegistrantGamePlayEntriesForProduct(swProduct, registrant);
		Assert.assertNotNull("entries is null", entries);
		Assert.assertFalse("entries is empty", entries.isEmpty());
		
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
		
		// Verify that we have a winner.
		Set<WinningGamePlayResult> winningGamePlayResults = promotion.getWinningGamePlayResults();
		Assert.assertEquals("Win list size is incorrect", Integer.toString(1), Integer.toString(winningGamePlayResults.size()));
		Iterator<WinningGamePlayResult> winningGamePlayResultsIterator = winningGamePlayResults.iterator();
		while (winningGamePlayResultsIterator.hasNext()) {
			
			WinningGamePlayResult winningGamePlayResult = winningGamePlayResultsIterator.next();
			
			GamePlayEntry gamePlayEntry = winningGamePlayResult.getGamePlayEntry();
			
			Registrant winningRegistrant = gamePlayEntry.getRegistrantVisit().getRegistrant();
			
			Assert.assertEquals("Prize Winner is incorrect", registrant, winningRegistrant);
		}
	}

	@Test
	public void test_evaluatePromotionState_live_sweepsGamePlay_200registrants_1000registrations() throws Exception {
		
		// Get the promotion into the live state.
		MontanaCommonHelper.configureAndLaunchTestSweepstakesPromotionEndDrawingToLiveState(promotion);
		java.sql.Date launchDate = promotion.getLaunchDate();

		// Get a reference to the sweeps for adding registrations.
		SweepstakesProduct swProduct = promotion.getSweepstakesProduct();
		
		int numberOfRegistrants = 200;
		int numberOfRegistrationsPerRegistrant = 5;
		for (int i=0; i < numberOfRegistrants; i++) {
			
			// Go through the promotion workflow as a first time user.
			promotion.getTimekeeper().setTimeToDate(launchDate);
			Registrant registrant = MontanaCommonHelper.createTestRegistrant(promotion, i);
			Assert.assertNotNull("Registrant wasn't added to promotion properly", registrant);
			
			for (int j=0; j < numberOfRegistrationsPerRegistrant; j++) {

				// Add visits (i.e. registrations)
				registrant.createRegistrantVisit(ipAddressPrefix + i, userAgent);
						
				// Get a game entry (sweeps entry) for this qualifying visit (since the registration frequency is once per day)
				Set<GamePlayEntry> entries = promotion.getRegistrantGamePlayEntriesForProduct(swProduct, registrant);
				Assert.assertNotNull("entries is null", entries);
				Assert.assertFalse("user: " + i + ", visit: " + j + ": entries is empty", entries.isEmpty());
				
				// Jump ahead one day because we are set to allow only one registration per day.
				java.sql.Date futureDate = promotion.getTimekeeper().getDateFromDatePlusNumberOfDays(launchDate, j+1);
				promotion.getTimekeeper().setTimeToDate(futureDate);
			}
		}
		
		
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
		
		// Verify that we have a winner.
		Set<WinningGamePlayResult> winningGamePlayResults = promotion.getWinningGamePlayResults();
		Assert.assertEquals("Win list size is incorrect", Integer.toString(1), Integer.toString(winningGamePlayResults.size()));
		
		// Verify that we have 200 registrants and 5 registrations each (for a total of 1000 registrations)
		Set<Registrant> completedStateRegistrants = promotion.getRegistrants();
		Assert.assertEquals("Number of registrants for promotion is incorrect", Integer.toString(200), Integer.toString(completedStateRegistrants.size()));
		Iterator<Registrant> completedStateRegistrantIterator = completedStateRegistrants.iterator();
		while (completedStateRegistrantIterator.hasNext()) {
			
			Registrant registrant = completedStateRegistrantIterator.next();
			
			Set<RegistrantVisit> registrantVisits = registrant.getRegistrantVisits();
			Assert.assertEquals("Number of registrant visits for registrant: [" + registrant + "] is incorrect", Integer.toString(5), Integer.toString(registrantVisits.size()));
		}
 	}
	
	@Test
	public void test_evaluatePromotionState_live_oddsBasedInstantwinGamePlay_trivial() throws Exception {

		// Get the promotion into the live state.
		int oddsForInstantWinAwarding = 1; // This means that the registrant is guaranteed to win.
		MontanaCommonHelper.configureAndLaunchTestOddsBasedInstantWinToLiveState(promotion, oddsForInstantWinAwarding);
		
		// Go through the promotion workflow as a first time user.
		int registrantIndex = 1;
		Registrant registrant = MontanaCommonHelper.createTestRegistrant(promotion, registrantIndex);
		Assert.assertNotNull("Registrant wasn't added to promotion properly", registrant);

		// Add their first visit.
		registrant.createRegistrantVisit(ipAddressPrefix + registrantIndex, userAgent);
				
		// Get a game entry for this qualifying visit (since the registration frequency is once per day)
		AbstractInstantWinProduct product = promotion.getInstantWinProduct();
		Set<GamePlayEntry> entries = promotion.getRegistrantGamePlayEntriesForProduct(product, registrant);
		Assert.assertNotNull("entries is null", entries);
		Assert.assertFalse("entries is empty", entries.isEmpty());
		
		// Verify that we have a winner.
		Set<WinningGamePlayResult> winningGamePlayResults = promotion.getWinningGamePlayResults();
		Assert.assertEquals("Win list size is incorrect", Integer.toString(1), Integer.toString(winningGamePlayResults.size()));
		Iterator<WinningGamePlayResult> winningGamePlayResultsIterator = winningGamePlayResults.iterator();
		while (winningGamePlayResultsIterator.hasNext()) {
			
			WinningGamePlayResult winningGamePlayResult = winningGamePlayResultsIterator.next();
			
			GamePlayEntry gamePlayEntry = winningGamePlayResult.getGamePlayEntry();
			
			Registrant winningRegistrant = gamePlayEntry.getRegistrantVisit().getRegistrant();
			
			Assert.assertEquals("Prize Winner is incorrect", registrant, winningRegistrant);
		}
	}
}