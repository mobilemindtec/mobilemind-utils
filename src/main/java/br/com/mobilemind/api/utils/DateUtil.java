package br.com.mobilemind.api.utils;

/*
 * #%L
 * Mobile Mind - Utils
 * %%
 * Copyright (C) 2012 Mobile Mind Empresa de Tecnologia
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe utilitaria para formaração de datas
 *
 * @author Ricardo Bocchi
 */
public class DateUtil {

    public static final int MAIOR = 1;
    public static final int MENOR = -1;
    public static final int IGUAL = 0;
    public static final String PATTER_DATE_FORMAT = "dd/MM/yyyy";
    public static final String PATTER_TIME_FORMAT = "HH:mm";
    public static final String PATTER_TIMESTEMP_FORMAT = "dd/MM/yyyy HH:mm";
    public static final String PATTER_TIMESTEMP_FORMAT_SS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String PATTER_ISO8681 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(PATTER_DATE_FORMAT);
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(PATTER_TIME_FORMAT);
    public static final SimpleDateFormat TIMESTEMP_FORMAT = new SimpleDateFormat(PATTER_TIMESTEMP_FORMAT);
    public static final SimpleDateFormat TIMESTEMP_FORMAT_SS = new SimpleDateFormat(PATTER_TIMESTEMP_FORMAT_SS);

    public static Date getMinHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getMaxHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        //calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static boolean equals(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(d1);
        c2.setTime(d2);

