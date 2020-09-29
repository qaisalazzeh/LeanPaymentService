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
@RedisHash("Account")
public class AccountEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private CustomerEntity customerId;
	private String type;
	private String accountNumber;
	private String iban;
	private String status;
	private Double balance;
	private String currencyCode;

	@Override
	public String toString() {
		return "Account [id=" + id + ", customerId=" + customerId + ", type=" + type + ", accountNumber=XXXX"
				+ ", iban= XXXX" + ", status=" + status + ", balance=" + balance + ", currencyCode=" + currencyCode
				+ "]";
	}

}
