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

package com.gtcgroup.justify.core.test.extension;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.opentest4j.AssertionFailedError;

import com.gtcgroup.justify.core.JstSystemPropertyConstant;
import com.gtcgroup.justify.core.helper.JstTimer;
import com.gtcgroup.justify.core.test.base.JstBaseExtension;
import com.gtcgroup.justify.core.test.helper.internal.LogTestConsoleUtilHelper;

class JstConfigureTestLogToConsoleExtension extends JstBaseExtension
        implements BeforeTestExecutionCallback, TestExecutionExceptionHandler, AfterTestExecutionCallback {

    private static boolean jUnitTest = false;

    static {
        JstConfigureTestLogToConsoleExtension.jUnitTest = true;
        System.setProperty(JstSystemPropertyConstant.JUNIT_TEST_RUNTIME, "true");
        System.setProperty(JstSystemPropertyConstant.JUSTIFY_VERSION, "8.5.0-a01");
    }

    public static boolean isJUnitTest() {
        return JstConfigureTestLogToConsoleExtension.jUnitTest;
    }

    @Override
    public void afterTestExecution(final ExtensionContext context) throws Exception {

        LogTestConsoleUtilHelper.logMethodDetailsToTestConsole(context.getUniqueId());
    }

    @Override
    public void beforeTestExecution(final ExtensionContext context) throws Exception {

        if (LogTestConsoleUtilHelper.isFirstTimeLoggingTheTestHeader()) {

            LogTestConsoleUtilHelper.logHeaderToTestConsole();
        }

        final StringBuilder message = LogTestConsoleUtilHelper.buildMethodBeginMessage(context.getDisplayName(),
                context.getRequiredTestClass().getSimpleName());

        LogTestConsoleUtilHelper.buildClasspath(message, context);

        LogTestConsoleUtilHelper.getStatusMapForTestMethod()
                .put(context.getUniqueId() + LogTestConsoleUtilHelper.STATUS, "Success");
        LogTestConsoleUtilHelper.getStatusMapForTestMethod()
                .put(context.getUniqueId() + LogTestConsoleUtilHelper.MESSAGE, message);
        LogTestConsoleUtilHelper.getStatusMapForTestMethod().put(context.getUniqueId() + LogTestConsoleUtilHelper.TIMER,
                new JstTimer());

    }

    @Override
    public void handleTestExecutionException(final ExtensionContext context, final Throwable throwable)
            throws Throwable {

        final StringBuilder message = (StringBuilder) LogTestConsoleUtilHelper.getStatusMapForTestMethod()
                .get(context.getUniqueId() + LogTestConsoleUtilHelper.MESSAGE);

        if (throwable instanceof AssertionFailedError) {
            LogTestConsoleUtilHelper.getStatusMapForTestMethod()
                    .put(context.getUniqueId() + LogTestConsoleUtilHelper.STATUS, "Error");
            LogTestConsoleUtilHelper.buildAssertionFailedMessage(throwable, message);

        } else {
            LogTestConsoleUtilHelper.getStatusMapForTestMethod()
                    .put(context.getUniqueId() + LogTestConsoleUtilHelper.STATUS, "Failure");
            LogTestConsoleUtilHelper.buildUnexpectedExceptionMessage(throwable, message);
        }
        throw throwable;
    }
}
