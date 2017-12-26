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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This Util Helper class provides a default implementation used for JUnit test
 * logging.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public enum SystemOutLoggingUtilHelper {

    INSTANCE;

    public static final String CONFIG = "CONFIG";
    public static final String FINE = "FINE";
    public static final String FINER = "FINER";
    public static final String FINEST = "FINEST";
    public static final String INFO = "INFO";
    public static final String SEVERE = "SEVERE";
    public static final String USER_ID = "testId";
    public static final String WARNING = "WARNING";

    public static void configLevel(final String className, final String methodName, final String message) {

        SystemOutLoggingUtilHelper.log(className, methodName, message, SystemOutLoggingUtilHelper.CONFIG);
    }

    public static void fineLevel(final String className, final String methodName, final String message) {

        SystemOutLoggingUtilHelper.log(className, methodName, message, SystemOutLoggingUtilHelper.FINE);
    }

    public static void finerLevel(final String className, final String methodName, final String message) {

        SystemOutLoggingUtilHelper.log(className, methodName, message, SystemOutLoggingUtilHelper.FINER);
    }

    public static void finestLevel(final String className, final String methodName, final String message) {

        SystemOutLoggingUtilHelper.log(className, methodName, message, SystemOutLoggingUtilHelper.FINEST);
    }

    private static String getUserId() {

        return SystemOutLoggingUtilHelper.USER_ID;
    }

    public static void infoLevel(final String className, final String methodName, final String message) {

        SystemOutLoggingUtilHelper.log(className, methodName, message, SystemOutLoggingUtilHelper.INFO);
    }

    /**
     * This method supports all logging.
     */
    public static void log(final String className, final String methodName, final String message, final String level) {

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss,SSS");
        final Date date = new Date();
        final String dateResult = formatter.format(date);

        final StringBuilder logStatement = new StringBuilder();

        logStatement.append("[JST ");
        logStatement.append(level);
        logStatement.append("] ");
        logStatement.append(dateResult);
        logStatement.append(" [");
        logStatement.append(Thread.currentThread().getName());
        logStatement.append("] ");
        logStatement.append(SystemOutLoggingUtilHelper.getUserId());
        logStatement.append(": ");
        logStatement.append(className);
        logStatement.append(".");
        logStatement.append(methodName);
        logStatement.append("() -");
        logStatement.append(message);

        LogToConsoleUtilHelper.logToConsole(logStatement.toString());
    }

    public static void severeLevel(final String className, final String methodName, final String message) {

        SystemOutLoggingUtilHelper.log(className, methodName, message, SystemOutLoggingUtilHelper.SEVERE);
    }

    public static void warningLevel(final String className, final String methodName, final String message) {

        SystemOutLoggingUtilHelper.log(className, methodName, message, SystemOutLoggingUtilHelper.WARNING);
    }
}
