package com.eprize.montana.common.model;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.eprize.montana.common.MontanaCommonConstants;
import com.eprize.montana.common.model.client.Client;
import com.eprize.montana.common.model.lookandfeel.form.LoginForm;
import com.eprize.montana.common.model.promotion.Promotion;
import com.eprize.montana.common.model.user.AccountCoordinator;
import com.eprize.montana.common.model.user.ClientAdministrator;


/**
 * 
 * @author Tom.Myers
 *
 */
public class DomainObjectFactoryTest implements MontanaCommonConstants {
		
	@Test
	public void test_createAccountCoordinator() throws Exception {
	
		AccountCoordinator accountCoordinator = DomainObjectFactory.createAccountCoordinator(accountCoordinatorUsername, accountCoordinatorPassword, accountCoordinatorPasswordVerify, team);
		accountCoordinator.validate();
	}
	
	
	
	@Test
	public void test_createClient() throws Exception {
		
		Client client = DomainObjectFactory.createClient(shortName, legalName, legalMailingAddress, objective, licenseNumber, taxId);
		client.validate();
	}
	
	@Test
	public void test_createClient_invalidShortName() throws Exception {
		
		Client client = DomainObjectFactory.createClient(null, legalName, legalMailingAddress, objective, licenseNumber, taxId);
		Map<String, String> validationErrors = client.validate();
		Assert.assertEquals("Validation errors is incorrect", Integer.toString(1), Integer.toString(validationErrors.size()));
	}
	
	
	@Test
	public void test_createPromotion() throws Exception {
		
		AccountCoordinator managingAccountCoordinator = DomainObjectFactory.createAccountCoordinator(accountCoordinatorUsername, accountCoordinatorPassword, accountCoordinatorPasswordVerify, team);
		Client client = DomainObjectFactory.createClient(shortName, legalName, legalMailingAddress, objective, licenseNumber, taxId);
		Promotion promotion = DomainObjectFactory.createPromotion(managingAccountCoordinator, client, promotionName, projectNumber);
		promotion.validate();
	}
	
	@Test
	public void test_createLoginForm() throws Exception {
		
		LoginForm loginForm = DomainObjectFactory.createLoginForm();
		Map<String, String> validationErrors = loginForm.validate();
		Assert.assertEquals("Validation errors is incorrect", "{}", validationErrors.toString());
	}
	
	@Test
	public void test_createClientAdministrator() throws Exception {

		Client client = DomainObjectFactory.createClient(shortName, legalName, legalMailingAddress, objective, licenseNumber, taxId);
		
		ClientAdministrator clientAdministrator = DomainObjectFactory.createClientAdministrator(
			accountCoordinatorUsername, 
			accountCoordinatorPassword, 
			accountCoordinatorPasswordVerify, 
			client, 
			termsAndConditionsAccepted);
		
		clientAdministrator.validate();
	}
}