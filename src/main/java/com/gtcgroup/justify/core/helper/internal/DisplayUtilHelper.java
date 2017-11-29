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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * This Util Helper class provides support for console display.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */

public enum DisplayUtilHelper {

    INSTANCE;

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
    public static void buildClasspath(final StringBuilder message) {

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
    public static StringBuilder buildMethodEndMessage(final StringBuilder message, final String status,
            final String milliseconds) {

        message.append("\n\t***  End:  [");
        message.append(status);
        message.append("] ");
        message.append(milliseconds);
        message.append(" ms ***");
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

    /**
     * This method is for console display.
     */
    public static void displayTestingHeader() {

        final String message = "\t* JUnit Strategy for Testing [JUST] / Justify v8.5 *";

        final StringBuilder border = ConversionUtilHelper.convertMessageLengthToBorder(message);

        System.out.print(border.toString() + message + border.toString());
    }
}
