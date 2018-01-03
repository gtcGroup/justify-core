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
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import com.gtcgroup.justify.core.helper.JstTypeConversionUtilHelper;

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
@SuppressWarnings("static-method")
public class JstTypeConversionUtilHelperTest extends JstBaseTest {

    private static final String BIRTHDAY = "DEC-05-1956";

    private static final String BIRTHDAY_EXCEPTION = "DEC051956";

    private static final long MILLISECONDS = new Long("-412542000000").longValue();

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(
            JstTypeConversionUtilHelperTest.STRING_DATE_FORMAT);

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT_EXCEPTION = new SimpleDateFormat(
            JstTypeConversionUtilHelperTest.STRING_DATE_FORMAT_EXCEPTION);

    private static final String STRING_DATE_FORMAT = "MMM-dd-yyyy";

    private static final String STRING_DATE_FORMAT_EXCEPTION = "MMMddyyyy";

    @Test
    public void testConvertCalendarToMMMddyyyyWithLocale() {

        assertEquals("dic-05-1956",
                JstTypeConversionUtilHelper.convertCalendarToMMMddyyyyWithLocale(
                        JstTypeConversionUtilHelper.instantiateCalendar(JstTypeConversionUtilHelperTest.BIRTHDAY,
                                JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT).get(),
                        Locale.ITALY).get());
    }

    @Test
    public void testConvertCalendarToMMMddyyyyWithLocale_false() {

        assertFalse(JstTypeConversionUtilHelper
                .convertCalendarToMMMddyyyyWithLocale(JstTypeConversionUtilHelper
                        .instantiateCalendar(JstTypeConversionUtilHelperTest.BIRTHDAY_EXCEPTION,
                                JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT)
                        .get(), new Locale("IT"))
                .isPresent());
    }

    @Test
    public void testConvertCalendarToMMMddyyyyWithLocale_nulls() {

        assertFalse(JstTypeConversionUtilHelper.convertCalendarToMMMddyyyyWithLocale(null, null).isPresent());
    }

    @Test()
    public void testConvertCalendarToXmlDateTime_empty() throws DatatypeConfigurationException {

        JstTypeConversionUtilHelper.convertCalendarToXmlDateTime(JstTypeConversionUtilHelper.instantiateCalendar("",
                JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT));
    }

    @Test
    public void testConvertCalendarToXmlDateTime_failure() throws DatatypeConfigurationException {

        final XMLGregorianCalendar value = JstTypeConversionUtilHelper.convertCalendarToXmlDateTime(
                JstTypeConversionUtilHelper.instantiateCalendar(JstTypeConversionUtilHelperTest.BIRTHDAY,
                        JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT_EXCEPTION));

        assertNotEquals(value.getMillisecond(), JstTypeConversionUtilHelperTest.MILLISECONDS);
    }

    @Test()
    public void testConvertCalendarToXmlDateTime_null() throws DatatypeConfigurationException {

        JstTypeConversionUtilHelper.convertCalendarToXmlDateTime(null);
    }

    @Test
    public final void testConvertDateMMMddyyyyToCalendar() {

        final Calendar value = JstTypeConversionUtilHelper
                .convertDateMMMddyyyyToCalendar(JstTypeConversionUtilHelperTest.BIRTHDAY);

        assertEquals(new Date(JstTypeConversionUtilHelperTest.MILLISECONDS), value.getTime());
    }

    @Test()
    public final void testConvertDateMMMddyyyyToCalendar_empty() {

        JstTypeConversionUtilHelper.convertDateMMMddyyyyToCalendar("");
    }

    @Test()
    public final void testConvertDateMMMddyyyyToCalendar_exception() {

        JstTypeConversionUtilHelper.convertDateMMMddyyyyToCalendar(JstTypeConversionUtilHelperTest.BIRTHDAY_EXCEPTION);
    }

    @Test
    public void testConvertDateMMMddyyyyToTimestamp() {

        final Timestamp value = JstTypeConversionUtilHelper
                .convertDateMMMddyyyyToTimestamp(JstTypeConversionUtilHelperTest.BIRTHDAY);

        assertEquals(JstTypeConversionUtilHelperTest.MILLISECONDS, value.getTime());
    }

