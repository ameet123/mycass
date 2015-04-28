package org.ameet.nosql.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class Utility {

	private Utility() {
		// 
	}
	public static String convertUtc(String time) {
		DateTimeFormatter parser = ISODateTimeFormat.dateTime();
        DateTime dt = parser.parseDateTime(time);

        DateTimeFormatter formatter = DateTimeFormat.mediumDateTime();
        return formatter.print(dt);
	}
}
