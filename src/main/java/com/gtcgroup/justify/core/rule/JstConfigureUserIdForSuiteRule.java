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

import org.junit.rules.TestRule;

import com.gtcgroup.justify.core.base.JstBaseForMethodRule;
import com.gtcgroup.justify.core.base.JstBaseForSuiteRule;
import com.gtcgroup.justify.core.si.JstUniqueForSuiteRuleSI;

/**
 * This Rule class initializes a user id for testing.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2016 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public class JstConfigureUserIdForSuiteRule extends JstBaseForSuiteRule {

	/**
	 * @param <RULE>
	 * @return {@link TestRule}
	 */
	public static <RULE extends TestRule> RULE withUserId() {

		return withUserId(JstConfigureUserIdForMethodRule.DEFAULT_USER_ID);
	}

	/**
	 * @param <RULE>
	 * @param userId
	 * @return {@link TestRule}
	 */
	@SuppressWarnings("unchecked")
	public static <RULE extends TestRule> RULE withUserId(final String userId) {

		return (RULE) new JstConfigureUserIdForSuiteRule(userId);
	}

	/**
	 * Constructor - protected
	 *
	 * @param userId
	 */
	protected JstConfigureUserIdForSuiteRule(final String userId) {

		super();
		JstConfigureUserIdForMethodRule.userID = userId;
	}

	/**
	 * @see JstBaseForMethodRule#beforeTM()
	 */
	@Override
	public void beforeTM() {

		return;
	}

	/**
	 * @see JstUniqueForSuiteRuleSI#uniqueSuiteIdentityTM()
	 */
	@Override
	public String uniqueSuiteIdentityTM() {

		return JstConfigureUserIdForMethodRule.DEFAULT_USER_ID;
	}
}