package com.lean.payment.service.service;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 
 * @author Qais Azzeh
 *
 */
public interface ResponseHandlerService {

	/**
	 * Prepare final Response API
	 * 
	 * @param response
	 * @return
	 * @throws JsonProcessingException
	 */
	Map<String, Object> prepareFinalApiResponse(Object response) throws JsonProcessingException;

	/**
	 * Prepare Failed response Api
	 * 
	 * @param message
	 * @param Code
	 * @return
	 */
	Map<String, Object> prepareFailedApiResponse(String message, String code);

}
