package com.lean.payment.service.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lean.payment.service.constants.LeanConstants.PARAMS;
import com.lean.payment.service.constants.LeanConstants.RESPONSE_CODE;
import com.lean.payment.service.constants.LeanConstants.RESPONSE_MSG;
import com.lean.payment.service.service.ResponseHandlerService;
import com.lean.payment.service.utilities.CollectionsUtil;

/**
 * 
 * @author Qais Azzeh
 *
 */
@Component
public class ResponseHandlerServiceImpl implements ResponseHandlerService {

	/**
	 * Prepare Final Response As Hash Map for API
	 */
	@Override
	public Map<String, Object> prepareFinalApiResponse(Object response) throws JsonProcessingException {
		if (Objects.isNull(response)) {
			return successEmptyResponse();
		}
		Map<String, Object> finalResponseMap = new HashMap<String, Object>();
		finalResponseMap.put(PARAMS.RESPONSE_CODE, RESPONSE_CODE.SUCCESS_RESPONSE_CODE);
		finalResponseMap.put(PARAMS.RESPONSE_MESSAGE, RESPONSE_MSG.YOUR_REQUEST_PROCESSED_SUCCESSFULLY);
		finalResponseMap.put(PARAMS.ENTITIES, CollectionsUtil.getObjectAsJson(response));
		return finalResponseMap;
	}

	/**
	 * Prepare Failed Response for API
	 */
	@Override
	public Map<String, Object> prepareFailedApiResponse(String message, String code) {
		Map<String, Object> finalResponseMap = new HashMap<String, Object>();
		finalResponseMap.put(PARAMS.RESPONSE_CODE, code);
		finalResponseMap.put(PARAMS.RESPONSE_MESSAGE, message);
		finalResponseMap.put(PARAMS.ENTITIES, new HashMap<String, Object>());
		return finalResponseMap;
	}

	/**
	 * Prepare Success Empty Response For APi
	 * 
	 * @return
	 */
	private Map<String, Object> successEmptyResponse() {
		Map<String, Object> finalResponseMap = new HashMap<String, Object>();
		finalResponseMap.put(PARAMS.RESPONSE_CODE, RESPONSE_CODE.SUCCESS_RESPONSE_CODE);
		finalResponseMap.put(PARAMS.RESPONSE_MESSAGE, RESPONSE_MSG.ENTITY_NOT_FOUND);
		finalResponseMap.put(PARAMS.ENTITIES, new HashMap<String, Object>());
		return finalResponseMap;
	}

}
