/*
 * [Licensed per the Open Source "MIT License".]
 *
 * Copyright (c) 2006 - 2018 by
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
package com.gtcgroup.justify.core.testing.extension;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * This {@link Extension} class initializes system properties for the duration
 * of the test class and then reinstates the original values.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since 8.5
 */
class ConfigureTestingSystemPropertyExtension extends JstBaseTestingExtension implements BeforeAllCallback, AfterAllCallback {

	protected String[] keyArray = new String[] {};

	protected String[] valueArray = new String[] {};

	protected List<String> durableKeyList = new ArrayList<>();

	protected List<String> durableValueList = new ArrayList<>();

	@Override
	public void afterAll(final ExtensionContext extensionContext) throws Exception {

		for (int i = 0; i < this.durableKeyList.size(); i++) {

			System.setProperty(this.durableKeyList.get(i), this.durableValueList.get(i));
		}
		return;

	}

	@Override
	public void beforeAll(final ExtensionContext extensionContext) throws Exception {

		try {
			initializePropertiesFromAnnotation(extensionContext);

			for (int i = 0; i < this.keyArray.length; i++) {

				this.durableKeyList.add(this.keyArray[i]);
				System.getProperty(this.keyArray[i]);
				this.durableValueList.add(this.keyArray[i]);

				System.setProperty(this.keyArray[i], this.valueArray[i]);
			}
		} catch (final RuntimeException e) {

			handleBeforeAllException(extensionContext, e); // Covered
		}
		return;
	}

	@Override
	protected Boolean initializePropertiesFromAnnotation(final ExtensionContext extensionContext) {

		final JstConfigureTestingSystemProperty configureTestSystemProperty = (JstConfigureTestingSystemProperty) retrieveAnnotation(
				extensionContext.getRequiredTestClass(), JstConfigureTestingSystemProperty.class);

		this.keyArray = configureTestSystemProperty.key();
		this.valueArray = configureTestSystemProperty.value();
		return Boolean.TRUE;
	}
}