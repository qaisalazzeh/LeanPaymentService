package com.lean.payment.service.utilities;

/**
 * 
 * @author Qais Azzeh
 *
 */
public class Encryptable {

	String plain;
	String encrypted;
	boolean changed = false;

	public Encryptable() {
	}

	public String getPlain() {

		return plain;
	}

	public Encryptable(String plain) {
		changed = true;
		this.plain = plain;
	}

	public void setPlain(String plain) {
		changed = true;
		this.plain = plain;
	}

	public String getEncrypted() {
		return encrypted;
	}

	public void setEncrypted(String encrypted) {
		this.encrypted = encrypted;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	@Override
	public String toString() {
		return "Encryptable [plain=" + plain + ", encrypted=" + encrypted + ", changed=" + changed + "]";
	}

}