    @Test()
    public void testConvertDateMMMddyyyyToTimestamp_empty() {

        JstTypeConversionUtilHelper.convertDateMMMddyyyyToTimestamp("");
    }

    @Test()
    public void testConvertDateMMMddyyyyToTimestamp_exception() {

        JstTypeConversionUtilHelper.convertDateMMMddyyyyToTimestamp(JstTypeConversionUtilHelperTest.BIRTHDAY_EXCEPTION);
    }

    @Test
    public void testConvertDateMMMddyyyyToXmlDate() {

        final XMLGregorianCalendar value = JstTypeConversionUtilHelper
                .convertDateMMMddyyyyToXmlDate(JstTypeConversionUtilHelperTest.BIRTHDAY);

        assertEquals(5, value.getDay());
    }

    @Test()
    public void testConvertDateMMMddyyyyToXmlDate_exception() {

        JstTypeConversionUtilHelper.convertDateMMMddyyyyToXmlDate(JstTypeConversionUtilHelperTest.BIRTHDAY_EXCEPTION);

    }

    @Test()
    public void testConvertDateMMMddyyyyToXmlDate_null() {

        JstTypeConversionUtilHelper.convertDateMMMddyyyyToXmlDate(null);

    }

    @Test
    public void testConvertDateMMMddyyyyToyyyymmdd() {

        final String value = JstTypeConversionUtilHelper
                .convertDateMMMddyyyyToyyyymmdd(JstTypeConversionUtilHelperTest.BIRTHDAY);

        assertEquals("1956-12-05", value);
    }

    @Test()
    public void testConvertDateMMMddyyyyToyyyymmdd_exception() {

        JstTypeConversionUtilHelper.convertDateMMMddyyyyToyyyymmdd(JstTypeConversionUtilHelperTest.BIRTHDAY_EXCEPTION);
    }

    @Test()
    public void testConvertDateMMMddyyyyToyyyymmdd_null() {

        JstTypeConversionUtilHelper.convertDateMMMddyyyyToyyyymmdd(null);

    }

    @Test
    public void testConvertDateStringToCalendar() {

        final Calendar value = JstTypeConversionUtilHelper.convertDateStringToCalendar(
                JstTypeConversionUtilHelperTest.BIRTHDAY, JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT);

        assertEquals(new Date(JstTypeConversionUtilHelperTest.MILLISECONDS), value.getTime());
    }

    @Test()
    public void testConvertDateStringToCalendar_exception() {

        JstTypeConversionUtilHelper.convertDateStringToCalendar("!@#$%^&*()",
                JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT_EXCEPTION);
    }

    @Test()
    public void testConvertDateStringToCalendar_null() {

        JstTypeConversionUtilHelper.convertDateStringToCalendar(JstTypeConversionUtilHelperTest.BIRTHDAY, null);

    }

    @Test
    public void testConvertDateStringToTimestamp() {

        final Timestamp value = JstTypeConversionUtilHelper.convertDateStringToTimestamp(
                JstTypeConversionUtilHelperTest.BIRTHDAY, JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT);

        assertEquals(JstTypeConversionUtilHelperTest.MILLISECONDS, value.getTime());
    }

    @Test()
    public void testConvertDateStringToTimestamp_exception() {

        JstTypeConversionUtilHelper.convertDateStringToTimestamp(")(*&^%$#@!",
                JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT);
    }

    @Test()
    public void testConvertDateStringToTimestamp_null() {

        JstTypeConversionUtilHelper.convertDateStringToTimestamp(null,
                JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT);
    }

    @Test
    public void testConvertDateStringToXmlDate() {

        final XMLGregorianCalendar value = JstTypeConversionUtilHelper.convertDateStringToXmlDate(
                JstTypeConversionUtilHelperTest.BIRTHDAY, JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT);

        assertEquals(5, value.getDay());
    }

    @Test()
    public void testConvertDateStringToXmlDate_emptyString() {

        JstTypeConversionUtilHelper.convertDateStringToXmlDate(JstTypeConversionUtilHelperTest.BIRTHDAY_EXCEPTION,
                new SimpleDateFormat(""));
    }

