package com.lean.payment.service.service;

import com.lean.payment.service.exceptions.LeanException;

/**
 * 
 * @author Qais Azzeh
 *
 */
public interface CommonServices {

	/**
	 * Function to clean request from Scripting attack like js attack , sql
	 * injection
	 * 
	 * @param requestParam
	 */
	void CrossSiteScripting(Object requestParam);

	/**
	 * Biz Validation for consumed requests
	 * 
	 * @param requestParam
	 * @param headerValue
	 * @return
	 * @throws LeanException
	 */
	Boolean isRequestValid(Object requestParam, String headerValue) throws LeanException;

}
