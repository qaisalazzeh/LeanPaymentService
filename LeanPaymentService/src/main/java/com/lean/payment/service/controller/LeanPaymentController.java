package com.lean.payment.service.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lean.payment.service.constants.LeanConstants;
import com.lean.payment.service.constants.LeanConstants.RESPONSE_CODE;
import com.lean.payment.service.constants.LeanConstants.RESPONSE_MSG;
import com.lean.payment.service.exceptions.LeanException;
import com.lean.payment.service.pojo.Account;
import com.lean.payment.service.pojo.Customer;
import com.lean.payment.service.pojo.Transcation;
import com.lean.payment.service.service.AccountServices;
import com.lean.payment.service.service.CommonServices;
import com.lean.payment.service.service.CustomerServices;
import com.lean.payment.service.service.ResponseHandlerService;
import com.lean.payment.service.service.TransactionsServices;
import com.lean.payment.service.utilities.CollectionsUtil;

/**
 * 
 * @author Qais Azzeh
 *
 */

@RestController
public class LeanPaymentController {

	private static final Logger LOGGER = LogManager.getLogger(LeanPaymentController.class);

	@Autowired
	CustomerServices customerServices;

	@Autowired
	AccountServices accountServices;

	@Autowired
	TransactionsServices transactionsServices;

	@Autowired
	CommonServices commonServices;

	@Autowired
	ResponseHandlerService responseHandlerService;

