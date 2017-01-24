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

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import com.gtcgroup.justify.core.exception.internal.AfterTestMethodRuleException;
import com.gtcgroup.justify.core.exception.internal.BeforeTestMethodRuleException;
import com.gtcgroup.justify.core.helper.internal.CodingConventionUtilHelper;
import com.gtcgroup.justify.core.helper.internal.RuleChainCacheHelper;
import com.gtcgroup.justify.core.rulechain.JstRuleChain;

/**
 * This {@link Rule} base class works in harmony with the {@link JstRuleChain}.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2016 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public abstract class JstBaseRule implements TestRule {

	/** Suffix For All Rules */
	public static final String RULE_SUFFIX = "Rule";

	/**
	 * Constructor
	 */
	public JstBaseRule() {
		super();

		CodingConventionUtilHelper.checkSuffixInClassName(this.getClass(), JstBaseRule.RULE_SUFFIX);
	}

	/**
	 * This Template Method ensures execution of logic after a test class
	 * {@link After} method.
	 *
	 * @throws Throwable
	 */
	public abstract void afterTM() throws Throwable;

	/**
	 * @see TestRule#apply(org.junit.runners.model.Statement,
	 *      org.junit.runner.Description)
	 */
	@Override
	public Statement apply(final Statement baseStatement, final Description description) {
		return createStatement(baseStatement, description);
	}

	/**
	 * This Template Method ensures execution of logic before a test class
	 * {@link Before} method.
	 *
	 * @throws Throwable
	 */
	public abstract void beforeTM() throws Throwable;

	private Statement createStatement(final Statement baseStatement, final Description description) {

		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				try {
					beforeTM();
				} catch (final Exception exceptionErrorDuringBefore) {

					final BeforeTestMethodRuleException beforeTestMethodRuleException = new BeforeTestMethodRuleException(
							description.getClassName(), exceptionErrorDuringBefore);

					RuleChainCacheHelper.getRuleChainHelper()
							.setExceptionErrorDuringBefore(beforeTestMethodRuleException);
					throw beforeTestMethodRuleException;
				}
				try {

					baseStatement.evaluate();

				} catch (final AssertionError assertionFailure) {

					RuleChainCacheHelper.getRuleChainHelper().setAssertionFailure(assertionFailure);
					throw assertionFailure;

				} catch (final Exception exceptionErrorDuringTestMethod) {

					RuleChainCacheHelper.getRuleChainHelper()
							.setExceptionErrorDuringTestMethod(exceptionErrorDuringTestMethod);
					throw exceptionErrorDuringTestMethod;

				} finally {

					try {
						afterTM();

					} catch (final Throwable exceptionErrorDuringAfter) {

						final AfterTestMethodRuleException afterTestMethodRuleException = new AfterTestMethodRuleException(
								description.getClassName(), exceptionErrorDuringAfter);
						RuleChainCacheHelper.getRuleChainHelper()
								.setExceptionErrorDuringAfter(afterTestMethodRuleException);
					}

					if (null != RuleChainCacheHelper.getRuleChainHelper().getExceptionErrorDuringAfter()) {
						throw RuleChainCacheHelper.getRuleChainHelper().getExceptionErrorDuringAfter();
					}
				}
			}
		};
	}
}