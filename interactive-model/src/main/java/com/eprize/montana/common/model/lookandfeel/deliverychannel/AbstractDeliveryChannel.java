package com.eprize.montana.common.model.lookandfeel.deliverychannel;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.lookandfeel.PromotionLookAndFeel;
import com.eprize.montana.common.model.lookandfeel.page.AbstractPage;
import com.eprize.montana.common.model.lookandfeel.page.ConfirmationPage;
import com.eprize.montana.common.model.lookandfeel.page.GamePlayPage;
import com.eprize.montana.common.model.lookandfeel.page.LoginPage;
import com.eprize.montana.common.model.lookandfeel.page.RegistrationPage;
import com.eprize.montana.common.model.lookandfeel.page.TellAFriendPage;
import com.eprize.montana.common.model.lookandfeel.page.ThankYouPage;
import com.eprize.montana.common.model.lookandfeel.page.WinnerUpdatePage;

/**
 * 
 * @author Tom.Myers
 *
 */
public abstract class AbstractDeliveryChannel extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long deliveryChannelId;

	/* */
	protected String name;

	/* */
	protected String metadata;
	
	/* */
	protected String header;
	
	/* */
	protected String footer;
		
	
	
	/* */
	protected LoginPage loginPage;
	
	/* */
	protected RegistrationPage registrationPage;

	/* */
	protected GamePlayPage gamePlayPage;
	
	/* */
	protected ConfirmationPage confirmationPage;
	
	/* */
	protected WinnerUpdatePage winnerUpdatePage;
	
	/* */
	protected TellAFriendPage tellAFriendPage;
	
	/* */
	protected ThankYouPage thankYouPage;
	
	
	
	/* Used to hold pages other than the standard one.  When they are rendered is dictated (like the other pages, by a combination
	 * of the current registrant state (i.e. "session) and the registrant state associated with the page, as well as the sequence 
	 * number for that state.  In other words, if an additional page is to be displayed BEFORE the "game play" page, i.e. after
	 * a login (in which the user is registered), then the game play page would have a sequence number of 2 and this additional 
	 * page would have a sequence number of 1.
	 */
	protected Set<AbstractPage> additionalPages = new TreeSet<AbstractPage>();

	
	
	/* */
	protected PromotionLookAndFeel promotionLookAndFeel;
	
	
	/**
	 * 
	 */
	public AbstractDeliveryChannel() {
		
		//this.loginPage = DomainObjectFactory.createLoginPage();
	}
	
	/**
	 * @return the deliveryChannelId
	 */
	public Long getDeliveryChannelId() {
		return deliveryChannelId;
	}

	/**
	 * @param deliveryChannelId the deliveryChannelId to set
	 */
	public void setDeliveryChannelId(Long deliveryChannelId) {
		this.deliveryChannelId = deliveryChannelId;
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
	 * @return the metadata
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return the footer
	 */
	public String getFooter() {
		return footer;
	}

	/**
	 * @param footer the footer to set
	 */
	public void setFooter(String footer) {
		this.footer = footer;
	}

	/**
	 * @return the loginPage
	 */
	public LoginPage getLoginPage() {
		return loginPage;
	}

	/**
	 * @param loginPage the loginPage to set
	 */
	public void setLoginPage(LoginPage loginPage) {
		loginPage.setAbstractDeliveryChannel(this);
		this.loginPage = loginPage;
	}

	/**
	 * @return the registrationPage
	 */
	public RegistrationPage getRegistrationPage() {
		return registrationPage;
	}

	/**
	 * @param registrationPage the registrationPage to set
	 */
	public void setRegistrationPage(RegistrationPage registrationPage) {
		registrationPage.setAbstractDeliveryChannel(this);
		this.registrationPage = registrationPage;
	}

	/**
	 * @return the gamePlayPage
	 */
	public GamePlayPage getGamePlayPage() {
		return gamePlayPage;
	}

	/**
	 * @param gamePlayPage the gamePlayPage to set
	 */
	public void setGamePlayPage(GamePlayPage gamePlayPage) {
		gamePlayPage.setAbstractDeliveryChannel(this);
		this.gamePlayPage = gamePlayPage;
	}

	/**
	 * @return the confirmationPage
	 */
	public ConfirmationPage getConfirmationPage() {
		return confirmationPage;
	}

	/**
	 * @param confirmationPage the confirmationPage to set
	 */
	public void setConfirmationPage(ConfirmationPage confirmationPage) {
		confirmationPage.setAbstractDeliveryChannel(this);
		this.confirmationPage = confirmationPage;
	}

	/**
	 * @return the winnerUpdatePage
	 */
	public WinnerUpdatePage getWinnerUpdatePage() {
		return winnerUpdatePage;
	}

	/**
	 * @param winnerUpdatePage the winnerUpdatePage to set
	 */
	public void setWinnerUpdatePage(WinnerUpdatePage winnerUpdatePage) {
		winnerUpdatePage.setAbstractDeliveryChannel(this);
		this.winnerUpdatePage = winnerUpdatePage;
	}

	/**
	 * @return the tellAFriendPage
	 */
	public TellAFriendPage getTellAFriendPage() {
		return tellAFriendPage;
	}

	/**
	 * @param tellAFriendPage the tellAFriendPage to set
	 */
	public void setTellAFriendPage(TellAFriendPage tellAFriendPage) {
		tellAFriendPage.setAbstractDeliveryChannel(this);
		this.tellAFriendPage = tellAFriendPage;
	}

	/**
	 * @return the thankYouPage
	 */
	public ThankYouPage getThankYouPage() {
		return thankYouPage;
	}

	/**
	 * @param thankYouPage the thankYouPage to set
	 */
	public void setThankYouPage(ThankYouPage thankYouPage) {
		thankYouPage.setAbstractDeliveryChannel(this);
		this.thankYouPage = thankYouPage;
	}

	/**
	 * @return the additionalPages
	 */
	public Set<AbstractPage> getAdditionalPages() {
		return additionalPages;
	}

	/**
	 * @param additionalPages the additionalPages to set
	 */
	public void setAdditionalPages(Set<AbstractPage> additionalPages) {
		this.additionalPages = additionalPages;
	}
	
	/**
	 * 
	 * @param page
	 * @return
	 */
	public boolean addAdditionalPage(AbstractPage page) {
		page.setAbstractDeliveryChannel(this);
		return this.additionalPages.add(page);
	}

	/**
	 * 
	 * @param page
	 * @return
	 */
	public boolean removeAdditionalPage(AbstractPage page) {
		page.setAbstractDeliveryChannel(null);
		return this.additionalPages.remove(page);
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

		return this.deliveryChannelId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {

		return this.name;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#validate()
	 */
	public Map<String, String> validate() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();

		validatePage(this.loginPage, validationErrors);
		validatePage(this.registrationPage, validationErrors);
		validatePage(this.gamePlayPage, validationErrors);
		validatePage(this.confirmationPage, validationErrors);
		validatePage(this.winnerUpdatePage, validationErrors);
		validatePage(this.tellAFriendPage, validationErrors);
		validatePage(this.thankYouPage, validationErrors);
		
		Iterator<AbstractPage> iterator = this.additionalPages.iterator();
		while (iterator.hasNext()) {
			
			AbstractPage page = iterator.next();
			validatePage(page, validationErrors);
		}
		
		return validationErrors;
	}

	/*
	 * 
	 * @param page
	 * @param validationErrors
	 */
	private void validatePage(AbstractPage page, Map<String, String> validationErrors) {
		
		if (page == null) {
			validationErrors.put(this.getName(), "Page cannot be null");
		} else {
			validationErrors.putAll(page.validate());	
		}
	}
}