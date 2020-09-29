package com.lean.payment.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lean.payment.service.constants.LeanConstants.RESPONSE_CODE;
import com.lean.payment.service.constants.LeanConstants.RESPONSE_MSG;
import com.lean.payment.service.exceptions.LeanException;
import com.lean.payment.service.service.CommonServices;
import com.lean.payment.service.service.ConfigServices;
import com.lean.payment.service.utilities.CrossScriptingUtil;
import com.lean.payment.service.utilities.TextUtil;

/**
 * 
 * @author Qais Azzeh
 *
 */
@Component
public class CommonServicesImpl implements CommonServices {

	@Autowired
	ConfigServices configServices;

	/**
	 * Clean Request from Scripting
	 */
	@Override
	public void CrossSiteScripting(Object requestParam) {
		CrossScriptingUtil.cleanXSS(requestParam);
	}

	/**
	 * Checks if the request valid
	 */
	@Override
	public Boolean isRequestValid(Object requestParam, String headerValue) throws LeanException {
		checkIfMerchantIsWhiteListed(headerValue);
		checkRequestNotNull(requestParam);
		checkRequestIsOnlyNumber(String.valueOf(requestParam));
		return true;
	}

	/**
	 * Checks if the request is null
	 * 
	 */
	private void checkRequestNotNull(Object requestParam) throws LeanException {
		if (TextUtil.isEmpty(requestParam)) {
			throw new LeanException(RESPONSE_MSG.YOUR_REQUEST_IS_INVALID, RESPONSE_CODE.FAILURE_RESPONSE_CODE);
		}
	}

	/**
	 * Checks if the request is only number; as per the API Docs
	 */
	private void checkRequestIsOnlyNumber(String requestParam) throws LeanException {
		if (!TextUtil.isNumeric(requestParam)) {
			throw new LeanException(RESPONSE_MSG.YOUR_REQUEST_IS_INVALID, RESPONSE_CODE.FAILURE_RESPONSE_CODE);
		}
	}

	/**
	 * Checks if the merchant is whitelisted
	 * 
	 */
	private void checkIfMerchantIsWhiteListed(String headerToken) throws LeanException {
		if (TextUtil.isEmpty(headerToken)) {
			throw new LeanException(RESPONSE_MSG.MISSING_LEAN_TOKEN, RESPONSE_CODE.FAILURE_RESPONSE_CODE);
		}

		if (!TextUtil.isEmpty(headerToken) && !configServices.getWhiteListedMerchants().contains(headerToken)) {
			throw new LeanException(RESPONSE_MSG.YOU_ARE_NOT_WHITELISTED_TO_USE_THIS_API,
					RESPONSE_CODE.FAILURE_RESPONSE_CODE);
		}
	}

}
