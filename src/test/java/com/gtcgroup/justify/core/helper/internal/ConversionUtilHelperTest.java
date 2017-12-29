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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.base.JstBaseTest;

/**
 * Test Class
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v8.5
 */
@SuppressWarnings("all")
public class ConversionUtilHelperTest extends JstBaseTest {

    private static final String BIRTHDAY = "DEC-05-1956";

    private static final String BIRTHDAY_EXCEPTION = "DEC051956";

    private static final long MILLISECONDS = new Long("-412542000000").longValue();

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(
            ConversionUtilHelperTest.STRING_DATE_FORMAT);

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT_EXCEPTION = new SimpleDateFormat(
            ConversionUtilHelperTest.STRING_DATE_FORMAT_EXCEPTION);

    private static final String STRING_DATE_FORMAT = "MMM-dd-yyyy";

    private static final String STRING_DATE_FORMAT_EXCEPTION = "MMMddyyyy";

    @Test
    public void testConvertCalendarToMMMddyyyyWithLocale() {

        final String value = ConversionUtilHelper.convertCalendarToMMMddyyyyWithLocale(ConversionUtilHelper
                .instantiateCalendar(ConversionUtilHelperTest.BIRTHDAY, ConversionUtilHelperTest.SIMPLE_DATE_FORMAT),
                Locale.ITALY);

        assertEquals("dic-05-1956", value);
    }

    @Test()
    public void testConvertCalendarToMMMddyyyyWithLocale_exception() {

        ConversionUtilHelper.convertCalendarToMMMddyyyyWithLocale(
                ConversionUtilHelper.instantiateCalendar(ConversionUtilHelperTest.BIRTHDAY_EXCEPTION,
                        ConversionUtilHelperTest.SIMPLE_DATE_FORMAT),
                new Locale("IT"));
    }

    @Test()
    public void testConvertCalendarToMMMddyyyyWithLocale_null() {

        ConversionUtilHelper.convertCalendarToMMMddyyyyWithLocale(null, null);
    }

    @Test()
    public void testConvertCalendarToXmlDateTime_empty() throws DatatypeConfigurationException {

        ConversionUtilHelper.convertCalendarToXmlDateTime(
                ConversionUtilHelper.instantiateCalendar("", ConversionUtilHelperTest.SIMPLE_DATE_FORMAT));
    }

    @Test
    public void testConvertCalendarToXmlDateTime_failure() throws DatatypeConfigurationException {

        final XMLGregorianCalendar value = ConversionUtilHelper.convertCalendarToXmlDateTime(
                ConversionUtilHelper.instantiateCalendar(ConversionUtilHelperTest.BIRTHDAY,
                        ConversionUtilHelperTest.SIMPLE_DATE_FORMAT_EXCEPTION));

        assertNotEquals(value.getMillisecond(), ConversionUtilHelperTest.MILLISECONDS);
    }

    @Test()
    public void testConvertCalendarToXmlDateTime_null() throws DatatypeConfigurationException {

        ConversionUtilHelper.convertCalendarToXmlDateTime(null);
    }

    @Test
    public final void testConvertDateMMMddyyyyToCalendar() {

        final Calendar value = ConversionUtilHelper.convertDateMMMddyyyyToCalendar(ConversionUtilHelperTest.BIRTHDAY);

        assertEquals(new Date(ConversionUtilHelperTest.MILLISECONDS), value.getTime());
    }

    @Test()
    public final void testConvertDateMMMddyyyyToCalendar_empty() {

        ConversionUtilHelper.convertDateMMMddyyyyToCalendar("");
    }

    @Test()
    public final void testConvertDateMMMddyyyyToCalendar_exception() {

        ConversionUtilHelper.convertDateMMMddyyyyToCalendar(ConversionUtilHelperTest.BIRTHDAY_EXCEPTION);
    }

    @Test
    public void testConvertDateMMMddyyyyToTimestamp() {

        final Timestamp value = ConversionUtilHelper.convertDateMMMddyyyyToTimestamp(ConversionUtilHelperTest.BIRTHDAY);

        assertEquals(ConversionUtilHelperTest.MILLISECONDS, value.getTime());
    }

