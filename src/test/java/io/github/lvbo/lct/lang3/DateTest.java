package io.github.lvbo.lct.lang3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

/**
 * @author lvbo created on 2023-07-04 23:54
 */
public class DateTest {

    /**
     * 字符串转日期
     */
    @Test
    public void testStringToDate() throws ParseException {
        final String strDate = "2021-07-04 11:11:11";
        final String pattern = "yyyy-MM-dd HH:mm:ss";

        // 原生写法
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date1 = sdf.parse(strDate);
        System.out.println(date1);

        // commons写法
        Date date2 = DateUtils.parseDate(strDate, pattern);
        System.out.println(date2);
    }

    /**
     * 日期转字符串
      */
    @Test
    public void testDateToString() {
        final Date date = new Date();
        final String pattern = "yyyy-MM-dd HH:mm:ss";

        // 原生写法
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String strDate1 = sdf.format(date);
        System.out.println(strDate1);

        // commons写法
        String strDate2 = DateFormatUtils.format(date, pattern);
        System.out.println(strDate2);
    }

    /**
     * 日期加减
     */
    @Test
    public void testDateCalc() {
        final Date date = new Date();
        // 原生写法
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE,5);//加5天
        cal.add(Calendar.HOUR_OF_DAY,-5);//减5小时

        //使用commons写法
        Date newDate1= DateUtils.addDays(date,5); // 加5天
        System.out.println(newDate1);
        Date newDate2 = DateUtils.addHours(date,-5); // 减5小时
        System.out.println(newDate2);
        Date newDate3 = DateUtils.truncate(date, Calendar.DATE); //过滤时分秒
        System.out.println(newDate3);
        boolean isSameDay = DateUtils.isSameDay(newDate1, newDate2); // 判断是否是同一天
        System.out.println(isSameDay);
    }
}
