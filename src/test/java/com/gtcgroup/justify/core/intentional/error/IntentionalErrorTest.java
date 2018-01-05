package com.gtcgroup.justify.core.intentional.error;

import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.po.JstExceptionPO;
import com.gtcgroup.justify.core.test.exception.internal.JustifyException;
import com.gtcgroup.justify.core.test.extension.JstConfigureTestLogToConsole;

/**
 * This class is for testing.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v8.5
 */
@SuppressWarnings("static-method")
@JstConfigureTestLogToConsole()
public class IntentionalErrorTest {

    @Test
    public void testIntentionalNestedError() {

        throw new JustifyException(
                JstExceptionPO.withMessage("A Justify Exception.")
                        .withClassName(IntentionalErrorTest.class.getSimpleName())
                        .withMethodName("testIntentionalNestedError").withSuppressLogging(false).withUserId("mToll4"),
                new NullPointerException("This is on purpose."));
    }

    @Test
    public void testIntentionalNestedExceptionUnexpected() {

        try {
            throw new NullPointerException("Root cause exception.");
        } catch (final Exception e) {

            throw new RuntimeException("Outer unexpected exception.", e);
        }

    }

    @Test
    public void testIntentionalUnexpectedException() {

        throw new RuntimeException("Unexpected Runtime Exception");
    }

}
