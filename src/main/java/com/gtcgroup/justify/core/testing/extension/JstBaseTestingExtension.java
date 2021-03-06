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
package com.gtcgroup.justify.core.testing.extension;

import java.lang.annotation.Annotation;

import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.gtcgroup.justify.core.JstConstant;
import com.gtcgroup.justify.core.helper.JstPatternEnabledDevelopmentUtilHelper;
import com.gtcgroup.justify.core.testing.helper.internal.LogTestConsoleUtilHelper;

/**
 * This {@link Extension} base class works in harmony with the JUnit life cycle.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public abstract class JstBaseTestingExtension {

	private static final String EXTENSION_SUFFIX = "Extension";

	protected static String userId = JstConstant.DEFAULT_USER_ID;

	public static String getUserId() {
		return userId;
	}

	protected static void handleBeforeAllException(final ExtensionContext extensionContext,
			final RuntimeException runtimeException) {

		final StringBuilder message = new StringBuilder(
				"\n\tThis following exception probably occured during annotation processing.");
		LogTestConsoleUtilHelper.setTestMethodStatus(extensionContext, LogTestConsoleUtilHelper.STATUS_FAILURE);
		LogTestConsoleUtilHelper.buildUnexpectedExceptionMessage(runtimeException, message);
		LogTestConsoleUtilHelper.logToConsole(message.toString());
		throw runtimeException;
	}

	protected static Annotation retrieveAnnotation(final Class<?> clazz,
			final Class<? extends Annotation> annotationClass) {

		return clazz.getAnnotation(annotationClass);
	}

	protected static void setUserId(final String userId) {
		JstBaseTestingExtension.userId = userId;
	}

	public JstBaseTestingExtension() {
		super();

		JstPatternEnabledDevelopmentUtilHelper.checkSuffixInClassName(this.getClass(),
				JstBaseTestingExtension.EXTENSION_SUFFIX);
	}

	protected abstract Object initializePropertiesFromAnnotation(final ExtensionContext extensionContext);

}