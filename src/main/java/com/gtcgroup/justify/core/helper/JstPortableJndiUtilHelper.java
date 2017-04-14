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

package com.gtcgroup.justify.core.helper;

import javax.naming.Context;
import javax.naming.spi.NamingManager;

import com.gtcgroup.justify.core.exception.internal.JustifyRuntimeException;
import com.gtcgroup.justify.core.helper.internal.JndiContextCacheHelper;
import com.gtcgroup.justify.core.jndi.internal.JndiInitialContextBuilder;

/**
 * This Util Helper class establishes a portable JNDI tree for binding and
 * subsequent lookup.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */

public enum JstPortableJndiUtilHelper {

	@SuppressWarnings("javadoc")
	INSTANCE;

	static {

		try {
			NamingManager.setInitialContextFactoryBuilder(new JndiInitialContextBuilder());

		} catch (final Exception e) {

			// Ignore, should never happen.
		}
	}

	/**
	 * @see Context#bind(String, Object)
	 */
	public static void bind(final String name, final Object object) {

		JndiContextCacheHelper.INSTANCE.bind(name, object);
	}

	/**
	 * @see Context#lookup(String)
	 */
	public static Object lookup(final String name) {

		final Object object = JndiContextCacheHelper.INSTANCE.lookup(name);

		if (null == object) {
			throw new JustifyRuntimeException(
					"The name [" + name + "] is not available in the portable JNDI container.");
		}
		return object;
	}

	/**
	 * @see Context#bind(String, Object)
	 */
	public static void rebind(final String name, final Object object) {

		JndiContextCacheHelper.INSTANCE.rebind(name, object);
	}
}
