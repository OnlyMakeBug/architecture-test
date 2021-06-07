package com.zj.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author zhoujian
 * @corporation luke
 * @finished true
 * @finishTime 2019��12��27�� 16:03:26
 * @version 1.0
 */
public abstract class DateUtilities {

	public static final String PATTERN_DATE = "yyyy-MM-dd";

	public static final String PATTERN_TIME = "yyyy-MM-dd HH:mm:ss";

	private DateUtilities() {
	}

	/**
	 * ���� 2019-12-27 15:13:07 �ĸ�ʽ�����Ʒ�������ʱ���ַ���
	 * 
	 */
	public static String formatDate(Date date) {
		return formatDate(date, PATTERN_TIME);
	}

	/**
	 * ���ո����ĵĸ�ʽ�����Ʒ�������ʱ���ַ���
	 */
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	public static Date parseString(String dateStr) {
		return parseString(dateStr, PATTERN_TIME);
	}

	/**
	 * �����ַ�����������һ�����ڶ���
	 * 
	 * @param pattern
	 *            ���еķ�����Ҫ��String������һ�£� String������Ҫ��day��hour֮�����пո�����׳��쳣
	 */
	public static Date parseString(String dateStr, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			if (!StringUtils.isEmpty(dateStr)) {
				return dateFormat.parse(dateStr);
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ȡdateΪ���ڼ�
	 */
	public static String getWeekStr(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int week = calendar.get(7);
		--week;
		String weekStr = "";
		switch (week) {
		case 0:
			weekStr = "������";
			break;
		case 1:
			weekStr = "����һ";
			break;
		case 2:
			weekStr = "���ڶ�";
			break;
		case 3:
			weekStr = "������";
			break;
		case 4:
			weekStr = "������";
			break;
		case 5:
			weekStr = "������";
			break;
		case 6:
			weekStr = "������";
		}
		return weekStr;
	}

	/**
	 * �����������ڶ���֮���΢��ʱ����
	 * 
	 * @return date1-date2>0�������ǰ�����ڽ�����ʱ���ڵ���
	 */
	public static long getMillionSecondsDiff(Date date1, Date date2) {
		if ((date1 == null) || (date2 == null)) {
			return 0L;
		}

		long time1 = date1.getTime();
		long time2 = date2.getTime();

		return time1 - time2;
	}

	/**
	 * ��ȡ�������ڶ�������������������� ���صĽ���Ǳ����������֣�С�����ֽ�ȥ date1 - date2
	 * 
	 * @return ��������
	 */
	public static int getDateDiff(Date date1, Date date2) {
		if ((date1 == null) || (date2 == null)) {
			return 0;
		}
		long time1 = date1.getTime();
		long time2 = date2.getTime();

		long diffMillions = time1 - time2;

		Long longValue = new Long(diffMillions / 86400000L);
		return longValue.intValue();
	}

	/**
	 * ���ظ���dateһ������ǰ��date����
	 * 
	 * @return ���ش�����ǰ�����ڶ���
	 */
	public static Date getDataBefore(Date date, int day) {
		if (date == null) {
			return null;
		}
		long time = date.getTime();
		time -= 86400000L * day;
		return new Date(time);
	}

	/**
	 * @returne �����й��˿������ڼ���ֵ����1~7�ֱ������1~���� ������Calendar�ڲ���1ΪSUNDAY
	 */
	public static int getCurrentWeekDayInt() {
		Calendar calendar = Calendar.getInstance();
		// ����1Ϊ���գ�����Ϊһ�ܵĿ�ʼ
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		--weekDay;
		if (weekDay == 0) {
			weekDay = 7;
		}
		return weekDay;
	}

	/**
	 * @return ���ش���������ڼ����ַ���
	 */
	public static String getCurrentWeekDayStr() {
		return getWeekStr(new Date());
	}

	/**
	 * @return ����yyyy
	 */
	public static int getCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * @return ����MM
	 */
	public static int getCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		// ���ص��·ݴӣ� ��ʼ�� ���ԣ��������ڴӣ���ʼ�����ǣ�Ϊ����
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * @return ����dd
	 */
	public static int getCurrentDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * ��������������ڲ��֣���ʽΪyyyymmdd
	 * 
	 * @param dateTime
	 *            �����ϸ�ƥ��"yyyy*MM*dd",�Ƽ�ʹ��{@code DateUtilities.formatDate(Date)}
	 * @return yyyymmdd
	 */
	public static String formatYearDate(String dateTime) {
		if ((dateTime != null) && (dateTime.length() >= 8)) {
			String formatDateTime = dateTime.replaceAll("-", "");
			formatDateTime = formatDateTime.replaceAll(":", "");
			String date = formatDateTime.substring(0, 8);
			return date;
		}

		return "";
	}

	/**
	 * ��������������ڲ��֣���ʽΪyyyymmdd
	 */
	public static String formatYearDate(Date date) {
		return formatYearDate(formatDate(date));
	}

	/**
	 * @return ����ʱ��yyyymmddhhmmss
	 */
	public static String formatDateNoSeparator(String dateTime) {
		if ((dateTime != null) && (dateTime.length() >= 8)) {
			String formatDateTime = dateTime.replaceAll("-", "").replace(" ", "").replaceAll(":", "");
			return formatDateTime.trim();
		}

		return "";
	}

	/**
	 * ʹ��Ĭ��pattern��ʽ��Date �������޷ָ������ַ�����2019-12-27 15:16:56 -> 20191227151656
	 * 
	 * @param date
	 *            different from formatDateTime
	 * @return
	 */
	public static String formatDateNoSeparator(Date date) {
		String dateTime = formatDate(date);
		return formatDateNoSeparator(dateTime);
	}
}