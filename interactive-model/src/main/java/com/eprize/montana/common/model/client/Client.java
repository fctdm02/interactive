/**
 * 
 */
package com.eprize.montana.common.model.client;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.exception.ObjectNotFoundException;
import com.eprize.montana.common.model.location.Country;
import com.eprize.montana.common.model.promotion.Promotion;
import com.eprize.montana.common.model.user.ClientAdministrator;

/**
 * 
 * @author tmyers
 * 
 */
@XmlRootElement
public class Client extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;

	
	/* */
	private Long clientId;
	
	/* */
	private String shortName;
	
	/* */
	private String legalName;
	
	/* */
	private String legalMailingAddress;

	/* */
	private String objective;
	
	/* */
	private String licenseNumber;

	/* */
	private String taxId;
			
    /* */
    private Set<Promotion> promotions = new TreeSet<Promotion>();
    
    /* */
    private Set<ClientAdministrator> clientAdministrators = new TreeSet<ClientAdministrator>();

    /* */
    private Set<Country> countries = new TreeSet<Country>();
    
   /**
    * 
    */
   public Client() {     
   }
   
	/**
    * @return the clientId
    */
   @XmlAttribute
   public Long getClientId() {
      return clientId;
   }

   /**
    * @param clientId the clientId to set
    */
   public void setClientId(Long clientId) {
      this.clientId = clientId;
   }

   /**
    * @return the shortName
    */
   @XmlAttribute
   public String getShortName() {
       return this.shortName;
   }

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
	    this.shortName = DomainObject.trimString(shortName);
	}

	/**
	 * @return the legalName
	 */
	@XmlAttribute
	public String getLegalName() {
		return legalName;
	}

	/**
	 * @param legalName the legalName to set
	 */
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	/**
	 * @return the legalMailingAddress
	 */
	@XmlAttribute
	public String getLegalMailingAddress() {
		return legalMailingAddress;
	}

	/**
	 * @param legalMailingAddress the legalMailingAddress to set
	 */
	public void setLegalMailingAddress(String legalMailingAddress) {
		this.legalMailingAddress = legalMailingAddress;
	}

	/**
	 * @return the objective
	 */
	@XmlAttribute
	public String getObjective() {
		return objective;
	}

	/**
	 * @param objective the objective to set
	 */
	public void setObjective(String objective) {
		this.objective = objective;
	}

	/**
	 * @return the licenseNumber
	 */
	@XmlAttribute
	public String getLicenseNumber() {
		return licenseNumber;
	}

	/**
	 * @param licenseNumber the licenseNumber to set
	 */
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	/**
	 * @return the taxId
	 */
	@XmlAttribute
	public String getTaxId() {
		return taxId;
	}

	/**
	 * @param taxId the taxId to set
	 */
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	
    /**
	 * @return the promotions
	 */
	@XmlElementWrapper(name="promotionSet")
    @XmlElement(name="promotion")
	public Set<Promotion> getPromotions() {
		return promotions;
	}

    /**
     * @param promotions the promotions to set
     */
    public void setPromotions(Set<Promotion> promotions) {
        this.promotions = promotions;
    }

    /**
     * 
     * @param promotion
     */
    public void addPromotion(Promotion promotion) {
        promotion.setClient(this);
        this.promotions.add(promotion);
    }
	
    /**
     * 
     * @param promotionName
     * @throws ObjectNotFoundException
     * @return Promotion
     */
    public Promotion removePromotion(String promotionName) throws ObjectNotFoundException {
        
        Promotion promotionToRemove = null;
        Iterator<Promotion> iterator = this.promotions.iterator();
        while (iterator.hasNext()) {
            
            Promotion promotion = iterator.next();
            if (promotion.getName().equalsIgnoreCase(promotionName)) {
                promotionToRemove = promotion;
            }
        }
        
        if (promotionToRemove == null) {
            throw new ObjectNotFoundException("Could not find promotion with name: [" + promotionName + "] in realm: [" + this + "].");
        }
        
        this.promotions.remove(promotionToRemove);
        
        return promotionToRemove;
    }
    	
	/**
	 * 
	 * @param promotionName
	 * @return The promotion with the given name
	 * @throws ObjectNotFoundException
	 */
	public Promotion getPromotionByPromotionName(String promotionName) throws ObjectNotFoundException {
	    
	    Iterator<Promotion> iterator = promotions.iterator();
	    while (iterator.hasNext()) {
	        
	        Promotion promotion = iterator.next();
	        if (promotion.getName().equalsIgnoreCase(promotionName)) {
	            return promotion;
	        }
	    }
	    throw new ObjectNotFoundException("Could not find promotion with name: " + promotionName + " for realm: " + this);
	}

	/**
	 * @return the clientAdministrators
	 */
	public Set<ClientAdministrator> getClientAdministrators() {
		return clientAdministrators;
	}

	/**
	 * @param clientAdministrators the clientAdministrators to set
	 */
	public void setClientAdministrators(Set<ClientAdministrator> clientAdministrators) {
		this.clientAdministrators = clientAdministrators;
	}

	/**
	 * 
	 * @param clientAdministrator
	 * @return
	 */
	public boolean addClientAdministrator(ClientAdministrator clientAdministrator) {
		clientAdministrator.setClient(this);
		return this.clientAdministrators.add(clientAdministrator);
	}
	
	/**
	 * 
	 * @param clientAdministrator
	 * @return
	 */
	public boolean removeClientAdministrator(ClientAdministrator clientAdministrator) {
		clientAdministrator.setClient(null);
		return this.clientAdministrators.remove(clientAdministrator);
	}
	
	/**
	 * @return the countries
	 */
	public Set<Country> getCountries() {
		return countries;
	}

	/**
	 * @param countries the countries to set
	 */
	public void setCountries(Set<Country> countries) {
		this.countries = countries;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		
	   return clientId;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
   public String getNaturalIdentity() {
	   
	   return shortName;
   }

    /*
     * (non-Javadoc)
     * @see com.eprize.montana.common.model.DomainObject#validate()
     */
	public Map<String, String> validate() {

		Map<String, String> validationErrors = new TreeMap<String, String>();
		
		validateRequiredAttribute(this.shortName, "shortName", validationErrors);
		validateRequiredAttribute(this.legalName, "legalName", validationErrors);
		validateRequiredAttribute(this.legalMailingAddress, "legalMailingAddress", validationErrors);
		validateRequiredAttribute(this.objective, "objective", validationErrors);
		validateRequiredAttribute(this.licenseNumber, "licenseNumber", validationErrors);
		validateRequiredAttribute(this.taxId, "taxId", validationErrors);
		
		return validationErrors;
	}
}