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
 * This {@link Rule} class initializes a system property for the duration of the
 * method and then reinstates the original property value, or if none, then it
 * clears the property.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2016 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public class JstConfigureSystemPropertyRule extends JstBaseRule {

	/**
	 * @return {@link TestRule}
	 */
	@SuppressWarnings("unchecked")
	public static <RULE extends TestRule> RULE withProperty(final String key, final String value) {

		return (RULE) new JstConfigureSystemPropertyRule(key, value);
	}

	private final String key;

	private final String value;

	private final String durableValue;

	/**
	 * Constructor - protected
	 */
	protected JstConfigureSystemPropertyRule(final String key, final String value) {

		this.durableValue = System.getProperty(key);
		this.key = key;
		this.value = value;
	}

	/**
	 * @see JstBaseRule#afterTM()
	 */
	@Override
	public void afterTM() {

		if (null == this.durableValue) {
			System.clearProperty(this.key);
		} else {
			System.setProperty(this.key, this.durableValue);
		}

		return;
	}

	/**
	 * @see JstBaseRule#beforeTM()
	 */
	@Override
	public void beforeTM() {

		System.setProperty(this.key, this.value);

		return;
	}
}