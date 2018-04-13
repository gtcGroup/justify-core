/*
 * [Licensed per the Open Source "MIT License".]
 *
 * Copyright (c) 2006 - 2018 by
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.base.JstBaseTest;
import com.gtcgroup.justify.core.test.extension.JstConfigureTestLogToConsole;

/**
 * Test Class
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since 8.5
 */
@JstConfigureTestLogToConsole
@SuppressWarnings("static-method")
public class JstTypeConversionUtilHelperTest extends JstBaseTest {

	private static final String BIRTHDAY = "DEC-05-1956";

	private static final String BIRTHDAY_EXCEPTION = "DEC051956";

	private static final long MILLISECONDS = new Long("-412542000000").longValue();

	private static final String STRING_DATE_FORMAT = "MMM-dd-yyyy";
	private static final String STRING_DATE_FORMAT_EXCEPTION = "MMMddyyyy";

	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(
			JstTypeConversionUtilHelperTest.STRING_DATE_FORMAT);

	private static final SimpleDateFormat SIMPLE_DATE_FORMAT_EXCEPTION = new SimpleDateFormat(
			JstTypeConversionUtilHelperTest.STRING_DATE_FORMAT_EXCEPTION);

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

		assertFalse(JstTypeConversionUtilHelper.convertCalendarToMMMddyyyyWithLocale(null, null).isPresent());
	}

	@Test
	public void testConvertCalendarToXmlGregorian() {

		assertTrue(
				JstTypeConversionUtilHelper
						.convertCalendarToXmlGregorian(JstTypeConversionUtilHelper
								.instantiateCalendar(JstTypeConversionUtilHelperTest.BIRTHDAY,
										JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT_EXCEPTION)
								.get())
						.isPresent());
	}

	@Test
	public void testConvertCalendarToXmlGregorian_false() {

		assertFalse(JstTypeConversionUtilHelper.convertCalendarToXmlGregorian(null).isPresent());
	}

	@Test
	public final void testConvertDateMMMddyyyyToCalendar() {

		assertTrue(JstTypeConversionUtilHelper.convertDateMMMddyyyyToCalendar(JstTypeConversionUtilHelperTest.BIRTHDAY)
				.isPresent());
	}

	@Test
	public final void testConvertDateMMMddyyyyToCalendar_false() {

		assertFalse(JstTypeConversionUtilHelper.convertDateMMMddyyyyToCalendar(null).isPresent());
	}

	@Test
	public void testConvertDateMMMddyyyyToTimestamp() {

		assertTrue(JstTypeConversionUtilHelper.convertDateMMMddyyyyToTimestamp(JstTypeConversionUtilHelperTest.BIRTHDAY)
				.isPresent());
	}

	@Test()
	public void testConvertDateMMMddyyyyToTimestamp_empty() {

		assertFalse(JstTypeConversionUtilHelper.convertDateMMMddyyyyToTimestamp("").isPresent());
	}

	@Test
	public void testConvertDateMMMddyyyyToXmlDate() {

		assertTrue(JstTypeConversionUtilHelper.convertDateMMMddyyyyToXmlDate(JstTypeConversionUtilHelperTest.BIRTHDAY)
				.isPresent());
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

		assertTrue(JstTypeConversionUtilHelper.convertDateMMMddyyyyToyyyymmdd(JstTypeConversionUtilHelperTest.BIRTHDAY)
				.isPresent());
	}

	@Test()
	public void testConvertDateMMMddyyyyToyyyymmdd_false() {

		assertFalse(JstTypeConversionUtilHelper.convertDateMMMddyyyyToyyyymmdd(null).isPresent());
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
	public void testConvertDateXmlToDateString() {

		assertTrue(JstTypeConversionUtilHelper.convertDateXmlToDateString(
				JstTypeConversionUtilHelper.instantiateXmlGregorianCalendar(JstTypeConversionUtilHelperTest.BIRTHDAY,
						JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT).get(),
				JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT).isPresent());
	}

	@Test()
	public void testConvertDateXmlToDateString_false() {

		assertFalse(JstTypeConversionUtilHelper
				.convertDateXmlToDateString(null, JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT_EXCEPTION)
				.isPresent());
	}

	@Test
	public void testConvertDateXmlToMMMddyyyy() {

		assertTrue(JstTypeConversionUtilHelper.convertDateXmlToMMMddyyyy(
				JstTypeConversionUtilHelper.instantiateXmlGregorianCalendar(JstTypeConversionUtilHelperTest.BIRTHDAY,
						JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT).get())
				.isPresent());
	}

	@Test
	public void testConvertFromUuidToByteArray() {

		assertTrue(JstTypeConversionUtilHelper.convertFromUuidToByteArray(UUID.randomUUID()).isPresent());
	}

	@Test
	public void testConvertFromUuidToByteArray_false() {

		assertFalse(JstTypeConversionUtilHelper.convertFromUuidToByteArray(null).isPresent());
	}

	@Test
	public void testConvertGregorianCalenderToCalendar() {

		assertTrue(JstTypeConversionUtilHelper.convertGregorianCalendarToCalendar(
				JstTypeConversionUtilHelper.instantiateXmlGregorianCalendar(JstTypeConversionUtilHelperTest.BIRTHDAY,
						JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT).get())
				.isPresent());
	}

	@Test
	public void testConvertGregorianCalenderToCalendar_false() {

		assertFalse(JstTypeConversionUtilHelper.convertGregorianCalendarToCalendar(null).isPresent());
	}

	@Test
	public void testConvertMessageLengthToBorder() {

		assertTrue(JstTypeConversionUtilHelper.convertMessageLengthToBorder("null").isPresent());
	}

	@Test
	public void testConvertMessageLengthToBorder_false() {

		assertFalse(JstTypeConversionUtilHelper.convertMessageLengthToBorder(null).isPresent());
	}

	@Test
	public void testConvertNanosecondToMillisecondString_false() {

		final long nanos = 999999999999999999L;

		assertFalse(JstTypeConversionUtilHelper.convertNanosecondToMillisecondString(nanos).get().contains("."));
	}

	@Test
	public void testConvertNanosecondToMillisecondString_five() {

		final long nanos = 99999L;

		assertTrue(JstTypeConversionUtilHelper.convertNanosecondToMillisecondString(nanos).get().contains(".999"));
	}

	@Test
	public void testConvertNanosecondToMillisecondString_four() {

		final long nanos = 9999L;

		assertTrue(JstTypeConversionUtilHelper.convertNanosecondToMillisecondString(nanos).get().contains(".999"));
	}

	@Test
	public void testConvertNanosecondToMillisecondString_one() {

		final long nanos = 9L;

		assertTrue(JstTypeConversionUtilHelper.convertNanosecondToMillisecondString(nanos).get().contains(".9"));
	}

	@Test
	public void testConvertNanosecondToMillisecondString_six() {

		final long nanos = 999999L;

		assertTrue(JstTypeConversionUtilHelper.convertNanosecondToMillisecondString(nanos).get().contains("."));
	}

	@Test
	public void testConvertNanosecondToMillisecondString_three() {

		final long nanos = 999L;

		assertTrue(JstTypeConversionUtilHelper.convertNanosecondToMillisecondString(nanos).get().contains(".999"));
	}

	@Test
	public void testConvertNanosecondToMillisecondString_two() {

		final long nanos = 99L;

		assertTrue(JstTypeConversionUtilHelper.convertNanosecondToMillisecondString(nanos).get().contains(".99"));
	}

	@Test
	public void testConvertNanosecondToMillisecondString_zero() {

		final long nanos = 0L;

		assertTrue(JstTypeConversionUtilHelper.convertNanosecondToMillisecondString(nanos).get().contains(".000"));
	}

	@Test
	public void testConvertStringWithReplacementPattern() {

		assertEquals("123765489",
				JstTypeConversionUtilHelper.convertStringWithReplacementPattern("123456789", "4567", "7654").get());
	}

	@Test
	public void testConvertStringWithReplacementPattern_false() {

		assertFalse(JstTypeConversionUtilHelper.convertStringWithReplacementPattern(null, null, null).isPresent());
	}

	@Test
	public void testConvertTime12HourToXmlTime() {

		assertTrue(JstTypeConversionUtilHelper.convertTime12HourToXmlTime("7:30 PM").isPresent());
	}

	@Test
	public void testConvertTime12HourToXmlTime_false() {

		assertFalse(JstTypeConversionUtilHelper.convertTime12HourToXmlTime("").isPresent());
	}

	@Test
	public void testConvertTimestampToDateString() {

		assertTrue(JstTypeConversionUtilHelper
				.convertTimestampToDateString(new Timestamp(JstTypeConversionUtilHelperTest.MILLISECONDS),
						JstTypeConversionUtilHelperTest.SIMPLE_DATE_FORMAT)
				.isPresent());
	}

	@Test
	public void testConvertTimestampToDateString_false() {

		assertFalse(JstTypeConversionUtilHelper.convertTimestampToDateString(null, null).isPresent());
	}

	@Test
	public void testConvertTimestampToMMMddyyyy() {

		assertTrue(JstTypeConversionUtilHelper
				.convertTimestampToMMMddyyyy(new Timestamp(JstTypeConversionUtilHelperTest.MILLISECONDS)).isPresent());
	}

	@Test
	public void testConvertTimestampToMMMddyyyy_failure() {

		assertFalse(JstTypeConversionUtilHelper.instantiateCalendar(null, null).isPresent());
	}
}
