package com.lean.payment.service.rids.repo;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.lean.payment.service.redis.entities.CustomerEntity;

/**
 * Customer Redis Repo Operations
 * 
 * @author Qais Azzeh
 *
 */
@Repository
public class CustomerRepository {
	private HashOperations hashOperations;

	/**
	 * 
	 * @param redisTemplate
	 */
	public CustomerRepository(RedisTemplate redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
	}

	/**
	 * 
	 * @param customerEntity
	 */
	public void create(CustomerEntity customerEntity) {
		hashOperations.put("CUSTOMER" + customerEntity.getId(), customerEntity.getId(), customerEntity);
	}

	/**
	 * 
	 * @param customerId
	 * @return
	 */
	public CustomerEntity get(Long customerId) {
		return (CustomerEntity) hashOperations.get("CUSTOMER" + customerId, customerId);
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, CustomerEntity> getAll() {
		return hashOperations.entries("CUSTOMER");
	}

	/**
	 * 
	 * @param customerEntity
	 */
	public void update(CustomerEntity customerEntity) {
		hashOperations.put("CUSTOMER" + customerEntity.getId(), customerEntity.getId(), customerEntity);
	}

	/**
	 * 
	 * @param userId
	 */
	public void delete(String userId) {
		hashOperations.delete("CUSTOMER", userId);
	}
}
