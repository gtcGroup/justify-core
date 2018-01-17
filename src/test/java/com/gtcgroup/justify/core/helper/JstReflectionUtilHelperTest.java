/*
 * [Licensed per the Open Source "MIT License".]
 *
 * Copyright (c) 2006 - 2018 by
 * Global Technology Consulting Group, Inc. at
 * http://gtcGroup.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.gtcgroup.justify.core.helper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.bean.dependency.GoodBean;
import com.gtcgroup.justify.core.bean.dependency.NonPublicConstructorBean;
import com.gtcgroup.justify.core.bean.dependency.NothingBean;
import com.gtcgroup.justify.core.po.JstStreamPO;
import com.gtcgroup.justify.core.test.extension.JstConfigureTestLogToConsole;

/**
 * Test Class
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
@SuppressWarnings("static-method")
@JstConfigureTestLogToConsole
public class JstReflectionUtilHelperTest {

    private static final String FAKE_CLASS_NAME = "FakeClassName";

    private static final String TEST_FILE_NAME = "testfile.xml";

    private static final String FAKE_FILE_NAME = "f@keFileName.xml";

    private static final String FAKE_METHOD_NAME = "f@keMethodName";

    private static final String TEST_METHOD_GET = "getString";

    private static final String TEST_METHOD_SET = "setString";

    private final Object[] CONSTRUCTOR_PARAMETER_VALUES = new Object[] { "string value" };

    private final Object[] CONSTRUCTOR_PARAMETER_FAKE_VALUES = new Object[] { "fake value1", "fake value2" };

    @Test
    public void testContainsPublicConstructorNoArgument() {

        assertTrue(JstReflectionUtilHelper.containsPublicConstructorNoArgument(NothingBean.class));
    }

    @Test
    public void testContainsPublicConstructorNoArgument_false() {

        assertFalse(JstReflectionUtilHelper.containsPublicConstructorNoArgument(JstReflectionUtilHelper.class));
    }

    @Test
    public void testContainsPublicNoArgumentConstructorOnly() {

        assertFalse(JstReflectionUtilHelper.containsPublicNoArgumentConstructorOnly(JstReflectionUtilHelper.class));
    }

    @Test
    public void testContainsPublicNoArgumentConstructorOnly_false() {

        assertFalse(JstReflectionUtilHelper.containsPublicNoArgumentConstructorOnly(JstReflectionUtilHelper.class));
    }

    @Test
    public void testExistsOnClasspath() {

        assertTrue(JstReflectionUtilHelper.existsOnClasspath(JstReflectionUtilHelper.class.getName()));
    }

    @Test
    public void testExistsOnClasspath_false() {

        assertFalse(JstReflectionUtilHelper.existsOnClasspath(JstReflectionUtilHelperTest.FAKE_FILE_NAME));
    }

    @Test
    public void testGetResourceAsStreamAndBeSureToClose() {

        assertTrue(JstReflectionUtilHelper
                .getResourceAsStreamAndBeSureToClose(JstReflectionUtilHelperTest.TEST_FILE_NAME).isPresent());
    }

    @Test
    public void testGetResourceAsStreamAndBeSureToClose_false() {

        assertFalse(JstReflectionUtilHelper
                .getResourceAsStreamAndBeSureToClose(JstReflectionUtilHelperTest.FAKE_FILE_NAME).isPresent());
    }

    @Test
    public void testGetResourceAsStreamPoAndBeSureToClose() {

        final Optional<JstStreamPO> jstStreamPO = JstReflectionUtilHelper
                .getResourceAsStreamPoAndBeSureToClose(JstReflectionUtilHelperTest.TEST_FILE_NAME);

        assertAll(() -> {
            assertTrue(jstStreamPO.isPresent());
            jstStreamPO.get().getInputStreamToBeClosed();
            jstStreamPO.get().closeInputStream();
            assertNotNull(jstStreamPO.get().getClassLoader());
        });
    }

    @Test
    public void testGetResourceAsStreamPoAndBeSureToClose_false() {
        assertFalse(JstReflectionUtilHelper.getResourceAsStreamPoAndBeSureToClose("fakeResourceName").isPresent());
    }

    @Test()
    public void testInstantiateInstanceUsingConstructorWithParameters() {

        assertTrue(JstReflectionUtilHelper.instantiateInstanceUsingConstructorWithParameters(GoodBean.class,
                this.CONSTRUCTOR_PARAMETER_VALUES, String.class).isPresent());
    }

    @Test()
    public void testInstantiateInstanceUsingConstructorWithParameters_false() {

        assertFalse(JstReflectionUtilHelper.instantiateInstanceUsingConstructorWithParameters(GoodBean.class,
                this.CONSTRUCTOR_PARAMETER_VALUES, Long.class).isPresent());
    }

    @Test()
    public void testInstantiateInstanceUsingConstructorWithParameters_falseValues() {

        assertFalse(JstReflectionUtilHelper.instantiateInstanceUsingConstructorWithParameters(GoodBean.class,
                this.CONSTRUCTOR_PARAMETER_FAKE_VALUES, String.class).isPresent());
    }

    @Test
    public void testInstantiateInstanceUsingNonPublicConstructorWithNoArgument() {

        assertTrue(JstReflectionUtilHelper
                .instantiateInstanceUsingNonPublicConstructorWithNoArgument(NonPublicConstructorBean.class)
                .isPresent());
    }

    @Test
    public void testInstantiateInstanceUsingNonPublicConstructorWithNoArgument_false() {

        assertFalse(JstReflectionUtilHelper
                .instantiateInstanceUsingNonPublicConstructorWithNoArgument(NothingBean.class).isPresent());
    }

    @Test
    public void testInstantiateInstanceUsingPublicConstructorWithNoArgument() {

        assertTrue(JstReflectionUtilHelper.instantiateInstanceUsingPublicConstructorWithNoArgument(GoodBean.class)
                .isPresent());
    }

    @Test
    public void testInstantiateInstanceUsingPublicConstructorWithNoArgument_false() {

        assertFalse(JstReflectionUtilHelper
                .instantiateInstanceUsingPublicConstructorWithNoArgument(NonPublicConstructorBean.class).isPresent());
    }

    @Test
    public void testInstantiateInstanceUsingPublicConstructorWithParameters() {

        assertTrue(JstReflectionUtilHelper.instantiateInstanceUsingPublicConstructorWithParameters(
                GoodBean.class.getName(), this.CONSTRUCTOR_PARAMETER_VALUES, String.class).isPresent());

    }

    @Test
    public void testInstantiateUsingPublicConstructorWithParameters_false1() {

        assertFalse(JstReflectionUtilHelper
                .instantiateInstanceUsingPublicConstructorWithParameters(JstReflectionUtilHelperTest.FAKE_CLASS_NAME,
                        this.CONSTRUCTOR_PARAMETER_VALUES, String.class)
                .isPresent());

    }

    @Test
    public void testInstantiateUsingPublicConstructorWithParameters_false2() {

        assertFalse(JstReflectionUtilHelper.instantiateInstanceUsingPublicConstructorWithParameters(
                JstReflectionUtilHelper.class, this.CONSTRUCTOR_PARAMETER_VALUES, String.class).isPresent());

    }

    @Test()
    public void testInvokePublicMethod_badString() {

        JstReflectionUtilHelper.invokePublicMethod("getFake", GoodBean.class);
    }

    @Test()
    public void testInvokePublicMethod_exception() {

        JstReflectionUtilHelper.invokePublicMethod("retrieveException", new GoodBean());
    }

    @Test
    public void testInvokePublicMethod_false() {

        assertFalse(JstReflectionUtilHelper
                .invokePublicMethod(JstReflectionUtilHelperTest.TEST_METHOD_SET, new GoodBean(), new Long(0))
                .isPresent());
    }

    @Test
    public void testInvokePublicMethod_get() {

        assertTrue(JstReflectionUtilHelper
                .invokePublicMethod(JstReflectionUtilHelperTest.TEST_METHOD_GET, new GoodBean()).isPresent());
    }

    @Test
    public void testInvokePublicMethod_set() {

        assertTrue(JstReflectionUtilHelper
                .invokePublicMethod(JstReflectionUtilHelperTest.TEST_METHOD_SET, new GoodBean(), GoodBean.STRING)
                .isPresent());
    }

    @Test()
    public void testInvokePublicMethod_withNoArgument() {

        JstReflectionUtilHelper.invokePublicMethod("getTextWithArgument", new GoodBean());
    }

    @Test
    public void testRetrieveClass_valid1() {

        JstReflectionUtilHelper.retrieveClass(GoodBean.class.getName());
    }

    @Test
    public void testRetrieveClass_valid2() {

        JstReflectionUtilHelper.retrieveClass(GoodBean.class.getName());
    }

    @Test
    public void testRetrieveConstructorWithParameters() {

        assertTrue(JstReflectionUtilHelper
                .retrieveConstructorWithParameters(NonPublicConstructorBean.class, String.class).isPresent());
    }

    @Test
    public void testRetrieveFieldInstanceWithDirectAccess() {

        assertTrue(JstReflectionUtilHelper.retrieveFieldInstanceWithDirectAccess(new GoodBean(), "string").isPresent());
    }

    @Test
    public void testRetrieveFieldInstanceWithDirectAccess_false() {

        assertFalse(JstReflectionUtilHelper
                .retrieveFieldInstanceWithDirectAccess(new NothingBean(), JstReflectionUtilHelperTest.FAKE_FILE_NAME)
                .isPresent());
    }

    @Test()
    public void testRetrieveFileAsDataInputStream_exception() {

        JstReflectionUtilHelper.retrieveFileAsDataInputStreamAndBeSureToClose("fakeFileName");
    }

    @Test
    public void testRetrieveFileAsDataInputStream_valid() {

        assertNotNull(JstReflectionUtilHelper
                .retrieveFileAsDataInputStreamAndBeSureToClose(JstReflectionUtilHelperTest.TEST_FILE_NAME));
    }

    @Test()
    public void testRetrieveFileAsReader_exception() {

        JstReflectionUtilHelper.retrieveFileAsReader("fakeFileName");
    }

    @Test
    public void testRetrieveFileAsReader_valid() {

        assertNotNull(JstReflectionUtilHelper.retrieveFileAsReader(JstReflectionUtilHelperTest.TEST_FILE_NAME));
    }

    @Test
    public void testRetrievePublicMethod_fake() {

        assertFalse(JstReflectionUtilHelper
                .retrievePublicMethod(JstReflectionUtilHelper.class, JstReflectionUtilHelperTest.FAKE_METHOD_NAME)
                .isPresent());
    }

    @Test
    public void testRetrievePublicMethod_valid() {

        assertTrue(JstReflectionUtilHelper
                .retrievePublicMethod(GoodBean.class, JstReflectionUtilHelperTest.TEST_METHOD_GET).isPresent());
    }
}
