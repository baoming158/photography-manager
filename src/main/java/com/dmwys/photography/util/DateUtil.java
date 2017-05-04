package com.dmwys.photography.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
public class DateUtil {

	public static Logger logger = LoggerFactory.getLogger(DateUtil.class);
    public static final String  format = "yyyy-MM-dd HH:mm:ss";
    public static final String  format_yymmdd = "yyyy-MM-dd";
    public static final  SimpleDateFormat sdf_yymmdd = new SimpleDateFormat("yyyy-MM-dd");
    public static String formatDateToString(Date date, String pattern) {
        if (null == date) return "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    
    
    public static Date parseStringToDate(String src, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if(src == null){
        	return null;
        }
        try {
            return sdf.parse(src);
        } catch (ParseException e) {
            return null;
        }
    }
    public static boolean dateEquals(Date date1,Date date2){
    	if(date1 == null && date2 == null){
    		return true;
    	}
    	if(date1 == null || date2 == null){
    		return false;
    	}
    	return DateUtils.isSameDay(date1, date2);
    }
    public static Date parseStringToDate(String src ) {
    	return parseStringToDate(src,format);
    }
    /**
	 * 取得当前日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		return calendar.getTime();
	}
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}
	/**
	 * 获取本月的天数
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
    
    public static int timeCompare(Date t1, Date t2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(t1);
			c2.setTime(t2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int result = c1.compareTo(c2); // 返回值：1:c1>c2 0:c1=c2 -1:c1<c2
		return result;
	}
    /**
     * day 天前的日期  负数为之后的日期
     * @param date
     * @param day
     * @return
     */
    public static Date getDayBefore(Date date,int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -day);
		date = calendar.getTime();
		return date;
	}
    public static String getDayBeforeFormat(Date date,int day) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DAY_OF_MONTH, -day);
    	date = calendar.getTime();
    	return formatDateToString(date,"yyyy-MM-dd HH:mm:ss");
    }
	/**
	 * 根据日期和天数参数，返回增加后的日期
	 * @param trigger_time 首次触发日期
	 * @param days 要增加的天数
	 * @return long 
	 */
	@SuppressWarnings("unused")
	private static long addDate(final Date trigger_time,final Integer days){
		Calendar cal = Calendar.getInstance();
		cal.setTime(trigger_time);
		cal.add(Calendar.DATE, days);
		return cal.getTimeInMillis();
	}
	
	/**
	 * 字符串转java.sql.Date
	 * @param date
	 * @param format
	 * @return
	 */
	public static java.util.Date formatToDate(String date,String format){
		if (Strings.isNullOrEmpty(date)) {
			return null;
		}
	
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			logger.error("",e);
		}
		
	    return null;
	}
	
	/**
	 * 
	 * @date:        2016年12月2日 下午4:01:27
	 * @Description: TODO(返回的日期格式固定了)
	 */
	public static String formatLongToDate(long sec,String format){
		SimpleDateFormat sd = new SimpleDateFormat(format);
		return sd.format(new Date(sec));
	}
	
	public static Timestamp formatToTimestamp(String date,String format){
		if (Strings.isNullOrEmpty(date)) {
			return null;
		}
		try {
			Date formatedate = new SimpleDateFormat(format).parse(date);
			
			return dateToTimestamp(formatedate);
		} catch (ParseException e) {
			logger.error("",e);
		}
		
	    return null;
	}
	/**
	 * 根据两个日期返回相隔的天数
	 * @param time1 当前日期
	 * @param time2 首次推送日期
	 * @return long 
	 */
	public static long getQuot(String time1, String time2){
		long quot = 0;
		try {
			Date date1 = sdf_yymmdd.parse(time1);
			Date date2 = sdf_yymmdd.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}
	public static Timestamp stringToTimeStamp(String arg){
		Timestamp ts = new Timestamp(System.currentTimeMillis());  
	        try {   
	            ts = Timestamp.valueOf(arg);   
	            System.out.println(ts);   
	        } catch (Exception e) {   
	            e.printStackTrace();   
	        }  
	        return ts;
	}
	public static Timestamp dateToTimestamp(Date date){
		 Timestamp ts = new Timestamp(date.getTime());
		 System.out.println(ts);
		 return ts;
	}
	public static void main(String[] args) {
//		Date d = DateUtil.getFirstDayOfMonth(new Date());
//		System.out.println(DateUtil.formatDateToString(d, format));
		String bb = "20161130122223023";
		Date bbb = DateUtil.formatToDate(bb, "yyyyMMddHHmmssSSS");
		dateToTimestamp(bbb);
//		String ccc = DateUtil.formatDateToString(bbb, "yyyyMMddHHmmssSSS");
//		System.out.println(ccc);
	}
}