    @Test()
    public void testConvertDateMMMddyyyyToTimestamp_empty() {

        ConversionUtilHelper.convertDateMMMddyyyyToTimestamp("");
    }

    @Test()
    public void testConvertDateMMMddyyyyToTimestamp_exception() {

        ConversionUtilHelper.convertDateMMMddyyyyToTimestamp(ConversionUtilHelperTest.BIRTHDAY_EXCEPTION);
    }

    @Test
    public void testConvertDateMMMddyyyyToXmlDate() {

        final XMLGregorianCalendar value = ConversionUtilHelper
                .convertDateMMMddyyyyToXmlDate(ConversionUtilHelperTest.BIRTHDAY);

        assertEquals(5, value.getDay());
    }

    @Test()
    public void testConvertDateMMMddyyyyToXmlDate_exception() {

        ConversionUtilHelper.convertDateMMMddyyyyToXmlDate(ConversionUtilHelperTest.BIRTHDAY_EXCEPTION);

    }

    @Test()
    public void testConvertDateMMMddyyyyToXmlDate_null() {

        ConversionUtilHelper.convertDateMMMddyyyyToXmlDate(null);

    }

    @Test
    public void testConvertDateMMMddyyyyToyyyymmdd() {

        final String value = ConversionUtilHelper.convertDateMMMddyyyyToyyyymmdd(ConversionUtilHelperTest.BIRTHDAY);

        assertEquals("1956-12-05", value);
    }

    @Test()
    public void testConvertDateMMMddyyyyToyyyymmdd_exception() {

        ConversionUtilHelper.convertDateMMMddyyyyToyyyymmdd(ConversionUtilHelperTest.BIRTHDAY_EXCEPTION);
    }

    @Test()
    public void testConvertDateMMMddyyyyToyyyymmdd_null() {

        ConversionUtilHelper.convertDateMMMddyyyyToyyyymmdd(null);

    }

    @Test
    public void testConvertDateStringToCalendar() {

        final Calendar value = ConversionUtilHelper.convertDateStringToCalendar(ConversionUtilHelperTest.BIRTHDAY,
                ConversionUtilHelperTest.SIMPLE_DATE_FORMAT);

        assertEquals(new Date(ConversionUtilHelperTest.MILLISECONDS), value.getTime());
    }

    @Test()
    public void testConvertDateStringToCalendar_exception() {

        ConversionUtilHelper.convertDateStringToCalendar("!@#$%^&*()",
                ConversionUtilHelperTest.SIMPLE_DATE_FORMAT_EXCEPTION);
    }

    @Test()
    public void testConvertDateStringToCalendar_null() {

        ConversionUtilHelper.convertDateStringToCalendar(ConversionUtilHelperTest.BIRTHDAY, null);

    }

    @Test
    public void testConvertDateStringToTimestamp() {

        final Timestamp value = ConversionUtilHelper.convertDateStringToTimestamp(ConversionUtilHelperTest.BIRTHDAY,
                ConversionUtilHelperTest.SIMPLE_DATE_FORMAT);

        assertEquals(ConversionUtilHelperTest.MILLISECONDS, value.getTime());
    }

    @Test()
    public void testConvertDateStringToTimestamp_exception() {

        ConversionUtilHelper.convertDateStringToTimestamp(")(*&^%$#@!", ConversionUtilHelperTest.SIMPLE_DATE_FORMAT);
    }

    @Test()
    public void testConvertDateStringToTimestamp_null() {

        ConversionUtilHelper.convertDateStringToTimestamp(null, ConversionUtilHelperTest.SIMPLE_DATE_FORMAT);
    }

    @Test
    public void testConvertDateStringToXmlDate() {

        final XMLGregorianCalendar value = ConversionUtilHelper.convertDateStringToXmlDate(
                ConversionUtilHelperTest.BIRTHDAY, ConversionUtilHelperTest.SIMPLE_DATE_FORMAT);

        assertEquals(5, value.getDay());
    }

    @Test()
    public void testConvertDateStringToXmlDate_emptyString() {

        ConversionUtilHelper.convertDateStringToXmlDate(ConversionUtilHelperTest.BIRTHDAY_EXCEPTION,
                new SimpleDateFormat(""));
    }

