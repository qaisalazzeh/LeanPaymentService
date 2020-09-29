package com.lean.payment.service.listeners;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import com.lean.payment.service.exceptions.LeanException;
import com.lean.payment.service.service.AccountServices;
import com.lean.payment.service.service.ConfigServices;
import com.lean.payment.service.service.CustomerServices;
import com.lean.payment.service.service.TransactionsServices;

/**
 * Listener on Bootup the Application to read all lean information files and
 * cache them on Redis server
 * 
 * @author Qais Azzeh
 *
 */
@Component
public class LeanPaymentApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	CustomerServices customerServices;

	@Autowired
	TransactionsServices transactionsServices;

	@Autowired
	AccountServices accountServices;

	@Autowired
	ConfigServices configServices;

	@Autowired
	RedisTemplate redisTemplate;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			if (isRedisEmpty()) {
				customerServices.prepareLeanPaymentRedisCustomersInformation();
				transactionsServices.prepareLeanPaymentRedisTransactionsInformation();
				accountServices.prepareLeanPaymentRedisAccountsInformation();
			}
			configServices.loadWhiteListedMerchants();
		} catch (ParseException | LeanException e) {
			e.printStackTrace();
		}

	}

	private Boolean isRedisEmpty() {
		List<String> redisKeys = new ArrayList<String>();
		RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
		ScanOptions options = ScanOptions.scanOptions().match("*").count(1000).build();
		Cursor c = redisConnection.scan(options);
		while (c.hasNext()) {
			redisKeys.add(new String((byte[]) c.next()));
		}

		return redisKeys.isEmpty();

	}

}
