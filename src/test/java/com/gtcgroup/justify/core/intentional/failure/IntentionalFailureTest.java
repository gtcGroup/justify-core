package com.gtcgroup.justify.core.intentional.failure;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.test.extension.JstConfigureTestLogToConsole;

@SuppressWarnings("static-method")
@JstConfigureTestLogToConsole()
public class IntentionalFailureTest {

    @Test
    public void testIntentionalAssertionError() {

        assertTrue(false);
    }

    @Test
    public void testIntentionalFailuresWithMultiples() {

        assertAll("Multiple Asserts", () -> assertEquals(System.getProperty("1"), "A"),
                () -> assertEquals(System.getProperty("2"), "B"), () -> assertEquals(System.getProperty("3"), "C"));
    }
}