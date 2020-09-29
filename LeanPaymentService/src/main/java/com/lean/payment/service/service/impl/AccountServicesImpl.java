package com.lean.payment.service.service.impl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.lean.payment.service.constants.LeanConstants.RESPONSE_CODE;
import com.lean.payment.service.constants.LeanConstants.RESPONSE_MSG;
import com.lean.payment.service.exceptions.LeanException;
import com.lean.payment.service.pojo.Account;
import com.lean.payment.service.pojo.Customer;
import com.lean.payment.service.redis.entities.AccountEntity;
import com.lean.payment.service.rids.repo.AccountRepository;
import com.lean.payment.service.service.AccountServices;
import com.lean.payment.service.utilities.Encryptable;

/**
 * 
 * @author Qais Azzeh
 *
 */
@Service
public class AccountServicesImpl implements AccountServices {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private EncryptableService encryptableService;

	/**
	 * Saves all accounts in redis entity
	 */
	@Override
	public void saveAccounts(List<AccountEntity> accountList) {

		for (AccountEntity accountEntity : accountList) {
			accountRepository.create(accountEntity);
		}
	}

	/**
	 * Load Account informations into rdis
	 * 
	 * @throws LeanException
	 */
	@Override
	public void prepareLeanPaymentRedisAccountsInformation() throws LeanException {
		List<Account> accountList = new ArrayList<Account>();
		try (Stream<String> content = Files.lines(
				Paths.get(new ClassPathResource("LeanPaymentInformation/Account.csv").getFile().getCanonicalPath()))) {
			accountList = content.skip(1).map(line -> toAccount(line)).collect(Collectors.toList());
			List<AccountEntity> accountEntities = accountList.stream().map(account -> account.createEntity())
					.collect(Collectors.toList());
			saveAccounts(accountEntities);
		} catch (Exception ex) {
			throw new LeanException(RESPONSE_MSG.GENERAL_EXCEPTION, RESPONSE_CODE.FAILURE_RESPONSE_CODE);
		}

	}

	/**
	 * Translate file content into biz objects
	 * 
	 * @param accountAsLine
	 * @return
	 */
	public Account toAccount(String accountAsLine) {
		try {
			String[] array = accountAsLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			Long id = Long.valueOf(array[0]);
			Customer customerId = new Customer(Long.valueOf(array[1]));
			String type = array[2];
			Encryptable accountNumber = encryptableService.newEncryptableWithPlainThenEncrypted(array[3]);
			Encryptable iban = encryptableService.newEncryptableWithPlainThenEncrypted(array[4]);
			String status = array[5];
			Double balance = Double.valueOf(array[6]);
			String currencyCode = array[7];
			return new Account(id, customerId, type, accountNumber, iban, status, balance, currencyCode);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Get Biz Object By id
	 */
	@Override
	public Account getAccountByid(Object id) {
		AccountEntity accountEntity = accountRepository.get(Long.valueOf(String.valueOf(id)));
		if (Objects.isNull(accountEntity)) {
			return null;
		}
		Account account = new Account(accountEntity);
		account.setAccountNumber(
				encryptableService.newEncryptableWithEncryptedThenPlain(account.getAccountNumber().getEncrypted()));
		account.setIban(encryptableService.newEncryptableWithEncryptedThenPlain(account.getIban().getEncrypted()));
		return account;

	}

}
