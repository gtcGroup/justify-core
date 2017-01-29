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
package com.gtcgroup.justify.core.jndi.internal;

import java.util.Enumeration;

import javax.naming.InvalidNameException;
import javax.naming.Name;

import com.gtcgroup.justify.core.exception.internal.MethodNotSupportedException;

/**
 * This {@link Name} supports required methods.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author
 * @since v.6.0
 */
public class JndiName implements Name {

	private static final long serialVersionUID = 1L;

	/**
	 * @see Name#add(int, String)
	 */
	@Override
	public Name add(final int posn, final String comp) throws InvalidNameException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Name#add(String)
	 */
	@Override
	public Name add(final String comp) throws InvalidNameException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Name#addAll(int, Name)
	 */
	@Override
	public Name addAll(final int posn, final Name n) throws InvalidNameException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Name#addAll(Name)
	 */
	@Override
	public Name addAll(final Name suffix) throws InvalidNameException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Object#clone()
	 */
	@Override
	public Object clone() {
		return null;
	}

	/**
	 * @see Name#compareTo(Object)
	 */
	@Override
	public int compareTo(final Object obj) {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Name#endsWith(Name)
	 */
	@Override
	public boolean endsWith(final Name n) {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Name#get(int)
	 */
	@Override
	public String get(final int posn) {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Name#getAll()
	 */
	@Override
	public Enumeration<String> getAll() {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Name#getPrefix(int)
	 */
	@Override
	public Name getPrefix(final int posn) {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Name#getSuffix(int)
	 */
	@Override
	public Name getSuffix(final int posn) {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Name#isEmpty()
	 */
	@Override
	public boolean isEmpty() {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Name#remove(int)
	 */
	@Override
	public Object remove(final int posn) throws InvalidNameException {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Name#size()
	 */
	@Override
	public int size() {

		throw new MethodNotSupportedException();
	}

	/**
	 * @see Name#startsWith(Name)
	 */
	@Override
	public boolean startsWith(final Name n) {

		throw new MethodNotSupportedException();
	}
}
