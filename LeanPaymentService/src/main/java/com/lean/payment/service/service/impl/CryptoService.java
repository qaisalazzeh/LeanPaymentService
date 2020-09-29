package com.lean.payment.service.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.lean.payment.service.constants.LeanConstants;

/**
 * 
 * @author Qais Azzeh
 *
 */
@Component
public class CryptoService {

	private static SecretKeySpec secretKey;
	private static byte[] key;

	/**
	 * Parse the Key
	 * 
	 * @param myKey
	 */
	public void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Encrypt The sent payload
	 * 
	 * @param strToEncrypt
	 * @return
	 */
	public String encrypt(String strToEncrypt) {
		try {
			setKey(LeanConstants.SECRET_KEY);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	/**
	 * Decrypt The sendt Paylod
	 * 
	 * @param strToDecrypt
	 * @return
	 */
	public String decrypt(String strToDecrypt) {
		try {
			setKey(LeanConstants.SECRET_KEY);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}
}