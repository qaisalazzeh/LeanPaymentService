package com.lean.payment.service.pojo;

import java.io.Serializable;
import java.text.ParseException;

import org.springframework.data.redis.core.RedisHash;

import com.lean.payment.service.redis.entities.CustomerEntity;
import com.lean.payment.service.utilities.DateUtil;
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
@RedisHash("Customer")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String dateOfBirth;
	private String nationality;
	private Encryptable emailAddress;
	private String address;

	public Customer() {
	}

	public Customer(Long id) {
		this.setId(id);
	}

	public Customer(Long id, String name, String dateOfBirth, String nationality, Encryptable emailAddress,
			String address) {
		this.setId(id);
		this.setName(name);
		this.setDateOfBirth(dateOfBirth);
		this.setNationality(nationality);
		this.setEmailAddress(emailAddress);
		this.setAddress(address);
	}

	public Customer(CustomerEntity customerEntity) {
		this.setId(customerEntity.getId());
		this.setName(customerEntity.getName());
		this.setDateOfBirth(DateUtil.getDateAsString(customerEntity.getDateOfBirth(), "dd-MM-yyyy"));
		this.setNationality(customerEntity.getNationality());
		this.setEmailAddress(EncryptableUtil.newEncryptableWithEncrypted(customerEntity.getEmailAddress()));
		this.setAddress(customerEntity.getAddress());

	}

	public CustomerEntity createFakeEntity() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(this.getId());
		return customerEntity;
	}

	public CustomerEntity createEntity() {
		try {
			CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setId(this.getId());
			customerEntity.setName(this.getName());
			customerEntity.setDateOfBirth(DateUtil.getStringAsDate(this.getDateOfBirth(), "dd-MM-yyyy"));
			customerEntity.setNationality(this.getNationality());
			customerEntity.setEmailAddress(this.getEmailAddress().getEncrypted());
			customerEntity.setAddress(this.getAddress());
			return customerEntity;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", nationality=" + nationality
				+ ", emailAddress=" + emailAddress + ", address=" + address + "]";
	}

}
