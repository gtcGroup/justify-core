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

package com.gtcgroup.justify.core.test.extension;

import java.util.List;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.opentest4j.AssertionFailedError;
import org.opentest4j.MultipleFailuresError;

import com.gtcgroup.justify.core.JstConstant;
import com.gtcgroup.justify.core.test.helper.internal.LogTestConsoleUtilHelper;

/**
 * This {@link Extension} class supports logging to the console.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v.8.5
 */
class ConfigureTestLogToConsoleExtension extends JstBaseExtension implements BeforeAllCallback,
		BeforeTestExecutionCallback, AfterTestExecutionCallback, TestExecutionExceptionHandler {

	static {
		System.setProperty(JstConstant.JUNIT_TEST_RUNTIME, "true");
		System.setProperty(JstConstant.JUSTIFY_VERSION_KEY, JstConstant.JUSTIFY_VERSION_VALUE);
	}

	protected boolean verbose;

	@Override
	public void afterTestExecution(final ExtensionContext extensionContext) throws Exception {

		LogTestConsoleUtilHelper.logMethodDetailsToTestConsole(extensionContext.getUniqueId());
	}

	@Override
	public void beforeAll(final ExtensionContext extensionContext) throws Exception {

		initializePropertiesFromAnnotation(extensionContext);
	}

	@Override
	public void beforeTestExecution(final ExtensionContext extensionContext) throws Exception {

		if (LogTestConsoleUtilHelper.isFirstTimeLoggingTheTestHeader()) {

			LogTestConsoleUtilHelper.logJustifyHeaderToTestConsole();
		}

		LogTestConsoleUtilHelper.logClassBeginToTestConsole(extensionContext, this.verbose);
	}

	@Override
	public void handleTestExecutionException(final ExtensionContext extensionContext, final Throwable throwable)
			throws Throwable {

		if (throwable instanceof AssertionFailedError) {

			LogTestConsoleUtilHelper.setTestMethodStatus(extensionContext, LogTestConsoleUtilHelper.STATUS_FAILURE);
			LogTestConsoleUtilHelper.logRedToConsole("\t\t" + throwable.getMessage());

		} else if (throwable instanceof MultipleFailuresError) {

			final MultipleFailuresError multipleFailuresError = (MultipleFailuresError) throwable;

			LogTestConsoleUtilHelper.setTestMethodStatus(extensionContext, LogTestConsoleUtilHelper.STATUS_FAILURE);
			final List<Throwable> throwableList = multipleFailuresError.getFailures();
			for (final Throwable throwableSingle : throwableList) {

				LogTestConsoleUtilHelper.logRedToConsole("\t\t" + throwableSingle.getMessage());
			}
		}

		else {

			final StringBuilder message = LogTestConsoleUtilHelper.getTestMethodMessage(extensionContext);
			LogTestConsoleUtilHelper.setTestMethodStatus(extensionContext, LogTestConsoleUtilHelper.STATUS_ERROR);
			LogTestConsoleUtilHelper.buildUnexpectedExceptionMessage(throwable, message);
		}
		throw throwable;
	}

	@Override
	protected void initializePropertiesFromAnnotation(final ExtensionContext extensionContext) {

		final JstConfigureTestLogToConsole configureTestLogToConsole = (JstConfigureTestLogToConsole) retrieveAnnotation(
				extensionContext.getRequiredTestClass(), JstConfigureTestLogToConsole.class);

		this.verbose = configureTestLogToConsole.verbose();
	}
}
