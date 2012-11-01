package com.eprize.montana.common.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.eprize.montana.common.model.promotion.PromotionTest;
import com.eprize.montana.common.model.promotion.eligibility.registrationfrequency.RegistrationFrequencyTest;
import com.eprize.montana.common.model.user.RegistrantTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	DomainObjectFactoryTest.class,
	PromotionTest.class,
	RegistrationFrequencyTest.class,
	RegistrantTest.class
})

/**
 * 
 * @author Tom.Myers
 *
 */
public class ModelAllTests {
		
}