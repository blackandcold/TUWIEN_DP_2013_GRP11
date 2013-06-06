package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class provides some basic helper methods
 * @author Stefan Weghofer
 */
public class Util {

	public static String formatDate(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		return formatter.format(date);
	}
	
	public static Date parseDate(String date, String format) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(date);
	}

	public static Date getDate(int day, int month, int year){
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, year);
		date.set(Calendar.MONTH, month);
		date.set(Calendar.DAY_OF_MONTH, day);
		return date.getTime();
	}

	public static Date getDate(int day, int month, int year, int hour, int minute, int second){
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, year);
		date.set(Calendar.MONTH, month);
		date.set(Calendar.DAY_OF_MONTH, day);
		date.set(Calendar.HOUR, hour);
		date.set(Calendar.MINUTE, minute);
		date.set(Calendar.SECOND, second);
		return date.getTime();
	}
}
