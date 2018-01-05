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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.gtcgroup.justify.core.base.JstBasePO;

/**
 * This Parameter Object class supports throwing of a
 * {@link JstBaseSelfLoggingRuntimeException} concrete class in production code.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v8.5
 */
public class JstSelfLoggingExceptionPO extends JstBasePO implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String DATE_TIME_FORMAT = "MMM dd, yyyy hh:mm:ss a";

    /**
     * This static method initializes an instance of the class.
     *
     * @return {@link JstSelfLoggingExceptionPO}
     */
    public static JstSelfLoggingExceptionPO withMessage(final String message) {

        return new JstSelfLoggingExceptionPO(message);
    }

    private final String message;
    private final UUID logReferenceCode = UUID.randomUUID();
    private final String logTimeStamp;

    private String exceptionClassName;
    private String exceptionMethodName;
    private String userId;
    private boolean suppressLogging;

    /**
     * Constructor
     */
    protected JstSelfLoggingExceptionPO(final String message) {

        super();
        if (null == message) {
            this.message = "The message is null.";
        } else {
            this.message = message;
        }

        final SimpleDateFormat formatter = new SimpleDateFormat(JstSelfLoggingExceptionPO.DATE_TIME_FORMAT);
        final Date dateValue = new Date();
        this.logTimeStamp = formatter.format(dateValue);

        return;
    }

    /**
     * @return {@link Optional}
     */
    public Optional<String> getExceptionClassName() {
        return Optional.ofNullable(this.exceptionClassName);
    }

    /**
     * @return {@link Optional}
     */
    public Optional<String> getExceptionMethodName() {

        return Optional.ofNullable(this.exceptionMethodName);
    }

    /**
     * @return {@link UUID}
     */
    public UUID getLogReferenceCode() {
        return this.logReferenceCode;
    }

    /**
     * @return {@link String}
     */
    public String getLogTimeStamp() {
        return this.logTimeStamp;
    }

    /**
     * @return {@link String}
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @return {@link Optional}
     */
    public Optional<String> getUserId() {
        return Optional.ofNullable(this.userId);
    }

    /**
     * @return boolean
     */
    public boolean isSuppressLogging() {

        return this.suppressLogging;
    }

    /**
     * @return {@link JstSelfLoggingExceptionPO}
     */
    public JstSelfLoggingExceptionPO withClassName(final String className) {

        this.exceptionClassName = className;
        return this;
    }

    /**
     * @return {@link JstSelfLoggingExceptionPO}
     */
    public JstSelfLoggingExceptionPO withMethodName(final String methodName) {

        this.exceptionMethodName = methodName;
        return this;
    }

    /**
     * @return {@link JstSelfLoggingExceptionPO}
     */
    public JstSelfLoggingExceptionPO withUserId(final String userId) {

        this.userId = userId;
        return this;
    }
}