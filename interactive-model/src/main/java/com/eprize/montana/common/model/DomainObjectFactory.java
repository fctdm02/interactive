/**
 * 
 */
package com.eprize.montana.common.model;

import java.sql.Date;

import com.eprize.montana.common.model.client.Client;
import com.eprize.montana.common.model.exception.ValidationException;
import com.eprize.montana.common.model.location.Country;
import com.eprize.montana.common.model.location.MailingAddress;
import com.eprize.montana.common.model.location.StateOrProvince;
import com.eprize.montana.common.model.location.ZipOrPostalCode;
import com.eprize.montana.common.model.lookandfeel.PromotionLookAndFeel;
import com.eprize.montana.common.model.lookandfeel.deliverychannel.AbstractDeliveryChannel;
import com.eprize.montana.common.model.lookandfeel.deliverychannel.MicrositeDeliveryChannel;
import com.eprize.montana.common.model.lookandfeel.form.LoginForm;
import com.eprize.montana.common.model.lookandfeel.form.RegistrationForm;
import com.eprize.montana.common.model.lookandfeel.form.TellAFriendForm;
import com.eprize.montana.common.model.lookandfeel.form.WinnerUpdateForm;
import com.eprize.montana.common.model.lookandfeel.form.field.AddressOneTextFormField;
import com.eprize.montana.common.model.lookandfeel.form.field.AddressTwoTextFormField;
import com.eprize.montana.common.model.lookandfeel.form.field.AgeRangeMultipleChoiceFormField;
import com.eprize.montana.common.model.lookandfeel.form.field.CaptchaFormField;
import com.eprize.montana.common.model.lookandfeel.form.field.CityTextFormField;
import com.eprize.montana.common.model.lookandfeel.form.field.ConfirmationEmailAddressFormField;
import com.eprize.montana.common.model.lookandfeel.form.field.CountryMultipleChoiceFormField;
import com.eprize.montana.common.model.lookandfeel.form.field.EmailAddressFormField;
import com.eprize.montana.common.model.lookandfeel.form.field.FirstNameTextFormField;
import com.eprize.montana.common.model.lookandfeel.form.field.GenderMultipleChoiceFormField;
import com.eprize.montana.common.model.lookandfeel.form.field.LastNameTextFormField;
import com.eprize.montana.common.model.lookandfeel.form.field.OptInBooleanChoiceFormField;
import com.eprize.montana.common.model.lookandfeel.form.field.RulesConsentBooleanChoiceFormField;
import com.eprize.montana.common.model.lookandfeel.form.field.StateOrProvinceMultipleChoiceFormField;
import com.eprize.montana.common.model.lookandfeel.form.field.ZipOrPostalCodeTextFormField;
import com.eprize.montana.common.model.lookandfeel.marketinggoal.CollectOptinsForListMarketingGoal;
import com.eprize.montana.common.model.lookandfeel.page.ConfirmationPage;
import com.eprize.montana.common.model.lookandfeel.page.GamePlayPage;
import com.eprize.montana.common.model.lookandfeel.page.LoginPage;
import com.eprize.montana.common.model.lookandfeel.page.RegistrationPage;
import com.eprize.montana.common.model.lookandfeel.page.TellAFriendPage;
import com.eprize.montana.common.model.lookandfeel.page.ThankYouPage;
import com.eprize.montana.common.model.lookandfeel.page.WinnerUpdatePage;
import com.eprize.montana.common.model.prize.AbstractPrize;
import com.eprize.montana.common.model.prize.DigitalPrize;
import com.eprize.montana.common.model.prize.awarding.WinningGamePlayResult;
import com.eprize.montana.common.model.prize.awarding.instantwin.OddsBasedInstantWinGamePlayAwarding;
import com.eprize.montana.common.model.prize.awarding.sweepstakes.ScheduledSweepstakesDrawingAwarding;
import com.eprize.montana.common.model.prize.entry.GamePlayEntry;
import com.eprize.montana.common.model.prize.fulfillment.EPrizePrizeFulfillment;
import com.eprize.montana.common.model.prize.pool.instantwin.InstantWinPrizePool;
import com.eprize.montana.common.model.prize.pool.sweepstakes.SweepstakesPrizePool;
import com.eprize.montana.common.model.product.prized.AbstractPrizedProduct;
import com.eprize.montana.common.model.product.prized.instantwin.AbstractInstantWinProduct;
import com.eprize.montana.common.model.product.prized.instantwin.CasinoInstantWinProduct;
import com.eprize.montana.common.model.product.prized.instantwin.RandomInstantWinProduct;
import com.eprize.montana.common.model.product.prized.instantwin.ShuffleInstantWinProduct;
import com.eprize.montana.common.model.product.prized.instantwin.SlotMachineInstantWinProduct;
import com.eprize.montana.common.model.product.prized.sweepstakes.SweepstakesProduct;
import com.eprize.montana.common.model.promotion.Promotion;
import com.eprize.montana.common.model.registration.BirthDate;
import com.eprize.montana.common.model.registration.EmailAddress;
import com.eprize.montana.common.model.registration.MobilePhoneNumber;
import com.eprize.montana.common.model.registration.RegistrantVisit;
import com.eprize.montana.common.model.user.AccountCoordinator;
import com.eprize.montana.common.model.user.ClearTextPassword;
import com.eprize.montana.common.model.user.ClientAdministrator;
import com.eprize.montana.common.model.user.Registrant;
import com.eprize.montana.common.model.user.state.IncompleteWinnerInfoRegistrantState;
import com.eprize.montana.common.model.user.state.LoggedInRegistrantState;
import com.eprize.montana.common.model.user.state.NotLoggedInRegistrantState;
import com.eprize.montana.common.model.user.state.PerformedSharingRegistrantState;
import com.eprize.montana.common.model.user.state.PlayedGameRegistrantState;
import com.eprize.montana.common.model.user.state.UnidentifiedRegistrantState;

