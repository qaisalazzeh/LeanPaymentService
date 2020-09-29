package com.lean.payment.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lean.payment.service.exceptions.LeanException;
import com.lean.payment.service.service.impl.CommonServicesImpl;

@SpringBootTest
public class CommonServicesImplTest {

	@Autowired
	CommonServicesImpl commonServicesImpl;

	@Test
	void testCrossSiteScriptingWithValidRequest() {
		commonServicesImpl.CrossSiteScripting(1);
	}

	@Test
	void testCrossSiteScriptingWithInValidRequest() {
		commonServicesImpl.CrossSiteScripting("1<Scrip>");
	}

	@Test
	void testIsRequestValidWithValidRequest() throws LeanException {
		assertTrue(commonServicesImpl.isRequestValid(1, "sdf79a8sd7f79adf"));
	}

	@Test
	void testIsRequestValidWithInValidRequestAndMerchantIsNotWhitelisted() throws LeanException {
		Assertions.assertThrows(LeanException.class, () -> {
			commonServicesImpl.isRequestValid(1, "sdf79aasasdasd8sd7f79adf");
		});

	}

	@Test
	void testIsRequestValidWithInvlaidNumberValidRequest() throws LeanException {
		Assertions.assertThrows(LeanException.class, () -> {
			commonServicesImpl.isRequestValid("lean", "sdf79a8sd7f79adf");
		});

	}
}
