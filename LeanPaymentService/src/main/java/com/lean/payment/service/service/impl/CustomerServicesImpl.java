package com.lean.payment.service.service.impl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.lean.payment.service.constants.LeanConstants.RESPONSE_CODE;
import com.lean.payment.service.constants.LeanConstants.RESPONSE_MSG;
import com.lean.payment.service.exceptions.LeanException;
import com.lean.payment.service.pojo.Customer;
import com.lean.payment.service.redis.entities.CustomerEntity;
import com.lean.payment.service.rids.repo.CustomerRepository;
import com.lean.payment.service.service.CustomerServices;
import com.lean.payment.service.utilities.DateUtil;
import com.lean.payment.service.utilities.Encryptable;

/**
 * 
 * @author Qais Azzeh
 *
 */
@Service
public class CustomerServicesImpl implements CustomerServices {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EncryptableService encryptableService;

	/**
	 * Save all Customer into Redis
	 */
	@Override
	public void saveAllCustomers(List<CustomerEntity> customerList) {
		for (CustomerEntity customerEntity : customerList) {
			customerRepository.create(customerEntity);
		}
	}

	/**
	 * Read Customer Information and save them into redis
	 * 
	 * @throws LeanException
	 */
	@Override
	public void prepareLeanPaymentRedisCustomersInformation() throws LeanException {
		List<Customer> customerList = new ArrayList<Customer>();
		try (Stream<String> content = Files.lines(
				Paths.get(new ClassPathResource("LeanPaymentInformation/customer.csv").getFile().getCanonicalPath()))) {
			customerList = content.skip(1).map(line -> toCustomer(line)).collect(Collectors.toList());
			List<CustomerEntity> customerListAsEntity = customerList.stream().map(customer -> customer.createEntity())
					.collect(Collectors.toList());
			saveAllCustomers(customerListAsEntity);
		} catch (Exception ex) {
			throw new LeanException(RESPONSE_MSG.GENERAL_EXCEPTION, RESPONSE_CODE.FAILURE_RESPONSE_CODE);
		}
	}

	/**
	 * Translate file content into biz objects
	 * 
	 * @param customerAsLine
	 * @return
	 */
	public Customer toCustomer(String customerAsLine) {
		try {
			String[] array = customerAsLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			Long id = Long.valueOf(array[0]);
			String name = String.valueOf(array[1]);
			Date customerDateAsDate = DateUtil.getStringAsDate(array[2], "dd-MM-yyyy");
			String customerDate = DateUtil.getDateAsString(customerDateAsDate, "dd-MM-yyyy");
			String nationality = String.valueOf(array[3]);
			Encryptable emailAddress = encryptableService
					.newEncryptableWithPlainThenEncrypted(String.valueOf(array[4]));
			String address = String.valueOf(array[5]);
			return new Customer(id, name, customerDate, nationality, emailAddress, address);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Get Biz Customer Object
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Customer getCustomerById(Object id) {
		CustomerEntity customerEntity = customerRepository.get(Long.valueOf(String.valueOf(id)));
		if (Objects.isNull(customerEntity)) {
			return null;
		}
		Customer customer = new Customer(customerEntity);
		customer.setEmailAddress(
				encryptableService.newEncryptableWithEncryptedThenPlain(customer.getEmailAddress().getEncrypted()));
		return customer;
	}

}
