package com.lean.payment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lean.payment.service.exceptions.LeanException;
import com.lean.payment.service.pojo.Customer;
import com.lean.payment.service.service.impl.CustomerServicesImpl;

@SpringBootTest
class CustomerServicesImplTest {

	@Autowired
	private CustomerServicesImpl customerServicesImpl;

	@Test
	public void testPrepareLeanPaymentRedisCustomerInformation() throws LeanException {
		customerServicesImpl.prepareLeanPaymentRedisCustomersInformation();
	}

	@Test
	public void testToCustomerFunctionWithValidData() throws LeanException {
		String customerAsLine = "1,John Doe,1990-09-15,SAUDI,user1@leantech.me,\"1 National Arabic Towers, Riyadh, Saudi Arabia\"\n";
		Customer customer = customerServicesImpl.toCustomer(customerAsLine);
		assertNotNull(customer);
	}

	@Test
	public void testToCustomerFunctionWithNull() throws LeanException {
		String customerAsLine = null;
		customerServicesImpl.toCustomer(customerAsLine);
		assertThat(NullPointerException.class);
	}

	@Test
	public void testToCustomerFunctionWithInValidData() throws LeanException {
		String accountAsLine = "1,3,Savings,789654123051,SA000000789654123051,,(),,,qasdasdasdsACTIVE,123.56,SAR";
		Customer customer = customerServicesImpl.toCustomer(accountAsLine);
		assertNull(customer);
	}

	@Test
	public void getCustomerByValidId() {
		Customer customer = customerServicesImpl.getCustomerById(1);
		assertNotNull(customer);
	}

	@Test
	public void getCustomerByValidIdNotExists() {
		Customer customer = customerServicesImpl.getCustomerById(1959);
		assertNull(customer);
	}

}
