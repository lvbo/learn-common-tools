package io.github.lvbo.lct.jodatime;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * @author lvbo
 * @date 2021/1/2 08:05
 */
public class JodaTimeTest {

    /**
     * joda-time LocalDate 使用示例
     */
    @Test
    public void testLocalDate() {
        // 1. 创建LocalDate对象
        // 1.1 使用当前系统时区
        LocalDate localDate = new LocalDate();
        System.out.println(localDate);
        // 1.2 使用指定时区
        localDate = new LocalDate(DateTimeZone.forID("Asia/Shanghai"));
        System.out.println(localDate);
        // 1.3 使用指定毫秒数
        localDate = new LocalDate(1609526400000L);
        System.out.println(localDate);
        // 1.4 使用指定年月日
        localDate = new LocalDate(2021, 1, 2);
        System.out.println(localDate);

        // 2. LocalDate转换为java.util.Date
        Date date = localDate.toDate();
        System.out.println(date);
    }

    /**
     * joda-time LocalTime 使用示例
     */
    @Test
    public void testLocalTime() {
        // 1. 创建LocalTime对象
        // 1.1 使用当前系统时区
        LocalTime localTime = new LocalTime();
        System.out.println(localTime);
        // 1.2 使用指定时区
        localTime = new LocalTime(DateTimeZone.forID("Asia/Shanghai"));
        System.out.println(localTime);
        // 1.3 使用指定毫秒数
        localTime = new LocalTime(1609526400000L);
        System.out.println(localTime);
        // 1.4 使用指定时分秒
        localTime = new LocalTime(8, 10, 10);
        System.out.println(localTime);

        // 2. LocalTime转换为java.util.Date
        Date date = localTime.toDateTimeToday().toDate();
        System.out.println(date);
    }

    @Test
    public void testInteroperability() {
        // from Joda to JDK
        DateTime dateTime = new DateTime();
        Date jdkDate = dateTime.toDate();

        // from JDK to Joda
        DateTime jodaTime = new DateTime(jdkDate);

        // from Joda to JDK
        DateTime dt = new DateTime();
        Calendar jdkCal = dt.toCalendar(Locale.CHINESE);

        // from JDK to Joda
        dt = new DateTime(jdkCal);
    }

    @Test
    public void testDateTime() {
        // 创建DateTime对象
        DateTime dateTime1 = new DateTime();
        System.out.println(dateTime1);
        DateTime dateTime2 = new DateTime(2016,2,14,0,0,0);
        System.out.println(dateTime2);
        DateTime dateTime3 = new DateTime(1456473917004L);
        System.out.println(dateTime3);
        DateTime dateTime4 = new DateTime(new Date());
        System.out.println(dateTime4);
        DateTime dateTime5 = new DateTime("2016-02-15T00:00:00.000+08:00");
        System.out.println(dateTime5);

        // 格式化
        System.out.println(dateTime1.toString("yyyy-MM-dd HH:mm:ss"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt6 = DateTime.parse("2020-8-20 7:22:45", dateTimeFormatter);
        System.out.println(dt6);
        System.out.println(dt6.toString("yyyy年MM月dd日 HH:mm:ss EE", Locale.CHINESE));

        // 运算
        DateTime tomorrow = dateTime1.plusDays(1);
        System.out.println(tomorrow);
        DateTime lastMonth = dateTime1.minusMonths(1);
        System.out.println(lastMonth);
        System.out.println(Days.daysBetween(dateTime1, dateTime2).getDays());

        // 获取值
        System.out.println("判断是否是闰年:" + dateTime1.monthOfYear().isLeap());
        System.out.println(dateTime1.monthOfYear().getAsText());
        System.out.println(dateTime1.monthOfYear().getAsText(Locale.CHINA));
        System.out.println(dateTime1.dayOfWeek().getAsShortText());
        System.out.println(dateTime1.dayOfWeek().getAsShortText(Locale.CHINESE));
        System.out.printf("hour:%s min:%s sec:%s", dateTime1.getHourOfDay(), dateTime1.getMinuteOfHour(), dateTime1.getSecondOfMinute());
    }
}
