package com.lean.payment.service.service;

import java.util.List;

/**
 * 
 * @author Qais Azzeh
 *
 */

public interface ConfigServices {

	/**
	 * load whitelisted merchant that allowed to use this API and saves them into
	 * static map
	 */
	public void loadWhiteListedMerchants();

	/**
	 * Static List holds the allowed merchant keys
	 * 
	 * @return
	 */
	List<String> getWhiteListedMerchants();

}
