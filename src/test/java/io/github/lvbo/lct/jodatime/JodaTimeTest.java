package io.github.lvbo.lct.jodatime;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
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
 * @author lvbo.lv
 * @date 2021/1/2 08:05
 */
public class JodaTimeTest {

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
    public void testQuerying() {
        DateTime dateTime = new DateTime();
        int iDoW = dateTime.getDayOfWeek();
        System.out.println(iDoW);

        DateTime.Property pDoW = dateTime.dayOfWeek();

        // returns "Mon", "Tue", etc.
        String strST = pDoW.getAsShortText();
        System.out.println(strST);

        // returns "Monday", "Tuesday", etc.
        String strT = pDoW.getAsText();
        System.out.println(strT);

        // returns "Lundi", etc.
        String strTF = pDoW.getAsText(Locale.ENGLISH);
        System.out.println(strTF);

        iDoW = pDoW.get();
        System.out.println(iDoW);
    }

    @Test
    public void testDateFields() {
//        dt.getEra();
//        dt.getYear();
//        dt.getWeekyear();
//        dt.getCenturyOfEra();
//        dt.getYearOfEra();
//        dt.getYearOfCentury();
//        dt.getMonthOfYear();
//        dt.getWeekOfWeekyear();
//        dt.getDayOfYear();
//        dt.getDayOfMonth();
//        dt.getDayOfWeek();

        DateTime dt = new DateTime();
        String month = dt.monthOfYear().getAsText();
        int maxDay = dt.dayOfMonth().getMaximumValue();
        boolean leapYear = dt.yearOfEra().isLeap();

        System.out.printf("month:%s maxDay:%s leapYear:%s", month, maxDay, leapYear);
    }

    @Test
    public void testTimeFields() {
        DateTime dt = new DateTime();
        int hour = dt.getHourOfDay();
        int min = dt.getMinuteOfHour();
        int sec = dt.getSecondOfMinute();

        System.out.printf("hour:%s min:%s sec:%s", hour, min, sec);
    }

    @Test
    public void testManipulatingDateTimes() {
        DateTime dt = new DateTime();
        DateTime result = dt.dayOfWeek().setCopy(DateTimeConstants.MONDAY);
        System.out.println(result);

        dt = new DateTime();
        result = dt.dayOfWeek().addToCopy(3);
        System.out.println(result);

        dt = new DateTime();
        result = dt.plusDays(3);
        System.out.println(result);

        // get current moment in default time zone
        dt = new DateTime();
        // translate to London local time
        DateTime dtLondon = dt.withZone(DateTimeZone.forID("Europe/London"));
        System.out.println(dtLondon);
    }

    @Test
    public void testInputAndOutput() {
        String strInputDateTime = "2200-10-10";
        // string is populated with a date time string in some fashion
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dt = fmt.parseDateTime(strInputDateTime);
        System.out.println(dt);
    }

    @Test
    public void testDirectAccess() {
        DateTime dt = new DateTime();
        String a = dt.toString();
        String b = dt.toString("dd:MM:yy");
        String c = dt.toString("EEE", Locale.FRENCH);

        System.out.printf("a:%s b:%s c:%s", a, b, c);
    }
}
