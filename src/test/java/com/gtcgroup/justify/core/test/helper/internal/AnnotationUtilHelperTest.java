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
package com.gtcgroup.justify.core.test.helper.internal;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.test.exception.internal.JustifyException;
import com.gtcgroup.justify.core.test.extension.JstConfigureTestLogToConsole;
import com.gtcgroup.justify.core.test.extension.JstConfigureTestSystemProperty;

@SuppressWarnings("static-method")
@JstConfigureTestLogToConsole(verbose = false)
public class AnnotationUtilHelperTest {

	@Test
	public void testRetrieveAnnotation() {

		Assertions.assertThrows(JustifyException.class, () -> {
			AnnotationUtilHelper.retrieveAnnotation(AnnotationUtilHelperTest.class,
					JstConfigureTestSystemProperty.class);
		});
	}

	@Test
	public void testRetrieveAnnotation_happyPath() {

		AnnotationUtilHelper.retrieveAnnotation(AnnotationUtilHelperTest.class, JstConfigureTestLogToConsole.class);
	}

	@Test
	public void testRetrieveAnnotation_optional() {

		Assertions.assertThrows(JustifyException.class, () -> {
			AnnotationUtilHelper.retrieveAnnotation(Optional.of(AnnotationUtilHelperTest.class),
					JstConfigureTestSystemProperty.class);
		});
	}

	@Test
	public void testRetrieveAnnotation_optionalEmpty() {

		assertFalse(AnnotationUtilHelper.retrieveAnnotation(Optional.empty(), JstConfigureTestSystemProperty.class)
				.isPresent());
	}
}