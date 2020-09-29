package com.lean.payment.service.service;

import java.util.List;

import com.lean.payment.service.exceptions.LeanException;
import com.lean.payment.service.pojo.Transcation;
import com.lean.payment.service.redis.entities.TranscationEntity;

/**
 * 
 * @author Qais Azzeh
 *
 */
public interface TransactionsServices {

	/**
	 * Save all transactions into redis
	 * 
	 * @param transactionsList
	 */
	void saveAllTransactions(List<TranscationEntity> transactionsList);

	/**
	 * Prepare All Account On BootUp
	 * 
	 * @throws LeanException
	 *
	 */
	void prepareLeanPaymentRedisTransactionsInformation() throws LeanException;

	/**
	 * Get Biz Transaction Object by id
	 * 
	 * @param id
	 * @return
	 */
	Transcation getTransactionById(Object id);

	/**
	 * Get All Transactions that occured on given Account
	 * 
	 * @param id
	 * @return
	 */
	List<Transcation> getAllTransactionByAccountId(Object id);
}
