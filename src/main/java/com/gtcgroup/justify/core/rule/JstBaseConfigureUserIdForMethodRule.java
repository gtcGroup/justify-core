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
package com.gtcgroup.justify.core.rule;

import com.gtcgroup.justify.core.base.JstBaseForMethodRule;

/**
 * This Rule class initializes a user id.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2016 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public class JstBaseConfigureUserIdForMethodRule extends JstBaseForMethodRule {

	/** Current userId. */
	public static String userId = JstBaseConfigureUserIdForMethodRule.TEST_ID;

	private static final String TEST_ID = "testId";

	/**
	 * Constructor
	 *
	 * @param <RULE>
	 * @return {@link JstBaseConfigureUserIdForMethodRule}
	 */
	public static <RULE extends JstBaseConfigureUserIdForMethodRule> RULE withUserId() {

		return withUserId(JstBaseConfigureUserIdForMethodRule.TEST_ID);
	}

	/**
	 * Constructor
	 *
	 * @param <RULE>
	 * @param userId
	 * @return {@link JstBaseConfigureUserIdForMethodRule}
	 */
	@SuppressWarnings("unchecked")
	public static <RULE extends JstBaseConfigureUserIdForMethodRule> RULE withUserId(final String userId) {


		JstBaseConfigureUserIdForMethodRule.userId = userId;
		return (RULE) new JstBaseConfigureUserIdForMethodRule();
	}

	/**
	 * Constructor - protected
	 *
	 * @param userId
	 */
	protected JstBaseConfigureUserIdForMethodRule() {

		super();
	}

	/**
	 * @see JstBaseForMethodRule#afterTM()
	 */
	@Override
	public void afterTM() {

		JstBaseConfigureUserIdForMethodRule.userId = JstBaseConfigureUserIdForMethodRule.TEST_ID;
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