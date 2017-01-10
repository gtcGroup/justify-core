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
package com.gtcgroup.justify.core.base;

import com.gtcgroup.justify.core.helper.internal.CodingConventionUtilHelper;

/**
 * This Exception base class supports readability.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2016 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author
 * @since v.6.0
 */
public abstract class JstBaseTestingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String SUFFIX = "Exception";

	/**
	 * Constructor
	 *
	 * @param message
	 */
	public JstBaseTestingException(final String message) {

		super(message);
		CodingConventionUtilHelper.checkSuffixInClassName(this.getClass(), JstBaseTestingException.SUFFIX);
	}

	/**
	 * Constructor
	 */
	public JstBaseTestingException(final String ruleClass, final Throwable exception) {

		super("\n\n\tRule Class: " + ruleClass + "\n\tMessage: " + exception.getMessage() + "\n", exception);
		CodingConventionUtilHelper.checkSuffixInClassName(this.getClass(), JstBaseTestingException.SUFFIX);
	}
}
