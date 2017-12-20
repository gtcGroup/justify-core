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

package com.gtcgroup.justify.core.extension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.opentest4j.AssertionFailedError;

import com.gtcgroup.justify.core.base.JstBaseExtension;
import com.gtcgroup.justify.core.helper.JstTimer;
import com.gtcgroup.justify.core.helper.internal.ConversionUtilHelper;
import com.gtcgroup.justify.core.helper.internal.DisplayUtilHelper;

public class JstConfigureDisplayOnConsoleExtension extends JstBaseExtension
        implements BeforeTestExecutionCallback, TestExecutionExceptionHandler, AfterTestExecutionCallback {

    protected static final String MESSAGE = "-message";
    protected static final String TIMER = "-timer";
    protected static final String STATUS = "-status";
    protected static boolean firstTestWantingHeaderDisplayed = true;
    protected static boolean firstTestWantingClasspathDisplayed = true;

    protected static final Map<String, Object> mapContainingStateForTestMethod = new ConcurrentHashMap<>();

    protected static boolean determineIfVerbose(final ExtensionContext context) {

        final JstConfigureDisplayOnConsole configureDisplayOnConsole = retrieveAnnotation(context,
                JstConfigureDisplayOnConsole.class);

        if (null != configureDisplayOnConsole) {

            return configureDisplayOnConsole.verbose();
        }
        return false;
    }

    protected static String displayMethodDetails(final String uniqueId) {

        final StringBuilder message = (StringBuilder) JstConfigureDisplayOnConsoleExtension.mapContainingStateForTestMethod
                .get(uniqueId + JstConfigureDisplayOnConsoleExtension.MESSAGE);

        final String status = (String) JstConfigureDisplayOnConsoleExtension.mapContainingStateForTestMethod
                .get(uniqueId + JstConfigureDisplayOnConsoleExtension.STATUS);

        final JstTimer jstTimer = (JstTimer) JstConfigureDisplayOnConsoleExtension.mapContainingStateForTestMethod
                .get(uniqueId + JstConfigureDisplayOnConsoleExtension.TIMER);

        return DisplayUtilHelper
                .buildMethodEndMessage(message, status,
                        ConversionUtilHelper
                                .convertNanosecondToMillisecondString(jstTimer.calculateElapsedNanoSeconds()))
                .toString();
    }

    @Override
    public void afterTestExecution(final ExtensionContext context) throws Exception {

        System.out.println(displayMethodDetails(context.getUniqueId()));
    }

    @Override
    public void beforeTestExecution(final ExtensionContext context) throws Exception {

        if (JstConfigureDisplayOnConsoleExtension.firstTestWantingHeaderDisplayed) {

            DisplayUtilHelper.displayTestingHeader();
            JstConfigureDisplayOnConsoleExtension.firstTestWantingHeaderDisplayed = false;
        }

        final StringBuilder message = DisplayUtilHelper.buildMethodBeginMessage(context.getDisplayName(),
                context.getRequiredTestClass().getSimpleName());

        if (JstConfigureDisplayOnConsoleExtension.firstTestWantingClasspathDisplayed) {

            if (determineIfVerbose(context)) {
                DisplayUtilHelper.buildClasspath(message);
                JstConfigureDisplayOnConsoleExtension.firstTestWantingClasspathDisplayed = false;
            }
        }

        JstConfigureDisplayOnConsoleExtension.mapContainingStateForTestMethod
                .put(context.getUniqueId() + JstConfigureDisplayOnConsoleExtension.STATUS, "Success");
        JstConfigureDisplayOnConsoleExtension.mapContainingStateForTestMethod
                .put(context.getUniqueId() + JstConfigureDisplayOnConsoleExtension.MESSAGE, message);
        JstConfigureDisplayOnConsoleExtension.mapContainingStateForTestMethod
                .put(context.getUniqueId() + JstConfigureDisplayOnConsoleExtension.TIMER, new JstTimer());
    }

    @Override
    public void handleTestExecutionException(final ExtensionContext context, final Throwable throwable)
            throws Throwable {

        final StringBuilder message = (StringBuilder) JstConfigureDisplayOnConsoleExtension.mapContainingStateForTestMethod
                .get(context.getUniqueId() + JstConfigureDisplayOnConsoleExtension.MESSAGE);

        if (throwable instanceof AssertionFailedError) {
            JstConfigureDisplayOnConsoleExtension.mapContainingStateForTestMethod
                    .put(context.getUniqueId() + JstConfigureDisplayOnConsoleExtension.STATUS, "Error");
            DisplayUtilHelper.buildAssertionFailedMessage(throwable, message);

        } else {
            JstConfigureDisplayOnConsoleExtension.mapContainingStateForTestMethod
                    .put(context.getUniqueId() + JstConfigureDisplayOnConsoleExtension.STATUS, "Failure");
            DisplayUtilHelper.buildUnexpectedExceptionMessage(throwable, message);
        }
        throw throwable;
    }
}
