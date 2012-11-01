/**
 * 
 */
package com.eprize.montana.common.model.user;

import java.util.Map;

import com.eprize.montana.common.model.client.Client;

/**
 * 
 * @author tmyers
 * 
 */
public class ClientAdministrator extends AuthenticatingUser {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private boolean termsAndConditionsAccepted;
	
	/* */
	private Client client;
		
	/**
	 * 
	 */
	public ClientAdministrator() {
		
	}
	
	/**
	 * @return the termsAndConditionsAccepted
	 */
	public boolean isTermsAndConditionsAccepted() {
		return termsAndConditionsAccepted;
	}

	/**
	 * @param termsAndConditionsAccepted the termsAndConditionsAccepted to set
	 */
	public void setTermsAndConditionsAccepted(boolean termsAndConditionsAccepted) {
		this.termsAndConditionsAccepted = termsAndConditionsAccepted;
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
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#validate()
	 */
	public Map<String, String> validate() {
		
		Map<String, String> validationErrors = super.validate();
		
		if (this.getClient() == null) {
			validationErrors.put("client", "Cannot be null");
		}
				
		return validationErrors;
	}
	
}