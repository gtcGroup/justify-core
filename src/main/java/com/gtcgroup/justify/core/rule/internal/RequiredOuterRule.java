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

import com.gtcgroup.justify.core.base.JstBaseTestingRule;
import com.gtcgroup.justify.core.exception.internal.AfterTestMethodRuleException;
import com.gtcgroup.justify.core.helper.internal.DisplayRuleMessagesUtilHelper;
import com.gtcgroup.justify.core.helper.internal.RuleChainCacheHelper;

/**
 * This {@link Rule} class demarks the completion of a test method.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2016 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public class RequiredOuterRule extends BaseTimerRule {

	private static void displayAssertionFailureFoooter() {

		System.err.println("\t\tFailure Message--> "
				+ RuleChainCacheHelper.getRuleChainHelper().getAssertionFailure().getMessage());

		DisplayRuleMessagesUtilHelper.displayMethodFooter("Assertion Failure", 0);

		return;
	}

	private static void displayErrorFooterAfter() {

		DisplayRuleMessagesUtilHelper.displayException(
				RuleChainCacheHelper.getRuleChainHelper().getExceptionErrorDuringAfter(), "RULE EXCEPTION");

		DisplayRuleMessagesUtilHelper.displayMethodFooter("Rule Error - Occurred AFTER the Test Method", 0);

		return;
	}

	private static void displayErrorFooterBefore() {

		DisplayRuleMessagesUtilHelper.displayException(
				RuleChainCacheHelper.getRuleChainHelper().getExceptionErrorDuringBefore(), "RULE EXCEPTION");

		DisplayRuleMessagesUtilHelper.displayMethodFooter("Rule Error - Occurred BEFORE the Test Method", 0);

		return;
	}

	private static void displayErrorTestMethod() {

		DisplayRuleMessagesUtilHelper.displayException(
				RuleChainCacheHelper.getRuleChainHelper().getExceptionErrorDuringTestMethod(), "TEST METHOD EXCEPTION");

		DisplayRuleMessagesUtilHelper.displayMethodFooter("Error", 0);

		return;
	}

	/**
	 * @throws Throwable
	 * @see JstBaseTestingRule#afterTM()
	 */
	@Override
	public void afterTM() throws Throwable {

		if (null != RuleChainCacheHelper.getRuleChainHelper().getExceptionErrorDuringBefore()) {

			DisplayRuleMessagesUtilHelper.displayTestingHeader();
			DisplayRuleMessagesUtilHelper
					.displayMethodHeader(RuleChainCacheHelper.getRuleChainHelper().getDescription());

			displayErrorFooterBefore();
			return;
		}

		if (null != RuleChainCacheHelper.getRuleChainHelper().getExceptionErrorDuringTestMethod()) {

			if (!(RuleChainCacheHelper.getRuleChainHelper()
					.getExceptionErrorDuringTestMethod() instanceof AfterTestMethodRuleException)) {
				displayErrorTestMethod();
				return;
			}
		}

		if (null != RuleChainCacheHelper.getRuleChainHelper().getAssertionFailure()) {

			displayAssertionFailureFoooter();
			return;
		}

		if (null != RuleChainCacheHelper.getRuleChainHelper().getExceptionErrorDuringAfter()) {

			displayErrorFooterAfter();
			throw RuleChainCacheHelper.getRuleChainHelper().getExceptionErrorDuringAfter();
		}

		DisplayRuleMessagesUtilHelper.displaySucceedFooter(BaseTimerRule.testMethodTimer);

		return;
	}

	/**
	 * @see JstBaseTestingRule#beforeTM()
	 */
	@Override
	public void beforeTM() {

		return;
	}
}