/**
 * 
 * @author tmyers
 * 
 */
public class DomainObjectFactory {

	/**
	 * 
	 * @param accountCoordinatorUsername
	 * @param password
	 * @param passwordVerify
	 * @param team
	 * @return
	 * @throws ValidationException
	 */
	public static AccountCoordinator createAccountCoordinator(
		String username, 
		String password, 
		String passwordVerify, 
		String team) throws ValidationException {

		ClearTextPassword clearTextPassword = new ClearTextPassword(password);
		ClearTextPassword clearTextPasswordVerify = new ClearTextPassword(passwordVerify);
		String encodedPassword = PasswordFactory.encodePassword(clearTextPassword, clearTextPasswordVerify);
		
		AccountCoordinator accountCoordinator = new AccountCoordinator(username, encodedPassword, team);
		
		accountCoordinator.validate();
		
		return accountCoordinator;
	}
	
	/**
	 * 
	 * @param shortName
	 * @param legalName
	 * @param legalMailingAddress
	 * @param objective
	 * @param licenseNumber
	 * @param taxId
	 * @return
	 */
	public static Client createClient(
		String shortName,
		String legalName,
		String legalMailingAddress,
		String objective,
		String licenseNumber,
		String taxId) {
		
		Client client = new Client();
		client.setShortName(shortName);
		client.setLegalName(legalName);
		client.setLegalMailingAddress(legalMailingAddress);
		client.setObjective(objective);
		client.setLicenseNumber(licenseNumber);
		client.setTaxId(taxId);
		
		client.validate();
		
		return client;
	}

	/**
	 * 
	 * @param accountCoordinatorUsername
	 * @param password
	 * @param passwordVerify
	 * @param client
	 * @param termsAndConditionsAccepted
	 * @return
	 * @throws ValidationException
	 */
	public static ClientAdministrator createClientAdministrator(
		String username,
		String password,
		String passwordVerify,
		Client client, 
		boolean termsAndConditionsAccepted) throws ValidationException {
	
		ClientAdministrator clientAdministrator = new ClientAdministrator();

		ClearTextPassword clearTextPassword = new ClearTextPassword(password);
		ClearTextPassword clearTextPasswordVerify = new ClearTextPassword(passwordVerify);
		String encodedPassword = PasswordFactory.encodePassword(clearTextPassword, clearTextPasswordVerify);
		
		clientAdministrator.setUsername(username);
		clientAdministrator.setEncodedPassword(encodedPassword);
		
		clientAdministrator.setClient(client);
		clientAdministrator.setTermsAndConditionsAccepted(termsAndConditionsAccepted);
		
		return clientAdministrator;
	}
	
