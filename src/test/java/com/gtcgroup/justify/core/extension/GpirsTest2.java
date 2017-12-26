package com.gtcgroup.justify.core.extension;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings("static-method")
@ExtendWith(JstConfigureLogToConsoleExtension.class)
public class GpirsTest2 {

    @Test
    public void testAssertionError() {

        assertTrue(false);
    }

    @Test
    public void testSuccess() {

        assertTrue(true);
    }

    @Test
    public void testUnexpectedException() {

        throw new RuntimeException("Unexpected Runtime");
    }
}
