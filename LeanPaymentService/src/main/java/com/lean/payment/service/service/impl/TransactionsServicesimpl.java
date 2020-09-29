package com.lean.payment.service.service.impl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
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
import com.lean.payment.service.pojo.Transcation;
import com.lean.payment.service.redis.entities.TranscationEntity;
import com.lean.payment.service.rids.repo.TranscationRepository;
import com.lean.payment.service.service.TransactionsServices;
import com.lean.payment.service.utilities.DateUtil;

/**
 * 
 * @author Qais Azzeh
 *
 */
@Service
public class TransactionsServicesimpl implements TransactionsServices {

	@Autowired
	private TranscationRepository transcationRepository;

	/**
	 * Save all Transactions into redis
	 */
	@Override
	public void saveAllTransactions(List<TranscationEntity> transactionsList) {
		for (TranscationEntity transcationEntity : transactionsList) {
			transcationRepository.create(transcationEntity);
		}

	}

	/**
	 * Prepare Transactions info and save them into redis
	 * 
	 * @throws LeanException
	 */
	@Override
	public void prepareLeanPaymentRedisTransactionsInformation() throws LeanException {
		List<Transcation> transcationList = new ArrayList<Transcation>();
		try (Stream<String> content = Files.lines(Paths
				.get(new ClassPathResource("LeanPaymentInformation/transaction.csv").getFile().getCanonicalPath()))) {
			transcationList = content.skip(1).map(line -> toTransaction(line)).collect(Collectors.toList());
			List<TranscationEntity> transcationEntities = transcationList.stream()
					.map(customer -> customer.createEntity()).collect(Collectors.toList());
			saveAllTransactions(transcationEntities);
		} catch (Exception ex) {
			throw new LeanException(RESPONSE_MSG.GENERAL_EXCEPTION, RESPONSE_CODE.FAILURE_RESPONSE_CODE);
		}

	}

	/**
	 * Translate File Content into Objects
	 * 
	 * @param transactionAsLine
	 * @return
	 */
	public Transcation toTransaction(String transactionAsLine) {
		try {
			String[] array = transactionAsLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			Long id = Long.valueOf(array[0]);
			Account accountId = new Account(Long.valueOf(array[1]));
			String type = array[2];
			String description = array[3];
			Double amount = Double.valueOf(array[4]);
			String currencyCode = array[5];
			Long timeStamp = DateUtil.getStringAsDate(array[6], "MM-dd-yyyy HH:mm").getTime();
			return new Transcation(id, accountId, type, description, amount, currencyCode, timeStamp);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Gets Transaction By id
	 */
	@Override
	public Transcation getTransactionById(Object id) {
		TranscationEntity transcationEntity = transcationRepository.get(Long.valueOf(String.valueOf(id)));
		if (Objects.isNull(transcationEntity)) {
			return null;
		}
		return new Transcation(transcationEntity);
	}

	/**
	 * Gets All transaction for Given Account
	 */
	@Override
	public List<Transcation> getAllTransactionByAccountId(Object id) {
		List<Transcation> listOfTransactions = transcationRepository.getAllKeysByKeyword("TRANSCATION").stream()
				.map(key -> getTranscationEntityByKey(key)).map(tranactionEn -> new Transcation(tranactionEn))
				.filter(tranactoin -> tranactoin.getAccountId().getId().equals(Long.valueOf(String.valueOf(id))))
				.collect(Collectors.toList());
		Collections.sort(listOfTransactions);
		return listOfTransactions;

	}

	/**
	 * Get Transactions Entitiy By Key from redis
	 * 
	 * @param key
	 * @return
	 */
	private TranscationEntity getTranscationEntityByKey(String key) {
		char last = key.charAt(key.length() - 1);
		long one = Character.getNumericValue(last);
		return transcationRepository.get(Long.valueOf(one));
	}

}
