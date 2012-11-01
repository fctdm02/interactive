/**
 * 
 */
package com.eprize.montana.common.model.promotion;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.client.Client;
import com.eprize.montana.common.model.exception.ObjectNotFoundException;
import com.eprize.montana.common.model.location.MailingAddress;
import com.eprize.montana.common.model.lookandfeel.PromotionLookAndFeel;
import com.eprize.montana.common.model.lookandfeel.deliverychannel.AbstractDeliveryChannel;
import com.eprize.montana.common.model.prize.AbstractPrize;
import com.eprize.montana.common.model.prize.PrizeGroup;
import com.eprize.montana.common.model.prize.awarding.AbstractGamePlayPrizeAwarding;
import com.eprize.montana.common.model.prize.awarding.WinningGamePlayResult;
import com.eprize.montana.common.model.prize.entry.AbstractGamePlayEntryStrategy;
import com.eprize.montana.common.model.prize.entry.GamePlayEntry;
import com.eprize.montana.common.model.prize.pool.AbstractPrizePool;
import com.eprize.montana.common.model.prize.pool.instantwin.InstantWinPrizePool;
import com.eprize.montana.common.model.prize.pool.sweepstakes.SweepstakesPrizeDrawing;
import com.eprize.montana.common.model.prize.pool.sweepstakes.SweepstakesPrizePool;
import com.eprize.montana.common.model.product.AbstractProduct;
import com.eprize.montana.common.model.product.prized.AbstractPrizedProduct;
import com.eprize.montana.common.model.product.prized.instantwin.AbstractInstantWinProduct;
import com.eprize.montana.common.model.product.prized.sweepstakes.SweepstakesProduct;
import com.eprize.montana.common.model.promotion.eligibility.PromotionEligibility;
import com.eprize.montana.common.model.promotion.state.AbstractPromotionState;
import com.eprize.montana.common.model.promotion.state.ArchivedPromotionState;
import com.eprize.montana.common.model.promotion.state.CompletedPromotionState;
import com.eprize.montana.common.model.promotion.state.ConceptPromotionState;
import com.eprize.montana.common.model.promotion.state.DraftPromotionState;
import com.eprize.montana.common.model.promotion.state.LivePromotionState;
import com.eprize.montana.common.model.promotion.state.PendingPromotionState;
import com.eprize.montana.common.model.promotion.time.ITimekeeper;
import com.eprize.montana.common.model.promotion.time.ProductionModeTimekeeper;
import com.eprize.montana.common.model.registration.EmailAddress;
import com.eprize.montana.common.model.user.AccountCoordinator;
import com.eprize.montana.common.model.user.Registrant;

/**
 * 
 * @author tmyers
 * 
 * Timezone information:
 * <pre>
	Area			Abbrev	Zone Name
	====================================
	Eastern Time	EST		America/New_York
	Central Time	CST		America/Chicago
	Mountain Time	MST		America/Denver
	Pacific Time	PST		America/Los_Angeles
	Alaska Time		AST		America/Anchorage
	Hawaii-Aleutian Time	HST	America/Adak
 * </pre>
 * 
 */
