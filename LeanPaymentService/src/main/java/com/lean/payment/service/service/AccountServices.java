package com.lean.payment.service.service;

import java.util.List;

import com.lean.payment.service.exceptions.LeanException;
import com.lean.payment.service.pojo.Account;
import com.lean.payment.service.redis.entities.AccountEntity;

/**
 * 
 * @author Qais Azzeh
 *
 */

public interface AccountServices {

	/**
	 * Saves All the accounts object into redis
	 * 
	 * @param accountList
	 */
	void saveAccounts(List<AccountEntity> accountList);

	/**
	 * Prepare All Account On BootUp
	 * 
	 * @throws LeanException
	 *
	 */
	void prepareLeanPaymentRedisAccountsInformation() throws LeanException;

	/**
	 * Return Account Biz Object
	 * 
	 * @param id
	 * @return
	 */
	Account getAccountByid(Object id);
}
