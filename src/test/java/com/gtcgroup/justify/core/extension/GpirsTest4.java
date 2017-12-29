package com.gtcgroup.justify.core.extension;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.test.extension.JstConfigureTestLogToConsole;

@SuppressWarnings("static-method")
@JstConfigureTestLogToConsole(verbose = true)
public class GpirsTest4 {

    @Test
    public void testSuccessWithVerbose() {

        assertTrue(true);
    }
}
