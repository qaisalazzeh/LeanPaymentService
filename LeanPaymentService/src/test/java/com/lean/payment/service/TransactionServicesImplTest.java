package com.lean.payment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lean.payment.service.exceptions.LeanException;
import com.lean.payment.service.pojo.Transcation;
import com.lean.payment.service.service.impl.TransactionsServicesimpl;

@SpringBootTest
class TransactionServicesImplTest {

	@Autowired
	private TransactionsServicesimpl transactionsServicesimpl;

	@Test
	public void testPrepareLeanPaymentRedisTransactionInformation() throws LeanException {
		transactionsServicesimpl.prepareLeanPaymentRedisTransactionsInformation();
	}

	@Test
	public void testToTransactionFunctionWithValidData() throws LeanException {
		String transactionAsLine = "2,1,Transfer,Transfer from Another Bank,11.99,SAR,2020-05-01 12:44:03";
		Transcation transcation = transactionsServicesimpl.toTransaction(transactionAsLine);
		assertNotNull(transcation);
	}

	@Test
	public void testToTransactionFunctionWithNull() throws LeanException {
		String transactionAsLine = null;
		transactionsServicesimpl.toTransaction(transactionAsLine);
		assertThat(NullPointerException.class);
	}

	@Test
	public void testToTransactionFunctionWithInValidData() throws LeanException {
		String transactionAsLine = "2,1,Transfer,Transfer from Anot43tmb,drtyrew5r35tgjsg,.her Bank,11.99,SAR,2020-05-01 12:44:03";
		Transcation transcation = transactionsServicesimpl.toTransaction(transactionAsLine);
		assertNull(transcation);
	}

	@Test
	public void getTransactionByValidId() {
		Transcation transcation = transactionsServicesimpl.getTransactionById(1);
		assertNotNull(transcation);
	}

	@Test
	public void getTransactionByValidIdNotExists() {
		Transcation transcation = transactionsServicesimpl.getTransactionById(1959);
		assertNull(transcation);
	}

	@Test
	public void getTransactionByValidAccountId() {
		List<Transcation> transcation = transactionsServicesimpl.getAllTransactionByAccountId(1);
		assertNotNull(transcation);
	}

	@Test
	public void getTransactionByInValidAccountId() {
		List<Transcation> transcation = transactionsServicesimpl.getAllTransactionByAccountId(2132);
		assertNotNull(transcation);
	}

}
