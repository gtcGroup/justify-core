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
package com.gtcgroup.justify.core.intentional.error;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.po.JstExceptionPO;
import com.gtcgroup.justify.core.test.exception.internal.JustifyException;
import com.gtcgroup.justify.core.test.extension.JstConfigureTestLogToConsole;

/**
 * This class is for testing.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v8.5
 */
@SuppressWarnings("static-method")
@JstConfigureTestLogToConsole()
@Tag(value = "intentional")
public class IntentionalErrorTest {

	@Test
	public void testIntentionalNestedError() {

		throw new JustifyException(JstExceptionPO.withMessage("A Justify Exception.")
				.withExceptionClassName(IntentionalErrorTest.class.getSimpleName())
				.withExceptionMethodName("testIntentionalNestedError").withSuppressLogging(false).withUserId("mToll4"),
				new NullPointerException("This is on purpose."));
	}

	@Test
	public void testIntentionalNestedExceptionUnexpected() {

		try {
			throw new NullPointerException("Root cause exception.");
		} catch (final Exception e) {

			throw new RuntimeException("Outer unexpected exception.", e);
		}

	}

	@Test
	public void testIntentionalUnexpectedException() {

		throw new RuntimeException("Unexpected Runtime Exception");
	}

}