	/**
	 * 
	 * @return
	 */
	public static LoginForm createLoginForm() {

		LoginForm form = new LoginForm();

		int sequenceNumber = 1;
		form.addFormField(new EmailAddressFormField(), Integer.valueOf(sequenceNumber++));
		
		form.setContent(form.getDefaultContent());
		
		return form;
	}
	
	/**
	 * 
	 * @return
	 */
	public static RegistrationForm createRegistrationForm() {

		RegistrationForm form = new RegistrationForm();
		
		int sequenceNumber = 1;
		form.addFormField(new FirstNameTextFormField(), Integer.valueOf(sequenceNumber++));
		form.addFormField(new LastNameTextFormField(), Integer.valueOf(sequenceNumber++));
		form.addFormField(new EmailAddressFormField(), Integer.valueOf(sequenceNumber++));		
		form.addFormField(new ConfirmationEmailAddressFormField(), Integer.valueOf(sequenceNumber++));
		form.addFormField(new OptInBooleanChoiceFormField(), Integer.valueOf(sequenceNumber++));
		form.addFormField(new RulesConsentBooleanChoiceFormField(), Integer.valueOf(sequenceNumber++));
		form.addFormField(new CaptchaFormField(), Integer.valueOf(sequenceNumber++));
		
		form.setContent(form.getDefaultContent());
		
		return form;
	}
	
	/**
	 * 
	 * @return
	 */
	public static WinnerUpdateForm createWinnerUpdateForm() {

		WinnerUpdateForm form = new WinnerUpdateForm();
		
		int sequenceNumber = 1;
		form.addFormField(new AgeRangeMultipleChoiceFormField(), Integer.valueOf(sequenceNumber++));
		form.addFormField(new GenderMultipleChoiceFormField(), Integer.valueOf(sequenceNumber++));
		form.addFormField(new AddressOneTextFormField(), Integer.valueOf(sequenceNumber++));
		form.addFormField(new AddressTwoTextFormField(), Integer.valueOf(sequenceNumber++));
		form.addFormField(new CityTextFormField(), Integer.valueOf(sequenceNumber++));
		form.addFormField(new StateOrProvinceMultipleChoiceFormField(), Integer.valueOf(sequenceNumber++));
		form.addFormField(new ZipOrPostalCodeTextFormField(), Integer.valueOf(sequenceNumber++));
		form.addFormField(new CountryMultipleChoiceFormField(), Integer.valueOf(sequenceNumber++));
		form.addFormField(new CaptchaFormField(), Integer.valueOf(sequenceNumber++));
		
		form.setContent(form.getDefaultContent());
		
		return form;
	}
	
	/**
	 * 
	 * @return
	 */
	public static TellAFriendForm createTellAFriendForm() {

		TellAFriendForm form = new TellAFriendForm();
		
		int sequenceNumber = 1;

		form.addFormField(new FirstNameTextFormField(), Integer.valueOf(sequenceNumber++));
		form.addFormField(new EmailAddressFormField(), Integer.valueOf(sequenceNumber++));		
		
		form.addFormField(new FirstNameTextFormField(), Integer.valueOf(sequenceNumber++));
		form.addFormField(new EmailAddressFormField(), Integer.valueOf(sequenceNumber++));		

		form.addFormField(new FirstNameTextFormField(), Integer.valueOf(sequenceNumber++));
		form.addFormField(new EmailAddressFormField(), Integer.valueOf(sequenceNumber++));		

		
		form.setContent(form.getDefaultContent());
		
		return form;
	}
	
	/**
	 * 
	 * @param abstractPrizedProduct
	 * @param registrantVisit
	 * @return
	 */
	public static GamePlayEntry createGamePlayEntry(
		AbstractPrizedProduct abstractPrizedProduct,
		RegistrantVisit registrantVisit) {
		
		GamePlayEntry gamePlayEntry = new GamePlayEntry();
		
		gamePlayEntry.setPrizedProduct(abstractPrizedProduct);
		gamePlayEntry.setRegistrantVisit(registrantVisit);
		gamePlayEntry.setEntryDate(new Date(abstractPrizedProduct.getPromotion().getTimekeeper().getCurrentTimeInMillis()));
		
		return gamePlayEntry;
	}
		
