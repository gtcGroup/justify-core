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
package com.gtcgroup.justify.core.helper.internal;

import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import com.gtcgroup.justify.core.exception.internal.TestingConstructorRuleException;
import com.gtcgroup.justify.core.rule.internal.InnerLastAndFirstRule;
import com.gtcgroup.justify.core.rule.internal.OuterFirstAndLastRule;
import com.gtcgroup.justify.core.rulechain.JstRuleChain;
import com.gtcgroup.justify.core.si.JstRuleChainSI;
import com.gtcgroup.justify.core.si.JstUniqueForSuiteRuleSI;

/**
 * This Cache Helper supports the {@link JstRuleChain}.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2016 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public enum RuleChainCacheHelper {

	@SuppressWarnings("javadoc") INSTANCE;

	private static RuleChainBeanHelper ruleChainBeanHelper;

	/**
	 * @param <RC>
	 * @param ruleInstance
	 * @param ruleChain
	 * @return {@link TestRule}
	 */
	@SuppressWarnings("unchecked")
	public static <RC extends JstRuleChainSI> RC aroundProcessing(final TestRule ruleInstance,
			final JstRuleChainSI ruleChain) {

		if (ruleInstance instanceof RuleChain) {

			throw new TestingConstructorRuleException("More than one RuleChain class type is being used concurrently.");
		}

		if (ruleInstance instanceof JstUniqueForSuiteRuleSI) {

			if (RuleChainCacheHelper.getRuleChainHelper()
					.isRuleAlreadyInvoked((JstUniqueForSuiteRuleSI) ruleInstance)) {

				return (RC) ruleChain;
			}
		}

		RuleChainCacheHelper.getRuleChainHelper().addRuleToList(ruleInstance);

		RuleChainCacheHelper.getRuleChainHelper()
		.appendRuleDisplayedInMethodFooter(" [" + ruleInstance.getClass().getSimpleName() + "]");

		return (RC) ruleChain;
	}

	/**
	 * @return {@link RuleChainBeanHelper}
	 */
	public static RuleChainBeanHelper getRuleChainHelper() {

		return RuleChainCacheHelper.ruleChainBeanHelper;
	}

	/**
	 * This method instantiates a new Parameter Object.
	 */
	public static void initializeRuleChainHelper() {

		RuleChainCacheHelper.ruleChainBeanHelper = new RuleChainBeanHelper();

		RuleChainCacheHelper.getRuleChainHelper().addRuleToList(new OuterFirstAndLastRule());
	}

	/**
	 * @param base
	 * @param description
	 * @return {@link Statement}
	 */
	public static Statement processApply(final Statement base, final Description description) {

		RuleChainCacheHelper.getRuleChainHelper().setDescription(description);

		Statement statement = base;

		// The last rule added.
		RuleChainCacheHelper.getRuleChainHelper().addRuleToList(new InnerLastAndFirstRule());

		for (final TestRule testRule : RuleChainCacheHelper.getRuleChainHelper().getRuleList()) {

			statement = testRule.apply(statement, description);
		}

		return statement;
	}

	/**
	 * @param <RC>
	 * @param ruleChainClass
	 * @return {@link TestRule}
	 */
	public static <RC extends JstRuleChainSI> RC processOuterRule(final Class<RC> ruleChainClass) {

		RuleChainCacheHelper.initializeRuleChainHelper();

		@SuppressWarnings("unchecked")
		final RC ruleChain = (RC) ReflectionUtilHelper.instantiateNonPublicConstructorNoArgument(ruleChainClass);

		return ruleChain;

	}

}