    @Test()
    public void testConvertDateStringToXmlDate_exception() {

        JstTypeConversionUtilHelper.convertDateStringToXmlDate(JstTypeConversionUtilHelperTest.BIRTHDAY_EXCEPTION,
                JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT);
    }

    @Test
    public void testConvertDatetimeXmlToCalendar() {

        final Calendar value = JstTypeConversionUtilHelper.convertGregorianCalendarToCalendar(
                JstTypeConversionUtilHelper.instantiateXmlGregorianCalendar(JstTypeConversionUtilHelperTest.BIRTHDAY,
                        JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT));

        assertNotEquals(value.getTimeInMillis(), JstTypeConversionUtilHelperTest.MILLISECONDS);
    }

    @Test
    public void testConvertDateXmlToDateString() {

        final String value = JstTypeConversionUtilHelper.convertDateXmlToDateString(
                JstTypeConversionUtilHelper.instantiateXmlGregorianCalendar(JstTypeConversionUtilHelperTest.BIRTHDAY,
                        JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT),
                JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT);

        assertEquals(JstTypeConversionUtilHelperTest.BIRTHDAY, value.toUpperCase());
    }

    @Test()
    public void testConvertDateXmlToDateString_emptyString() {

        JstTypeConversionUtilHelper.convertDateXmlToDateString(
                JstTypeConversionUtilHelper.instantiateXmlGregorianCalendar("", new SimpleDateFormat("")),
                JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT_EXCEPTION);
    }

    @Test
    public void testConvertDateXmlToDateString_failure() {

        final String value = JstTypeConversionUtilHelper.convertDateXmlToDateString(
                JstTypeConversionUtilHelper.instantiateXmlGregorianCalendar(JstTypeConversionUtilHelperTest.BIRTHDAY,
                        JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT),
                JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT_EXCEPTION);

        assertNotEquals(value.toUpperCase(), JstTypeConversionUtilHelperTest.BIRTHDAY);
    }

    @Test()
    public void testConvertDateXmlToDateString_null() {

        final String value = JstTypeConversionUtilHelper.convertDateXmlToDateString(null,
                JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT_EXCEPTION);

        assertNotEquals(JstTypeConversionUtilHelperTest.BIRTHDAY, value.toUpperCase());
    }

    @Test
    public void testConvertDateXmlToMMMddyyyy() {

        final String value = JstTypeConversionUtilHelper.convertDateXmlToMMMddyyyy(
                JstTypeConversionUtilHelper.instantiateXmlGregorianCalendar(JstTypeConversionUtilHelperTest.BIRTHDAY,
                        JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT));

        assertEquals(JstTypeConversionUtilHelperTest.BIRTHDAY, value.toUpperCase());
    }

    @Test()
    public void testConvertDateXmlToMMMddyyyy_exception() {

        JstTypeConversionUtilHelper.convertDateXmlToMMMddyyyy(JstTypeConversionUtilHelper
                .instantiateXmlGregorianCalendar("!@#$%^&*()", JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT));
    }

    @Test
    public void testConvertNanosecondToMicrosecondString_five() {

        final long nanos = 99999L;

        assertTrue(JstTypeConversionUtilHelper.convertNanosecondToMillisecondString(nanos).contains(".0"));
    }

    @Test
    public void testConvertNanosecondToMicrosecondString_four() {

        final long nanos = 9999L;

        assertTrue(JstTypeConversionUtilHelper.convertNanosecondToMillisecondString(nanos).contains(".00"));
    }

    @Test
    public void testConvertNanosecondToMicrosecondString_one() {

        final long nanos = 9L;

        assertTrue(JstTypeConversionUtilHelper.convertNanosecondToMillisecondString(nanos).contains(".00000"));
    }

    @Test
    public void testConvertNanosecondToMicrosecondString_six() {

        final long nanos = 999999L;

        assertTrue(JstTypeConversionUtilHelper.convertNanosecondToMillisecondString(nanos).contains("."));
    }