	/**
	 * 
	 * @param gamePlayEntry
	 * @param prize
	 * @param promotion
	 * @return
	 */
	public static WinningGamePlayResult createWinningGamePlayResult(GamePlayEntry gamePlayEntry, AbstractPrize prize, Promotion promotion) {
		
		WinningGamePlayResult winningGamePlayResult = new WinningGamePlayResult();
		winningGamePlayResult.setGamePlayEntry(gamePlayEntry);
		winningGamePlayResult.setPrize(prize);
		winningGamePlayResult.setResultDate(new Date(System.currentTimeMillis()));
		winningGamePlayResult.setPromotion(promotion);
		
		return winningGamePlayResult;
	}
	
	/**
	 * 
	 * @return
	 */
	public static LoginPage createLoginPage(AbstractDeliveryChannel abstractDeliveryChannel) {
		
		LoginPage page = new LoginPage();
		
		page.setLoginForm(DomainObjectFactory.createLoginForm());
		page.setRegistrantState(new NotLoggedInRegistrantState());
		page.setSequenceNumber(Integer.valueOf(1));
		page.setContent(page.getDefaultContent());
		
		abstractDeliveryChannel.setLoginPage(page);

		return page;
	}
	
	/**
	 * 
	 * @return
	 */
	public static RegistrationPage createRegistrationPage(AbstractDeliveryChannel abstractDeliveryChannel) {
		
		RegistrationPage page = new RegistrationPage();
		
		page.setRegistrationForm(DomainObjectFactory.createRegistrationForm());
		page.setRegistrantState(new UnidentifiedRegistrantState());
		page.setSequenceNumber(Integer.valueOf(1));
		page.setContent(page.getDefaultContent());
		
		abstractDeliveryChannel.setRegistrationPage(page);
		
		return page;
	}
	
	/**
	 * 
	 * @return
	 */
	public static GamePlayPage createGamePlayPage(AbstractDeliveryChannel abstractDeliveryChannel) {
		
		GamePlayPage page = new GamePlayPage();
		
		page.setRegistrantState(new LoggedInRegistrantState());
		page.setSequenceNumber(Integer.valueOf(1));
		page.setContent(page.getDefaultContent());
		
		abstractDeliveryChannel.setGamePlayPage(page);
		
		return page;
	}
	
	/**
	 * 
	 * @return
	 */
	public static ConfirmationPage createConfirmationPage(AbstractDeliveryChannel abstractDeliveryChannel) {
		
		ConfirmationPage page = new ConfirmationPage();
		
		page.setRegistrantState(new LoggedInRegistrantState());
		page.setSequenceNumber(Integer.valueOf(2));
		page.setContent(page.getDefaultContent());
		
		abstractDeliveryChannel.setConfirmationPage(page);
		
		return page;
	}
	
	/**
	 * 
	 * @return
	 */
	public static WinnerUpdatePage createWinnerUpdatePage(AbstractDeliveryChannel abstractDeliveryChannel) {
		
		WinnerUpdatePage page = new WinnerUpdatePage();
		
		page.setWinnerUpdateForm(DomainObjectFactory.createWinnerUpdateForm());
		page.setRegistrantState(new IncompleteWinnerInfoRegistrantState());
		page.setSequenceNumber(Integer.valueOf(1));
		page.setContent(page.getDefaultContent());
		
		abstractDeliveryChannel.setWinnerUpdatePage(page);
		
		return page;
	}
	
	/**
	 * 
	 * @return
	 */
	public static TellAFriendPage createTellAFriendPage(AbstractDeliveryChannel abstractDeliveryChannel) {
		
		TellAFriendPage page = new TellAFriendPage();
		
		page.setTellAFriendForm(DomainObjectFactory.createTellAFriendForm());
		page.setRegistrantState(new PlayedGameRegistrantState());
		page.setSequenceNumber(Integer.valueOf(1));
		page.setContent(page.getDefaultContent());
		
		abstractDeliveryChannel.setTellAFriendPage(page);
		
		return page;
	}
	
	/**
	 * 
	 * @return
	 */
	public static ThankYouPage createThankYouPage(AbstractDeliveryChannel abstractDeliveryChannel) {
		
		ThankYouPage page = new ThankYouPage();
		
		page.setRegistrantState(new PerformedSharingRegistrantState());
		page.setSequenceNumber(Integer.valueOf(1));
		page.setContent(page.getDefaultContent());
		
		abstractDeliveryChannel.setThankYouPage(page);
		
		return page;
	}
		
