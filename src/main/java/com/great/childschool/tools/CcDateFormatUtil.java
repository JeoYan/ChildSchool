package com.great.childschool.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CcDateFormatUtil
{
	public static Date getLastWeekMonday(Date date){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(getThisWeekMonday(date));
		calendar.add(Calendar.DATE,-7);
		return  calendar.getTime();
	}

	public static Date getThisWeekMonday(Date date){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(date);
		//获得当前日期是一个星期的第几天
		int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek){
			calendar.add(Calendar.DAY_OF_MONTH,-1);
		}
		//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		//获得当前日期是一个星期的第几天
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		calendar.add(Calendar.DATE,calendar.getFirstDayOfWeek()-day);
		return calendar.getTime();
	}

	public static Date getNextWeekMonday(Date date){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(getThisWeekMonday(date));
		calendar.add(Calendar.DATE,7);
		return calendar.getTime();
	}

	public static List<Date> dateToWeek(Date mdate){
		int b = mdate.getDay();
		Date fdate;
		List <Date> list =new ArrayList<Date>();
		Long fTime =mdate.getTime() - b*24*3600000;
		for (int i = 1; i <=7 ; i++)
		{
			fdate =new Date();
			fdate.setTime(fTime+(i*24*3600000));
			list.add(i-1,fdate);
		}
		return list;
	}

	public static String weekString(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd EEE");
		String dateStr =sdf.format(date);
		return dateStr;
	}

	public static String dateString(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateStr =sdf.format(date);
		return dateStr;
	}

	public static String completeTime(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
		String dateStr =sdf.format(new Date());
		return dateStr;
	}
}