    @Test()
    public void testConvertDateStringToXmlDate_exception() {

        ConversionUtilHelper.convertDateStringToXmlDate(ConversionUtilHelperTest.BIRTHDAY_EXCEPTION,
                ConversionUtilHelperTest.SIMPLE_DATE_FORMAT);
    }

    @Test
    public void testConvertDatetimeXmlToCalendar() {

        final Calendar value = ConversionUtilHelper.convertDatetimeXmlToCalendar(
                ConversionUtilHelper.instantiateXmlGregorianCalendar(ConversionUtilHelperTest.BIRTHDAY,
                        ConversionUtilHelperTest.SIMPLE_DATE_FORMAT));

        assertNotEquals(value.getTimeInMillis(), ConversionUtilHelperTest.MILLISECONDS);
    }

    @Test
    public void testConvertDateXmlToDateString() {

        final String value = ConversionUtilHelper
                .convertDateXmlToDateString(
                        ConversionUtilHelper.instantiateXmlGregorianCalendar(ConversionUtilHelperTest.BIRTHDAY,
                                ConversionUtilHelperTest.SIMPLE_DATE_FORMAT),
                        ConversionUtilHelperTest.SIMPLE_DATE_FORMAT);

        assertEquals(ConversionUtilHelperTest.BIRTHDAY, value.toUpperCase());
    }

    @Test()
    public void testConvertDateXmlToDateString_emptyString() {

        ConversionUtilHelper.convertDateXmlToDateString(
                ConversionUtilHelper.instantiateXmlGregorianCalendar("", new SimpleDateFormat("")),
                ConversionUtilHelperTest.SIMPLE_DATE_FORMAT_EXCEPTION);
    }

    @Test
    public void testConvertDateXmlToDateString_failure() {

        final String value = ConversionUtilHelper.convertDateXmlToDateString(
                ConversionUtilHelper.instantiateXmlGregorianCalendar(ConversionUtilHelperTest.BIRTHDAY,
                        ConversionUtilHelperTest.SIMPLE_DATE_FORMAT),
                ConversionUtilHelperTest.SIMPLE_DATE_FORMAT_EXCEPTION);

        assertNotEquals(value.toUpperCase(), ConversionUtilHelperTest.BIRTHDAY);
    }

    @Test()
    public void testConvertDateXmlToDateString_null() {

        final String value = ConversionUtilHelper.convertDateXmlToDateString(null,
                ConversionUtilHelperTest.SIMPLE_DATE_FORMAT_EXCEPTION);

        assertNotEquals(ConversionUtilHelperTest.BIRTHDAY, value.toUpperCase());
    }

    @Test
    public void testConvertDateXmlToMMMddyyyy() {

        final String value = ConversionUtilHelper.convertDateXmlToMMMddyyyy(
                ConversionUtilHelper.instantiateXmlGregorianCalendar(ConversionUtilHelperTest.BIRTHDAY,
                        ConversionUtilHelperTest.SIMPLE_DATE_FORMAT));

        assertEquals(ConversionUtilHelperTest.BIRTHDAY, value.toUpperCase());
    }

    @Test()
    public void testConvertDateXmlToMMMddyyyy_exception() {

        ConversionUtilHelper.convertDateXmlToMMMddyyyy(ConversionUtilHelper
                .instantiateXmlGregorianCalendar("!@#$%^&*()", ConversionUtilHelperTest.SIMPLE_DATE_FORMAT));
    }

    @Test
    public void testConvertNanosecondToMicrosecondString_five() {

        final long nanos = 99999L;

        assertTrue(ConversionUtilHelper.convertNanosecondToMillisecondString(nanos).contains(".0"));
    }

    @Test
    public void testConvertNanosecondToMicrosecondString_four() {

        final long nanos = 9999L;

        assertTrue(ConversionUtilHelper.convertNanosecondToMillisecondString(nanos).contains(".00"));
    }

    @Test
    public void testConvertNanosecondToMicrosecondString_one() {

        final long nanos = 9L;

        assertTrue(ConversionUtilHelper.convertNanosecondToMillisecondString(nanos).contains(".00000"));
    }