	/*
	 * 
	 * @param promotionLookAndFeel
	 * @param deliveryChannel
	 */
	private static void initializeDeliveryChannel(PromotionLookAndFeel promotionLookAndFeel, AbstractDeliveryChannel deliveryChannel) {
		
		deliveryChannel.setLoginPage(DomainObjectFactory.createLoginPage(deliveryChannel));
		deliveryChannel.setRegistrationPage(DomainObjectFactory.createRegistrationPage(deliveryChannel));
		deliveryChannel.setGamePlayPage(DomainObjectFactory.createGamePlayPage(deliveryChannel));
		deliveryChannel.setConfirmationPage(DomainObjectFactory.createConfirmationPage(deliveryChannel));
		deliveryChannel.setWinnerUpdatePage(DomainObjectFactory.createWinnerUpdatePage(deliveryChannel));
		deliveryChannel.setTellAFriendPage(DomainObjectFactory.createTellAFriendPage(deliveryChannel));
		deliveryChannel.setThankYouPage(DomainObjectFactory.createThankYouPage(deliveryChannel));
		
		promotionLookAndFeel.addDeliveryChannel(deliveryChannel);
	}
	
	/**
	 * 
	 * @param promotion
	 * @return
	 */
	public static MicrositeDeliveryChannel createMicrositeDeliveryChannel(Promotion promotion) {
		
		PromotionLookAndFeel promotionLookAndFeel = promotion.getPromotionLoookAndFeel();
		MicrositeDeliveryChannel deliveryChannel = new MicrositeDeliveryChannel();
		initializeDeliveryChannel(promotionLookAndFeel, deliveryChannel);
		return deliveryChannel;
	}
	
	/**
	 * 
	 * @param promotion
	 * @return
	 */
	public static SweepstakesProduct createSweepstakesProduct(Promotion promotion) {
		
		SweepstakesProduct sweepstakesProduct = new SweepstakesProduct();
		
		promotion.addProduct(sweepstakesProduct);
		
		return sweepstakesProduct;
	}

	/**
	 * 
	 * @param promotion
	 * @return
	 */
	public static CasinoInstantWinProduct createCasinoInstantWinProduct(Promotion promotion) {
		
		CasinoInstantWinProduct instantWinProduct = new CasinoInstantWinProduct();
		
		promotion.addProduct(instantWinProduct);
		
		return instantWinProduct;
	}
	
	/**
	 * 
	 * @param promotion
	 * @return
	 */
	public static RandomInstantWinProduct createRandomInstantWinProduct(Promotion promotion) {
		
		RandomInstantWinProduct instantWinProduct = new RandomInstantWinProduct();
		
		promotion.addProduct(instantWinProduct);
		
		return instantWinProduct;
	}

	/**
	 * 
	 * @param promotion
	 * @return
	 */
	public static SlotMachineInstantWinProduct createSlotMachineInstantWinProduct(Promotion promotion) {
		
		SlotMachineInstantWinProduct instantWinProduct = new SlotMachineInstantWinProduct();
		
		promotion.addProduct(instantWinProduct);
		
		return instantWinProduct;
	}
	
	/**
	 * 
	 * @param promotion
	 * @return
	 */
	public static ShuffleInstantWinProduct createShuffleInstantWinProduct(Promotion promotion) {
		
		ShuffleInstantWinProduct instantWinProduct = new ShuffleInstantWinProduct();
		
		promotion.addProduct(instantWinProduct);
		
		return instantWinProduct;
	}
	
	/**
	 * 
	 * @param name
	 * @param description
	 * @param quantity
	 * @param arv
	 * @param arvCurrency
	 * @param tenNineNineParty
	 * @param affidavitRequired
	 * @param isGrandPrize
	 * @param sendWinningEmail
	 * @param allowDifferentPrizeTypeWins
	 * @param numberOfRepickRounds
	 * @param individualWinLimit
	 * @param digitalCode
	 * @return
	 */
	public static DigitalPrize createDigitalPrize(
		String name,
		String description,
		Integer quantity,
		Double arv,
		String arvCurrency,
		String tenNineNineParty,
		Boolean affidavitRequired,
		Boolean isGrandPrize,
		Boolean sendWinningEmail,
		Boolean allowDifferentPrizeTypeWins,
		Integer numberOfRepickRounds,
		Integer individualWinLimit,
		String digitalCode) {
		
		DigitalPrize prize = new DigitalPrize();

		prize.setName(name);
		prize.setDescription(description);
		prize.setQuantity(quantity);
		prize.setArv(arv);
		prize.setArvCurrency(arvCurrency);
		prize.setTenNineNineParty(tenNineNineParty);
		prize.setAffidavitRequired(affidavitRequired);
		prize.setIsGrandPrize(isGrandPrize);
		prize.setSendWinningEmail(sendWinningEmail);
		prize.setAllowDifferentPrizeTypeWins(allowDifferentPrizeTypeWins);
		prize.setNumberOfRepickRounds(numberOfRepickRounds);
		prize.setIndividualWinLimit(individualWinLimit);
		prize.setDigitalCode(digitalCode);
		
		return prize;
	}

