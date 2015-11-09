package ar.edu.uces.progweb2.agenda.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {

	public static Date[] getWeek(Date date) {
		Date[] week = new Date[7];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);// convert date object to calendar object
		int day = calendar.get(Calendar.DAY_OF_WEEK); // day (Sunday = 1)1 to 7
		if (day == 1) {
			week[0] = calendar.getTime(); // Sunday
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Monday
			week[1] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Tuesday
			week[2] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Wednesday
			week[3] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Thursday
			week[4] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Friday
			week[5] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Saturday
			week[6] = calendar.getTime();
		} else if (day == 2) {
			calendar.add(Calendar.DAY_OF_WEEK, -1); // Sunday
			week[0] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Monday
			week[1] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Tuesday
			week[2] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Wednesday
			week[3] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Thursday
			week[4] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Friday
			week[5] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Saturday
			week[6] = calendar.getTime();
		} else if (day == 3) {
			calendar.add(Calendar.DAY_OF_WEEK, -2); // Sunday
			week[0] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Monday
			week[1] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Tuesday
			week[2] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Wednesday
			week[3] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Thursday
			week[4] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Friday
			week[5] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Saturday
			week[6] = calendar.getTime();
		} else if (day == 4) {
			calendar.add(Calendar.DAY_OF_WEEK, -3); // Sunday
			week[0] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Monday
			week[1] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Tuesday
			week[2] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Wednesday
			week[3] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Thursday
			week[4] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Friday
			week[5] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Saturday
			week[6] = calendar.getTime();
		} else if (day == 5) {
			calendar.add(Calendar.DAY_OF_WEEK, -4); // Sunday
			week[0] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Monday
			week[1] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Tuesday
			week[2] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Wednesday
			week[3] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Thursday
			week[4] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Friday
			week[5] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Saturday
			week[6] = calendar.getTime();
		} else if (day == 6) {
			calendar.add(Calendar.DAY_OF_WEEK, -5); // Sunday
			week[0] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Monday
			week[1] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Tuesday
			week[2] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Wednesday
			week[3] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Thursday
			week[4] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Friday
			week[5] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Saturday
			week[6] = calendar.getTime();
		} else if (day == 7) {
			calendar.add(Calendar.DAY_OF_WEEK, -6); // Sunday
			week[0] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Monday
			week[1] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Tuesday
			week[2] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Wednesday
			week[3] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Thursday
			week[4] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Friday
			week[5] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Saturday
			week[6] = calendar.getTime();
		}
		return week;
	}

	public static Date getDate(int actualPage, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_WEEK, actualPage);
		return calendar.getTime();
	}
	
	public static Date getDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy HH:mm");
		Date dateFormat = null;
		try {
			dateFormat = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateFormat;
	}
}

