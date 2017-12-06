package com.gtcgroup.justify.core.extension;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

@SuppressWarnings("static-method")
@JstConfigureDisplayOnConsole()
@JstConfigureSystemProperty(key = { "1", "2", "3" }, value = { "A", "B", "C" })
public class GpirsTest5 {

    @Test
    public void testSuccessWithMultiples() {

        assertAll("Multiple Asserts", () -> assertEquals(System.getProperty("1"), "A"),
                () -> assertEquals(System.getProperty("2"), "B"), () -> assertEquals(System.getProperty("3"), "C"));
    }
}