package com.gtcgroup.justify.core.extension;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

@SuppressWarnings("static-method")
@JstConfigureDisplayOnConsole(verbose = true)
public class GpirsTest3 {

    @Test
    public void testNestedExceptionUnexpected() {

        try {
            throw new NullPointerException("Root cause exception.");
        } catch (final Exception e) {

            throw new RuntimeException("Outer unexpected exception.", e);
        }
    }

    @Test
    public void testRuntimeExpected() {

        assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("a message");
        });

    }
}
