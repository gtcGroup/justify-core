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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;

import com.gtcgroup.justify.core.base.BaseBeanHelper;
import com.gtcgroup.justify.core.rule.internal.RequiredInnerRule;
import com.gtcgroup.justify.core.rule.internal.RequiredOuterRule;

/**
 * This Bean Helper class supports {@link Rule} processing.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2016 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public class RuleChainBeanHelper extends BaseBeanHelper {

	/** Used by {@link RequiredOuterRule}. */
	private AssertionError assertionFailure;

	/**
	 * Used by {@link RequiredOuterRule} and {@link RequiredInnerRule}.
	 */
	private Description description;

	/** Used by {@link RequiredOuterRule}. */
	private Throwable exceptionErrorDuringAfter;

	/** Used by {@link RequiredOuterRule}. */
	private Throwable exceptionErrorDuringBefore;

	/** Used by {@link RequiredOuterRule}. */
	private Throwable exceptionErrorDuringTestMethod;

	private final List<TestRule> ruleList = new ArrayList<TestRule>();

	/** Used by {@link RequiredOuterRule}. */
	private final StringBuilder rulesDisplayedInMethodFooter = new StringBuilder();

	/**
	 * @param testRule
	 * @return List<TestRule>
	 */
	public RuleChainBeanHelper addRuleToList(final TestRule testRule) {
		this.ruleList.add(testRule);
		return this;
	}

	/**
	 * @param ruleDisplayedInMethodFooter
	 * @return {@link RuleChainBeanHelper}
	 */
	public RuleChainBeanHelper appendRuleDisplayedInMethodFooter(final String ruleDisplayedInMethodFooter) {
		this.rulesDisplayedInMethodFooter.append(ruleDisplayedInMethodFooter);
		return this;
	}

	/**
	 * @return AssertionError
	 */
	public AssertionError getAssertionFailure() {
		return this.assertionFailure;
	}

	/**
	 * @return Description
	 */
	public Description getDescription() {
		return this.description;
	}

	/**
	 * @return Throwable
	 */
	public Throwable getExceptionErrorDuringAfter() {
		return this.exceptionErrorDuringAfter;
	}

	/**
	 * @return Throwable
	 */
	public Throwable getExceptionErrorDuringBefore() {
		return this.exceptionErrorDuringBefore;
	}

	/**
	 * @return Throwable
	 */
	public Throwable getExceptionErrorDuringTestMethod() {
		return this.exceptionErrorDuringTestMethod;
	}

	/**
	 * @return List<TestRule>
	 */
	public List<TestRule> getRuleList() {

		Collections.reverse(this.ruleList);

		return this.ruleList;
	}

	/**
	 * @return StringBuilder
	 */
	public StringBuilder getRulesDisplayedInMethodFooter() {
		return this.rulesDisplayedInMethodFooter;
	}

	/**
	 * @param assertionFailure
	 * @return {@link RuleChainBeanHelper}
	 */
	public RuleChainBeanHelper setAssertionFailure(final AssertionError assertionFailure) {
		this.assertionFailure = assertionFailure;
		return this;
	}

	/**
	 * @param description
	 * @return {@link RuleChainBeanHelper}
	 */
	public RuleChainBeanHelper setDescription(final Description description) {
		this.description = description;
		return this;
	}

	/**
	 * @param exceptionErrorDuringAfter
	 * @return {@link RuleChainBeanHelper}
	 */
	public RuleChainBeanHelper setExceptionErrorDuringAfter(final Throwable exceptionErrorDuringAfter) {
		this.exceptionErrorDuringAfter = exceptionErrorDuringAfter;
		return this;
	}

	/**
	 * @param exceptionErrorDuringBefore
	 * @return {@link RuleChainBeanHelper}
	 */
	public RuleChainBeanHelper setExceptionErrorDuringBefore(final Throwable exceptionErrorDuringBefore) {
		this.exceptionErrorDuringBefore = exceptionErrorDuringBefore;
		return this;
	}

	/**
	 * @param exceptionErrorDuringTestMethod
	 * @return {@link RuleChainBeanHelper}
	 */
	public RuleChainBeanHelper setExceptionErrorDuringTestMethod(final Throwable exceptionErrorDuringTestMethod) {
		this.exceptionErrorDuringTestMethod = exceptionErrorDuringTestMethod;
		return this;
	}
}