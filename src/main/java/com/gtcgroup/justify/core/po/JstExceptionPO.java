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
package com.gtcgroup.justify.core.po;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.gtcgroup.justify.core.JstConstant;
import com.gtcgroup.justify.core.base.JstBasePO;

/**
 * This Parameter Object class supports {@link Exception} processing.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since 8.5
 */
public class JstExceptionPO extends JstBasePO implements Serializable {

	public static final String THE_MESSAGE_IS_NULL = "The message is null.";

	private static final long serialVersionUID = 1L;

	private static final String DATE_TIME_FORMAT = "MMM dd, yyyy hh:mm:ss a";

	/**
	 * This static method initializes an instance of the class.
	 *
	 * @return {@link JstExceptionPO}
	 */
	public static JstExceptionPO withMessage(final String message) {

		return new JstExceptionPO(message);
	}

	private final String message;
	private final UUID logReferenceCode = UUID.randomUUID();
	private final String logTimeStamp;

	private String exceptionClassName;
	private String exceptionMethodName;
	private String userId = JstConstant.DEFAULT_USER_ID;
	private boolean suppressLogging = false;

	/**
	 * Constructor
	 */
	protected JstExceptionPO(final String message) {

		super();
		if (null == message) {
			this.message = JstExceptionPO.THE_MESSAGE_IS_NULL;
		} else {
			this.message = message;
		}

		final SimpleDateFormat formatter = new SimpleDateFormat(JstExceptionPO.DATE_TIME_FORMAT);
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
	 * @return boolean
	 */
	public boolean getSuppressLogging() {

		return this.suppressLogging;
	}

	/**
	 * @return {@link Optional}
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * @return {@link JstExceptionPO}
	 */
	public JstExceptionPO withExceptionClassName(final String exceptionClassName) {

		this.exceptionClassName = exceptionClassName;
		return this;
	}

	/**
	 * @return {@link JstExceptionPO}
	 */
	public JstExceptionPO withExceptionMethodName(final String exceptionMethodName) {

		this.exceptionMethodName = exceptionMethodName;
		return this;
	}

	/**
	 * @return {@link JstExceptionPO}
	 */
	public JstExceptionPO withSuppressLogging(final boolean suppressLogging) {

		this.suppressLogging = suppressLogging;
		return this;
	}

	/**
	 * @return {@link JstExceptionPO}
	 */
	public JstExceptionPO withUserId(final String userId) {

		this.userId = userId;
		return this;
	}
}
