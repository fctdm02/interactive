package com.eprize.montana.common.model.prize.entry;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.eprize.montana.common.model.DomainObjectFactory;
import com.eprize.montana.common.model.product.prized.AbstractPrizedProduct;
import com.eprize.montana.common.model.registration.RegistrantVisit;
import com.eprize.montana.common.model.user.Registrant;

/**
 * 
 * @author Tom.Myers
 *
 */
public class UponRegistrationGamePlayEntryStrategy extends AbstractGamePlayEntryStrategy {
	
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

			// If the registrant has exactly one visit, then that means that they just registered.  
			// If so, award a game play entry. Otherwise, do nothing.
			List<RegistrantVisit> registrantVisitList = registrant.getRegistrantVisitsAsList();
			int size = registrantVisitList.size();
			if (size == 1) {
				gamePlayEntries.add(DomainObjectFactory.createGamePlayEntry(abstractPrizedProduct, registrantVisitList.get(size-1)));
			}
		}		
		
		return gamePlayEntries;
	}
}