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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.extension.ExtensionContext;

import com.gtcgroup.justify.core.JstConstant;
import com.gtcgroup.justify.core.JstDurationTimer;
import com.gtcgroup.justify.core.helper.JstTypeConversionUtilHelper;
import com.gtcgroup.justify.core.test.extension.JstConfigureTestLogToConsole;

/**
 * This Util Helper class provides support for console test logging.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */

public enum LogTestConsoleUtilHelper {

    INSTANCE;

    public static final String MESSAGE = "-message";
    public static final String TIMER = "-timer";
    public static final String STATUS = "-status";
    private static boolean firstTimeLoggingHeaderToTestConsole = true;
    private static boolean firstTimeLoggingClasspathToTestConsole = true;

    private static final Map<String, Object> executionStatusForTestMethod = new ConcurrentHashMap<>();

    /**
     * This is a Collecting Parameter method.
     */
    public static void buildAssertionFailedMessage(final Throwable throwable, final StringBuilder message) {
        message.append("\n\t\t");
        message.append(throwable.getMessage());
    }

    /**
     * This is a Collecting Parameter method.
     */
    public static void buildClassPath(final StringBuilder message) {

        message.append("\n Verbose: true");

        message.append("\n\t  Root Directory - ");
        message.append(System.getProperty("user.dir"));
        message.append("\n\t  Java Home      - ");
        message.append(System.getProperty("java.home"));
        message.append("\n\t  Java Version   - ");
        message.append(System.getProperty("java.version"));

        message.append("\n\t  Java Classpath -\n");

        final String[] tokens = System.getProperty("java.class.path").replace(":/", "~&~").replace(":\\", "~#~")
                .replace(":", ";").replace("~#~", ":\\").replace("~&~", ":/").split(";");

        Arrays.sort(tokens);
        for (final String token : tokens) {
            message.append("\t\t" + token + "\n");
        }
    }

    /**
     * This is a Collecting Parameter method.
     */
    public static void buildClassPath(final StringBuilder message, final ExtensionContext extensionContext) {

        if (LogTestConsoleUtilHelper.isFirstTimeLoggingTheTestClasspath()
                && LogTestConsoleUtilHelper.isVerbose(extensionContext)) {

            buildClassPath(message);
        }
    }

    public static StringBuilder buildMethodBeginMessage(final String methodName, final String className) {
        final StringBuilder message = new StringBuilder();
        message.append("\n\t*** Begin: ");
        message.append(methodName);
        message.append("/");
        message.append(className);
        message.append(" ***");
        return message;
    }

    /**
     * This is a Collecting Parameter method.
     */
    public static void buildUnexpectedExceptionMessage(final Throwable throwable, final StringBuilder message) {

        message.append("\n\n[");
        message.append("UNEXPECTED EXCEPTION");
        message.append("]");
        message.append("\n");
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        message.append(stringWriter.toString());
    }

    public static Map<String, Object> getExecutionStatusForTestMethod() {
        return LogTestConsoleUtilHelper.executionStatusForTestMethod;
    }

    public static boolean isFirstTimeLoggingTheTestClasspath() {
        final boolean firstTest = LogTestConsoleUtilHelper.firstTimeLoggingClasspathToTestConsole;
        LogTestConsoleUtilHelper.firstTimeLoggingClasspathToTestConsole = false;
        return firstTest;
    }

    public static boolean isFirstTimeLoggingTheTestHeader() {
        final boolean firstTest = LogTestConsoleUtilHelper.firstTimeLoggingHeaderToTestConsole;
        LogTestConsoleUtilHelper.firstTimeLoggingHeaderToTestConsole = false;
        return firstTest;
    }

    public static boolean isVerbose(final ExtensionContext extensionContext) {

        @SuppressWarnings("unchecked")
        final Optional<JstConfigureTestLogToConsole> annotation = (Optional<JstConfigureTestLogToConsole>) AnnotationUtilHelper
                .retrieveAnnotation(extensionContext, JstConfigureTestLogToConsole.class);

        return annotation.get().verbose();
    }

    public static void logHeaderToTestConsole() {

        final String message = "\t* Justify v" + System.getProperty(JstConstant.JUSTIFY_VERSION) + " *";

        final Optional<StringBuilder> border = JstTypeConversionUtilHelper.convertMessageLengthToBorder(message);

        logToConsole(border.get().toString() + message + border.get().toString());
    }

    public static void logMethodDetailsToTestConsole(final String uniqueId) {

        final StringBuilder message = (StringBuilder) LogTestConsoleUtilHelper.executionStatusForTestMethod
                .get(uniqueId + LogTestConsoleUtilHelper.MESSAGE);

        final String status = (String) LogTestConsoleUtilHelper.executionStatusForTestMethod
                .get(uniqueId + LogTestConsoleUtilHelper.STATUS);

        final JstDurationTimer durationTimer = (JstDurationTimer) LogTestConsoleUtilHelper.executionStatusForTestMethod
                .get(uniqueId + LogTestConsoleUtilHelper.TIMER);

        message.append("\n\t***  End:  [");
        message.append(status);
        message.append("] ");
        message.append(JstTypeConversionUtilHelper
                .convertNanosecondToMillisecondString(durationTimer.calculateDurationInNanoSeconds()).get());
        message.append(" ms ***");
        logToConsole(message.toString());
    }

    public static void logToConsole(final String message) {
        System.out.println(message);
    }

    public static void setFirstTimeLoggingClasspathToTestConsole(final boolean firstTimeLoggingClasspathToTestConsole) {
        LogTestConsoleUtilHelper.firstTimeLoggingClasspathToTestConsole = firstTimeLoggingClasspathToTestConsole;
    }
}
