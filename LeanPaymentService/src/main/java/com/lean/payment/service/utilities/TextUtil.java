package com.lean.payment.service.utilities;

/**
 * 
 * @author Qais Azzeh
 *
 */
public class TextUtil {

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(final String str) {

		// null or empty
		if (str == null || str.length() == 0) {
			return false;
		}

		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}

		return true;

	}

	public static boolean isEmpty(String s) {
		if (s == null || s.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Object s) {
		if (s == null || s.toString().trim().isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(Object s) {
		return !isEmpty(s);
	}

}