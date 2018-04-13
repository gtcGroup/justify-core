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

package com.gtcgroup.justify.core.helper.internal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import com.gtcgroup.justify.core.po.JstExceptionPO;
import com.gtcgroup.justify.core.test.helper.internal.LogTestConsoleUtilHelper;

/**
 * This Util Helper class provides a default implementation for internal
 * {@link System}.out logging.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public enum SystemOutLoggingUtilHelper {

	INSTANCE;

	public static final String SEVERE = "SEVERE";
	public static final String USER_ID = "testId";

	public static void logException(final JstExceptionPO exceptionPO) {

		if (!exceptionPO.getSuppressLogging()) {
			final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss,SSS");
			final Date date = new Date();
			final String dateResult = formatter.format(date);

			final StringBuilder logStatement = new StringBuilder();

			logStatement.append("[JST SEVERE] ");
			logStatement.append(dateResult);
			logStatement.append(" [");
			logStatement.append(Thread.currentThread().getName());
			logStatement.append("] ");
			logStatement.append(SystemOutLoggingUtilHelper.getUserId());
			logStatement.append(": ");

			final Optional<String> exceptionClassName = exceptionPO.getExceptionClassName();
			logStatement.append(exceptionClassName.orElse("UndisclosedClassName"));

			logStatement.append(".");

			final Optional<String> exceptionMethodName = exceptionPO.getExceptionMethodName();
			logStatement.append(exceptionMethodName.orElse("undisclosedMethodName"));

			logStatement.append("()\n\t");
			logStatement.append(exceptionPO.getMessage());

			LogTestConsoleUtilHelper.logRedToConsole(logStatement.toString());
		}
	}

	private static String getUserId() {

		return SystemOutLoggingUtilHelper.USER_ID;
	}
}