        if (c1.get(Calendar.DATE) != c2.get(Calendar.DATE)) {
            return false;
        }
        if (c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH)) {
            return false;
        }
        if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR)) {
            return false;
        }

        return true;
    }

    public static int compare(Date d1, Date d2) {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(getMinHour(d1));
        c2.setTime(getMinHour(d2));

        if (c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR)) {
            return DateUtil.MAIOR;
        } else if (c1.get(Calendar.YEAR) < c2.get(Calendar.YEAR)) {
            return DateUtil.MENOR;
        }

        if (c1.get(Calendar.MONTH) > c2.get(Calendar.MONTH)) {
            return DateUtil.MAIOR;
        } else if (c1.get(Calendar.MONTH) < c2.get(Calendar.MONTH)) {
            return DateUtil.MENOR;
        }

        if (c1.get(Calendar.DATE) > c2.get(Calendar.DATE)) {
            return DateUtil.MAIOR;
        } else if (c1.get(Calendar.DATE) < c2.get(Calendar.DATE)) {
            return DateUtil.MENOR;
        }

        return DateUtil.IGUAL;
    }

    /**
     * verifica se a data 1 é maior que a data 2
     * @param d1
     * @param d2
     * @return 
     */
    public static int compareDate(Date d1, Date d2) {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(d1);
        c2.setTime(d2);


        if (c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR)) {
            return DateUtil.MAIOR;
        } else if (c1.get(Calendar.YEAR) < c2.get(Calendar.YEAR)) {
            return DateUtil.MENOR;
        }

        if (c1.get(Calendar.MONTH) > c2.get(Calendar.MONTH)) {
            return DateUtil.MAIOR;
        } else if (c1.get(Calendar.MONTH) < c2.get(Calendar.MONTH)) {
            return DateUtil.MENOR;
        }

        if (c1.get(Calendar.DATE) > c2.get(Calendar.DATE)) {
            return DateUtil.MAIOR;
        } else if (c1.get(Calendar.DATE) < c2.get(Calendar.DATE)) {
            return DateUtil.MENOR;
        }

        return DateUtil.IGUAL;
    }

    /**
     * verifica se a hora 1 é maior que a hora 2
     * @param d1
     * @param d2
     * @return 
     */
    public static int compareHourMin(Date d1, Date d2) {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(d1);
        c2.setTime(d2);

        int h1 = c1.get(Calendar.HOUR_OF_DAY);
        int h2 = c2.get(Calendar.HOUR_OF_DAY);
        int m1 = c1.get(Calendar.MINUTE);
        int m2 = c2.get(Calendar.MINUTE);

        if (h1 > h2) {
            return DateUtil.MAIOR;
        } else if (h1 < h2) {
            return DateUtil.MENOR;
        }


        if (m1 > m2) {
            return DateUtil.MAIOR;
        } else if (m1 < m2) {
            return DateUtil.MENOR;
        }


        return DateUtil.IGUAL;
    }

    public static int compareTime(Date d1, Date d2) {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(d1);
        c2.setTime(d2);

        int h1 = c1.get(Calendar.HOUR_OF_DAY);
        int h2 = c2.get(Calendar.HOUR_OF_DAY);

        int m1 = c1.get(Calendar.MINUTE);
        int m2 = c2.get(Calendar.MINUTE);

        int s1 = c1.get(Calendar.SECOND);
        int s2 = c2.get(Calendar.SECOND);

        if (h1 > h2) {
            return DateUtil.MAIOR;
        } else if (h1 < h2) {
            return DateUtil.MENOR;
        }

        if (m1 > m2) {
            return DateUtil.MAIOR;
        } else if (m1 < m2) {
            return DateUtil.MENOR;
        }

        if (s1 > s2) {
            return DateUtil.MAIOR;
        } else if (s1 < s2) {
            return DateUtil.MENOR;
        }


        return DateUtil.IGUAL;
    }

    public static int compareTimestamp(Date timeOne, Date timeTwo) {
        int comparDate = DateUtil.compareDate(timeOne, timeTwo);
        int comparTime = DateUtil.compareTime(timeOne, timeTwo);

        if (comparDate == IGUAL && comparTime == IGUAL) {
            return IGUAL;
        }

        if (comparDate == MENOR) {
            return MENOR;
        }

        if (comparDate == MAIOR) {
            return MAIOR;
        }

        return comparTime;
    }

    public static int compareTimestampNow(Date timeTwo) {
        Date time = Calendar.getInstance().getTime();
        return compareTimestamp(time, timeTwo);
    }

    public static int compareDateNow(Date timeTwo) {
        Date time = Calendar.getInstance().getTime();
        return compare(time, timeTwo);
    }

    public static int compareHourMinNow(Date timeTwo) {
        Date time = Calendar.getInstance().getTime();
        return compareHourMin(time, timeTwo);
    }

    public static int compareTimeNow(Date timeTwo) {
        Date time = Calendar.getInstance().getTime();
        return compareTime(time, timeTwo);
    }

    public static Date strToDate(String date) {
        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException ex) {
        }
        return null;
    }

    public static Date strToTime(String time) {
        try {
            return TIME_FORMAT.parse(time);
        } catch (ParseException ex) {
        }
        return null;
    }

    public static Date strToTimestamp(String timestamp) {
        try {
            return TIMESTEMP_FORMAT.parse(timestamp);
        } catch (ParseException ex) {
        }
        return null;
    }

    public static Date strToTimestampSS(String timestamp) {
        try {
            return TIMESTEMP_FORMAT_SS.parse(timestamp);
        } catch (ParseException ex) {
        }
        return null;
    }

    public static String dateToStr(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static String timeToStr(Date time) {
        return TIME_FORMAT.format(time);
    }

    public static String timestampToStr(Date timestamp) {
        return TIMESTEMP_FORMAT.format(timestamp);
    }

    public static String timestampSSToStr(Date timestamp) {
        return TIMESTEMP_FORMAT_SS.format(timestamp);
    }

    public static Calendar iso8681ToCalendar(String value) {
        return javax.xml.bind.DatatypeConverter.parseDateTime(value);
    }

    public static String calendarToISO8681(Calendar calendar) {
        return javax.xml.bind.DatatypeConverter.printDateTime(calendar);
    }
    
    public static Date sumDays(int amountOfDays, Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, amountOfDays);  // number of days to add
        return c.getTime();
    }
    
    public static Date substractDays(int amountOfDays, Date date) { 
        Date dateBefore = new Date(date.getTime() - amountOfDays * 24 * 3600 * 1000); //Subtract n days 
        return dateBefore;
    }
    
    
    public static Date createDateFromInts(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        return c.getTime();
    }

    public static Map<String, Integer> getIntsFromToday() {
        Map<String, Integer> returnValues = new HashMap<String, Integer>();
        final Calendar c = Calendar.getInstance();
        returnValues.put("dayOfMonth", c.get(Calendar.DAY_OF_MONTH));
        returnValues.put("monthOfYear", c.get(Calendar.MONTH));
        returnValues.put("year", c.get(Calendar.YEAR));
        return returnValues;
    }
    
    public static Calendar getCalendarAtSpecificTime(int hour, int minute, int second, int millis) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, millis);
        return cal;
    } 
}
