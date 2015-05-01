package org.ameet.rts.cassandra.util;

import java.io.File;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class Utility {

	private static final String RTS_FILE_NAME = "well.json";
	
	private Utility() {
		// 
	}
	public static String convertUtc(String time) {
		DateTimeFormatter parser = ISODateTimeFormat.dateTime();
        DateTime dt = parser.parseDateTime(time);

        DateTimeFormatter formatter = DateTimeFormat.mediumDateTime();
        return formatter.print(dt);
	}
	public static Date convertUtcToDate(String time) {
		DateTimeFormatter parser = ISODateTimeFormat.dateTime();
        DateTime dt = parser.parseDateTime(time);
        return dt.toDate();
	}
	public static File pickupTemplateWellJsonFile() {
		return new File(Utility.class.getClassLoader().getResource(RTS_FILE_NAME).getFile());
		
	}
}