	/**
	 * Creates a sweepstakes prize pool for the given prize with a single drawing held after the promotion 
	 * for the given sweepstakes product ends.  Prizes are fulfilled by ePrize. 
	 * 
	 * @param prizePoolName
	 * @param promotion
	 * @param prize
	 * 
	 * @return
	 */
	public static SweepstakesPrizePool createSweepstakesPrizePoolWithPromotionConclusionDrawing(
		String prizePoolName,
		SweepstakesProduct sweepstakesProduct,
		AbstractPrize prize) {
		
		SweepstakesPrizePool sweepstakesPrizePool = new SweepstakesPrizePool();
		
		sweepstakesPrizePool.setName(prizePoolName);
		
		sweepstakesPrizePool.setGamePlayPrizeAwarding(new ScheduledSweepstakesDrawingAwarding());
		
		sweepstakesPrizePool.setPrizeFulfillment(new EPrizePrizeFulfillment());
		
		sweepstakesProduct.addPrizePool(sweepstakesPrizePool);
				
		sweepstakesPrizePool.createPromotionConclusionSweepstakesDrawing(prize);
		
		return sweepstakesPrizePool;
	}

	/**
	 * 
	 * @param prizePoolName
	 * @param instantWinProduct
	 * @param prize
	 * @param constrainStartDate
	 * @param constrainEndDate
	 * @return
	 */
	public static InstantWinPrizePool createInstantWinPrizePoolWithOddsBasedAwarding(
		String prizePoolName,
		AbstractInstantWinProduct instantWinProduct,
		AbstractPrize prize,
		java.sql.Date constrainStartDate,
		java.sql.Date constrainEndDate,
		int odds) {
		
		InstantWinPrizePool instantWinPrizePool = new InstantWinPrizePool();
		
		instantWinPrizePool.setName(prizePoolName);
		instantWinPrizePool.setPrizedProduct(instantWinProduct);
		instantWinPrizePool.setPrize(prize);
		instantWinPrizePool.setConstrainStartDate(constrainStartDate);
		instantWinPrizePool.setConstrainEndDate(constrainEndDate);
		
		OddsBasedInstantWinGamePlayAwarding gamePlayPrizeAwarding = new OddsBasedInstantWinGamePlayAwarding();
		gamePlayPrizeAwarding.setOdds(odds);
		instantWinPrizePool.setGamePlayPrizeAwarding(gamePlayPrizeAwarding);
		instantWinProduct.addPrizePool(instantWinPrizePool);
		
		return instantWinPrizePool;
	}
	
	/**
	 * 
	 * @param managingAccountCoordinator
	 * @param client
	 * @param promotionName
	 * @param projectNumber
	 * @return
	 */
	public static Promotion createPromotion(
		AccountCoordinator managingAccountCoordinator, 
		Client client, 
		String promotionName,
		Long projectNumber) {
		
		Promotion promotion = new Promotion(
			managingAccountCoordinator,
			client,
			promotionName,
			projectNumber);
		
		promotion.getPromotionLoookAndFeel().addMarketingGoal(new CollectOptinsForListMarketingGoal());

		promotion.resolveChildReferencesToPromotion();
		promotion.validate();
		
		return promotion;
	}
	
	/**
	 * @param originalEmailAddress
	 */
	public static EmailAddress createEmailAddress(String originalEmailAddress) {
		EmailAddress emailAddress = new EmailAddress();
		emailAddress.setOriginalEmailAddress(originalEmailAddress);
		return emailAddress;
	}
	
	/**
	 * 
	 * @param birthMonth
	 * @param birthDay
	 * @param birthYear
	 * @return
	 */
	public static BirthDate createBirthDate(
		int birthMonth,
		int birthDay,
		int birthYear) {
		BirthDate birthDate = new BirthDate();
		birthDate.setBirthMonth(Integer.valueOf(birthMonth));
		birthDate.setBirthDay(Integer.valueOf(birthDay));
		birthDate.setBirthYear(Integer.valueOf(birthYear));
		return birthDate;
	}
	
