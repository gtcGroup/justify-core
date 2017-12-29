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

package com.gtcgroup.justify.core.exception.internal;

import java.util.Optional;

import com.gtcgroup.justify.core.JstSystemPropertyConstant;
import com.gtcgroup.justify.core.exception.JstBaseSelfLoggingRuntimeException;
import com.gtcgroup.justify.core.helper.internal.SystemOutLoggingUtilHelper;

public enum SelfLoggingUtilHelper {

    INSTANCE;

    /**
     * @return boolean
     */
    public static boolean containsCausalException(
            final JstBaseSelfLoggingRuntimeException selfLoggingRuntimeException) {

        return retrieveCausalException(selfLoggingRuntimeException).isPresent();
    }

    /**
     * @return {@link Optional}
     */
    private static boolean containsCausalSelfLoggingException(
            final JstBaseSelfLoggingRuntimeException selfLoggingRuntimeException) {

        final JstBaseSelfLoggingRuntimeException currentThrowable = selfLoggingRuntimeException;

        while (null != currentThrowable) {

            final Optional<Throwable> newThrowable = retrieveCausalException(currentThrowable);

            if (newThrowable.isPresent() && newThrowable.get() instanceof JstBaseSelfLoggingRuntimeException) {

                return true;
            }
        }
        return false;
    }

    public static void logThrowable(final JstBaseSelfLoggingRuntimeException selfLoggingRuntimeException) {

        if (null == System.getProperty(JstSystemPropertyConstant.JUNIT_TEST_RUNTIME)
                && !containsCausalException(selfLoggingRuntimeException)) {

            // final Object[] parameters = new Object[] { getUserPrincipalName(),
            // selfLoggingRuntimeException.getExceptionPO().getClass(),
            // selfLoggingRuntimeException.getLogReferenceCode(),
            // selfLoggingRuntimeException.getClass().getName() };
            // final String msg =
            // MessageFormat.format(SelfLoggingUtilHelper.EXCEPTION_LOG_MSG, parameters);

            SystemOutLoggingUtilHelper.logException(
                    selfLoggingRuntimeException.getExceptionPO().getExceptionClassName().get(),
                    selfLoggingRuntimeException.getExceptionPO().getExceptionMethodName().get(),
                    selfLoggingRuntimeException.getExceptionPO().getMessage());
        }
    }

    /**
     * @return {@link Optional}
     */
    private static Optional<Throwable> retrieveCausalException(
            final JstBaseSelfLoggingRuntimeException selfLoggingRuntimeException) {

        return Optional.ofNullable(selfLoggingRuntimeException.getCause());
    }
}