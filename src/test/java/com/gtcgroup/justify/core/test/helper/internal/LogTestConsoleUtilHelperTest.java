package com.gtcgroup.justify.core.test.helper.internal;

import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.test.extension.JstConfigureTestLogToConsole;
import com.gtcgroup.justify.core.test.helper.internal.LogTestConsoleUtilHelper;

@SuppressWarnings("static-method")
@JstConfigureTestLogToConsole()
public class LogTestConsoleUtilHelperTest {

    @Test
    public void testBuildClassPath() {

        final StringBuilder stringBuilder = new StringBuilder();
        LogTestConsoleUtilHelper.buildClassPath(stringBuilder);

        System.out.println(stringBuilder);
    }
}