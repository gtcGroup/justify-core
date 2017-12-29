package com.gtcgroup.justify.core.intentional.error;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.test.exception.internal.JustifyTestingException;
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
    public void testIntentionalAssertionError() {

        assertTrue(false);
    }

    @Test
    public void testIntentionalNestedError() {

        throw new JustifyTestingException(new NullPointerException("This is on purpose."));
    }

    @Test
    public void testIntentionalUnexpectedException() {

        throw new RuntimeException("Unexpected Runtime Exception");
    }

    @Test
    public void testNestedExceptionUnexpected() {

        try {
            throw new NullPointerException("Root cause exception.");
        } catch (final Exception e) {

            throw new RuntimeException("Outer unexpected exception.", e);
        }

    }

}