	/**
	 * 
	 * @param originalMobilePhoneNumber
	 * @return
	 */
	public static MobilePhoneNumber createMobilePhoneNumber(String originalMobilePhoneNumber) {
		MobilePhoneNumber mobilePhoneNumber = new MobilePhoneNumber();
		mobilePhoneNumber.setOriginalMobilePhoneNumber(originalMobilePhoneNumber);
		return mobilePhoneNumber;
	}
	
	/**
	 * 
	 * @param countryCode
	 * @param countryName
	 * @param abbreviation
	 * @return
	 */
	public static Country createCountry(
		String countryCode,
		String countryName,
		String abbreviation) {

		Country country = new Country();
		
		country.setCountryCode(countryCode);
		country.setCountryName(countryName);
		country.setCountryAbbreviation(abbreviation);
		
		return country;
	}
	
	/**
	 * 
	 * @param stateName
	 * @param stateAbbreviation
	 * @param country
	 * @return
	 */
	public static StateOrProvince createStateOrProvince(
		String stateName,
		String stateAbbreviation,
		Country country) {
		
		StateOrProvince stateOrProvince = new StateOrProvince();
		
		stateOrProvince.setStateName(stateName);
		stateOrProvince.setStateAbbreviation(stateAbbreviation);
		stateOrProvince.setCountry(country);
		
		return stateOrProvince;
	}
	/**
	 * 
	 * @param zipOrPostalCodeValue
	 * @param stateOrProvince
	 * @return
	 */
	public static ZipOrPostalCode createZipOrPostalCode(
		String zipOrPostalCodeValue,
		StateOrProvince stateOrProvince) {
		
		ZipOrPostalCode zipOrPostalCode = new ZipOrPostalCode();

		zipOrPostalCode.setZipOrPostalCodeValue(zipOrPostalCodeValue);
		zipOrPostalCode.setStateOrProvince(stateOrProvince);
			
		return zipOrPostalCode;
	}
	
	/**
	 * 
	 * @param street1
	 * @param street2
	 * @param city
	 * @param zipOrPostalCode
	 * @return
	 */
	public static MailingAddress createMailingAddress(
		String street1,
		String street2,
		String city,
		ZipOrPostalCode zipOrPostalCode) {
		
		MailingAddress mailingAddress = new MailingAddress();
		
		mailingAddress.setStreet1(street1);
		mailingAddress.setStreet2(street2);
		mailingAddress.setCity(city);
		mailingAddress.setZipOrPostalCode(zipOrPostalCode);
		
		return mailingAddress;
	}

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param originalEmailAddress
	 * @param birthMonth
	 * @param birthDay
	 * @param birthYear
	 * @param gender
	 * @param originalMobilePhoneNumber
	 * @param unsubscribe
	 * @param primaryOptIn
	 * @param promotion
	 * @return
	 */
	public static Registrant createRegistrantNoAddressNonReferredForPromotion(
		 String firstName,
		 String lastName,
		 String originalEmailAddress,
		 int birthMonth,
		 int birthDay,
		 int birthYear,
		 String gender,
		 String originalMobilePhoneNumber,
		 boolean unsubscribe,
		 boolean primaryOptIn,
		 Promotion promotion) {
		
		EmailAddress emailAddress = DomainObjectFactory.createEmailAddress(originalEmailAddress);
		BirthDate birthDate = DomainObjectFactory.createBirthDate(birthMonth, birthDay, birthYear);
		MobilePhoneNumber mobilePhoneNumber = DomainObjectFactory.createMobilePhoneNumber(originalMobilePhoneNumber);
		
		Registrant registrant = new Registrant();

		registrant.setFirstName(firstName);
		registrant.setLastName(lastName);
		registrant.setEmailAddress(emailAddress);
		registrant.setBirthDate(birthDate);
		registrant.setMobilePhoneNumber(mobilePhoneNumber);
		registrant.setGender(gender);
		registrant.setUnsubscribe(Boolean.valueOf(unsubscribe));
		registrant.setPrimaryOptIn(Boolean.valueOf(primaryOptIn));
		
		promotion.addRegistrant(registrant);
		
		return registrant;
	}
}