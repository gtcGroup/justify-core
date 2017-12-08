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

package com.gtcgroup.justify.core.helper.internal;

import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.gtcgroup.justify.core.exception.internal.JustifyRuntimeException;

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
public enum ConversionUtilHelper {

    @SuppressWarnings("javadoc")
    INSTANCE;

    private static DatatypeFactory DATATYPE_FACTORY;

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(
            ConversionUtilHelper.STRING_DATE_FORMAT);

    private static final String STRING_DATE_FORMAT = "MMM-dd-yyyy";

    static {
        try {
            ConversionUtilHelper.DATATYPE_FACTORY = DatatypeFactory.newInstance();

        } catch (@SuppressWarnings("unused") final Exception e) {
            // Ignore, not likely.
        }
    }

    /**
     * This method converts a {@link Calendar} into a different {@link Locale} and
     * return the date value in MMM-dd-yyyy format.
     *
     * @return {@link String}
     */
    public static String convertCalendarToMMMddyyyyWithLocale(final Calendar calendar, final Locale inLocale) {

        final String dateString;

        try {
            final SimpleDateFormat formatter = new SimpleDateFormat(ConversionUtilHelper.STRING_DATE_FORMAT, inLocale);
            final Date today = new Date(calendar.getTimeInMillis());
            dateString = formatter.format(today);

        } catch (final Exception e) {

            throw new JustifyRuntimeException(e);
        }
        return dateString;
    }

    /**
     * This method converts a {@link Calendar} to an {@link XMLGregorianCalendar}
     * that can be used for XML DateTime processing.
     *
     * @return {@link XMLGregorianCalendar}
     * @throws DatatypeConfigurationException
     */
    public static XMLGregorianCalendar convertCalendarToXmlDateTime(final Calendar calendar)
            throws DatatypeConfigurationException {

        XMLGregorianCalendar xmlGregorianCalendar;

        try {

            final Date date = new Date(calendar.getTimeInMillis());

            final GregorianCalendar gregorianCalendar = (GregorianCalendar) Calendar.getInstance();
            gregorianCalendar.setTime(date);

            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

        } catch (final Exception e) {

            throw new JustifyRuntimeException(e);
        }

        return xmlGregorianCalendar;
    }

    /**
     * This method converts a date {@link String} in MMM-dd-yyyy (e.g. Jul-27-2015)
     * format into a {@link Calendar} with the hours, minutes and seconds set to 0.
     *
     * @return {@link Calendar}
     */
    public static Calendar convertDateMMMddyyyyToCalendar(final String MMMddyyyyString) {

        final Calendar calendar = ConversionUtilHelper.convertDateStringToCalendar(MMMddyyyyString,
                ConversionUtilHelper.SIMPLE_DATE_FORMAT);

        return calendar;
    }

    /**
     * This method converts a date {@link String} in the format MMM-dd-yyyy (e.g.
     * Jul-27-2015) into a {@link Timestamp} with the hours, minutes and seconds set
     * to 0.
     *
     * @return {@link Timestamp}
     */
    public static Timestamp convertDateMMMddyyyyToTimestamp(final String MMMddyyyyString) {

        // SimpleDateFormat object for "MMM-dd-yyyy" (e.g. Jul-27-2015)
        final SimpleDateFormat dateFormatMMMddyyyy = new SimpleDateFormat("MMM-dd-yyyy");

        final Timestamp timestamp = ConversionUtilHelper.convertDateStringToTimestamp(MMMddyyyyString,
                dateFormatMMMddyyyy);

        return timestamp;
    }

