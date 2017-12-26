package com.gtcgroup.justify.core.extension;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
// @ExtendWith(JstConfigureLogToConsoleExtension.class)
public class GpirsTest1 {

    // @BeforeAll
    // public void beforeAll() {
    // System.out.println("/n/tempty block");
    // }

    @SuppressWarnings("static-method")
    @Test
    public void testSuccessWithoutExtension() {

        assertTrue(true);
    }
}
