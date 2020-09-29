package com.lean.payment.service.constants;

/**
 * 
 * @author Qais Azzeh
 *
 */
public class LeanConstants {

	public static final String SECRET_KEY = "LeanSecretKey";

	public static final class RESPONSE_MSG {
		public static final String YOUR_REQUEST_IS_INVALID = "Your Request is Invalid";
		public static final String GENERAL_EXCEPTION = "An Exception Occured";
		public static final String ENTITY_NOT_FOUND = "Entity Not Found";
		public static final String YOUR_REQUEST_PROCESSED_SUCCESSFULLY = "Your Request Processed Successfully";
		public static final String YOU_ARE_NOT_WHITELISTED_TO_USE_THIS_API = "Your are not white listed to use this API";
		public static final String MISSING_LEAN_TOKEN = "Missing Lean Token";

	}

	public static final class RESPONSE_CODE {
		public static final String FAILURE_RESPONSE_CODE = "FAILURE";
		public static final String SUCCESS_RESPONSE_CODE = "SUCCESS";
	}

	public static final class PARAMS {
		public static final String RESPONSE_CODE = "response_code";
		public static final String RESPONSE_MESSAGE = "response_Message";
		public static final String ENTITIES = "entities";
		public static final String HEADER_KEY = "lean-token";
	}
}