    @Test
    public void testConvertNanosecondToMicrosecondString_six() {

        final long nanos = 999999L;

        assertTrue(ConversionUtilHelper.convertNanosecondToMillisecondString(nanos).contains("."));
    }

    @Test
    public void testConvertNanosecondToMicrosecondString_three() {

        final long nanos = 999L;

        assertTrue(ConversionUtilHelper.convertNanosecondToMillisecondString(nanos).contains(".000"));
    }

    @Test
    public void testConvertNanosecondToMicrosecondString_two() {

        final long nanos = 99L;

        assertTrue(ConversionUtilHelper.convertNanosecondToMillisecondString(nanos).contains(".0000"));
    }

    @Test
    public void testConvertSourceWithReplacementPattern() {

        assertEquals("123765489",
                ConversionUtilHelper.convertStringWithReplacementPattern("123456789", "4567", "7654"));
    }

    @Test
    public void testConvertSourceWithReplacementPattern_empty() {

        assertEquals("", ConversionUtilHelper.convertStringWithReplacementPattern("", "ABCD", "7654"));
    }

    @Test
    public void testConvertSourceWithReplacementPattern_none() {

        assertEquals("123456789",
                ConversionUtilHelper.convertStringWithReplacementPattern("123456789", "ABCD", "7654"));
    }

    @Test
    public void testConvertSourceWithReplacementPattern_null() {

        assertEquals("", ConversionUtilHelper.convertStringWithReplacementPattern(null, "ABCD", "7654"));
    }

    @Test
    public void testConvertTime12HourToXmlTime() throws DatatypeConfigurationException {

        final XMLGregorianCalendar value = ConversionUtilHelper.convertTime12HourToXmlTime("7:30 PM");

        assertEquals("19:30:00", value.toXMLFormat());
    }

    @Test()
    public void testConvertTime12HourToXmlTime_emptyString() throws DatatypeConfigurationException {

        ConversionUtilHelper.convertTime12HourToXmlTime("");
    }

    @Test()
    public void testConvertTime12HourToXmlTime_exception() throws DatatypeConfigurationException {

        final XMLGregorianCalendar value = ConversionUtilHelper.convertTime12HourToXmlTime("7:30 @#");

        assertEquals("19:30:00", value.toXMLFormat());
    }

    @Test
    public void testConvertTimestampToDateString() {

        final String value = ConversionUtilHelper.convertTimestampToDateString(
                new Timestamp(ConversionUtilHelperTest.MILLISECONDS), ConversionUtilHelperTest.SIMPLE_DATE_FORMAT);

        assertEquals(value.toUpperCase(), ConversionUtilHelperTest.BIRTHDAY);
    }

    @Test
    public void testConvertTimestampToDateString_failure() {

        final String value = ConversionUtilHelper.convertTimestampToDateString(new Timestamp(new Long("0").longValue()),
                ConversionUtilHelperTest.SIMPLE_DATE_FORMAT);

        assertNotEquals(value.toUpperCase(), ConversionUtilHelperTest.BIRTHDAY);
    }

    @Test
    public void testConvertTimestampToMMMddyyyy() {

        final String value = ConversionUtilHelper
                .convertTimestampToMMMddyyyy(new Timestamp(ConversionUtilHelperTest.MILLISECONDS));

        assertEquals(ConversionUtilHelperTest.BIRTHDAY, value.toUpperCase());
    }

    @Test
    public void testConvertTimestampToMMMddyyyy_failure() {

        final String value = ConversionUtilHelper.convertTimestampToMMMddyyyy(new Timestamp(new Long("0").longValue()));

        assertNotEquals(value.toUpperCase(), ConversionUtilHelperTest.BIRTHDAY);
    }

    @Test
    public void testUuidRoundTripConversions() {

        final byte[] uuidArray1 = ConversionUtilHelper.convertRandomUUID();

        final String hexString = ConversionUtilHelper.convertFromUuidByteArrayToHexString(uuidArray1);

        final byte[] uuidArray2 = ConversionUtilHelper.convertFromUuidHexStringToByteArray(hexString);

        assertEquals(ConversionUtilHelper.convertFromUuidByteArrayToHexString(uuidArray2),
                ConversionUtilHelper.convertFromUuidByteArrayToHexString(uuidArray1));
    }
}
