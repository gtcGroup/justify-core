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
package com.gtcgroup.justify.core.extension;

import java.util.Optional;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.gtcgroup.justify.core.base.JstBaseExtension;
import com.gtcgroup.justify.core.helper.internal.LogToConsoleUtilHelper;

/**
 * This {@link Extension} class initializes a public user id for the duration of
 * the method and then reinstates the original user id value.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public class JstConfigureUserIdExtension extends JstBaseExtension
        implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    protected static void initializeUserId(final ExtensionContext context) {

        final Optional<JstConfigureUserId> configureUserId = LogToConsoleUtilHelper.retrieveAnnotation(context,
                JstConfigureUserId.class);

        if (configureUserId.isPresent()) {

            JstBaseExtension.userId = configureUserId.get().userId();
        } else {
            JstBaseExtension.userId = JstBaseExtension.DEFAULT_USER_ID;
        }
    }

    @Override
    public void afterTestExecution(final ExtensionContext context) throws Exception {

        JstBaseExtension.setUserId(JstBaseExtension.DEFAULT_USER_ID);
        return;
    }

    @Override
    public void beforeTestExecution(final ExtensionContext context) throws Exception {

        initializeUserId(context);
    }
}