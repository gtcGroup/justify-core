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
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.opentest4j.AssertionFailedError;
import org.opentest4j.MultipleFailuresError;

import com.gtcgroup.justify.core.JstConstant;
import com.gtcgroup.justify.core.test.base.JstBaseExtension;
import com.gtcgroup.justify.core.test.helper.internal.LogTestConsoleUtilHelper;

public class JstConfigureTestLogToConsoleExtension extends JstBaseExtension
		implements BeforeTestExecutionCallback, AfterTestExecutionCallback, TestExecutionExceptionHandler {

	static {
		System.setProperty(JstConstant.JUNIT_TEST_RUNTIME, "true");
		System.setProperty(JstConstant.JUSTIFY_VERSION_KEY, JstConstant.JUSTIFY_VERSION_VALUE);
	}

	@Override
	public void afterTestExecution(final ExtensionContext extensionContext) throws Exception {

		LogTestConsoleUtilHelper.logMethodDetailsToTestConsole(extensionContext.getUniqueId());
	}

	@Override
	public void beforeTestExecution(final ExtensionContext extensionContext) throws Exception {

		if (LogTestConsoleUtilHelper.isFirstTimeLoggingTheTestHeader()) {

			LogTestConsoleUtilHelper.logJustifyHeaderToTestConsole();
		}

		LogTestConsoleUtilHelper.logClassBeginToTestConsole(extensionContext);
	}

	@Override
	public void handleTestExecutionException(final ExtensionContext extensionContext, final Throwable throwable)
			throws Throwable {

		if (throwable instanceof AssertionFailedError) {

			LogTestConsoleUtilHelper.setTestMethodStatus(extensionContext, LogTestConsoleUtilHelper.STATUS_FAILURE);
			LogTestConsoleUtilHelper.logToConsole("\t\t" + throwable.getMessage());

		} else if (throwable instanceof MultipleFailuresError) {

			final MultipleFailuresError multipleFailuresError = (MultipleFailuresError) throwable;

			LogTestConsoleUtilHelper.setTestMethodStatus(extensionContext, LogTestConsoleUtilHelper.STATUS_FAILURE);
			final List<Throwable> throwableList = multipleFailuresError.getFailures();
			for (final Throwable throwableSingle : throwableList) {

				LogTestConsoleUtilHelper.logToConsole("\t\t" + throwableSingle.getMessage());
			}
		}

		else {

			final StringBuilder message = LogTestConsoleUtilHelper.getTestMethodMessage(extensionContext);
			LogTestConsoleUtilHelper.setTestMethodStatus(extensionContext, LogTestConsoleUtilHelper.STATUS_ERROR);
			LogTestConsoleUtilHelper.buildUnexpectedExceptionMessage(throwable, message);
		}
		throw throwable;
	}
}
