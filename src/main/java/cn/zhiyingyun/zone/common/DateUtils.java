package cn.zhiyingyun.zone.common;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 常用日期函数类
 *
 * @author llzhao
 * @version $Id: $
 */
public class DateUtils {

  /**
   * 长日期格式化:yyyy-MM-dd HH:mm:ss
   */
  public static String FORMATTER_L = "yyyy-MM-dd HH:mm:ss";
  /**
   * 公安部要求的日期格式
   */
  public static String DATESTR_FORMATTER_L = "yyyyMMddHHmmss";

  /**
   * 短日期格式化:yyyy-MM-dd
   */
  public static String FORMATTER_S = "yyyy-MM-dd";

  /**
   * 获取格式化的日期字符串
   * <p>
   * <BR>
   * G Era 标志符 Text AD <BR>
   * y 年 Year 1996; 96 <BR>
   * M 年中的月份 Month July; Jul; 07 <BR>
   * w 年中的周数 Number 27 <BR>
   * W 月份中的周数 Number 2 <BR>
   * D 年中的天数 Number 189 <BR>
   * d 月份中的天数 Number 10 <BR>
   * F 月份中的星期 Number 2 <BR>
   * E 星期中的天数 Text Tuesday; Tue <BR>
   * a Am/pm 标记 Text PM <BR>
   * H 一天中的小时数（0-23） Number 0 <BR>
   * k 一天中的小时数（1-24） Number 24 <BR>
   * K am/pm 中的小时数（0-11） Number 0 <BR>
   * h am/pm 中的小时数（1-12） Number 12 <BR>
   * m 小时中的分钟数 Number 30 <BR>
   * s 分钟中的秒数 Number 55 <BR>
   * S 毫秒数 Number 978 <BR>
   * z 时区 General time zone Pacific Standard Time; PST; GMT-08:00 <BR>
   * Z 时区 RFC 822 time zone -0800
   *
   * @param date      日期
   * @param formatStr 格式 如：yyyy-MM-dd HH:mm:ss SSS
   * @return 格式化的日期字符串
   */
  public static String parseDate(Date date, String formatStr) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);

    if (date == null) {
      return null;
    } else {
      return dateFormat.format(date);
    }
  }

  public static String parseTimestamp(Timestamp timestamp) {
    DateFormat sdf = new SimpleDateFormat(FORMATTER_S);
    return sdf.format(timestamp);
  }

  public static String parseTimestamp(Timestamp timestamp, String formatStr) {
    DateFormat sdf = new SimpleDateFormat(formatStr);
    return sdf.format(timestamp);
  }

  /**
   * 根据日期，时分秒构造一个日期
   *
   * @param date 日期
   * @param hour 时
   * @param min  分
   * @param sec  秒
   * @return Date
   */
  public static Date getDate(Date date, int hour, int min, int sec) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR, hour);
    cal.set(Calendar.MINUTE, min);
    cal.set(Calendar.SECOND, sec);
    return cal.getTime();
  }

  /**
   * 字符串转日期
   *
   * @param strDateTime    日期字符串
   * @param dateTimeFormat 格式 如：yyyy-MM-dd
   * @return 转化后的日期
   */
  public static Date toDate(String strDateTime, String dateTimeFormat) {
    if ((strDateTime == null) || (strDateTime.length() == 0)
            || (dateTimeFormat == null) || (dateTimeFormat.length() == 0)) {
      return null;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);
    Date date = dateFormat.parse(strDateTime, new ParsePosition(0));

    if (date == null) {
      return null;
    }

    String dateStr = parseDate(date, dateTimeFormat);

    if (!strDateTime.equals(dateStr)) {
      return null;
    }

    return date;
  }

  /**
   * 字符串转日期
   *
   * @param dateTimeStr    日期字符串
   * @param dateTimeFormat 格式 如：yyyy-MM-dd
   * @return 转化后的日期
   */
  public static Timestamp toTimestamp(String dateTimeStr, String dateTimeFormat) {

    return toTimestamp(toDate(dateTimeStr, dateTimeFormat));
  }

  /**
   * date转Timestamp
   *
   * @param date
   * @return Timestamp
   */
  public static Timestamp toTimestamp(Date date) {
    if (date == null) {
      return null;
    }
    return new Timestamp(date.getTime());
  }

  public static Timestamp currentTimestamp() {
    Date date = new Date();

    return new Timestamp(date.getTime());
  }

  /**
   * 判断开始时间是否小于结束时间
   *
   * @param startTime 开始时间
   * @param endTime   结束时间
   * @return true or false
   */
  public static boolean isSmallerThan(Date startTime, Date endTime) {
    if ((startTime == null) || (endTime == null)) {
      return true;
    }
    if (startTime.getTime() > endTime.getTime()) {
      return false;
    }
    return true;
  }

  /**
   * 给一个时间加上N秒钟或减去N秒钟后得到一个新的日期
   *
   * @param startDate 需要增加的日期时间
   * @param addnos    添加的秒钟数，可以是正数也可以是负数
   * @return 操作后的日期
   */
  public static Date addSecond(Date startDate, int addnos) {
    if (startDate == null)
      return null;
    Calendar cc = Calendar.getInstance();
    cc.setTime(startDate);
    cc.add(Calendar.SECOND, addnos);
    return cc.getTime();
  }

  /**
   * 增加数字
   *
   * @param type 类型:日、月、年为等，Calendar.DATE等
   * @param date 日期
   * @param num  增加的数字
   * @return 增加后的日期
   */
  public static final Date addDate(int type, Date date, int num) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(type, num);
    return cal.getTime();
  }

  /**
   * 给日期增加天数
   *
   * @param date    日期
   * @param numDays 增加的天数
   * @return 增加后的日期
   */
  public static Date addDaysToDate(Date date, int numDays) {
    if (date == null) {
      return null;
    }
    return addDate(Calendar.DATE, date, numDays);
  }

  /**
   * 给日期增加小时
   *
   * @param date     日期
   * @param numHours 增加的小时数
   * @return 增加后的日期
   */
  public static Date addHoursToDate(Date date, int numHours) {
    if (date == null) {
      return null;
    }
    return addDate(Calendar.HOUR_OF_DAY, date, numHours);
  }

  /**
   * 给日期增加分钟
   *
   * @param date    日期
   * @param numMins 增加的分钟数
   * @return 增加后的日期
   */
  public static Date addMinutesToDate(Date date, int numMins) {
    if (date == null) {
      return null;
    }
    return addDate(Calendar.MINUTE, date, numMins);
  }

  /**
   * 给日期增加月份
   *
   * @param date      日期
   * @param numMonths 增加的月数
   * @return 增加后的日期
   */
  public static Date addMonthsToDate(Date date, int numMonths) {
    if (date == null) {
      return null;
    }
    return addDate(Calendar.MONTH, date, numMonths);
  }

  /**
   * 给日期增加年份
   *
   * @param date     日期
   * @param numYears 增加的年数
   * @return 增加后的日期
   */
  public static Date addYearsToDate(Date date, int numYears) {
    if (date == null) {
      return null;
    }
    return addDate(Calendar.YEAR, date, numYears);
  }

  /**
   * 计算两个日期相差的月数
   *
   * @param st  起始日期
   * @param end 结束日期
   * @return int
   */
  public static int compareMonth(Date st, Date end) {
    int y = Math.abs((getYear(end) < 0 ? 0 : getYear(end))
            - (getYear(st) < 0 ? 0 : getYear(st)));
    int m = 0;
    if (y > 0) {
      y--;
      m = Math.abs(12 - getMonth(st) + getMonth(end));
    } else {
      m = Math.abs(getMonth(end) - getMonth(st));
    }
    return (y * 12) + m;
  }

  /**
   * 计算两个日期相差的毫秒数
   *
   * @param start 启始时间
   * @param end   结束时间
   * @return long
   */
  public static long compare(Date start, Date end) {
    if (start != null && end != null) {
      return end.getTime() - start.getTime();
    }
    return 0l;
  }

  /**
   * 获取当前的年,如果是-1，则表示错误
   *
   * @return int
   */
  public static int getYear() {
    return getYear(new Date());
  }

  /**
   * 获取指定日期的年,如果是-1，则表示错误
   *
   * @param date
   * @return int
   */
  public static int getYear(Date date) {
    if (date == null) {
      return -1;
    }
    return DateToCalendar(date).get(Calendar.YEAR);
  }

  /**
   * 获取当前月，如果返回"0"，则表示错误
   *
   * @return int
   */
  public static int getMonth() {
    return getMonth(new Date());
  }

  /**
   * 获取当前月，如果返回"0"，则表示错误
   *
   * @param date
   * @return int
   */
  public static int getMonth(Date date) {
    if (date == null) {
      return 0;
    }
    return DateToCalendar(date).get(Calendar.MONTH) + 1;
  }

  public static String getMonthDay(Date date) {
    return getMonth(date) + "-" + getDay(date);
  }

  /**
   * 计算两个日期之间相差的天数
   *
   * @param smdate 较小的时间
   * @param bdate  较大的时间
   * @return 相差天数
   * @throws ParseException
   */
  public static int daysBetween(Date smdate, Date bdate) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    smdate = sdf.parse(sdf.format(smdate));
    bdate = sdf.parse(sdf.format(bdate));
    Calendar cal = Calendar.getInstance();
    cal.setTime(smdate);
    long time1 = cal.getTimeInMillis();
    cal.setTime(bdate);
    long time2 = cal.getTimeInMillis();
    long between_days = (time2 - time1) / (1000 * 3600 * 24);

    return Integer.parseInt(String.valueOf(between_days));
  }

  /**
   * 获取当天日,如果返回"0",表示该日期无效或为null
   *
   * @return int
   */
  public static int getDay() {
    return getDay(new Date());
  }

  /**
   * 取一个日期的日,如果返回"0",表示该日期无效或为null
   *
   * @param da
   * @return int
   */
  public static int getDay(Date da) {
    if (da == null) {
      return 0;
    }
    return DateToCalendar(da).get(Calendar.DATE);
  }

  /**
   * 将java.util.Date类型的日期格式转换成java.util.Calendar格式的日期
   *
   * @param date
   * @return Calendar
   */
  public static Calendar DateToCalendar(Date date) {
    Calendar cc = Calendar.getInstance();
    cc.setTime(date);
    return cc;
  }

  /**
   * 得到给定日期的前一个周日的日期
   *
   * @param date
   * @return Date
   */
  public static Date getUpWeekDay(Date date) {
    if (date == null) {
      return null;
    } else {
      Calendar cc = Calendar.getInstance();
      cc.setTime(date);
      int week = cc.get(Calendar.DAY_OF_WEEK);
      return addDaysToDate(date, (1 - week));
    }
  }

  /**
   * 得到给定日期所在周的周一日期
   *
   * @param date
   * @return Date
   */
  public static Date getMonday(Date date) {
    if (date == null) {
      return null;
    } else {
      Calendar cc = Calendar.getInstance();
      cc.setTime(date);
      int week = cc.get(Calendar.DAY_OF_WEEK);
      return addDaysToDate(date, (2 - week));
    }
  }

  /**
   * 得到指定日期所在的周（1-7），惹指定的日期不存在，则返回“-1”
   *
   * @param date
   * @return -1 or 1-7
   */
  public static int getWeek(Date date) {
    if (date == null) {
      return -1;
    } else {
      Calendar cc = Calendar.getInstance();
      cc.setTime(date);
      int week = cc.get(Calendar.DAY_OF_WEEK);
      if (week == 1) {
        week = 7;
      } else {
        week--;
      }
      return week;
    }
  }

  /**
   * 产生随机数
   *
   * @param lo
   * @return String
   */
  public static String getRandNum(int lo) {
    if (lo < 1) {
      lo = 4;
    }
    StringBuffer temp = new StringBuffer();
    Random rand = new Random();
    for (int i = 0; i < lo; i++) {
      temp.append(String.valueOf(rand.nextInt(10)));
    }
    return temp.toString();
  }

  /**
   * 产生文件函数名，以当然日期+4位随机码为主
   *
   * @return String
   */
  public static String getDataName() {
    return parseDate(new Date(), "yyyyMMddHHmmss") + getRandNum(4);
  }

  /**
   * yyyy-mm-dd hh24:mi:ss格式化日期字符串 yyyymmddhh24miss
   *
   * @param date
   * @return String
   */
  public static String formatDS(String date) {
    if (date == null) {
      return "";
    }
    return date.replace("-", "").replace(":", "").replace(" ", "");
  }

  /**
   * 当前日期转长日期字符串:yyyy-MM-dd HH:mm:ss
   *
   * @return
   */
  public static String currentTimeToString() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date d = new Date();
    return sdf.format(d);
  }

  public static String formatDateToString(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    return sdf.format(date);
  }

  public static String currentTimeToShortDateString() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date d = new Date();
    return sdf.format(d);
  }


  public static String currentDateString() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    Date d = new Date();
    return sdf.format(d);
  }

  public static String date2Str(String strDate) {
    String str = "";
    if (StringUtils.isBlank(strDate)) {
      return "";
    } else {
      if (strDate.length() > 8) {
        str = strDate.substring(4, 6).concat("-").concat(strDate.substring(6, 8));
      }
    }
    return str;
  }

  public static String parseBeginDateStr(String beginDate) {
    if (StringUtils.isNotEmpty(beginDate)) {
      beginDate = beginDate + " 00:00:00";

      return beginDate;

    } else {
      return null;
    }

  }

  public static String parseEndDateStr(String endDate) {
    if (StringUtils.isNotEmpty(endDate)) {
      endDate = endDate + " 23:59:59";

      return endDate;

    } else {
      return null;
    }
  }
}