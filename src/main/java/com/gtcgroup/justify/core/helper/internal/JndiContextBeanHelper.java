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
package com.gtcgroup.justify.core.helper.internal;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import com.gtcgroup.justify.core.exception.internal.MethodNotSupportedException;
import com.gtcgroup.justify.core.pattern.palette.internal.BaseBeanHelper;

/**
 * See {@link Context}.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2016 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public class JndiContextBeanHelper extends BaseBeanHelper implements Context {

	private final Map<String, Object> bindings = new HashMap<String, Object>();

	/**
	 * @see Context#addToEnvironment(java.lang.String, java.lang.Object)
	 */
	@Override
	public Object addToEnvironment(final String s, final Object o) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#bind(javax.naming.Name, java.lang.Object)
	 */
	@Override
	public void bind(final Name name, final Object object) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#bind(java.lang.String, java.lang.Object)
	 */
	@Override
	public void bind(final String name, final Object object) throws NamingException {

		try {
			this.bindings.put(name, object);
		} catch (final Exception e) {
			System.out.println("\nUnable to bind (" + name + ") to portable JNDI container." + e.getMessage() + "\n");
		}
	}

	/**
	 * @see Context#close()
	 */
	@Override
	public void close() throws NamingException {

		// Empty Block
	}

	/**
	 * @see Context#composeName(javax.naming.Name, javax.naming.Name)
	 */
	@Override
	public Name composeName(final Name n, final Name pfx) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#composeName(java.lang.String, java.lang.String)
	 */
	@Override
	public String composeName(final String s, final String pfx) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#createSubcontext(javax.naming.Name)
	 */
	@Override
	public Context createSubcontext(final Name n) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#createSubcontext(java.lang.String)
	 */
	@Override
	public Context createSubcontext(final String name) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#destroySubcontext(javax.naming.Name)
	 */
	@Override
	public void destroySubcontext(final Name n) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#destroySubcontext(java.lang.String)
	 */
	@Override
	public void destroySubcontext(final String s) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#getEnvironment()
	 */
	@Override
	public Hashtable<?, ?> getEnvironment() throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#getNameInNamespace()
	 */
	@Override
	public String getNameInNamespace() throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#getNameParser(javax.naming.Name)
	 */
	@Override
	public NameParser getNameParser(final Name n) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#getNameParser(java.lang.String)
	 */
	@Override
	public NameParser getNameParser(final String s) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#list(javax.naming.Name)
	 */
	@Override
	public NamingEnumeration<NameClassPair> list(final Name n) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#list(java.lang.String)
	 */
	@Override
	public NamingEnumeration<NameClassPair> list(final String s) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#listBindings(javax.naming.Name)
	 */
	@Override
	public NamingEnumeration<Binding> listBindings(final Name n) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#listBindings(java.lang.String)
	 */
	@Override
	public NamingEnumeration<Binding> listBindings(final String s) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#lookup(Name)
	 */
	@Override
	public Object lookup(final Name compoundName) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#lookup(java.lang.String)
	 */
	@Override
	public Object lookup(final String name) throws NamingException {

		try {
			this.bindings.get(name);
		} catch (final Exception e) {
			System.out
					.println("\nUnable to return (" + name + ") from portable JNDI container." + e.getMessage() + "\n");
		}

		return this.bindings.get(name);
	}

	/**
	 * @see Context#lookupLink(javax.naming.Name)
	 */
	@Override
	public Object lookupLink(final Name n) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#lookupLink(java.lang.String)
	 */
	@Override
	public Object lookupLink(final String s) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#rebind(javax.naming.Name, java.lang.Object)
	 */
	@Override
	public void rebind(final Name n, final Object o) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#rebind(java.lang.String, java.lang.Object)
	 */
	@Override
	public void rebind(final String s, final Object o) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#removeFromEnvironment(java.lang.String)
	 */
	@Override
	public Object removeFromEnvironment(final String s) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#rename(javax.naming.Name, javax.naming.Name)
	 */
	@Override
	public void rename(final Name nOld, final Name nNew) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#rename(java.lang.String, java.lang.String)
	 */
	@Override
	public void rename(final String sOld, final String sNew) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#unbind(javax.naming.Name)
	 */
	@Override
	public void unbind(final Name n) throws NamingException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Context#unbind(java.lang.String)
	 */
	@Override
	public void unbind(final String s) throws NamingException {

		throw new MethodNotSupportedException();
	}
}