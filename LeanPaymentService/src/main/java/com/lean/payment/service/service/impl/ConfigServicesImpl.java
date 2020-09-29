package com.lean.payment.service.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.lean.payment.service.service.ConfigServices;

/**
 * 
 * @author Qais Azzeh
 *
 */
@Component
public class ConfigServicesImpl implements ConfigServices {

	public static List<String> whiteListedMerchants = new ArrayList<String>();

	/**
	 * Reads merchant Keys from the file and cache them into list
	 */
	@Override
	public void loadWhiteListedMerchants() {
		whiteListedMerchants = new ArrayList<String>();
		try (Stream<String> content = Files.lines(Paths
				.get(new ClassPathResource("LeanPaymentInformation/whitelist.txt").getFile().getCanonicalPath()))) {
			whiteListedMerchants = content.collect(Collectors.toList());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Returns the cached list
	 */
	@Override
	public List<String> getWhiteListedMerchants() {
		return whiteListedMerchants;
	}

}
