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

import org.junit.Rule;
import org.junit.rules.TestRule;

import com.gtcgroup.justify.core.base.JstBaseRule;

/**
 * This {@link Rule} class initializes a public user id for the duration of the
 * method and then reinstates the original user id value.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2016 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public class JstConfigureUserIdRule extends JstBaseRule {

	/** Default user id. */
	protected static final String DEFAULT_USER_ID = "$userId";

	/** Current userId. */
	public static String userID = JstConfigureUserIdRule.DEFAULT_USER_ID;

	/**
	 * @return {@link TestRule}
	 */
	@SuppressWarnings("unchecked")
	protected static <RULE extends TestRule, SUBCLASS extends JstConfigureUserIdRule> RULE decorateSubClassInstance(
			final SUBCLASS subClassInstance) {

		return (RULE) subClassInstance;
	}

	/**
	 * @return {@link TestRule}
	 */
	public static <RULE extends TestRule> RULE withUserId() {

		return withUserId(JstConfigureUserIdRule.DEFAULT_USER_ID);
	}

	/**
	 * @return {@link TestRule}
	 */
	@SuppressWarnings("unchecked")
	public static <RULE extends TestRule> RULE withUserId(final String userId) {

		return (RULE) new JstConfigureUserIdRule(userId);
	}

	/**
	 * Constructor - protected
	 */
	protected JstConfigureUserIdRule(final String userId) {

		JstConfigureUserIdRule.userID = userId;
	}

	/**
	 * @see JstBaseRule#afterTM()
	 */
	@Override
	public void afterTM() {

		JstConfigureUserIdRule.userID = JstConfigureUserIdRule.DEFAULT_USER_ID;

		return;
	}

	/**
	 * @see JstBaseRule#beforeTM()
	 */
	@Override
	public void beforeTM() {

		return;
	}
}