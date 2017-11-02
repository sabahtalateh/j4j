package com.sabahtalateh.jenkov_tutorials.datetime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Example.
 */
public class Example {
    /**
     * @param args args.
     * @throws InterruptedException exception.
     * @throws ParseException       exception.
     */
    public static void main(String[] args) throws InterruptedException, ParseException {


        Instant now = Instant.now();

        Instant later = now.plusSeconds(5);
        Instant earlier = now.minusSeconds(5);

        System.out.println(later);
        System.out.println(earlier);


        Duration duration = Duration.between(later, earlier);

        System.out.println(duration.getSeconds());

        System.out.println(LocalDate.now());

        System.out.println(DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now()));


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("============Old style============");


        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());

        Date d1 = new Date();
        Thread.sleep(100);
        Date d2 = new Date();
        System.out.println(d1.compareTo(d2));
        System.out.println(d1.before(d2));

        java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
        System.out.println(sqlDate);

        Timestamp ts = new Timestamp(d1.getTime());
        System.out.println(ts);

        Calendar calendar = new GregorianCalendar();
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.toInstant());
        calendar.add(Calendar.DATE, 3);
        System.out.println(calendar.toInstant());
        System.out.println(calendar.getTimeZone());

        TimeZone la = TimeZone.getTimeZone("America/Los_Angeles");

        calendar.setTimeZone(la);
        System.out.println(calendar.get(Calendar.HOUR));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(new Date()));
        System.out.println(format.parse("2017-08-11"));

    }
}
