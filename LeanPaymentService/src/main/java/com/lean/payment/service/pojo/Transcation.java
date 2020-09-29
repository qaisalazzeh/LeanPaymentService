package com.lean.payment.service.pojo;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import com.lean.payment.service.redis.entities.TranscationEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Qais Azzeh
 *
 */
@Getter
@Setter
@RedisHash("Transcation")
public class Transcation implements Serializable, Comparable<Transcation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Account accountId;
	private String type;
	private String description;
	private Double amount;
	private String currencyCode;
	private Long timeStamp;

	public Transcation() {

	}

	public Transcation(Long id, Account accountId, String type, String description, Double amount, String currencyCode,
			Long timeStamp) {
		this.id = id;
		this.accountId = accountId;
		this.type = type;
		this.description = description;
		this.amount = amount;
		this.currencyCode = currencyCode;
		this.timeStamp = timeStamp;
	}

	public Transcation(TranscationEntity transcationEntity) {
		this.setId(transcationEntity.getId());
		this.setAccountId(new Account(transcationEntity.getAccountId().getId()));
		this.setType(transcationEntity.getType());
		this.setDescription(transcationEntity.getDescription());
		this.setAmount(transcationEntity.getAmount());
		this.setCurrencyCode(transcationEntity.getCurrencyCode());
		this.setTimeStamp(transcationEntity.getTimeStamp());
	}

	public TranscationEntity createEntity() {
		TranscationEntity transcationEntity = new TranscationEntity();
		transcationEntity.setId(this.getId());
		transcationEntity.setAccountId(this.getAccountId().createFakeEntitiy());
		transcationEntity.setType(this.getType());
		transcationEntity.setDescription(this.getDescription());
		transcationEntity.setAmount(this.getAmount());
		transcationEntity.setCurrencyCode(this.getCurrencyCode());
		transcationEntity.setTimeStamp(this.getTimeStamp());
		return transcationEntity;
	}

	@Override
	public String toString() {
		return "Transcation [id=" + id + ", accountId=" + accountId + ", type=" + type + ", description=" + description
				+ ", amount=" + amount + ", currencyCode=" + currencyCode + ", timeStamp=" + timeStamp + "]";
	}

	@Override
	public int compareTo(Transcation transcation) {
		return this.getTimeStamp().compareTo(transcation.getTimeStamp());
	}

}