    /**
     * This method converts a date {@link String} in the format MMM-dd-yyyy (e.g.
     * Jul-27-2015) into a {@link XMLGregorianCalendar} with the hours, minutes and
     * seconds set to 0.
     *
     * @return {@link XMLGregorianCalendar}
     */
    public static XMLGregorianCalendar convertDateMMMddyyyyToXmlDate(final String MMMddyyyyString) {

        // SimpleDateFormat object for "MMM-dd-yyyy" (e.g. Jul-27-2015)
        final SimpleDateFormat dateFormatMMMddyyyy = new SimpleDateFormat("MMM-dd-yyyy");

        final XMLGregorianCalendar xmlDate = ConversionUtilHelper.convertDateStringToXmlDate(MMMddyyyyString,
                dateFormatMMMddyyyy);

        return xmlDate;
    }

    /**
     * This method converts a date {@link String} in the format MMM-dd-yyyy (e.g.
     * Jul-27-2015) into a date {@link String} in the format yyyy-mm-dd (e.g.
     * 2015-07-27)
     *
     * @return {@link String}
     */
    public static String convertDateMMMddyyyyToyyyymmdd(final String MMMddyyyyString) {

        final Timestamp timestamp = ConversionUtilHelper.convertDateMMMddyyyyToTimestamp(MMMddyyyyString);

        // Use java.sql.Date to do the conversion because it does this
        // via the toString() method.
        final java.sql.Date sqlDate = new java.sql.Date(timestamp.getTime());

        return sqlDate.toString();
    }

    /**
     * This method converts a date {@link String} in the format specified into a
     * {@link Calendar} with the hours, minutes and seconds set to 0.
     *
     * @return {@link Calendar}
     */
    public static Calendar convertDateStringToCalendar(final String dateString,
            final SimpleDateFormat dateStringFormat) {

        final Calendar calendar = Calendar.getInstance();

        try {

            final Date date = dateStringFormat.parse(dateString);
            calendar.setTime(date);

        } catch (final Exception e) {

            throw new JustifyRuntimeException(e);
        }

        return calendar;
    }

    /**
     * This method converts a date {@link String} in the format specified into a
     * {@link Timestamp} with the hours, minutes and seconds set to 0.
     *
     * @return {@link Timestamp}
     */
    public static Timestamp convertDateStringToTimestamp(final String dateString,
            final SimpleDateFormat dateStringFormat) {

        Timestamp timestamp;

        try {
            final Date date = dateStringFormat.parse(dateString);
            timestamp = new Timestamp(date.getTime());

        } catch (final Exception e) {

            throw new JustifyRuntimeException(e);
        }

        return timestamp;

    }

    /**
     * This method converts a date String in the format specified into a
     * {@link XMLGregorianCalendar} with the hours, minutes and seconds set to 0.
     *
     * @return {@link XMLGregorianCalendar}
     */
    public static XMLGregorianCalendar convertDateStringToXmlDate(final String dateString,
            final SimpleDateFormat dateStringFormat) {

        XMLGregorianCalendar xmlDate;

        xmlDate = ConversionUtilHelper.instantiateXmlGregorianCalendar(dateString, dateStringFormat);

        return xmlDate;
    }

    /**
     * This method converts an {@link XMLGregorianCalendar} into a {@link Calendar}
     * with the format specified.
     *
     * @return {@link Calendar}
     */
    public static Calendar convertDatetimeXmlToCalendar(final XMLGregorianCalendar xmlDatetime) {

        Calendar calendar;

        try {
            // The time zone is only available from the Gregorian calendar.
            final GregorianCalendar gregorianCalendar = xmlDatetime.toGregorianCalendar();

            final TimeZone timeZone = gregorianCalendar.getTimeZone();

            calendar = Calendar.getInstance();
            calendar.clear();
            calendar.setTimeZone(timeZone);
            calendar.set(Calendar.YEAR, xmlDatetime.getYear());
            calendar.set(Calendar.MONTH, xmlDatetime.getMonth() - 1);
            calendar.set(Calendar.DATE, xmlDatetime.getDay());
            calendar.set(Calendar.HOUR_OF_DAY, xmlDatetime.getHour());
            calendar.set(Calendar.MINUTE, xmlDatetime.getMinute());
            calendar.set(Calendar.SECOND, xmlDatetime.getSecond());
        } catch (final Exception e) {
            throw new JustifyRuntimeException(e);
        }

        return calendar;
    }

