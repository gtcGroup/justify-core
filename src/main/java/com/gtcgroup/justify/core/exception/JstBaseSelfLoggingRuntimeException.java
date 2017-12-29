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
package com.gtcgroup.justify.core.exception;

import com.gtcgroup.justify.core.exception.internal.SelfLoggingUtilHelper;
import com.gtcgroup.justify.core.test.extension.JstConfigureTestLogToConsole;

/**
 * This {@link RuntimeException} base class defaults to logging production code
 * exceptions while suppressing logging to the console when running a
 * JstSystemPropertyConstant JUnit test with the
 * {@link JstConfigureTestLogToConsole} annotation. In addition, the exception
 * is logged once and only once. *
 * <p>
 * There are two ways to suppress logging. First, by explicitly indicating
 * suppression with the {@link JstSelfLoggingExceptionPO}. Secondly, by using
 * the {@link JstConfigureTestLogToConsole} annotation while running a JUnit
 * test.
 * </p>
 * <p>
 * <h2>Sample:</h2>
 *
 * <pre>
 * <code>
 *  throw new {@link JstBaseSelfLoggingRuntimeException}(new {@link JstSelfLoggingExceptionPO}.
 *       CLASS_NAME, METHOD_NAME).build(),
 *       'Test exception message.');
 * </code>
 * </pre>
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v8.5
 */
public abstract class JstBaseSelfLoggingRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final JstSelfLoggingExceptionPO exceptionPO;

    public JstBaseSelfLoggingRuntimeException(final JstSelfLoggingExceptionPO exceptionPO) {

        super(exceptionPO.getMessage());
        this.exceptionPO = exceptionPO;
        SelfLoggingUtilHelper.logThrowable(this);
    }

    public JstBaseSelfLoggingRuntimeException(final JstSelfLoggingExceptionPO exceptionPO, final String message,
            final Throwable cause) {

        super(message, cause);
        this.exceptionPO = exceptionPO;
        SelfLoggingUtilHelper.logThrowable(this);
    }

    public JstSelfLoggingExceptionPO getExceptionPO() {
        return this.exceptionPO;
    }
}
