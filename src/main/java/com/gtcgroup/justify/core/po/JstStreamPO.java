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

package com.gtcgroup.justify.core.po;

import java.io.InputStream;

import com.gtcgroup.justify.core.base.JstBasePO;

/**
 * This Parameter Object class supports {@link InputStream} processing.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */

public class JstStreamPO extends JstBasePO {

	/**
	 * @return {@link JstStreamPO}
	 */
	public static JstStreamPO withInputStream(final InputStream inputStream) {

		return new JstStreamPO(inputStream);
	}

	private ClassLoader classLoader;

	private final InputStream inputStream;

	protected JstStreamPO(final InputStream inputStream) {
		super();

		this.inputStream = inputStream;
	}

	/**
	 * This convenience method will close the {@link InputStream}.
	 */
	public void closeInputStream() {

		if (this.inputStream != null) {
			try {
				this.inputStream.close();
			} catch (@SuppressWarnings("unused") final Exception e) {
				// Should not occur.
			}
		}
	}

	/**
	 * @return {@link ClassLoader}
	 */
	public ClassLoader getClassLoader() {

		return this.classLoader;
	}

	/**
	 * @return {@link InputStream}
	 */
	public InputStream getInputStreamToBeClosed() {

		return this.inputStream;
	}

	/**
	 * @return {@link JstStreamPO}
	 */
	public JstStreamPO withClassLoader(final ClassLoader classLoader) {
		this.classLoader = classLoader;
		return this;
	}
}