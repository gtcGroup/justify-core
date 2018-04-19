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
package com.gtcgroup.test.core.testing.helper.internal.dependency;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.gtcgroup.test.core.testing.helper.internal.LogTestConsoleUtilHelperTest;

/**
 * Test Class
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author
 * @since 8.5
 */
public class DependencyExtensionContext implements ExtensionContext {

	@Override
	public Optional<String> getConfigurationParameter(final String key) {
		return null;
	}

	@Override
	public String getDisplayName() {
		return null;
	}

	@Override
	public Optional<AnnotatedElement> getElement() {
		return null;
	}

	@Override
	public Optional<Throwable> getExecutionException() {
		return null;
	}

	@Override
	public Optional<ExtensionContext> getParent() {
		return null;
	}

	@Override
	public Object getRequiredTestInstance() {
		return new LogTestConsoleUtilHelperTest();
	}

	@Override
	public ExtensionContext getRoot() {
		return null;
	}

	@Override
	public Store getStore(final Namespace namespace) {
		return null;
	}

	@Override
	public Set<String> getTags() {
		return null;
	}

	@Override
	public Optional<Class<?>> getTestClass() {
		return Optional.of(LogTestConsoleUtilHelperTest.class);
	}

	@Override
	public Optional<Object> getTestInstance() {
		return Optional.of(new LogTestConsoleUtilHelperTest());
	}

	@Override
	public Optional<Lifecycle> getTestInstanceLifecycle() {
		return null;
	}

	@Override
	public Optional<Method> getTestMethod() {
		try {
			return Optional.of(LogTestConsoleUtilHelperTest.class.getMethod("testLogHeaderToTestConsole"));
		} catch (@SuppressWarnings("unused") final Exception e) {
			// Empty Block
		}
		return null;
	}

	@Override
	public String getUniqueId() {
		return null;
	}

	@Override
	public void publishReportEntry(final Map<String, String> map) {
		// Empty String
	}
}