    @Test
    public void testConvertNanosecondToMicrosecondString_three() {

        final long nanos = 999L;

        assertTrue(JstTypeConversionUtilHelper.convertNanosecondToMillisecondString(nanos).contains(".000"));
    }

    @Test
    public void testConvertNanosecondToMicrosecondString_two() {

        final long nanos = 99L;

        assertTrue(JstTypeConversionUtilHelper.convertNanosecondToMillisecondString(nanos).contains(".0000"));
    }

    @Test
    public void testConvertSourceWithReplacementPattern() {

        assertEquals("123765489",
                JstTypeConversionUtilHelper.convertStringWithReplacementPattern("123456789", "4567", "7654"));
    }

    @Test
    public void testConvertSourceWithReplacementPattern_empty() {

        assertEquals("", JstTypeConversionUtilHelper.convertStringWithReplacementPattern("", "ABCD", "7654"));
    }

    @Test
    public void testConvertSourceWithReplacementPattern_none() {

        assertEquals("123456789",
                JstTypeConversionUtilHelper.convertStringWithReplacementPattern("123456789", "ABCD", "7654"));
    }

    @Test
    public void testConvertSourceWithReplacementPattern_null() {

        assertEquals("", JstTypeConversionUtilHelper.convertStringWithReplacementPattern(null, "ABCD", "7654"));
    }

    @Test
    public void testConvertTime12HourToXmlTime() throws DatatypeConfigurationException {

        final XMLGregorianCalendar value = JstTypeConversionUtilHelper.convertTime12HourToXmlTime("7:30 PM");

        assertEquals("19:30:00", value.toXMLFormat());
    }

    @Test()
    public void testConvertTime12HourToXmlTime_emptyString() throws DatatypeConfigurationException {

        JstTypeConversionUtilHelper.convertTime12HourToXmlTime("");
    }

    @Test()
    public void testConvertTime12HourToXmlTime_exception() throws DatatypeConfigurationException {

        final XMLGregorianCalendar value = JstTypeConversionUtilHelper.convertTime12HourToXmlTime("7:30 @#");

        assertEquals("19:30:00", value.toXMLFormat());
    }

    @Test
    public void testConvertTimestampToDateString() {

        final String value = JstTypeConversionUtilHelper.convertTimestampToDateString(
                new Timestamp(JstTypeConversionUtilHelperTest.MILLISECONDS),
                JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT);

        assertEquals(value.toUpperCase(), JstTypeConversionUtilHelperTest.BIRTHDAY);
    }

    @Test
    public void testConvertTimestampToDateString_failure() {

        final String value = JstTypeConversionUtilHelper.convertTimestampToDateString(
                new Timestamp(new Long("0").longValue()), JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT);

        assertNotEquals(value.toUpperCase(), JstTypeConversionUtilHelperTest.BIRTHDAY);
    }

    @Test
    public void testConvertTimestampToMMMddyyyy() {

        final String value = JstTypeConversionUtilHelper
                .convertTimestampToMMMddyyyy(new Timestamp(JstTypeConversionUtilHelperTest.MILLISECONDS));

        assertEquals(JstTypeConversionUtilHelperTest.BIRTHDAY, value.toUpperCase());
    }

    @Test
    public void testConvertTimestampToMMMddyyyy_failure() {

        final String value = JstTypeConversionUtilHelper
                .convertTimestampToMMMddyyyy(new Timestamp(new Long("0").longValue()));

        assertNotEquals(value.toUpperCase(), JstTypeConversionUtilHelperTest.BIRTHDAY);
    }

    @Test
    public void testUuidRoundTripConversions() {

        final byte[] uuidArray1 = JstTypeConversionUtilHelper.convertRandomUUID();

        final String hexString = JstTypeConversionUtilHelper.convertFromUuidByteArrayToHexString(uuidArray1);

        final byte[] uuidArray2 = JstTypeConversionUtilHelper.convertFromUuidHexStringToByteArray(hexString);

        assertEquals(JstTypeConversionUtilHelper.convertFromUuidByteArrayToHexString(uuidArray2),
                JstTypeConversionUtilHelper.convertFromUuidByteArrayToHexString(uuidArray1));
    }
}