public class Promotion extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/** The base cost of a promotion. */
	public static final int PROMOTION_BASE_COST = 7000;
	
	/** */
	public static final int DEFAULT_MAXIMUM_TELL_A_FRIEND_REFERRALS = 10;

	/** */
	public static final String DEFAULT_TIMEZONE_AMERICA_NEW_YORK = "America/New_York";

	/** */
	public static final String TIMEZONE_AMERICA_CHICAGO = "America/Chicago";
	
	/** */
	public static final String TIMEZONE_AMERICA_DENVER = "America/Denver";
	
	/** */
	public static final String TIMEZONE_AMERICA_LOS_ANGELES = "America/Los_Angeles";
	
	/** */
	public static final String TIMEZONE_AMERICA_ANCHORAGE = "America/Anchorage";
	
	/** */
	public static final String TIMEZONE_AMERICA_HAWAII = "America/Adak";
	
	
	/* */
	private Long promotionId;
	
	/* */
	private String name;
	
	/* */
	private Client client;

	/* */
	private UUID promotionUUID = UUID.randomUUID();
	
	/* */
	private UUID promoterUUID = UUID.randomUUID();
	
	/* */
	private Long projectNumber;
	
	/* */
	private Date conceptDate;
	
	/* */
	private Date launchDate;
	
	/* */
	private Date endDate;
	
	/* */
	private Date archiveDate;
	
	/* */
	private String timezone = DEFAULT_TIMEZONE_AMERICA_NEW_YORK;
	
	/* */
	private URL optOutUrl;

	/* */
	private Boolean sendDailyReminderEmailToRegistrants = Boolean.TRUE;
	
	/* */
	private Boolean includeTellAFriendSharing = Boolean.TRUE;;
	
	/* */
	private Boolean sendRegistrationConfirmationEmail = Boolean.TRUE;
	
	/* */
	private Boolean sendWinConfirmationEmail = Boolean.TRUE;
	
	/* */
	private Boolean sendTellAFriendSharingEmail = Boolean.TRUE;
		
	/* */
	private Integer maxTellAFriendReferrals = Integer.valueOf(DEFAULT_MAXIMUM_TELL_A_FRIEND_REFERRALS); 
	
	/* */
	private Boolean awardEntriesForTellAFriend = Boolean.FALSE;
		
	/* */
	private Boolean includeTwitterSharing = Boolean.FALSE;
	
	/* */
	private Boolean includeGooglePlusSharing = Boolean.FALSE;
	
	/* */
	private Boolean includeFacebookSharing = Boolean.FALSE;
	
	/* */
	private Boolean requireFacebookLikeForEntry = Boolean.FALSE;
	
	/* */
	private String facebookPlacesVenueId;
	
	/* */
	private String fourSquarePlaceId;
	
	/* */
	private Boolean hasBeenPaidFor = Boolean.FALSE;
	
	/* */
	private Boolean useGroupWinLimiting = Boolean.FALSE;
	
	/* */
	private PromotionLookAndFeel promotionLoookAndFeel = new PromotionLookAndFeel();
	
	/* */
	private PromotionEligibility promotionEligibility = new PromotionEligibility();
	
	/* */
	private Set<AbstractProduct> products = new TreeSet<AbstractProduct>();

	/* */
	private Set<PrizeGroup> prizeGroups = new TreeSet<PrizeGroup>();

	/* */
	private Boolean usePrizeGroupWinLimiting = Boolean.FALSE;
	
	/* */
	private Set<WinningGamePlayResult> winningGamePlayResults = new TreeSet<WinningGamePlayResult>();
	
	/* 
	 * This field contains a lazy collection of all registrants. 
	 */
	private Set<Registrant> registrants = new TreeSet<Registrant>();
	
	/* */
	private AccountCoordinator managingAccountCoordinator;
	
	/* */
	private ITimekeeper timekeeper = new ProductionModeTimekeeper();
	
	/* */
	private AbstractPromotionState promotionState = new ConceptPromotionState();

	/*
	 * When set to <code>true</code> it is <b>assumed</b> that the static promotion content 
	 * (i.e. images, CSS, Javascript, HTML5 widgets, Flash modules, etc.) has been extracted
	 * from the promotion object graph (mainly by traversing through the PromotionLookAndFeel
	 * sub object graph) and copying the content to the web server filesystem (or whatever 
	 * edge/cache server mechanism will be used.)  The point is, is that it will be something
	 * that actually gets the promotion to be viewable in a web browser. 
	 * <p>
	 * So, what makes the DRAFT state different from the PENDING state is that in PENDING state, 
	 * the promotion has been "deployed"....
	 */
	private Boolean hasBeenDeployed = Boolean.FALSE;
	
	/**
	 * 
	 */
	public Promotion() {
	}
	
	/**
	 * 
	 * @param managingAccountCoordinator
	 * @param client
	 * @param promotionName
	 * @param projectNumber
	 */
	public Promotion(
		AccountCoordinator managingAccountCoordinator, 
		Client client, 
		String promotionName,
		Long projectNumber) {

		super();
		this.setManagingAccountCoordinator(managingAccountCoordinator);
		this.setClient(client);
		this.setName(promotionName);
		this.setProjectNumber(projectNumber);
		this.conceptDate = this.timekeeper.getCurrentDate();
	}
	
	/**
	 * 
	 */
	public void resolveChildReferencesToPromotion() {
		this.promotionEligibility.setPromotion(this);
		this.promotionLoookAndFeel.setPromotion(this);
		this.timekeeper.setPromotion(this);
	}
	
	/**
	 * @return the promotionId
	 */
	public Long getPromotionId() {
		return promotionId;
	}

	/**
	 * @param promotionId the promotionId to set
	 */
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
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
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

    /**
	 * @return the products
	 */
	public Set<AbstractProduct> getProducts() {
		return products;
	}

    /**
	 * @return the prized products
	 */
	public Set<AbstractPrizedProduct> getPrizedProducts() {
		Set<AbstractPrizedProduct> abstractPrizedProducts = new TreeSet<AbstractPrizedProduct>();
		Iterator<AbstractProduct> iterator = this.products.iterator();
		while (iterator.hasNext()) {
			AbstractProduct abstractProduct = iterator.next();
			if (abstractProduct instanceof AbstractPrizedProduct) {
				abstractPrizedProducts.add((AbstractPrizedProduct)abstractProduct);
			}
		}
		return abstractPrizedProducts;
	}
	
	/**
	 * 
	 * @return
	 */
	public SweepstakesProduct getSweepstakesProduct() {
		
		Iterator<AbstractProduct> iterator = this.products.iterator();
		while (iterator.hasNext()) {
			AbstractProduct abstractProduct = iterator.next();
			if (abstractProduct instanceof SweepstakesProduct) {
				return (SweepstakesProduct)abstractProduct;
			}
		}
		throw new IllegalStateException("Promotion" + this + " does not have a sweepstakes product.");
	}

	/**
	 * 
	 * @return
	 */
	public AbstractInstantWinProduct getInstantWinProduct() {
		
		Iterator<AbstractProduct> iterator = this.products.iterator();
		while (iterator.hasNext()) {
			AbstractProduct abstractProduct = iterator.next();
			if (abstractProduct instanceof AbstractInstantWinProduct) {
				return (AbstractInstantWinProduct)abstractProduct;
			}
		}
		throw new IllegalStateException("Promotion" + this + " does not have a instant win product.");
	}
	
    /**
     * @param products the products to set
     */
    public void setProducts(Set<AbstractProduct> products) {
    	
        this.products = products;
    }
    
    /**
     * 
     * @param abstractProduct
     */
    public void addProduct(AbstractProduct abstractProduct) {
    	
    	abstractProduct.setPromotion(this);
    	
    	if (this.getProducts().contains(abstractProduct)) {
    		throw new IllegalStateException("Promotion: [" + this + "] already contains product: [" + abstractProduct + "].");
    	}
    	        
        this.products.add(abstractProduct);
    }
	        
    /**
     * 
     * @param productName
     * @throws ObjectNotFoundException
     * @return AbstractProduct
     */
    public AbstractProduct removeProduct(String productName) throws ObjectNotFoundException {
        
        AbstractProduct productToRemove = null;
        Iterator<AbstractProduct> iterator = this.products.iterator();
        while (iterator.hasNext()) {
            
            AbstractProduct abstractProduct = iterator.next();
            if (abstractProduct.getName().equalsIgnoreCase(productName)) {
                productToRemove = abstractProduct;
            }
        }
        
        if (productToRemove == null) {
            throw new ObjectNotFoundException("Could not find product with name: [" + productName + "] in realm: [" + this + "].");
        }
        
        this.products.remove(productToRemove);
        
        return productToRemove;
    }
    	
	/**
	 * 
	 * @param productName
	 * @return The product with the given name
	 * @throws ObjectNotFoundException
	 */
	public AbstractProduct getProductByProductName(String productName) throws ObjectNotFoundException {
	    
	    Iterator<AbstractProduct> iterator = products.iterator();
	    while (iterator.hasNext()) {
	        
	        AbstractProduct abstractProduct = iterator.next();
	        if (abstractProduct.getName().equalsIgnoreCase(productName)) {
	            return abstractProduct;
	        }
	    }
	    throw new ObjectNotFoundException("Could not find product with name: " + productName + " for realm: " + this);
	}
	
	/**
	 * @return the prizeGroups
	 */
	public Set<PrizeGroup> getPrizeGroups() {
		return prizeGroups;
	}

	/**
	 * @param prizeGroups the prizeGroups to set
	 */
	public void setPrizeGroups(Set<PrizeGroup> prizeGroups) {
		this.prizeGroups = prizeGroups;
	}

	/**
	 * @return the promotionUUID
	 */
	public UUID getPromotionUUID() {
		return promotionUUID;
	}

	/**
	 * @param promotionUUID the promotionUUID to set
	 */
	public void setPromotionUUID(UUID promotionUUID) {
		this.promotionUUID = promotionUUID;
	}

	/**
	 * @return the promoterUUID
	 */
	public UUID getPromoterUUID() {
		return promoterUUID;
	}

	/**
	 * @param promoterUUID the promoterUUID to set
	 */
	public void setPromoterUUID(UUID promoterUUID) {
		this.promoterUUID = promoterUUID;
	}

	/**
	 * @return the projectNumber
	 */
	public Long getProjectNumber() {
		return projectNumber;
	}

	/**
	 * @param projectNumber the projectNumber to set
	 */
	public void setProjectNumber(Long projectNumber) {
		this.projectNumber = projectNumber;
	}

	/**
	 * @return the conceptDate
	 */
	public Date getConceptDate() {
		return conceptDate;
	}

	/**
	 * @param conceptDate the conceptDate to set
	 */
	public void setConceptDate(Date conceptDate) {
		this.conceptDate = conceptDate;
	}
	
	/**
	 * @return the launchDate
	 */
	public Date getLaunchDate() {
		return launchDate;
	}

	/**
	 * @param launchDate the launchDate to set
	 */
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	
	/**
	 * 
	 * @param numberOfDays
	 */
	public void setLaunchDateFromConceptDate(int numberOfDays) {
		this.setLaunchDate(this.timekeeper.getDateFromDatePlusNumberOfDays(this.conceptDate, numberOfDays));
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 
	 * @param numberOfDays
	 */
	public void setEndDateFromLaunchDate(int numberOfDays) {
		this.setEndDate(this.timekeeper.getDateFromDatePlusNumberOfDays(this.launchDate, numberOfDays));
	}
	
	/**
	 * @return the archiveDate
	 */
	public Date getArchiveDate() {
		return archiveDate;
	}

	/**
	 * @param archiveDate the archiveDate to set
	 */
	public void setArchiveDate(Date archiveDate) {
		this.archiveDate = archiveDate;
	}

	/**
	 * 
	 * @param numberOfDays
	 */
	public void setArchiveDateFromEndDate(int numberOfDays) {
		this.setArchiveDate(this.timekeeper.getDateFromDatePlusNumberOfDays(this.endDate, numberOfDays));
	}
	
	/**
	 * @return the timezone
	 */
	public String getTimezone() {
		return timezone;
	}

	/**
	 * @param timezone the timezone to set
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	/**
	 * @return the optOutUrl
	 */
	public URL getOptOutUrl() {
		return optOutUrl;
	}

	/**
	 * @param optOutUrl the optOutUrl to set
	 * @throws MalformedURLException 
	 */
	public void setOptOutUrl(String optOutUrl) throws MalformedURLException {
		this.optOutUrl = new URL(optOutUrl);
	}
	
	/**
	 * @param optOutUrl the optOutUrl to set
	 */
	public void setOptOutUrl(URL optOutUrl) {
		this.optOutUrl = optOutUrl;
	}

	/**
	 * @return the sendDailyReminderEmailToRegistrants
	 */
	public Boolean getSendDailyReminderEmailToRegistrants() {
		return sendDailyReminderEmailToRegistrants;
	}

	/**
	 * @param sendDailyReminderEmailToRegistrants the sendDailyReminderEmailToRegistrants to set
	 */
	public void setSendDailyReminderEmailToRegistrants(
			Boolean sendDailyReminderEmailToRegistrants) {
		this.sendDailyReminderEmailToRegistrants = sendDailyReminderEmailToRegistrants;
	}

	/**
	 * @return the includeTellAFriendSharing
	 */
	public Boolean getIncludeTellAFriendSharing() {
		return includeTellAFriendSharing;
	}

	/**
	 * @param includeTellAFriendSharing the includeTellAFriendSharing to set
	 */
	public void setIncludeTellAFriendSharing(Boolean includeTellAFriendSharing) {
		this.includeTellAFriendSharing = includeTellAFriendSharing;
	}

	/**
	 * @return the sendRegistrationConfirmationEmail
	 */
	public Boolean getSendRegistrationConfirmationEmail() {
		return sendRegistrationConfirmationEmail;
	}

	/**
	 * @param sendRegistrationConfirmationEmail the sendRegistrationConfirmationEmail to set
	 */
	public void setSendRegistrationConfirmationEmail(
			Boolean sendRegistrationConfirmationEmail) {
		this.sendRegistrationConfirmationEmail = sendRegistrationConfirmationEmail;
	}

	/**
	 * @return the sendWinConfirmationEmail
	 */
	public Boolean getSendWinConfirmationEmail() {
		return sendWinConfirmationEmail;
	}

	/**
	 * @param sendWinConfirmationEmail the sendWinConfirmationEmail to set
	 */
	public void setSendWinConfirmationEmail(Boolean sendWinConfirmationEmail) {
		this.sendWinConfirmationEmail = sendWinConfirmationEmail;
	}

	/**
	 * @return the sendTellAFriendSharingEmail
	 */
	public Boolean getSendTellAFriendSharingEmail() {
		return sendTellAFriendSharingEmail;
	}

	/**
	 * @param sendTellAFriendSharingEmail the sendTellAFriendSharingEmail to set
	 */
	public void setSendTellAFriendSharingEmail(Boolean sendTellAFriendSharingEmail) {
		this.sendTellAFriendSharingEmail = sendTellAFriendSharingEmail;
	}

	/**
	 * @return the maxTellAFriendReferrals
	 */
	public Integer getMaxTellAFriendReferrals() {
		return maxTellAFriendReferrals;
	}

	/**
	 * @param maxTellAFriendReferrals the maxTellAFriendReferrals to set
	 */
	public void setMaxTellAFriendReferrals(Integer maxTellAFriendReferrals) {
		this.maxTellAFriendReferrals = maxTellAFriendReferrals;
	}

	/**
	 * @return the managingAccountCoordinator
	 */
	public AccountCoordinator getManagingAccountCoordinator() {
		return managingAccountCoordinator;
	}

	/**
	 * @param managingAccountCoordinator the managingAccountCoordinator to set
	 */
	public void setManagingAccountCoordinator(
			AccountCoordinator managingAccountCoordinator) {
		this.managingAccountCoordinator = managingAccountCoordinator;
	}

	/**
	 * @return the timekeeper
	 */
	public ITimekeeper getTimekeeper() {
		return timekeeper;
	}

	/**
	 * @param timekeeper the timekeeper to set
	 */
	public void setTimekeeper(ITimekeeper timekeeper) {
		timekeeper.setPromotion(this);
		this.timekeeper = timekeeper;
	}
	
	/**
	 * @return the promotionLoookAndFeel
	 */
	public PromotionLookAndFeel getPromotionLoookAndFeel() {
		return promotionLoookAndFeel;
	}

	/**
	 * @param promotionLoookAndFeel the promotionLoookAndFeel to set
	 */
	public void setPromotionLoookAndFeel(PromotionLookAndFeel promotionLoookAndFeel) {
		this.promotionLoookAndFeel = promotionLoookAndFeel;
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

	/**
	 * @return the registrants
	 */
	public Set<Registrant> getRegistrants() {
		return registrants;
	}

	/**
	 * @param registrants the registrants to set
	 */
	public void setRegistrants(Set<Registrant> registrants) {
		this.registrants = registrants;
	}
	
	/**
	 * 
	 * @param registrant
	 * @return
	 */
	public boolean addRegistrant(Registrant registrant) {
		registrant.setPromotion(this);
		return this.registrants.add(registrant);
	}

	/**
	 * @return the awardEntriesForTellAFriend
	 */
	public Boolean getAwardEntriesForTellAFriend() {
		return awardEntriesForTellAFriend;
	}

	/**
	 * @param awardEntriesForTellAFriend the awardEntriesForTellAFriend to set
	 */
	public void setAwardEntriesForTellAFriend(Boolean awardEntriesForTellAFriend) {
		this.awardEntriesForTellAFriend = awardEntriesForTellAFriend;
	}

	/**
	 * @return the includeTwitterSharing
	 */
	public Boolean getIncludeTwitterSharing() {
		return includeTwitterSharing;
	}

	/**
	 * @param includeTwitterSharing the includeTwitterSharing to set
	 */
	public void setIncludeTwitterSharing(Boolean includeTwitterSharing) {
		this.includeTwitterSharing = includeTwitterSharing;
	}

	/**
	 * @return the includeGooglePlusSharing
	 */
	public Boolean getIncludeGooglePlusSharing() {
		return includeGooglePlusSharing;
	}

	/**
	 * @param includeGooglePlusSharing the includeGooglePlusSharing to set
	 */
	public void setIncludeGooglePlusSharing(Boolean includeGooglePlusSharing) {
		this.includeGooglePlusSharing = includeGooglePlusSharing;
	}

	/**
	 * @return the includeFacebookSharing
	 */
	public Boolean getIncludeFacebookSharing() {
		return includeFacebookSharing;
	}

	/**
	 * @param includeFacebookSharing the includeFacebookSharing to set
	 */
	public void setIncludeFacebookSharing(Boolean includeFacebookSharing) {
		this.includeFacebookSharing = includeFacebookSharing;
	}

	/**
	 * @return the requireFacebookLikeForEntry
	 */
	public Boolean getRequireFacebookLikeForEntry() {
		return requireFacebookLikeForEntry;
	}

	/**
	 * @param requireFacebookLikeForEntry the requireFacebookLikeForEntry to set
	 */
	public void setRequireFacebookLikeForEntry(Boolean requireFacebookLikeForEntry) {
		this.requireFacebookLikeForEntry = requireFacebookLikeForEntry;
	}

	/**
	 * @return the facebookPlacesVenueId
	 */
	public String getFacebookPlacesVenueId() {
		return facebookPlacesVenueId;
	}

	/**
	 * @param facebookPlacesVenueId the facebookPlacesVenueId to set
	 */
	public void setFacebookPlacesVenueId(String facebookPlacesVenueId) {
		this.facebookPlacesVenueId = facebookPlacesVenueId;
	}

	/**
	 * @return the fourSquarePlaceId
	 */
	public String getFourSquarePlaceId() {
		return fourSquarePlaceId;
	}

	/**
	 * @param fourSquarePlaceId the fourSquarePlaceId to set
	 */
	public void setFourSquarePlaceId(String fourSquarePlaceId) {
		this.fourSquarePlaceId = fourSquarePlaceId;
	}

	/**
	 * @return the hasBeenPaidFor
	 */
	public Boolean getHasBeenPaidFor() {
		return hasBeenPaidFor;
	}

	/**
	 * @param hasBeenPaidFor the hasBeenPaidFor to set
	 */
	public void setHasBeenPaidFor(Boolean hasBeenPaidFor) {
		this.hasBeenPaidFor = hasBeenPaidFor;
	}

	/**
	 * @return the useGroupWinLimiting
	 */
	public Boolean getUseGroupWinLimiting() {
		return useGroupWinLimiting;
	}

	/**
	 * @param useGroupWinLimiting the useGroupWinLimiting to set
	 */
	public void setUseGroupWinLimiting(Boolean useGroupWinLimiting) {
		this.useGroupWinLimiting = useGroupWinLimiting;
	}

	/**
	 * @return the usePrizeGroupWinLimiting
	 */
	public Boolean isUsePrizeGroupWinLimiting() {
		return usePrizeGroupWinLimiting;
	}

	/**
	 * @param usePrizeGroupWinLimiting the usePrizeGroupWinLimiting to set
	 */
	public void setUsePrizeGroupWinLimiting(Boolean usePrizeGroupWinLimiting) {
		this.usePrizeGroupWinLimiting = usePrizeGroupWinLimiting;
	}
	
	/**
	 * @return the winningGamePlayResults
	 */
	public Set<WinningGamePlayResult> getWinningGamePlayResults() {
		return winningGamePlayResults;
	}

	/**
	 * @param winningGamePlayResults the winningGamePlayResults to set
	 */
	public void setWinningGamePlayResults(
			Set<WinningGamePlayResult> winningGamePlayResults) {
		this.winningGamePlayResults = winningGamePlayResults;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		
	   return promotionId;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
   public String getNaturalIdentity() {
	   
	   return name + NATURAL_IDENTITY_DELIMITER + projectNumber;
   }

	/**
	 * Returns the last computed promotion state.
	 * 
	 * @see evaluatePromotionState()
	 * 
	 * @return
	 */
	public AbstractPromotionState getPromotionState() {

		return this.promotionState;
	}

	/**
	 * @param promotionState the promotionState to set
	 */
	public void setPromotionState(AbstractPromotionState promotionState) {
		this.promotionState = promotionState;
	}
		
	/**
	 * @return the hasBeenDeployed
	 */
	public Boolean getHasBeenDeployed() {
		return hasBeenDeployed;
	}

	/**
	 * @param hasBeenDeployed the hasBeenDeployed to set
	 */
	public void setHasBeenDeployed(Boolean hasBeenDeployed) {
		this.hasBeenDeployed = hasBeenDeployed;
	}

	/**
	 * 
	 * @return
	 */
	public int getTotalCostForPromotion() {
		return getCost();
	}	

	/**
	 * TODO: TDM: This can also be done with a service layer query that can be passed into whatever domain object needs it.
	 * 
	 * @param emailAddress
	 * @return The number of Registrants that are associated with the given EmailAddress
	 */
	public int getNumberOfRegistrantsWithEmailAddress(EmailAddress emailAddress) {
		
		int numberOfRegistrantsWithEmailAddress = 0;
		
		Iterator<Registrant> iterator = this.registrants.iterator();
		while (iterator.hasNext()) {
			
			Registrant iteratedRegistrant = iterator.next();
			EmailAddress iteratedEmailAddress = iteratedRegistrant.getEmailAddress();
			if (iteratedEmailAddress.equals(emailAddress)) {
				
				numberOfRegistrantsWithEmailAddress = numberOfRegistrantsWithEmailAddress + 1;
			}
		}
		
		return numberOfRegistrantsWithEmailAddress;
	}
	
	/**
	 * TODO: TDM: This can also be done with a service layer query that can be passed into whatever domain object needs it.
	 * 
	 * @param mailingAddress
	 * @return The number of Registrants that live at the given mailing address.
	 */
	public int getNumberOfRegistrantsWithMailingAddress(MailingAddress mailingAddress) {
		
		int numberOfRegistrantsWithMailingAddress = 0;
		
		Iterator<Registrant> iterator = this.registrants.iterator();
		while (iterator.hasNext()) {
			
			Registrant iteratedRegistrant = iterator.next();
			MailingAddress iteratedMailingAddress = iteratedRegistrant.getMailingAddress();
			if (iteratedMailingAddress.equals(mailingAddress)) {
				
				numberOfRegistrantsWithMailingAddress = numberOfRegistrantsWithMailingAddress + 1;
			}
		}
		
		return numberOfRegistrantsWithMailingAddress;
	}

	/**
	 * TODO: TDM: This method assumes that all Registrant names have been normalized by an outside service!!!
	 * (e.g. firstName="Johnny" lastName="Doe" and firstName="John" lastName="Doe" would resolve to the same
	 * name, presumably whatever is the legal first name)
	 * 
	 * TODO: TDM: This can also be done with a service layer query that can be passed into whatever domain object needs it.
	 * 
	 * @param mailingAddress
	 * @return The number of Registrants that live at the given mailing address.
	 */
	public int getNumberOfRegistrantsWithGivenName(String normalizedFirstName, String normalizedLastName) {
		
		int numberOfRegistrantsWithGivenName = 0;
		
		Iterator<Registrant> iterator = this.registrants.iterator();
		while (iterator.hasNext()) {
			
			Registrant iteratedRegistrant = iterator.next();
			String iteratedFirstName = iteratedRegistrant.getFirstName();
			String iteratedLastName = iteratedRegistrant.getLastName();
			if (iteratedFirstName.equals(normalizedFirstName) && iteratedLastName.equals(normalizedLastName)) {
				
				numberOfRegistrantsWithGivenName = numberOfRegistrantsWithGivenName + 1;
			}
		}
		
		return numberOfRegistrantsWithGivenName;
	}
	
	/**
	 * TODO: TDM: This can also be done with a service layer query that can be passed into whatever domain object needs it.
	 * 
	 * @param ipAddress
	 * @return
	 */
	public int getNumberOfRegistrantsWithIpAddress(String ipAddress) {
		
		Map<String, Integer> numberOfRegistrantsToIpAddressMap = new HashMap<String, Integer>();

		Iterator<Registrant> registrantIterator = this.registrants.iterator();
		while (registrantIterator.hasNext()) {
			
			Registrant registrant = registrantIterator.next();
			
			Set<String> uniqueIpAddressesForRegistrantVisits = registrant.getUniqueIpAddressesForRegistrantVisits();
			Iterator<String> ipAddressIterator = uniqueIpAddressesForRegistrantVisits.iterator();
			while (ipAddressIterator.hasNext()) {
				
				String iteratedIpAddress = ipAddressIterator.next();
				
				Integer registrantCount = numberOfRegistrantsToIpAddressMap.get(iteratedIpAddress);
				if (registrantCount == null) {
					registrantCount = Integer.valueOf(1);
				} else {
					registrantCount = Integer.valueOf(registrantCount.intValue() + 1);
				}
				numberOfRegistrantsToIpAddressMap.put(iteratedIpAddress, registrantCount);
			}
		}
		
		Integer numberOfRegistrantsWithIpAddress = numberOfRegistrantsToIpAddressMap.get(ipAddress);
		return numberOfRegistrantsWithIpAddress.intValue();
	}
   
	/**
	 * TODO: TDM: This can also be done with a service layer query that can be passed into whatever domain object needs it.
	 * 
	 * @param prize
	 * @return
	 */
	public int getNumberOfPrizesAwardedForPrize(AbstractPrize prize) {
		
		int numberOfPrizesAwardedForPrize = 0;
		
		Iterator<WinningGamePlayResult> iterator = this.winningGamePlayResults.iterator();
		while (iterator.hasNext()) {
			
			WinningGamePlayResult winningGamePlayResult = iterator.next();
			if (winningGamePlayResult.getPrize().equals(prize)) {
				
				numberOfPrizesAwardedForPrize = numberOfPrizesAwardedForPrize + 1;
			}
		}
		
		return numberOfPrizesAwardedForPrize;
	}
	
	/**
	 * TODO: TDM: This can also be done with a service layer query that can be passed into whatever domain object needs it.
	 * 
	 * @param emailAddress
	 * @return
	 * @throws ObjectNotFoundException
	 */
	public Registrant getRegistrantByEmailAddress(String emailAddress) throws ObjectNotFoundException {
		
		Registrant registrant = this.getRegistrantByEmailAddressNullIfNotExists(emailAddress);
		
		if (registrant != null) {
			return registrant;	
		}
		
		throw new ObjectNotFoundException("Registrant with email address: [" + emailAddress + "] doesn't exist for promotion: [" + this + "].");
	}

	/**
	 * 
	 * @param emailAddress
	 * @return
	 */
	public Registrant getRegistrantByEmailAddressNullIfNotExists(String emailAddress) {
		
		Registrant registrant = null;
		
		Iterator<Registrant> iterator = this.registrants.iterator();
		while (iterator.hasNext()) {
			
			Registrant iteratedRegistrant = iterator.next();
			EmailAddress iteratedEmailAddress = iteratedRegistrant.getEmailAddress();
			if (iteratedEmailAddress.getOriginalEmailAddress().equals(emailAddress)) {
				
				registrant = iteratedRegistrant;
			}
		}
		
		return registrant;
	}
	
	/**
	 * 
	 * @param validateNonCoreAttributes
	 * @return
	 */
	public Map<String, String> validate(boolean validateNonCoreAttributes) {

		Map<String, String> validationErrors = new TreeMap<String, String>();
		
		if (this.getName() == null || this.getName().isEmpty()) {
			validationErrors.put("name", "Cannot be null or empty");
		}
		
		if (this.getClient() == null) {
			validationErrors.put("client", "Cannot be null");
		}

		if (this.getPromotionUUID() == null) {
			validationErrors.put("promotionUUID", "Cannot be null");
		}

		if (this.getPromoterUUID() == null) {
			validationErrors.put("promoterUUID", "Cannot be null");
		}

		if (this.getManagingAccountCoordinator() == null) {
			validationErrors.put("managingAccountCoordinator", "Cannot be null");
		}

		if (this.getTimekeeper() == null) {
			validationErrors.put("timekeeper", "Cannot be null");
		}
		
		if (this.getPromotionLoookAndFeel() == null) {
			validationErrors.put("promotionLookAndFeel", "Cannot be null");
		}
		
		if (this.getPromotionEligibility() == null) {
			validationErrors.put("promotionEligibility", "Cannot be null");
		}
		
		// We can only perform non-trivial validation if the 'base' objects are set (e.g. promotionLookAndFeel)
		if (validateNonCoreAttributes && validationErrors.isEmpty()) {

			Map<String, String> nonCoreValidationErrors = getNonCoreAttributeValidationErrors();

			if (nonCoreValidationErrors.size() > 0) {
				validationErrors.putAll(nonCoreValidationErrors);
			}
		}
		
		return validationErrors;
	}
	
    /*
     * (non-Javadoc)
     * @see com.eprize.montana.common.model.DomainObject#validate()
     */
	public Map<String, String> validate() {
		return validate(false);
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String, String> getNonCoreAttributeValidationErrors() {

		Map<String, String> validationErrors = new TreeMap<String, String>();
		
		validationErrors.putAll(this.validateBasePromotionDetails());
		validationErrors.putAll(this.validateDataCollectionDetails());
		validationErrors.putAll(this.validateLookAndFeelDetails());
		validationErrors.putAll(this.validatePrizingDetails());
		validationErrors.putAll(this.validateReportingMetrics());
		validationErrors.putAll(this.validateSharingDetails());
		
		return validationErrors;
	}
	
	/**
	 * 
	 * @return The percentage of Promotion configuration that's been completed.  There are 6 use cases for promotion configuration, 
	 * so there's 6 steps to perform (this does not have to be done sequentially).  Each step then, is roughly 16.6% of the total.
	 */
	public float getPercentageComplete() {
		
		float step = 16.66f;
		float percentageComplete = 0f;
				
		Map<String, String> baseDetailsValidationErrors = this.validateBasePromotionDetails();
		if (baseDetailsValidationErrors.isEmpty()) {
			percentageComplete = percentageComplete + step;
		}
		
		Map<String, String> dataCollectionDetailsValidationErrors = this.validateDataCollectionDetails();
		if (dataCollectionDetailsValidationErrors.isEmpty()) {
			percentageComplete = percentageComplete + step;
		}

		Map<String, String> lookAndFeelDetailsValidationErrors = this.validateLookAndFeelDetails();
		if (lookAndFeelDetailsValidationErrors.isEmpty()) {
			percentageComplete = percentageComplete + step;
		}

		Map<String, String> prizingDetailsValidationErrors = this.validatePrizingDetails();
		if (prizingDetailsValidationErrors.isEmpty()) {
			percentageComplete = percentageComplete + step;
		}

		Map<String, String> reportingMetricsValidationErrors = this.validateReportingMetrics();
		if (reportingMetricsValidationErrors.isEmpty()) {
			percentageComplete = percentageComplete + step;
		}

		Map<String, String> sharingDetailsValidationErrors = this.validateSharingDetails();
		if (sharingDetailsValidationErrors.isEmpty()) {
			percentageComplete = percentageComplete + step;
		}		
		
		if (percentageComplete > 99f) {
			percentageComplete = 100f;
		}
		
		return percentageComplete;
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, String> validateBasePromotionDetails() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();
		
		if (this.getConceptDate() == null) {
			validationErrors.put("conceptDate", "Cannot be null");
		}

		if (this.getLaunchDate() == null) {
			validationErrors.put("launchDate", "Cannot be null");
		}

		if (this.getEndDate() == null) {
			validationErrors.put("endDate", "Cannot be null");
		}
		
		if (this.getArchiveDate() == null) {
			validationErrors.put("archiveDate", "Cannot be null");
		}
		
		if (validationErrors.isEmpty()) {
			
			if (this.conceptDate.after(this.launchDate)
			   || this.conceptDate.after(this.endDate) 
			   || this.conceptDate.after(this.archiveDate)) {
				validationErrors.put("conceptDate", "conceptDate cannot be after launch/end/archive dates");
			}

			if (this.launchDate.after(this.endDate) 
			   || this.launchDate.after(this.archiveDate)) {
				validationErrors.put("launchDate", "launchDate cannot be after end/archive dates");
			}

			if (this.endDate.after(this.archiveDate)) {
				validationErrors.put("endDate", "endDate cannot be after archive date");
			}
	
			
			if (this.archiveDate.before(this.conceptDate)
			   || this.archiveDate.before(this.launchDate) 
			   || this.archiveDate.before(this.endDate)) {
				validationErrors.put("archiveDate", "archiveDate cannot be before concept/launch/end dates");
			}

			if (this.endDate.before(this.conceptDate)
			   || this.endDate.before(this.launchDate)) {
				validationErrors.put("endDate", "endDate cannot be before concept/launch dates");
			}
			
			if (this.launchDate.before(this.conceptDate)) {
				validationErrors.put("launchDate", "launchDate cannot be before concept date");
			}
			
			if (this.conceptDate.equals(this.launchDate)
			   || this.conceptDate.equals(this.endDate)
			   || this.conceptDate.equals(this.archiveDate)) {
				validationErrors.put("conceptDate", "conceptDate cannot be on launch/end/archive dates");
			}

			if (this.launchDate.equals(this.endDate)
			   || this.launchDate.equals(this.archiveDate)) {
				validationErrors.put("launchDate", "launchDate cannot be on end/archive dates");
			}
			
			if (this.endDate.equals(this.archiveDate)) {
				validationErrors.put("endDate", "endDate cannot be on archive date");
			}
		}

		
		if (this.getOptOutUrl() == null) {
			validationErrors.put("optOutUrl", "optOutUrl must be specified");
		}

		if (this.getTimezone() == null || this.getTimezone().isEmpty()) {
			validationErrors.put("timezone", "Cannot be null");
		} else {
			String parsedTimezone = TimeZone.getTimeZone(this.timezone).getID(); 
			if (!this.timezone.equals(parsedTimezone)) {
				validationErrors.put("timezone", "Parsed timezone[" + parsedTimezone + "] does not match expected timezone: [" + this.timezone + "].");
			}
 		}
		
		if (this.getProjectNumber() == null || this.getProjectNumber().equals(Long.valueOf(0))) {
			validationErrors.put("projectNumber", "Cannot be null or zero");
		}
		
		if (this.getPromotionLoookAndFeel().getDeliveryChannels().isEmpty()) {
			validationErrors.put("promotionLookAndFeel.deliveryChannels", "At least one delivery channel must be specified");
		}

		if (this.getPromotionLoookAndFeel().getMarketingGoals().isEmpty()) {
			validationErrors.put("promotionLookAndFeel.marketingGoals", "At least marketing goal must be specified");
		}
		
		return validationErrors;
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, String> validateDataCollectionDetails() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();
		
		PromotionLookAndFeel promotionLookAndFeel = this.getPromotionLoookAndFeel();
		Set<AbstractDeliveryChannel> deliveryChannels = promotionLookAndFeel.getDeliveryChannels();

		if (deliveryChannels == null || deliveryChannels.isEmpty()) {
			validationErrors.put("promotionLookAndFeel.deliveryChannels", "At least one delivery channel must be specified");
		} else {
			Iterator<AbstractDeliveryChannel> iterator = deliveryChannels.iterator();
			while (iterator.hasNext()) {
				
				AbstractDeliveryChannel deliveryChannel = iterator.next();
				validationErrors.putAll(deliveryChannel.validate());
			}
		}
		
		return validationErrors;
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, String> validatePrizingDetails() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();
		
		if (this.getProducts() == null || this.getProducts().isEmpty()) {
			validationErrors.put("products", "At least one product must be specified");
		}
		
		Set<AbstractPrizedProduct> abstractPrizedProducts = getPrizedProducts();
		if (!abstractPrizedProducts.isEmpty()) {
			
			Iterator<AbstractPrizedProduct> prizedProductIterator = abstractPrizedProducts.iterator();
			while (prizedProductIterator.hasNext()) {
				
				AbstractPrizedProduct abstractPrizedProduct = prizedProductIterator.next();
				validationErrors.putAll(abstractPrizedProduct.validate());
				
				Set<AbstractPrizePool> prizePools = abstractPrizedProduct.getPrizePools();
				Iterator<AbstractPrizePool> prizePoolIterator = prizePools.iterator();
				while (prizePoolIterator.hasNext()) {
					
					AbstractPrizePool prizePool = prizePoolIterator.next();
					validationErrors.putAll(prizePool.validate());
					
					if (prizePool instanceof InstantWinPrizePool) {
						
						InstantWinPrizePool instantWinPrizePool = (InstantWinPrizePool)prizePool;
						
						AbstractPrize prize = instantWinPrizePool.getPrize();
						validationErrors.putAll(prize.validate());
						
					} else if (prizePool instanceof SweepstakesPrizePool) {

						SweepstakesPrizePool sweepstakesPrizePool = (SweepstakesPrizePool)prizePool;
												
						Iterator<SweepstakesPrizeDrawing> prizeDrawingIterator = sweepstakesPrizePool.getSweepstakesPrizeDrawings().iterator();
						while (prizeDrawingIterator.hasNext()) {
							
							SweepstakesPrizeDrawing sweepstakesPrizeDrawing = prizeDrawingIterator.next();
							AbstractPrize prize = sweepstakesPrizeDrawing.getPrize();
							validationErrors.putAll(prize.validate());
						}
					} 
				}
			}
		}
		
		return validationErrors;
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, String> validateLookAndFeelDetails() {
		
		// TODO: TDM: There needs to be a canonical promotion that isn't
		// affiliated with any client whose object graph is copied when
		// creating a promotion.
		// For all of the various "canned" elements in the look and feel,
		// most notably any images, the validation routine here should
		// report validation errors when these canned images haven't 
		// been replaced with client/promotion specific images/copy.
		Map<String, String> validationErrors = new TreeMap<String, String>();
		//validationErrors.put("TODO", "Remove when implementing");
		return validationErrors;
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, String> validateReportingMetrics() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();
		//validationErrors.put("TODO", "Remove when implementing");		
		return validationErrors;
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, String> validateSharingDetails() {

		Map<String, String> validationErrors = new TreeMap<String, String>();
		//validationErrors.put("TODO", "Remove when implementing");
		return validationErrors;
	}
		
	/**
	 * This is how a Promotion evolves through time.  It is assumed that the service layer has some scheduling process to 
	 * iterate through all Promotions and invoke this method in order to induce a state change (based upon the given time)
	 * <p>
	 * For example, if a Promotion is in a <code>Pending</code> state, <b>and</b> the "current time" is on/after the 
	 * Promotion's launch date, then move the Promotion into the <code>Live</code> state.
	 * Another example is if a Promotion is in a <code>Live</code> state, then any time-based strategy objects neeed to be
	 * processed (such as a Daily sweepstakes drawing)
	 * <p>
	 * The current state of a promotion is derived as follows:
	 * <ol>
	 *   <li>CONCEPT: If the nascent promotion has been created, but whose object graph components are in an invalid state 
	 *   (i.e. not configured fully).  There are 6 use cases (in terms of Validation) for getting the promotion 
	 *   into a draft (fully configured) state:
	 *   <ol>
	 *     <li> <b>Edit Base Promotion Details</b>:
	 *     <li> <b>Edit Data Collection Details</b>: 
	 *     <li> <b>Edit Prizing Details</b>:
	 *     <li> <b>Edit Look and Feel Details</b>:
	 *     <li> <b>Edit Reporting Metrics</b>:
	 *     <li> <b>Edit Sharing Details</b>:
	 *   </ol>
	 *   NOTE: Each step is roughly 16.66% of the total process and after the base promotion details, the steps do not have to be done
	 *   in sequential order.
	 *   
	 *   <li>DRAFT:  The concept promotion object graph is in in a valid state (i.e. fully configured) 
	 *   but has not yet been "deployed" to the "Live" environment. Deployment here means that the web application context (or virtual host or whatever)
	 *   has been provisioned and whose static web content (e.g. images, javascript, css, static HTML) has been pushed out to the edge servers (or whatever)
	 *   
	 *   <li>PENDING: The draft promotion has been deployed to the "Live" environment and whose launch date is in the future.
	 *   At this point, Account Coordinators and Client Administrators can interact with the promotion (equivalent to QA/Review), but non authorized
	 *   users are presented with whatever page says the promotion has not been launched yet.
	 *   
	 *   <li>LIVE:  The pending promotion's launch date has now arrived and the promotion is available to the general public.
	 *   
	 *   <li>COMPLETED: The live promotion's end date has now arrived and the promotion once again becomes unavailable to non authorized users
	 *   (who are presented with whatever page says the promotion has ended)
	 * </ol>
	 * 
	 * @return the current promotion state (which may or may not have changed)
	 */
	public AbstractPromotionState evaluatePromotionState() {
		
		long currentTimeMillis = this.timekeeper.getCurrentTimeInMillis();

		// See if the promotion object graph is in a valid state first.
		Map<String, String> validationErrors = this.validate(true);
		
		// If the promotion has validation errors, then we are in the concept state.
		if (!validationErrors.isEmpty()) {

			this.promotionState = new ConceptPromotionState();
			
		} else {

			// If the promotion is in a valid state and we have not been "deployed" yet, then the promotion is in a draft state.
			if (!this.hasBeenDeployed.booleanValue()) {
				
				this.promotionState = new DraftPromotionState();
				
			} else {

				// If the current time is before the launch date, then the promotion is in a pending state.
				if (currentTimeMillis < launchDate.getTime()) {

					this.promotionState = new PendingPromotionState();
					
				} else {
					
					// If the current time is after the launch date, but before the end date, then the promotion is in a live state.
					if (currentTimeMillis >= launchDate.getTime() && currentTimeMillis < endDate.getTime()) {
						
						this.promotionState = new LivePromotionState();
						
						// TODO: TDM: It is during this state, that most of the promotion graph's state transitions are done (e.g. game play).
						
					} else {

						// If the current time is after the end date, but before the archive date, then the promotion is in a completed state.
						if (currentTimeMillis >= endDate.getTime() && currentTimeMillis < archiveDate.getTime()) {
							
							this.promotionState = new CompletedPromotionState();
							
							// Perform any post-live processing, such as promotion conclusion sweepstakes drawings.
							Iterator<AbstractPrizedProduct> prizedProductIterator = this.getPrizedProducts().iterator();
							while (prizedProductIterator.hasNext()) {
								
								AbstractPrizedProduct prizedProduct = prizedProductIterator.next();
								
								Iterator<AbstractPrizePool> prizePoolIterator = prizedProduct.getPrizePools().iterator();
								while (prizePoolIterator.hasNext()) {
									
									AbstractPrizePool prizePool = prizePoolIterator.next();
									
									AbstractGamePlayPrizeAwarding gamePlayPrizeAwarding = prizePool.getGamePlayPrizeAwarding();
									
									gamePlayPrizeAwarding.performPrizeAwarding();
								}
							}
							
						} else {

							// It is in this state, than some scheduled process perform any "undeployment" operation.
							this.promotionState = new ArchivedPromotionState();
							
						}
					}
				}
			}
		}
		
		return this.promotionState;
	}
	
	/**
	 * This method takes into account whether or not prize group limiting is in place.
	 * 
	 * @param prize
	 * @return
	 */
	public final int getPrizeWinLimit(AbstractPrize prize) {
		
		int winLimit = 0;
		if (this.getUseGroupWinLimiting()) {
			PrizeGroup prizeGroup = prize.getPrizeGroup();
			winLimit = prizeGroup.getGroupWinLimit().intValue();
		} else {
			winLimit = prize.getIndividualWinLimit().intValue();
		}
		return winLimit;
	}
	
	/**
	 * 
	 * @param registrant
	 * @return <code>null</code> if the given <code>registrant</code> has violated promotion eligibility, game play entry otherwise.
	 */
	public Set<GamePlayEntry> getRegistrantGamePlayEntriesForProduct(AbstractPrizedProduct product, Registrant registrant) {
	
		if (!this.getPrizedProducts().contains(product)) {
			throw new IllegalStateException("product: " + product + " is not defined in promotion: " + this);
		}
		
		Set<GamePlayEntry> gamePlayEntries = new TreeSet<GamePlayEntry>();

		// The registrant has different ways of getting game play entries, so iterate through the list of configured 
		// game entry strategies and build up a set of game play entries.
		Iterator<AbstractGamePlayEntryStrategy> gamePlayEntryStrategiesIterator = product.getGamePlayEntryStrategies().iterator();
		while (gamePlayEntryStrategiesIterator.hasNext()) {
			
			AbstractGamePlayEntryStrategy gamePlayEntryStrategy = gamePlayEntryStrategiesIterator.next();
			gamePlayEntries.addAll(gamePlayEntryStrategy.getGamePlayEntry(product, registrant));
		}
		product.getGamePlayEntries().addAll(gamePlayEntries);

		
		// If the product is instant win, then we need to evaluate whether or not they have just now won a prize.
		if (product instanceof AbstractInstantWinProduct) {
			
			AbstractInstantWinProduct instantWinProduct = (AbstractInstantWinProduct)product;
			
			Iterator<AbstractPrizePool> prizePoolIterator = instantWinProduct.getPrizePools().iterator();
			while (prizePoolIterator.hasNext()) {
				
				AbstractPrizePool prizePool = prizePoolIterator.next();
				
				prizePool.getGamePlayPrizeAwarding().performPrizeAwarding();
			}
		}
		
		return gamePlayEntries;
	}
}