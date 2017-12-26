package com.gtcgroup.justify.core.extension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

@SuppressWarnings("static-method")
@JstConfigureLogToConsole()
public class GpirsTest6 {

    @Test
    public void testFailuresWithMultiples() {

        assertAll("Multiple Asserts", () -> assertEquals(System.getProperty("1"), "A"),
                () -> assertEquals(System.getProperty("2"), "B"), () -> assertEquals(System.getProperty("3"), "C"));
    }
}