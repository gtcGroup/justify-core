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
package com.gtcgroup.justify.core.test.exception.internal;

import com.gtcgroup.justify.core.helper.JstCodingConventionUtilHelper;

/**
 * This Exception base class supports readability.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v.6.0
 */
public class JustifyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String SUFFIX = "Exception";

    protected static String formulateExceptionMessage(final Throwable exception, final StringBuilder message) {

        if (null == exception.getCause()) {

            message.append("\n\n\tCausal exception: " + exception.getClass().getName() + "\n\tA causal message: "
                    + exception.getMessage() + "\n");

            return message.toString();
        }

        message.append("\n\n\tCausal exception: " + exception.getClass().getName() + "\n\tA causal message: "
                + exception.getMessage() + "\n");

        return formulateExceptionMessage(exception.getCause(), message);

    }

    protected static String init(final Throwable exception) {

        final StringBuilder logMessage = new StringBuilder();
        logMessage.append("\n\n\tA [");
        logMessage.append(JustifyException.class.getSimpleName());
        logMessage.append("] is intended to indicate a problem with the test machinery...");
        logMessage.append("\n\t\t\t\t\t... not the code under test.");
        logMessage.append("\n\n\tException Message: ");
        logMessage.append(exception.getMessage());
        logMessage.append("\n");
        logMessage.toString();

        return formulateExceptionMessage(exception, logMessage);
    }

    /**
     * Constructor
     */
    public JustifyException(final String exceptionMessage) {

        super(exceptionMessage);
        JstCodingConventionUtilHelper.checkSuffixInClassName(this.getClass(), JustifyException.SUFFIX);
    }

    /**
     * Constructor
     */
    public JustifyException(final Throwable exception) {

        super(init(exception));
        JstCodingConventionUtilHelper.checkSuffixInClassName(this.getClass(), JustifyException.SUFFIX);
    }

}