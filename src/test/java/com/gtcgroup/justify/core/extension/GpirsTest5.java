package com.gtcgroup.justify.core.extension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.test.extension.JstConfigureTestLogToConsole;
import com.gtcgroup.justify.core.test.extension.JstConfigureTestSystemProperty;

@SuppressWarnings("static-method")
@JstConfigureTestLogToConsole()
@JstConfigureTestSystemProperty(key = { "1", "2", "3" }, value = { "A", "B", "C" })
public class GpirsTest5 {

    @Test
    public void testSuccessWithMultiples() {

        assertAll("Multiple Asserts", () -> assertEquals(System.getProperty("1"), "A"),
                () -> assertEquals(System.getProperty("2"), "B"), () -> assertEquals(System.getProperty("3"), "C"));
    }
}