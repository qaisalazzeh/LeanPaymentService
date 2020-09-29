package com.lean.payment.service.utilities;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lean.payment.service.json.JsonJacksonParser;

/**
 * 
 * @author qazzeh
 *
 */
public class CollectionsUtil {

	/**
	 * to JSON
	 * 
	 * @param params
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static String resolveJsonContentType(Map<String, Object> params) throws IOException {
		return new JsonJacksonParser<Map>(Map.class).parseObjectToJSON(params);
	}

	public static String getObjectAsJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	/**
	 * get headers
	 * 
	 * @param headerValue
	 * @return
	 */
	public static HttpHeaders getHeader(String headerValue) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, headerValue + ";charset=UTF-8");
		return headers;
	}
}
