package com.lean.payment.service.rids.repo;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.lean.payment.service.redis.entities.TranscationEntity;

/**
 * Transactions Redis Repo Operations
 * 
 * @author Qais Azzeh
 *
 */
@Repository
public class TranscationRepository {
	private HashOperations hashOperations;

	@Autowired
	RedisTemplate redisTemplate;

	/**
	 * 
	 * @param redisTemplate
	 */
	public TranscationRepository(RedisTemplate redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
	}

	/**
	 * 
	 * @param transcationEntity
	 */
	public void create(TranscationEntity transcationEntity) {
		hashOperations.put("TRANSCATION" + transcationEntity.getId(), transcationEntity.getId(), transcationEntity);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public TranscationEntity get(Long id) {
		return (TranscationEntity) hashOperations.get("TRANSCATION" + id, id);
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, TranscationEntity> getAll() {
		return hashOperations.entries("TRANSCATION");
	}

	/**
	 * 
	 * @param transcationEntity
	 */
	public void update(TranscationEntity transcationEntity) {
		hashOperations.put("TRANSCATION" + transcationEntity.getId(), transcationEntity.getId(), transcationEntity);
	}

	/**
	 * 
	 * @param transactionId
	 */
	public void delete(String transactionId) {
		hashOperations.delete("TRANSCATION", transactionId);
	}

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public Set<String> getAllKeysByKeyword(String keyword) {
		return redisTemplate.keys(keyword + "*");

	}
}
