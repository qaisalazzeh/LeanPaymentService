package com.lean.payment.service.rids.repo;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.lean.payment.service.redis.entities.AccountEntity;
import com.lean.payment.service.redis.entities.CustomerEntity;

/**
 * Account Redis Repo Operations.
 * 
 * @author Qais Azzeh
 *
 */
@Repository
public class AccountRepository {
	private HashOperations hashOperations;

	/**
	 * 
	 * @param redisTemplate
	 */
	public AccountRepository(RedisTemplate redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
	}

	/**
	 * 
	 * @param accountEntity
	 */
	public void create(AccountEntity accountEntity) {
		hashOperations.put("ACCOUNT" + accountEntity.getId(), accountEntity.getId(), accountEntity);
	}

	/**
	 * 
	 * @param accountId
	 * @return
	 */
	public AccountEntity get(Long accountId) {
		return (AccountEntity) hashOperations.get("ACCOUNT" + accountId, accountId);
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, CustomerEntity> getAll() {
		return hashOperations.entries("ACCOUNT");
	}

	/**
	 * 
	 * @param accountEntity
	 */
	public void update(AccountEntity accountEntity) {
		hashOperations.put("ACCOUNT" + accountEntity.getId(), accountEntity.getId(), accountEntity);
	}

	/**
	 * 
	 * @param accountId
	 */
	public void delete(String accountId) {
		hashOperations.delete("ACCOUNT", accountId);
	}
}
