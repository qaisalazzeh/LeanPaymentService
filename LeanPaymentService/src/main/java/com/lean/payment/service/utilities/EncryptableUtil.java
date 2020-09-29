package com.lean.payment.service.utilities;

/**
 * 
 * @author Qais Azzeh
 *
 */
public class EncryptableUtil {

	public static Encryptable newEncryptableWithPlain(String plain) {
		Encryptable e = new Encryptable(plain);
		return e;
	}

	public static Encryptable newEncryptableWithEncrypted(String encrypted) {
		Encryptable e = new Encryptable(null);
		e.setEncrypted(encrypted);
		return e;
	}
}
