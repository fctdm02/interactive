/**
 * 
 */
package com.eprize.montana.common.model.prize;


/**
 * 
 * @author tmyers
 * 
 */
public class PhysicalPrize extends AbstractPrize {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private boolean isTravelPrize;
	
	/* */
	private boolean isVehiclePrize;
	
	/* */
	private boolean isClothingPrize;
	
	/**
	 * 
	 */
	public PhysicalPrize() {
		
	}

	/**
	 * @return the isTravelPrize
	 */
	public boolean isTravelPrize() {
		return isTravelPrize;
	}

	/**
	 * @param isTravelPrize the isTravelPrize to set
	 */
	public void setTravelPrize(boolean isTravelPrize) {
		this.isTravelPrize = isTravelPrize;
	}

	/**
	 * @return the isVehiclePrize
	 */
	public boolean isVehiclePrize() {
		return isVehiclePrize;
	}

	/**
	 * @param isVehiclePrize the isVehiclePrize to set
	 */
	public void setVehiclePrize(boolean isVehiclePrize) {
		this.isVehiclePrize = isVehiclePrize;
	}

	/**
	 * @return the isClothingPrize
	 */
	public boolean isClothingPrize() {
		return isClothingPrize;
	}

	/**
	 * @param isClothingPrize the isClothingPrize to set
	 */
	public void setClothingPrize(boolean isClothingPrize) {
		this.isClothingPrize = isClothingPrize;
	}
}