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

package com.gtcgroup.justify.core.pattern.palette.internal;

import java.io.Serializable;

/**
 * This Domain Entity base class provides support for entity identity.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2016 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public abstract class BaseDE extends BaseClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Object entityIdentity = assignEntityIdentityInstanceTM();

	/**
	 * @see Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final BaseDE other = (BaseDE) obj;
		if (getEntityIdentity() == null) {
			if (other.getEntityIdentity() != null) {
				return false;
			}
		} else if (!getEntityIdentity().equals(other.getEntityIdentity())) {
			return false;
		}
		return true;
	}

	/**
	 * @return {@link Object}
	 */
	public Object getEntityIdentity() {

		return this.entityIdentity;
	}

	/**
	 * @see Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getEntityIdentity() == null ? 0 : getEntityIdentity().hashCode());
		return result;
	}

	/**
	 * @return {@link String}
	 */
	public abstract String retrieveEntityIdentityAsString();

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return "[Entity Identity = " + retrieveEntityIdentityAsString() + "]";
	}

	/**
	 * @return {@link Object}
	 */
	protected abstract Object assignEntityIdentityInstanceTM();

	/**
	 * @see BaseClass#assignPatternSuffixTM()
	 */
	@Override
	protected String assignPatternSuffixTM() {

		return "DE";
	}
}
