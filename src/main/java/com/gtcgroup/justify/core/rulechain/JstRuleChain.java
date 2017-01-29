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
package com.gtcgroup.justify.core.rulechain;

import java.util.List;

import org.junit.Rule;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import com.gtcgroup.justify.core.helper.internal.RuleChainCacheHelper;
import com.gtcgroup.justify.core.rule.internal.BindJavaUtilLoggingToLog4j2Rule;
import com.gtcgroup.justify.core.si.JstRuleChainSI;

/**
 * This {@link RuleChain} class manages the {@link Rule} life cycle.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public class JstRuleChain implements JstRuleChainSI {

	@SuppressWarnings("javadoc")
	public static boolean displayVerboseStartupLogging;

	/**
	 * @return {@link JstRuleChainSI}
	 */
	@SuppressWarnings("unchecked")
	public static <RC extends JstRuleChainSI> RC outerRule() {

		return (RC) outerRule(false, JstRuleChain.class);
	}

	/**
	 * @return {@link JstRuleChainSI}
	 */
	@SuppressWarnings("unchecked")
	public static <RC extends JstRuleChainSI> RC outerRule(final boolean displayVerboseStartupLogging) {

		return (RC) outerRule(displayVerboseStartupLogging, JstRuleChain.class);
	}

	/**
	 * @return {@link JstRuleChainSI}
	 */
	public static <RC extends JstRuleChainSI> RC outerRule(final boolean displayVerboseStartupLogging,
			final Class<RC> ruleChainClass) {

		JstRuleChain.displayVerboseStartupLogging = displayVerboseStartupLogging;

		return RuleChainCacheHelper.processOuterRule(ruleChainClass);
	}

	/**
	 * Constructor
	 */
	protected JstRuleChain() {

		super();
		return;
	}

	/**
	 * @see TestRule#apply(Statement, Description)
	 */
	@Override
	public Statement apply(final Statement base, final Description description) {

		return RuleChainCacheHelper.processApply(base, description);
	}

	/**
	 * @see JstRuleChainSI#around(org.junit.rules.TestRule)
	 */
	@Override
	public <RC extends JstRuleChainSI> RC around(final TestRule ruleInstance) {

		return RuleChainCacheHelper.aroundProcessing(ruleInstance, this);
	}

	/**
	 * @see JstRuleChainSI#aroundList(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <RC extends JstRuleChainSI, RULE extends TestRule> RC aroundList(final List<RULE> testRuleList) {

		for (final TestRule testRule : testRuleList) {
			around(testRule);
		}
		return (RC) this;
	}

	/**
	 * @see JstRuleChainSI#bindJulToLog4j(boolean)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <RC extends JstRuleChainSI> RC bindJulToLog4j(final boolean bind) {

		if (bind) {

			return RuleChainCacheHelper.aroundProcessing(new BindJavaUtilLoggingToLog4j2Rule(), this);
		}
		return (RC) this;
	}
}