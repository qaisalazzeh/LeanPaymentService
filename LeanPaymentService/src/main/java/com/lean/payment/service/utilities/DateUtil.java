package com.lean.payment.service.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Qais Azzeh
 *
 */
public class DateUtil {

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date getStringAsDate(String date, String format) throws ParseException {
		DateFormat df = new SimpleDateFormat(format);
		Date startDate = df.parse(date);
		return startDate;
	}

	public static String getDateAsString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String stringDate = sdf.format(date);
		return stringDate;
	}

}