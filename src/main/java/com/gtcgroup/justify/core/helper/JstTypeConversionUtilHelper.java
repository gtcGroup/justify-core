/*
 * [Licensed per the Open Source "MIT License".]
 *
 * Copyright (c) 2006 - 2017 by
 * Global Technology Consulting Group, Inc. at
 * http://gtcGroup.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.gtcgroup.justify.core.helper;

import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;
import java.util.UUID;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * This Util Helper class provides support for data type conversions.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public enum JstTypeConversionUtilHelper {

    INSTANCE;

    private static DatatypeFactory dataTypeFactory;

    static {
        try {
            JstTypeConversionUtilHelper.dataTypeFactory = DatatypeFactory.newInstance();

        } catch (@SuppressWarnings("unused") final Exception e) {
            // Ignore, not likely.
        }
    }

    /**
     * @return {@link String}
     */
    public static Optional<String> convertCalendarToMMMddyyyyWithLocale(final Calendar calendar,
            final Locale inLocale) {

        try {
            final SimpleDateFormat formatter = new SimpleDateFormat(INSTANCE.monthDayYear, inLocale);
            final Date today = new Date(calendar.getTimeInMillis());
            return Optional.of(formatter.format(today));

        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue.
        }
        return Optional.empty();
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<XMLGregorianCalendar> convertCalendarToXmlGregorian(final Calendar calendar) {

        try {

            final Date date = new Date(calendar.getTimeInMillis());

            final GregorianCalendar gregorianCalendar = (GregorianCalendar) Calendar.getInstance();
            gregorianCalendar.setTime(date);

            return Optional.of(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar));

        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue.
        }
        return Optional.empty();
    }

    /**
     * This method converts a date {@link String} in MMM-dd-yyyy (e.g. Jul-27-2015)
     * format into a {@link Calendar} with the hours, minutes and seconds set to 0.
     *
     * @return {@link Optional}
     */
    public static Optional<Calendar> convertDateMMMddyyyyToCalendar(final String MMMddyyyyString) {

        final Optional<Calendar> calendar = JstTypeConversionUtilHelper.convertDateStringToCalendar(MMMddyyyyString,
                INSTANCE.simpleDateFormat);

        if (calendar.isPresent()) {
            return calendar;
        }
        return Optional.empty();
    }

    /**
     * This method converts a date {@link String} in the format MMM-dd-yyyy (e.g.
     * Jul-27-2015) into a {@link Timestamp} with the hours, minutes and seconds set
     * to 0.
     *
     * @return {@link Optional}
     */
    public static Optional<Timestamp> convertDateMMMddyyyyToTimestamp(final String MMMddyyyyString) {

        final Optional<Timestamp> timestamp = JstTypeConversionUtilHelper.convertDateStringToTimestamp(MMMddyyyyString,
                INSTANCE.simpleDateFormat);

        if (timestamp.isPresent()) {
            return timestamp;
        }
        return Optional.empty();
    }

    /**
     * This method converts a date {@link String} in the format MMM-dd-yyyy (e.g.
     * Jul-27-2015) into a {@link XMLGregorianCalendar} with the hours, minutes and
     * seconds set to 0.
     *
     * @return {@link Optional}
     */
    public static Optional<XMLGregorianCalendar> convertDateMMMddyyyyToXmlDate(final String MMMddyyyyString) {

        final SimpleDateFormat dateFormatMMMddyyyy = new SimpleDateFormat(INSTANCE.monthDayYear);

        return convertDateStringToXmlDate(MMMddyyyyString, dateFormatMMMddyyyy);
    }

    /**
     * This method converts a date {@link String} in the format MMM-dd-yyyy (e.g.
     * Jul-27-2015) into a date {@link String} in the format yyyy-mm-dd (e.g.
     * 2015-07-27)
     *
     * @return {@link Optional}
     */
    public static Optional<String> convertDateMMMddyyyyToyyyymmdd(final String MMMddyyyyString) {

        final Optional<Timestamp> timestamp = convertDateMMMddyyyyToTimestamp(MMMddyyyyString);

        if (timestamp.isPresent()) {

            // Use java.sql.Date to do the conversion because it does this
            // via the toString() method.
            final java.sql.Date sqlDate = new java.sql.Date(timestamp.get().getTime());

            return Optional.of(sqlDate.toString());
        }

        return Optional.empty();
    }

    /**
     * This method converts a date {@link String} in the format specified into a
     * {@link Calendar} with the hours, minutes and seconds set to 0.
     *
     * @return {@link Optional}
     */
    public static Optional<Calendar> convertDateStringToCalendar(final String dateString,
            final SimpleDateFormat simpleDateFormat) {

        try {

            final Calendar calendar = Calendar.getInstance();
            final Date date = simpleDateFormat.parse(dateString);
            calendar.setTime(date);
            return Optional.of(calendar);

        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue.
        }
        return Optional.empty();
    }

    /**
     * This method converts a date {@link String} in the format specified into a
     * {@link Timestamp} with the hours, minutes and seconds set to 0.
     *
     * @return {@link Optional}
     */
    public static Optional<Timestamp> convertDateStringToTimestamp(final String dateString,
            final SimpleDateFormat simpleDateFormat) {

        try {
            final Date date = simpleDateFormat.parse(dateString);
            return Optional.of(new Timestamp(date.getTime()));

        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue.
        }
        return Optional.empty();

    }

    /**
     * This method converts a date String in the format specified into a
     * {@link XMLGregorianCalendar} with the hours, minutes and seconds set to 0.
     *
     * @return {@link Optional}
     */
    public static Optional<XMLGregorianCalendar> convertDateStringToXmlDate(final String dateString,
            final SimpleDateFormat simpleDateFormat) {

        return instantiateXmlGregorianCalendar(dateString, simpleDateFormat);
    }

    /**
     * This method converts an {@link XMLGregorianCalendar} into a {@link String}
     * with the format specified.
     *
     * @return {@link Optional}
     */
    public static Optional<String> convertDateXmlToDateString(final XMLGregorianCalendar xmlDate,
            final SimpleDateFormat simpleDateFormat) {

        try {

            final Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, xmlDate.getYear());
            calendar.set(Calendar.MONTH, xmlDate.getMonth() - 1);
            calendar.set(Calendar.DAY_OF_MONTH, xmlDate.getDay());

            return Optional.of(simpleDateFormat.format(calendar.getTime()));

        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue.
        }
        return Optional.empty();
    }

    /**
     * This method converts an {@link XMLGregorianCalendar} into a String with the
     * format MMM-dd-yyyy (e.g. Jul-27-2015).
     *
     * @return {@link Optional}
     */
    public static Optional<String> convertDateXmlToMMMddyyyy(final XMLGregorianCalendar xmlDate) {

        return convertDateXmlToDateString(xmlDate, JstTypeConversionUtilHelper.INSTANCE.simpleDateFormat);
    }

    /**
     * This method returns the byte array equivalent of a {@link UUID}.
     *
     * @return {@link Optional}
     */
    public static Optional<byte[]> convertFromUuidToByteArray(final UUID uuid) {

        try {
            final ByteBuffer byteBuffer = ByteBuffer.allocate(16);
            byteBuffer.putLong(uuid.getMostSignificantBits()).putLong(uuid.getLeastSignificantBits());

            return Optional.of(byteBuffer.array());
        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue.
        }
        return Optional.empty();
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<Calendar> convertGregorianCalendarToCalendar(
            final XMLGregorianCalendar xmlGregorianCalendar) {

        try {

            // The time zone is available from the Gregorian calendar.
            final GregorianCalendar gregorianCalendar = xmlGregorianCalendar.toGregorianCalendar();

            final TimeZone timeZone = gregorianCalendar.getTimeZone();

            final Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.setTimeZone(timeZone);
            calendar.set(Calendar.YEAR, xmlGregorianCalendar.getYear());
            calendar.set(Calendar.MONTH, xmlGregorianCalendar.getMonth() - 1);
            calendar.set(Calendar.DATE, xmlGregorianCalendar.getDay());
            calendar.set(Calendar.HOUR_OF_DAY, xmlGregorianCalendar.getHour());
            calendar.set(Calendar.MINUTE, xmlGregorianCalendar.getMinute());
            calendar.set(Calendar.SECOND, xmlGregorianCalendar.getSecond());

            return Optional.of(calendar);

        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue.
        }
        return Optional.empty();
    }

    /**
     * This method creates a set of asterisks depending on the length of the
     * message.
     *
     * @return {@link Optional}
     */
    public static Optional<StringBuilder> convertMessageLengthToBorder(final String message) {

        try {

            final StringBuilder border = new StringBuilder();
            border.append("\n\t");

            for (int i = 1; i < message.length(); i++) {
                border.append("*");
            }
            border.append("\n");

            return Optional.of(border);

        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue.
        }
        return Optional.empty();
    }

    /**
     * This method creates a microsecond string or, if zero microseconds, a two
     * digit decimal representation.
     *
     * @return {@link Optional}
     */
    public static Optional<String> convertNanosecondToMillisecondString(final long nanos) {

        final String longString = Long.toString(nanos);

        final long ms = nanos / 1000000;

        if (0 == ms) {

            if (longString.length() == 6) {

                return Optional.of("." + longString.substring(0, 3));
            } else if (longString.length() == 5) {
                return Optional.of(".0" + longString.substring(0, 2));
            } else if (longString.length() == 4) {
                return Optional.of(".00" + longString.substring(0, 1));
            } else if (longString.length() == 3) {
                return Optional.of(".000" + longString.substring(0, 1));
            } else if (longString.length() == 2) {
                return Optional.of(".0000" + longString.substring(0, 1));
            } else {
                return Optional.of(".00000" + longString);
            }
        }
        return Optional.of(Long.toString(ms));
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<String> convertStringWithReplacementPattern(final String originalString,
            final String pattern, final String replace) {

        try {
            final int len = pattern.length();
            final StringBuilder sb = new StringBuilder();
            int found = -1;
            int start = 0;

            while ((found = originalString.indexOf(pattern, start)) != -1) {
                sb.append(originalString.substring(start, found));
                sb.append(replace);
                start = found + len;
            }

            sb.append(originalString.substring(start));
            return Optional.of(sb.toString());

        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue.
        }
        return Optional.empty();
    }

    /**
     * This method converts a time String with the format of "hh:mm a" (e.g 7:30 PB)
     * into an XMLGregorianCalendar with a lexical time format (e.g. 19:30:00).
     *
     * @return {@link Optional}
     */
    public static Optional<XMLGregorianCalendar> convertTime12HourToXmlTime(final String tweleveHourTime) {

        try {

            // SimpleDateFormate object for "hh:mm a" (e.g. 10:33 PB)
            final SimpleDateFormat timeFormat12hour = new SimpleDateFormat("hh:mm a");

            // SimpleDateFormate object for "HH:mm:ss" (e.g. 22:33:00)
            final SimpleDateFormat timeFormat24hour = new SimpleDateFormat("HH:mm:ss");

            final Date timeAsDate = timeFormat12hour.parse(tweleveHourTime);

            final String lexicalTime = timeFormat24hour.format(timeAsDate);

            return Optional.of(DatatypeFactory.newInstance().newXMLGregorianCalendar(lexicalTime));

        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue.
        }
        return Optional.empty();
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<String> convertTimestampToDateString(final Timestamp timestamp,
            final SimpleDateFormat simpleDateFormat) {

        try {
            final long time = timestamp.getTime();
            final Date date = new Date(time);

            return Optional.of(simpleDateFormat.format(date));

        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue.
        }
        return Optional.empty();
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<String> convertTimestampToMMMddyyyy(final Timestamp timestamp) {

        return convertTimestampToDateString(timestamp, JstTypeConversionUtilHelper.INSTANCE.simpleDateFormat);
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<Calendar> instantiateCalendar(final String dateString,
            final SimpleDateFormat simpleDateFormat) {

        final Calendar calendar = Calendar.getInstance();

        try {

            calendar.setTime(simpleDateFormat.parse(dateString));
            return Optional.of(calendar);

        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue.
        }
        return Optional.empty();
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<XMLGregorianCalendar> instantiateXmlGregorianCalendar(final String dateString,
            final SimpleDateFormat simpleDateFormat) {

        try {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(dateString));

            final XMLGregorianCalendar calendarGregorian = JstTypeConversionUtilHelper.dataTypeFactory
                    .newXMLGregorianCalendarDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                            calendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);

            return Optional.of(calendarGregorian);

        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue.
        }
        return Optional.empty();
    }

    private final String monthDayYear = "MMM-dd-yyyy";

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.monthDayYear);

}
