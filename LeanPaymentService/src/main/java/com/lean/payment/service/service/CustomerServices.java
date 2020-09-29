package com.lean.payment.service.service;

import java.text.ParseException;
import java.util.List;

import com.lean.payment.service.exceptions.LeanException;
import com.lean.payment.service.pojo.Customer;
import com.lean.payment.service.redis.entities.CustomerEntity;

/**
 * 
 * @author Qais Azzeh
 *
 */

public interface CustomerServices {

	/**
	 * Saves All customer into redis
	 * 
	 * @param customerList
	 */
	void saveAllCustomers(List<CustomerEntity> customerList);

	/**
	 * Prepare All Customer On BootUp
	 * 
	 * @throws ParseException
	 * @throws LeanException
	 *
	 */
	void prepareLeanPaymentRedisCustomersInformation() throws ParseException, LeanException;

	/**
	 * Get Biz Customer Object
	 * 
	 * @param Id
	 * @return
	 */
	Customer getCustomerById(Object Id);

}
