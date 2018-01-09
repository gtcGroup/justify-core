package com.gtcgroup.justify.core.base;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.base.dependency.GoodDE;
import com.gtcgroup.justify.core.test.extension.JstConfigureTestLogToConsole;

@SuppressWarnings("static-method")
@JstConfigureTestLogToConsole(verbose = true)
public class JstBaseDeTest {

    @Test
    public void testRuntimeExpected() {

        assertNotNull(new GoodDE());
    }
}
