package com.lean.payment.service.redis.entities;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

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
public class TranscationEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private AccountEntity accountId;
	private String type;
	private String description;
	private Double amount;
	private String currencyCode;
	private Long timeStamp;

	@Override
	public String toString() {
		return "TranscationEntity [id=" + id + ", accountId=" + accountId + ", type=" + type + ", description="
				+ description + ", amount=" + amount + ", currencyCode=" + currencyCode + ", timeStamp=" + timeStamp
				+ "]";
	}

}