	@RequestMapping(value = "/customers/{id}", method = { RequestMethod.GET })
	public @ResponseBody ResponseEntity<String> handleCustomerRequest(HttpServletRequest req,
			@PathVariable("id") Object id) throws IOException, TimeoutException {
		Customer customer = new Customer();
		Map<String, Object> finalResponseMap = new HashMap<String, Object>();
		try {
			LOGGER.info("Get Customer By Id {}", id);
			commonServices.CrossSiteScripting(id);

			LOGGER.info("Validating Request");
			commonServices.isRequestValid(id, req.getHeader(LeanConstants.PARAMS.HEADER_KEY));

			LOGGER.info("Send Request to Redis Service");
			customer = customerServices.getCustomerById((id));

			LOGGER.info("Start PrepareResponse");
			finalResponseMap = responseHandlerService.prepareFinalApiResponse(customer);

		} catch (LeanException e) {
			LOGGER.error("LeanException occured {}", e);
			finalResponseMap = responseHandlerService.prepareFailedApiResponse(e.getResponseMessage(),
					e.getResponseCode());
		} catch (Exception e) {
			LOGGER.error("UnExpected Exception occured {}", e);
			finalResponseMap = responseHandlerService.prepareFailedApiResponse(RESPONSE_MSG.GENERAL_EXCEPTION,
					RESPONSE_CODE.FAILURE_RESPONSE_CODE);
		}
		return new ResponseEntity<>(CollectionsUtil.resolveJsonContentType(finalResponseMap),
				CollectionsUtil.getHeader(MediaType.APPLICATION_JSON_VALUE), HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{id}", method = { RequestMethod.GET })
	public @ResponseBody ResponseEntity<String> handleAccountRequest(HttpServletRequest req,
			@PathVariable("id") Object id) throws IOException, TimeoutException {

		Account account = new Account();
		Map<String, Object> finalResponseMap = new HashMap<String, Object>();
		try {
			LOGGER.info("Get Account By Id {}", id);
			commonServices.CrossSiteScripting(id);

			LOGGER.info("Validating Request");
			commonServices.isRequestValid(id, req.getHeader(LeanConstants.PARAMS.HEADER_KEY));

			LOGGER.info("Send Request to Redis Service");
			account = accountServices.getAccountByid(id);

			LOGGER.info("Start PrepareResponse");
			finalResponseMap = responseHandlerService.prepareFinalApiResponse(account);

		} catch (LeanException e) {
			LOGGER.error("LeanException occured {}", e);
			finalResponseMap = responseHandlerService.prepareFailedApiResponse(e.getResponseMessage(),
					e.getResponseCode());
		} catch (Exception e) {
			LOGGER.error("UnExpected Exception occured {}", e);
			finalResponseMap = responseHandlerService.prepareFailedApiResponse(RESPONSE_MSG.GENERAL_EXCEPTION,
					RESPONSE_CODE.FAILURE_RESPONSE_CODE);
		}
		return new ResponseEntity<>(CollectionsUtil.resolveJsonContentType(finalResponseMap),
				CollectionsUtil.getHeader(MediaType.APPLICATION_JSON_VALUE), HttpStatus.OK);

	}

	@RequestMapping(value = "/transactions/{id}", method = { RequestMethod.GET })
	public @ResponseBody ResponseEntity<String> handleTransactionsRequest(HttpServletRequest req,
			@PathVariable("id") Object id) throws IOException, TimeoutException {

		Transcation transcation = new Transcation();
		Map<String, Object> finalResponseMap = new HashMap<String, Object>();
		try {
			LOGGER.info("Get Transaction By Id {}", id);
			commonServices.CrossSiteScripting(id);

			LOGGER.info("Validating Request");
			commonServices.isRequestValid(id, req.getHeader(LeanConstants.PARAMS.HEADER_KEY));

			LOGGER.info("Send Request to Redis Service");
			transcation = transactionsServices.getTransactionById(id);

			LOGGER.info("Start PrepareResponse");
			finalResponseMap = responseHandlerService.prepareFinalApiResponse(transcation);

		} catch (LeanException e) {
			LOGGER.error("LeanException occured {}", e);
			finalResponseMap = responseHandlerService.prepareFailedApiResponse(e.getResponseMessage(),
					e.getResponseCode());
		} catch (Exception e) {
			LOGGER.error("UnExpected Exception occured {}", e);
			finalResponseMap = responseHandlerService.prepareFailedApiResponse(RESPONSE_MSG.GENERAL_EXCEPTION,
					RESPONSE_CODE.FAILURE_RESPONSE_CODE);
		}
		return new ResponseEntity<>(CollectionsUtil.resolveJsonContentType(finalResponseMap),
				CollectionsUtil.getHeader(MediaType.APPLICATION_JSON_VALUE), HttpStatus.OK);

	}

	@RequestMapping(value = "/accounts/{id}/transactions", method = { RequestMethod.GET })
	public @ResponseBody ResponseEntity<String> handleOrderTransactionsRequest(HttpServletRequest req,
			@PathVariable("id") Object id) throws IOException, TimeoutException {

		List<Transcation> responseList = new ArrayList<Transcation>();
		Map<String, Object> finalResponseMap = new HashMap<String, Object>();
		try {
			LOGGER.info("Get List of Transactions By account id Id {}", id);
			commonServices.CrossSiteScripting(id);

			LOGGER.info("Validating Request");
			commonServices.isRequestValid(id, req.getHeader(LeanConstants.PARAMS.HEADER_KEY));

			LOGGER.info("Send Request to Redis Service");
			responseList = transactionsServices.getAllTransactionByAccountId(id);

			LOGGER.info("Start PrepareResponse");
			finalResponseMap = responseHandlerService.prepareFinalApiResponse(responseList);

		} catch (LeanException e) {
			LOGGER.error("LeanException occured {}", e);
			finalResponseMap = responseHandlerService.prepareFailedApiResponse(e.getResponseMessage(),
					e.getResponseCode());
		} catch (Exception e) {
			LOGGER.error("UnExpected Exception occured {}", e);
			finalResponseMap = responseHandlerService.prepareFailedApiResponse(RESPONSE_MSG.GENERAL_EXCEPTION,
					RESPONSE_CODE.FAILURE_RESPONSE_CODE);
		}
		return new ResponseEntity<>(CollectionsUtil.resolveJsonContentType(finalResponseMap),
				CollectionsUtil.getHeader(MediaType.APPLICATION_JSON_VALUE), HttpStatus.OK);
	}
}
