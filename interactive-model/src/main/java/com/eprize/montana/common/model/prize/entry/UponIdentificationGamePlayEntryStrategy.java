package com.eprize.montana.common.model.prize.entry;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.eprize.montana.common.model.DomainObjectFactory;
import com.eprize.montana.common.model.product.prized.AbstractPrizedProduct;
import com.eprize.montana.common.model.promotion.Promotion;
import com.eprize.montana.common.model.promotion.eligibility.PromotionEligibility;
import com.eprize.montana.common.model.promotion.eligibility.registrationfrequency.AbstractRegistrationFrequency;
import com.eprize.montana.common.model.registration.RegistrantVisit;
import com.eprize.montana.common.model.user.Registrant;

/**
 * Otherwise known as "Upon Login"...
 * 
 * @author Tom.Myers
 *
 */
public class UponIdentificationGamePlayEntryStrategy extends AbstractGamePlayEntryStrategy {

	/* */
	private static final long serialVersionUID = 1L;
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.product.prized.entry.IGamePlayEntryStrategy#getGamePlayEntry(com.eprize.montana.common.model.product.prized.AbstractPrizedProduct, com.eprize.montana.common.model.registration.Registrant)
	 */
	public Set<GamePlayEntry> getGamePlayEntry(AbstractPrizedProduct abstractPrizedProduct, Registrant registrant) {

		Set<GamePlayEntry> gamePlayEntries = new TreeSet<GamePlayEntry>();
		
		// See if the registrant meets the various promotion constraints 
		// (e.g. minimum ave, max. number of registrations per email/person/household/IP Address)
		if (super.isRegistrantEligible(abstractPrizedProduct, registrant)) {

			// If the registrant has more than one visit, then that means that they just identified themselves (i.e. logged in).  
			// If so, see if they meet the registration frequency period and if so, award a game play entry.  
			// Otherwise, do nothing.
			List<RegistrantVisit> registrantVisitList = registrant.getRegistrantVisitsAsList();
			int size = registrantVisitList.size();
			if (size > 1) {

				Promotion promotion = abstractPrizedProduct.getPromotion();
				PromotionEligibility promotionEligibility = promotion.getPromotionEligibility();

				AbstractRegistrationFrequency registrationFrequency = promotionEligibility.getRegistrationFrequency();
				
				if (registrationFrequency.isEligibleForRegistration(registrant)) {
					
					gamePlayEntries.add(DomainObjectFactory.createGamePlayEntry(abstractPrizedProduct, registrantVisitList.get(size-1)));	
				}
			}
		}
		
		return gamePlayEntries;
	}
}