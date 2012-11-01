/**
 * 
 */
package com.eprize.montana.common.model.product.prized;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.eprize.montana.common.model.prize.entry.AbstractGamePlayEntryStrategy;
import com.eprize.montana.common.model.prize.entry.GamePlayEntry;
import com.eprize.montana.common.model.prize.entry.UponIdentificationGamePlayEntryStrategy;
import com.eprize.montana.common.model.prize.entry.UponRegistrationGamePlayEntryStrategy;
import com.eprize.montana.common.model.prize.pool.AbstractPrizePool;
import com.eprize.montana.common.model.product.AbstractProduct;

/**
 * 
 * @author tmyers
 * 
 */
public abstract class AbstractPrizedProduct extends AbstractProduct {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* TODO: Implement entry codes.  These are only used by the "Upon Entry Code" game play play entry strategy. */
	//protected Set<IEntryCode> entryCodes = new TreeSet<IEntryCode>();
	
	/* */
	protected Set<AbstractGamePlayEntryStrategy> gamePlayEntryStrategies = new TreeSet<AbstractGamePlayEntryStrategy>();
	
	/* */
	protected Set<GamePlayEntry> gamePlayEntries = new TreeSet<GamePlayEntry>();
	
	/* */
	protected Set<AbstractPrizePool> prizePools = new TreeSet<AbstractPrizePool>();
	
	/**
	 * 
	 */
	public AbstractPrizedProduct() {
		super();
		this.addGamePlayEntryStrategy(new UponRegistrationGamePlayEntryStrategy());
		this.addGamePlayEntryStrategy(new UponIdentificationGamePlayEntryStrategy());
	}
	
	/**
	 * @return the gamePlayEntries
	 */
	public Set<GamePlayEntry> getGamePlayEntries() {
		return gamePlayEntries;
	}

	/**
	 * @param gamePlayEntries the gamePlayEntries to set
	 */
	public void setGamePlayEntries(Set<GamePlayEntry> gamePlayEntries) {
		this.gamePlayEntries = gamePlayEntries;
	}

	/**
	 * @return the prizePools
	 */
	public Set<AbstractPrizePool> getPrizePools() {
		return prizePools;
	}

	/**
	 * @param prizePools the prizePools to set
	 */
	public void setPrizePools(Set<AbstractPrizePool> prizePools) {
		this.prizePools = prizePools;
	}
	
	/**
	 * 
	 * @param prizePool
	 * @return
	 */
	public boolean addPrizePool(AbstractPrizePool prizePool) {
		prizePool.setPrizedProduct(this);
		return this.prizePools.add(prizePool);
	}
	
	/**
	 * 
	 * @param prizePool
	 * @return
	 */
	public boolean removePrizePool(AbstractPrizePool prizePool) {
		prizePool.setPrizedProduct(null);
		return this.prizePools.remove(prizePool);
	}
	
	/**
	 * @return the gamePlayEntryStrategies
	 */
	public Set<AbstractGamePlayEntryStrategy> getGamePlayEntryStrategies() {
		return gamePlayEntryStrategies;
	}

	/**
	 * @param gamePlayEntryStrategies the gamePlayEntryStrategies to set
	 */
	public void setGamePlayEntryStrategies(Set<AbstractGamePlayEntryStrategy> gamePlayEntryStrategies) {
		this.gamePlayEntryStrategies = gamePlayEntryStrategies;
	}
	
	/**
	 * 
	 * @param gamePlayEntryStrategy
	 * @return
	 */
	public boolean addGamePlayEntryStrategy(AbstractGamePlayEntryStrategy gamePlayEntryStrategy) {
		gamePlayEntryStrategy.setPrizedProduct(this);
		return this.gamePlayEntryStrategies.add(gamePlayEntryStrategy);
	}

	/**
	 * 
	 * @param gamePlayEntryStrategy
	 * @return
	 */
	public boolean removeGamePlayEntryStrategy(AbstractGamePlayEntryStrategy gamePlayEntryStrategy) {
		gamePlayEntryStrategy.setPrizedProduct(null);
		return this.gamePlayEntryStrategies.remove(gamePlayEntryStrategy);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.product.AbstractProduct#validate()
	 */
	public Map<String, String> validate() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();
		
		if (this.getPrizePools() == null || this.getPrizePools().isEmpty()) {
			validationErrors.put("prizePool", "At least one prize pool must be specified for product: " + this);
		}
		
		if (this.gamePlayEntryStrategies == null || this.gamePlayEntryStrategies.isEmpty()) {
			validationErrors.put("gamePlayEntryStrategies", "At least one game play entry strategy must be specified for product: " + this);
		}
		
		return validationErrors;
	}
}