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

import org.junit.jupiter.api.extension.ExtensionContext;

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

    public static Optional<? extends Annotation> retrieveAnnotation(final ExtensionContext extensionContext,
            final Class<? extends Annotation> annotationClass) {

        return Optional
                .ofNullable(extensionContext.getRequiredTestInstance().getClass().getAnnotation(annotationClass));
    }

    public static Optional<? extends Annotation> retrieveAnnotation(final Object object,
            final Class<? extends Annotation> annotationClass) {

        return Optional.ofNullable(object.getClass().getAnnotation(annotationClass));
    }

}