    /**
     * This method converts an {@link XMLGregorianCalendar} into a {@link String}
     * with the format specified.
     *
     * @return {@link String}
     */
    public static String convertDateXmlToDateString(final XMLGregorianCalendar xmlDate,
            final SimpleDateFormat dateStringFormat) {

        Calendar calendar;

        try {

            calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, xmlDate.getYear());
            calendar.set(Calendar.MONTH, xmlDate.getMonth() - 1);
            calendar.set(Calendar.DAY_OF_MONTH, xmlDate.getDay());

        } catch (final Exception e) {
            throw new JustifyRuntimeException(e);
        }

        return dateStringFormat.format(calendar.getTime());
    }

    /**
     * This method converts an {@link XMLGregorianCalendar} into a String with the
     * format MMM-dd-yyyy (e.g. Jul-27-2015).
     *
     * @return {@link String}
     */
    public static String convertDateXmlToMMMddyyyy(final XMLGregorianCalendar xmlDate) {

        final String MMMddyyyyString = ConversionUtilHelper.convertDateXmlToDateString(xmlDate,
                ConversionUtilHelper.SIMPLE_DATE_FORMAT);

        return MMMddyyyyString;
    }

    /**
     * Converts a {@link UUID} byte array to a hex string.
     *
     * @return String is a hex string with hyphens.
     */
    public static String convertFromUuidByteArrayToHexString(final byte[] uuidByteArray) {

        final ByteBuffer bb = ByteBuffer.allocate(16);
        bb.put(uuidByteArray);
        bb.flip();
        final UUID uuid = new UUID(bb.getLong(), bb.getLong());
        return uuid.toString();
    }

    /**
     * Converts a hex string back to a {@link UUID} byte array.
     *
     * @return byte array
     */
    public static byte[] convertFromUuidHexStringToByteArray(final String hexStringWithHyphens) {

        final UUID uuid = UUID.fromString(hexStringWithHyphens);
        final ByteBuffer bb = ByteBuffer.allocate(16);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    /**
     * This method returns byte array equivalent of a {@link UUID}.
     *
     * @return byte array
     */
    private static byte[] convertFromUuidInstanceToByteArray(final UUID uuid) {

        final ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        byteBuffer.putLong(uuid.getMostSignificantBits()).putLong(uuid.getLeastSignificantBits());
        return byteBuffer.array();
    }

    /**
     * This method creates a set of asterisks depending on the length of the
     * message.
     *
     * @return {@link StringBuilder}
     */
    public static StringBuilder convertMessageLengthToBorder(final String message) {
        final StringBuilder border = new StringBuilder();

        border.append("\n\t");
        for (int i = 1; i < message.length(); i++) {
            border.append("*");
        }
        border.append("\n");
        return border;
    }

    /**
     * This method creates a microsecond string or, if zero microseconds, a two
     * digit decimal representation.
     *
     * @return {@link String}
     */
    public static String convertNanosecondToMillisecondString(final long nanos) {

        final String longString = Long.toString(nanos);

        final long ms = nanos / 1000000;

        if (0 == ms) {

            if (longString.length() == 6) {

                return "." + longString.substring(0, 3);

            } else if (longString.length() == 5) {
                return ".0" + longString.substring(0, 2);
            } else if (longString.length() == 4) {
                return ".00" + longString.substring(0, 1);
            } else if (longString.length() == 3) {
                return ".000" + longString.substring(0, 1);
            } else if (longString.length() == 2) {
                return ".0000" + longString.substring(0, 1);
            } else {
                return ".00000" + longString;
            }
        }
        return Long.toString(ms);
    }

    /**
     * This method initializes a random {@link UUID}.
     *
     * @return byte[]
     */
    public static byte[] convertRandomUUID() {

        return ConversionUtilHelper.convertFromUuidInstanceToByteArray(UUID.randomUUID());
    }

    /**
     * This method returns a string with characters replaced. A null is returned as
     * an empty string.
     *
     * @return {@link String}
     */
    public static String convertStringWithReplacementPattern(final String originalString, final String pattern,
            final String replace) {

        if (originalString != null) {
            final int len = pattern.length();
            final StringBuffer sb = new StringBuffer();
            int found = -1;
            int start = 0;

            while ((found = originalString.indexOf(pattern, start)) != -1) {
                sb.append(originalString.substring(start, found));
                sb.append(replace);
                start = found + len;
            }

            sb.append(originalString.substring(start));

            return sb.toString();
        }

        return "";
    }

    /**
     * This method converts a time String with the format of "hh:mm a" (e.g 7:30 PB)
     * into an XMLGregorianCalendar with a lexical time format (e.g. 19:30:00).
     *
     * @return {@link XMLGregorianCalendar}
     * @throws DatatypeConfigurationException
     */
    public static XMLGregorianCalendar convertTime12HourToXmlTime(final String tweleveHourTime)
            throws DatatypeConfigurationException {

        XMLGregorianCalendar xmlTime;

        try {

            // SimpleDateFormate object for "hh:mm a" (e.g. 10:33 PB)
            final SimpleDateFormat timeFormat12hour = new SimpleDateFormat("hh:mm a");

            // SimpleDateFormate object for "HH:mm:ss" (e.g. 22:33:00)
            final SimpleDateFormat timeFormat24hour = new SimpleDateFormat("HH:mm:ss");

            final Date timeAsDate = timeFormat12hour.parse(tweleveHourTime);

            final String lexicalTime = timeFormat24hour.format(timeAsDate);

            xmlTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(lexicalTime);

        } catch (final Exception e) {

            throw new JustifyRuntimeException(e);
        }

        return xmlTime;
    }

    /**
     * This method converts a {@link Timestamp} into a String with the format
     * specified.
     *
     * @return {@link String}
     */
    public static String convertTimestampToDateString(final Timestamp timestamp,
            final SimpleDateFormat simpleDateFormat) {

        String stringDate;

        try {
            final long time = timestamp.getTime();

            final Date date = new Date(time);
            stringDate = simpleDateFormat.format(date);

        } catch (final Exception e) {
            throw new JustifyRuntimeException(e);
        }

        return stringDate;
    }

    /**
     * This method returns a {@link String} instance in the "MMM-dd-yyyy" format
     * from a timestamp.
     *
     * @return {@link String}
     */
    public static String convertTimestampToMMMddyyyy(final Timestamp timestamp) {

        final String MMMddyyyyString = ConversionUtilHelper.convertTimestampToDateString(timestamp,
                ConversionUtilHelper.SIMPLE_DATE_FORMAT);

        return MMMddyyyyString;
    }

    /**
     * @return {@link XMLGregorianCalendar}
     */
    public static Calendar instantiateCalendar(final String dateString, final SimpleDateFormat dateStringFormat) {

        final Calendar calendar = Calendar.getInstance();

        try {

            calendar.setTime(dateStringFormat.parse(dateString));

        } catch (final Exception e) {
            throw new JustifyRuntimeException(e);
        }

        return calendar;
    }

    /**
     * @return {@link XMLGregorianCalendar}
     */
    public static XMLGregorianCalendar instantiateXmlGregorianCalendar(final String dateString,
            final SimpleDateFormat dateStringFormat) {

        XMLGregorianCalendar xmlDate;

        try {
            final Calendar calendar = Calendar.getInstance();

            calendar.setTime(dateStringFormat.parse(dateString));
            xmlDate = ConversionUtilHelper.DATATYPE_FACTORY.newXMLGregorianCalendarDate(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH),
                    DatatypeConstants.FIELD_UNDEFINED);
        } catch (final Exception e) {

            throw new JustifyRuntimeException(e);
        }

        return xmlDate;
    }

}
