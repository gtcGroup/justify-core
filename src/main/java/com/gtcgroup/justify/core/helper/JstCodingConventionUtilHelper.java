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

import com.gtcgroup.justify.core.po.JstExceptionPO;
import com.gtcgroup.justify.core.test.exception.internal.JustifyException;

/**
 * This Util Helper class provides methods for runtime detection of coding
 * convention violations.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public enum JstCodingConventionUtilHelper {

    INSTANCE;

    /**
     * This method throws an exception if a suffix violation occurs.
     */
    public static void checkSuffixInClassName(final Class<?> clazz, final String containsCharacters) {

        // Verify naming convention.
        if (!clazz.getSimpleName().contains(containsCharacters)) {

            throw JstCodingConventionUtilHelper.instantiateException(clazz, containsCharacters);
        }
    }

    /**
     * @return {@link JustifyException}
     */
    private static JustifyException instantiateException(final Class<?> clazz, final String... endsWith) {

        final StringBuilder message = new StringBuilder();
        message.append("The class named [");
        message.append(clazz.getName());
        message.append("] MUST end with ");

        for (final String endWith : endsWith) {

            message.append("[");
            message.append(endWith);
            message.append("]");
        }
        message.append(".");

        return new JustifyException(JstExceptionPO.withMessage(message.toString()));
    }
}