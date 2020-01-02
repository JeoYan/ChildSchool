package com.great.childschool.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * @Author MJChen
 * @Description //日期工具类
 * @Date 11:50 2019/12/24
 **/
public class DateFormatUtil
{
	public static Date getLastWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, -7);
		return cal.getTime();
	}
	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}
	public static Date getNextWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, 7);
		return cal.getTime();
	}
	public static List<Date> dateToWeek(Date mdate) {
		int b = mdate.getDay();
		Date fdate;
		List<Date> list = new ArrayList<Date>();
		Long fTime = mdate.getTime() - b * 24 * 3600000;
		for (int a = 1; a <= 7; a++) {
			fdate = new Date();
			fdate.setTime(fTime + (a * 24 * 3600000));
			list.add(a-1, fdate);
		}
		return list;
	}
	public static String weekString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEE");
		String dateStr = sdf.format(date);
		return dateStr;
	}
	public static String dateString(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = simpleDateFormat.format(date);
		return dateStr;
	}
	public static String completeTime(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = simpleDateFormat.format(new Date());
		return dateStr;
	}

	public static Date dateParse(String date) throws ParseException
	{
		return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	}

	public static Date dateParseWeek(String date) throws ParseException
	{
		return new SimpleDateFormat("yyyy-MM-dd EEE").parse(date);
	}
}
