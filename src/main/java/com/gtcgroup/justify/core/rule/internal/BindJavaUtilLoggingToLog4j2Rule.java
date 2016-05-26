/*
 * [Licensed per the Open Source "MIT License".]
 *
 * Copyright (c) 2006 - 2016 by
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
package com.gtcgroup.justify.core.rule.internal;

import org.junit.Rule;

import com.gtcgroup.justify.core.base.JstBaseForMethodRule;
import com.gtcgroup.justify.core.pattern.palette.internal.JstBaseRule;

/**
 * This {@link Rule} class initializes the use of Log4j2 for Java Util Logging.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2016 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public class BindJavaUtilLoggingToLog4j2Rule extends JstBaseForMethodRule {

	/** Support for Java Util Logging */
	public static final String LOG_MANAGER_VALUE = "org.apache.logging.log4j.jul.LogManager";

	/** Support for Java Util Logging */
	public static final String LOGGING_MANAGER_KEY = "java.util.logging.manager";

	/**
	 * Constructor
	 */
	public BindJavaUtilLoggingToLog4j2Rule() {

		super();

		System.setProperty(BindJavaUtilLoggingToLog4j2Rule.LOGGING_MANAGER_KEY,
				BindJavaUtilLoggingToLog4j2Rule.LOG_MANAGER_VALUE);

		return;
	}

	/**
	 * @see JstBaseRule#afterTM()
	 */
	@Override
	public void afterTM() throws Throwable {

		System.clearProperty(BindJavaUtilLoggingToLog4j2Rule.LOGGING_MANAGER_KEY);

		return;
	}

	/**
	 * @see JstBaseForMethodRule#beforeTM()
	 */
	@Override
	public void beforeTM() {

		return;
	}
}