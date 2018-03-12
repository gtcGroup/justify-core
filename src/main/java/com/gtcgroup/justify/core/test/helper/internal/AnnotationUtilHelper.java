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

package com.gtcgroup.justify.core.test.helper.internal;

import java.lang.annotation.Annotation;
import java.util.Optional;

import com.gtcgroup.justify.core.po.JstExceptionPO;
import com.gtcgroup.justify.core.test.exception.internal.JustifyException;

/**
 * This Util Helper class provides support for {@link Annotation} processing.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v8.5
 */

public enum AnnotationUtilHelper {

	INSTANCE;

	private static String CLASS_NAME = AnnotationUtilHelper.class.getSimpleName();

	private static String METHOD_NAME = "retrieveAnnotation";

	public static Optional<? extends Annotation> retrieveAnnotation(final Class<?> clazz,
			final Class<? extends Annotation> annotationClass) {

		try {
			return Optional.of(clazz.getAnnotation(annotationClass));
		} catch (final Exception e) {

			throw new JustifyException(JstExceptionPO.withMessage(e.getMessage()).withExceptionClassName(CLASS_NAME)
					.withExceptionMethodName(METHOD_NAME));
		}
	}

	public static Optional<? extends Annotation> retrieveAnnotation(final Optional<Class<?>> classOptional,
			final Class<? extends Annotation> annotationClass) {

		try {
			if (classOptional.isPresent()) {
				return Optional.of(classOptional.get().getAnnotation(annotationClass));
			}
		} catch (@SuppressWarnings("unused") final Exception e) {

			throw new JustifyException(JstExceptionPO
					.withMessage("The Annotation class [" + annotationClass + "] is not retrievable from the class ["
							+ classOptional.get() + "].")
					.withExceptionClassName(CLASS_NAME).withExceptionMethodName(METHOD_NAME));
		}
		return Optional.empty();
	}
}
