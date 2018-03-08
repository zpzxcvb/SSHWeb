package com.zhangpan.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author 张攀
 * @ClassName : DateUtil
 * @ModifiedBy : 张攀
 * @date : 2017-1-18 下午2:43:14
 */
public class DateUtil {
	
	private static final String datePattern="yyyy-MM-dd";
	private static final String dateTimePattern="yyyy-MM-dd HH:mm:ss";
	private static SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
	private static String YEAR;
	private static String MONTH;
	private static String WEEK_OF_YEAR;
	private static String WEEK_OF_MONTH;
	private static String DAY_OF_MONTH;
	private static String hour;
	private static String minute;
	private  String second;
	
	/**
	 * 将指定Date类型转换成String
	 * @param date
	 * @return
	 */
	public static String formatDateToString(Date date){
		return sdf.format(date);
	}
	
	/**
	 * 将指定日期String转换成Date
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static Date paserDate(String date) throws ParseException{
		return sdf.parse(date);
	}
	
	/**
	 * 获取当前日期
	 * @return
	 */
	public static String currentDate(){
		return formatDateToString(new Date());
	}
	
	/**
	 * 获取当前日期+时间
	 * @return
	 */
	public static String currentDateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat(dateTimePattern);
		return sdf.format(new Date());
	}
	
	/**
	 * 获取n月第一天
	 * @return
	 */
	public static String firstMonthDay(int n){
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, n);
		Date date=calendar.getTime();
		return formatDateToString(date);
	}
	
	/**
	 * 获取n月最后一天
	 * @return
	 */
	public static String lastMonthDay(int n){
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.add(Calendar.MONTH, n);
		Date date=calendar.getTime();
		return formatDateToString(date);
	}
	
	/**
	 * 当前日期n天后
	 * @param n
	 * @return
	 */
	public static String lastDate(int n){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, n);
		Date date=calendar.getTime();
		return formatDateToString(date);
	}
	
	/**
	 * 当前日期n月后
	 * @param n
	 * @return
	 */
	public static String lastMonth(int n){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, n);
		Date date=calendar.getTime();
		return formatDateToString(date);
	}
	
	/**
	 * 当前日期n周后
	 * @param month
	 * @param day
	 * @return
	 */
	public static String lastWeek(int n){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_MONTH, n);
		Date date=calendar.getTime();
		return formatDateToString(date);
	}
	
	/**
	 * 两个日期段之间的日期(包含最后一天)
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static List<String> betweenDate(String beginDate,String endDate) throws ParseException{
		List<String> list=new ArrayList<String>();
		Date begin=paserDate(beginDate);
		Date end=paserDate(endDate);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(begin);
		Date startDate=calendar.getTime();
		while(startDate.before(end)||startDate.compareTo(end)==0){
			list.add(formatDateToString(startDate));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			startDate=calendar.getTime();
		}
		return list;
	}
	
	/**
	 * 时间戳转日期
	 * @return
	 */
	public static String longToTime(long time){
		SimpleDateFormat sdf = new SimpleDateFormat(dateTimePattern);
		return sdf.format(time);
	}
	
	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		System.out.println(longToTime(System.currentTimeMillis()));
		Calendar calendar=new GregorianCalendar();
		/*System.out.println(currentDate());
		System.out.println(currentDateTime());
		System.out.println(lastDate(1));
		System.out.println(lastWeek(-1));
		System.out.println(lastMonth(-1));
		System.out.println(betweenDate("2016-10-5", "2016-10-21"));
		System.out.println(firstMonthDay(-1));
		System.out.println(lastMonthDay(1));
		*/
		/*int a=calendar.get(Calendar.DAY_OF_MONTH);
		calendar.setTime(end);
		int b=calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println(b-a);*/
		/*GregorianCalendar calendar=new GregorianCalendar();
//		 calendar.set(2017, 1, 12,16,16,16);
//		 calendar.add(Calendar.MONTH, 1);
//		 System.out.println(calendar.isLeapYear(2016));
		 System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
		 System.out.println("MONTH: " + (calendar.get(Calendar.MONTH)+1));
		 System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
		 System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
		 System.out.println("DATE: " + calendar.get(Calendar.DATE));
		 System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
		 System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
		 System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
		 System.out.println("DAY_OF_WEEK_IN_MONTH: "
		                    + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		 System.out.println("AM_PM: " + calendar.get(Calendar.AM_PM));
		 System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
		 System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
		 System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
		 System.out.println("SECOND: " + calendar.get(Calendar.SECOND));*/
	}

}
