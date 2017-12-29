package com.gtcgroup.justify.core.extension;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.test.extension.JstConfigureTestLogToConsole;

@SuppressWarnings("static-method")
@JstConfigureTestLogToConsole(verbose = true)
public class GpirsTest3 {

    @Test
    public void testRuntimeExpected() {

        assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("a message");
        });

    }
}
