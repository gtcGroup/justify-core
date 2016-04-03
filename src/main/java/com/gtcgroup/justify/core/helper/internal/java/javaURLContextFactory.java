/*
 * [Licensed per the Open Source "MIT License".]
 *
 * Copyright (c) 2006 - 2015 by
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
package com.gtcgroup.justify.core.helper.internal.java;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.ObjectFactory;

import com.gtcgroup.justify.core.helper.internal.JndiContextBeanHelper;

/**
 * See {@link InitialContextFactory}. This class has a crazy name in order to
 * support IBM's WAS 8 implementation.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2016 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public class javaURLContextFactory implements InitialContextFactory, ObjectFactory {

	private static JndiContextBeanHelper jndiContextBeanHelper;

	/**
	 * @see javaURLContextFactory#getInitialContext(java.util.Hashtable)
	 */
	@Override
	public synchronized Context getInitialContext(final Hashtable<?, ?> portableInitialContextFactory)
			throws NamingException {

		if (javaURLContextFactory.jndiContextBeanHelper == null) {
			javaURLContextFactory.jndiContextBeanHelper = new JndiContextBeanHelper();
		}
		return javaURLContextFactory.jndiContextBeanHelper;
	}

	/**
	 * @see javax.naming.spi.ObjectFactory#getObjectInstance(java.lang.Object,
	 *      javax.naming.Name, javax.naming.Context, java.util.Hashtable)
	 */
	@Override
	public Object getObjectInstance(final Object obj, final Name name, final Context nameCtx,
			final Hashtable<?, ?> environment) throws Exception {

		return getInitialContext(environment);
	}
}