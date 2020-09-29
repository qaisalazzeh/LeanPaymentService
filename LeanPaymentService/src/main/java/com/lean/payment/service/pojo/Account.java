package com.lean.payment.service.pojo;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import com.lean.payment.service.redis.entities.AccountEntity;
import com.lean.payment.service.utilities.Encryptable;
import com.lean.payment.service.utilities.EncryptableUtil;

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
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Customer customerId;
	private String type;
	private Encryptable accountNumber;
	private Encryptable iban;
	private String status;
	private Double balance;
	private String currencyCode;

	public Account() {
	}

	public Account(Long id) {
		this.id = id;
	}

	public Account(Long id, Customer customerId, String type, Encryptable accountNumber, Encryptable iban,
			String status, Double balance, String currencyCode) {
		this.id = id;
		this.customerId = customerId;
		this.type = type;
		this.accountNumber = accountNumber;
		this.iban = iban;
		this.status = status;
		this.balance = balance;
		this.currencyCode = currencyCode;
	}

	public Account(AccountEntity accountEntity) {
		this.setId(accountEntity.getId());
		this.setCustomerId(new Customer(accountEntity.getCustomerId().getId()));
		this.setType(accountEntity.getType());
		this.setAccountNumber(EncryptableUtil.newEncryptableWithEncrypted(accountEntity.getAccountNumber()));
		this.setIban(EncryptableUtil.newEncryptableWithEncrypted(accountEntity.getIban()));
		this.setStatus(accountEntity.getStatus());
		this.setBalance(accountEntity.getBalance());
		this.setCurrencyCode(accountEntity.getCurrencyCode());

	}

	public AccountEntity createEntity() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId(this.getId());
		accountEntity.setCustomerId(this.getCustomerId().createFakeEntity());
		accountEntity.setType(this.getType());
		accountEntity.setAccountNumber(this.getAccountNumber().getEncrypted());
		accountEntity.setIban(this.getIban().getEncrypted());
		accountEntity.setStatus(this.getStatus());
		accountEntity.setBalance(this.getBalance());
		accountEntity.setCurrencyCode(this.getCurrencyCode());
		return accountEntity;
	}

	public AccountEntity createFakeEntitiy() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId(this.getId());
		return accountEntity;

	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", customerId=" + customerId + ", type=" + type + ", accountNumber=XXXX"
				+ ", iban= XXXX" + ", status=" + status + ", balance=" + balance + ", currencyCode=" + currencyCode
				+ "]";
	}

}
