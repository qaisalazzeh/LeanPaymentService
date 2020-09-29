package com.lean.payment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lean.payment.service.exceptions.LeanException;
import com.lean.payment.service.pojo.Account;
import com.lean.payment.service.service.impl.AccountServicesImpl;

@SpringBootTest
class AccountServicesImplTest {

	@Autowired
	private AccountServicesImpl accountServices;

	@Test
	public void testPrepareLeanPaymentRedisAccountsInformation() throws LeanException {
		accountServices.prepareLeanPaymentRedisAccountsInformation();
	}

	@Test
	public void testToAccountFunctionWithValidData() throws LeanException {
		String accountAsLine = "1,3,Savings,789654123051,SA000000789654123051,ACTIVE,123.56,SAR";
		Account Account = accountServices.toAccount(accountAsLine);
		assertNotNull(Account);
	}

	@Test
	public void testToAccountFunctionWithNull() throws LeanException {
		String accountAsLine = null;
		accountServices.toAccount(accountAsLine);
		assertThat(NullPointerException.class);
	}

	@Test
	public void testToAccountFunctionWithInValidData() throws LeanException {
		String accountAsLine = "1,3,Savings,789654123051,SA000000789654123051,,(),,,qasdasdasdsACTIVE,123.56,SAR";
		Account Account = accountServices.toAccount(accountAsLine);
		assertNull(Account);
	}

	@Test
	public void getAccountByValidId() {
		Account account = accountServices.getAccountByid(1);
		assertNotNull(account);
	}

	@Test
	public void getAccountByValidIdNotExists() {
		Account account = accountServices.getAccountByid(1959);
		assertNull(account);
	}

}
