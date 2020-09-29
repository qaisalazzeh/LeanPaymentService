package com.lean.payment.service.redis.entities;

import java.io.Serializable;
import java.util.Date;

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
@RedisHash("Customer")
public class CustomerEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Date dateOfBirth;
	private String nationality;
	private String emailAddress;
	private String address;

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", nationality=" + nationality
				+ ", emailAddress= XXXX" + ", address=" + address + "]";
	}

}
