package com.lean.payment.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lean.payment.service.utilities.Encryptable;

/**
 * 
 * @author Qais Azzeh
 *
 */
@Component
public class EncryptableService {

	@Autowired
	private CryptoService cryptoService;

	/**
	 * Takes encrypted and decrypt value to save them both in memory
	 * 
	 * @param encrypted
	 * @return
	 */
	public Encryptable newEncryptableWithEncryptedThenPlain(String encrypted) {
		Encryptable e = new Encryptable(null);
		e.setPlain(cryptoService.decrypt(encrypted));
		e.setEncrypted(encrypted);
		return e;
	}

	/**
	 * Takes plain data and encrypt value to save them both in memory
	 * 
	 * @param plain
	 * @return
	 */
	public Encryptable newEncryptableWithPlainThenEncrypted(String plain) {
		Encryptable e = new Encryptable(null);
		e.setPlain(plain);
		e.setEncrypted(cryptoService.encrypt(plain));
		return e;
	}
}
