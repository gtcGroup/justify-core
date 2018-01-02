/*
 * [Licensed per the Open Source "MIT License".]
 *
 * Copyright (c) 2006 - 2017 by
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

package com.gtcgroup.justify.core.helper.internal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.base.dependency.NoteDE;
import com.gtcgroup.justify.core.exception.JstReflectionSelfLoggingException;
import com.gtcgroup.justify.core.helper.JstReflectionUtilHelper;
import com.gtcgroup.justify.core.helper.dependency.ReflectionBean;
import com.gtcgroup.justify.core.helper.dependency.ReflectionExceptionBean;
import com.gtcgroup.justify.core.po.JstStreamPO;
import com.gtcgroup.justify.core.test.extension.JstConfigureTestLogToConsole;

/**
 * Test Class
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
@SuppressWarnings("static-method")
@JstConfigureTestLogToConsole
public class ReflectionUtilHelperTest {

    private static final String TEST_FILE_NAME = "testfile.xml";

    private static final String FAKE_FILE_NAME = "f@keFileName.xml";

    private static final String FAKE_METHOD_NAME = "f@keMethodName";

    private static final String TEST_METHOD_NAME = "getString";

    private final Object[] CONSTRUCTOR_PARAMETER_VALUES = new Object[] { "string value" };

    @Test
    public void testContainsPublicConstructorNoArgument_false2() {

        assertFalse(JstReflectionUtilHelper.containsPublicConstructorNoArgument(JstReflectionUtilHelper.class));
    }

    @Test
    public void testContainsPublicConstructorNoArgument_valid1() {

        assertTrue(JstReflectionUtilHelper.containsPublicConstructorNoArgument(NoteDE.class));
    }

    @Test
    public void testContainsPublicConstructorNoArgument_valid2() {

        assertTrue(JstReflectionUtilHelper.containsPublicConstructorNoArgument(NoteDE.class));
    }

    @Test
    public void testExists_false() {

        assertFalse(JstReflectionUtilHelper.existsOnClasspath(ReflectionUtilHelperTest.FAKE_FILE_NAME));
    }

    @Test
    public void testExists_true() {

        assertTrue(JstReflectionUtilHelper.existsOnClasspath(JstReflectionUtilHelper.class.getName()));
    }

    @Test
    public void testFieldInstanceWithDirectAccess() {

        JstReflectionUtilHelper.retrieveFieldInstanceWithDirectAccess(new NoteDE(), "text");
    }

    @Test()
    public void testFieldInstanceWithDirectAccess_exception() {

        JstReflectionUtilHelper.retrieveFieldInstanceWithDirectAccess(new NoteDE(),
                ReflectionUtilHelperTest.FAKE_FILE_NAME);
    }

    @Test
    public void testGetResourceAsStream() {
        final Optional<JstStreamPO> jstStreamPO = JstReflectionUtilHelper
                .getResourceAsStreamPoAndBeSureToClose(ReflectionUtilHelperTest.TEST_FILE_NAME);

        assertAll(() -> {
            assertNotNull(jstStreamPO.get().getInputStreamToBeClosed());
            assertNotNull(jstStreamPO.get().getClassLoader());
        });
    }

    @Test
    public void testGetResourceAsStream_exception_suppress() {
        assertNull(JstReflectionUtilHelper.getResourceAsStreamAndBeSureToClose("fakeResourceName"));
    }

    @Test
    public void testInstantiateInstanceWithNonPublicConstructorWithArgument_constructorListEmpty() {

        assertNull(JstReflectionUtilHelper.instantiateInstanceUsingNonPublicConstructorWithNoArgument(
                this.CONSTRUCTOR_PARAMETER_VALUES, new ArrayList<Constructor<?>>()));

    }

    @Test
    public void testInstantiatePublicConstructorNoArgument() {

        JstReflectionUtilHelper.retrievePublicConstructorNoArgument(ReflectionBean.class);

        assertNotNull(JstReflectionUtilHelper.instantiateInstanceFromConstructorWithNoArgument(constructor)WithPublicConstructorNoArgument(ReflectionBean.class));
    }

    @Test
    public void testInstantiatePublicConstructorNoArgument__valid() {

        assertNotNull(
                JstReflectionUtilHelper.instantiateInstanceWithPublicConstructorNoArgument(ReflectionBean.class, true));
    }

    @Test()
    public void testInstantiatePublicConstructorNoArgument_exception() {

        assertNotNull(JstReflectionUtilHelper
                .instantiateInstanceWithPublicConstructorNoArgument(ReflectionExceptionBean.class, false));
    }

    @Test()
    public void testInstantiatePublicConstructorNoArgument_exception_null() {

        JstReflectionUtilHelper.instantiateInstanceWithPublicConstructorNoArgument(JstReflectionUtilHelper.class);
    }

    @Test
    public void testInstantiatePublicConstructorNoArgument_false() {

        assertNotNull(JstReflectionUtilHelper.instantiateInstanceWithPublicConstructorNoArgument(ReflectionBean.class,
                false));
    }

    @Test
    public void testInstantiatePublicConstructorNoArgument_suppress() {

        assertNotNull(JstReflectionUtilHelper.instantiateInstanceWithPublicConstructorNoArgument(ReflectionBean.class,
                false));
    }

    @Test
    public void testInstantiatePublicConstructorNoArgument_valid1() {

        assertNotNull(JstReflectionUtilHelper.instantiateInstanceWithPublicConstructorNoArgument(ReflectionBean.class));
    }

    @Test
    public void testInstantiatePublicConstructorNoArgument_valid2() {

        JstReflectionUtilHelper.instantiateInstanceWithPublicConstructorNoArgument(ReflectionBean.class, true);
    }

    @Test
    public void testInstantiatePublicConstructorWithArgument_null() {

        assertNull(JstReflectionUtilHelper.instantiateInstanceWithPublicConstructorWithArgument(
                ReflectionBean.class.getName(), true, null, String.class));

    }

    @Test
    public void testInstantiatePublicConstructorWithArgument_null1() {

        assertNull(JstReflectionUtilHelper.instantiateInstanceUsingPublicConstructorWithParameters(
                "com.gtcgroup.FakeName", this.CONSTRUCTOR_PARAMETER_VALUES, true));
    }

    @Test()
    public void testInstantiatePublicConstructorWithArgument_null2() {

        assertNull(JstReflectionUtilHelper.instantiateInstanceUsingPublicConstructorWithParameters(
                "com.gtcgroup.FakeName", this.CONSTRUCTOR_PARAMETER_VALUES, false));
    }

    @Test
    public void testInstantiatePublicConstructorWithArgument_valid() {

        assertNotNull(JstReflectionUtilHelper.instantiateInstanceWithPublicConstructorWithArgument(
                this.CONSTRUCTOR_PARAMETER_VALUES, ReflectionBean.class));
    }

    @Test
    public void testInstantiatePublicConstructorWithArgument_valid1() {

        assertNotNull(JstReflectionUtilHelper.instantiateInstanceWithPublicConstructorWithArgument(ReflectionBean.class,
                false, this.CONSTRUCTOR_PARAMETER_VALUES, String.class));

    }

    @Test
    public void testInstantiatePublicConstructorWithArgument_valid2() {

        assertNotNull(JstReflectionUtilHelper.instantiateInstanceWithPublicConstructorWithArgument(
                ReflectionBean.class.getName(), false, this.CONSTRUCTOR_PARAMETER_VALUES, String.class));

    }

    @Test
    public void testInstantiatePublicConstructorWithArgument_valide() {

        assertNotNull(JstReflectionUtilHelper.instantiateInstanceUsingPublicConstructorWithParameters(
                this.CONSTRUCTOR_PARAMETER_VALUES, ReflectionBean.class, true));
    }

    @Test
    public void testInstantiatePublicConstructorWithArgument_validOne() {

        assertNotNull(JstReflectionUtilHelper.instantiateInstanceWithPublicConstructorWithArgument(
                ReflectionBean.class.getName(), this.CONSTRUCTOR_PARAMETER_VALUES));
    }

    @Test
    public void testInstantiatePublicConstructorWithArgument_validTwo() {

        assertNotNull(JstReflectionUtilHelper.instantiateInstanceUsingPublicConstructorWithParameters(
                ReflectionBean.class.getName(), this.CONSTRUCTOR_PARAMETER_VALUES, true));
    }

    @Test
    public void testInvokePublicMethod() {

        assertEquals(JstReflectionUtilHelper.invokePublicMethod("getString", new ReflectionBean()),
                ReflectionBean.STRING);
    }

    @Test()
    public void testInvokePublicMethod_badString() {

        JstReflectionUtilHelper.invokePublicMethod("getFake", ReflectionBean.class);
    }

    @Test()
    public void testInvokePublicMethod_exception() {

        JstReflectionUtilHelper.invokePublicMethod("retrieveException", new ReflectionBean());
    }

    @Test()
    public void testInvokePublicMethod_null() {

        assertThrows(JstReflectionSelfLoggingException.class, () -> {
            JstReflectionUtilHelper.invokePublicMethod(null, ReflectionBean.class);
        });

    }

    public void testInvokePublicMethod_withArgument() {

        JstReflectionUtilHelper.invokePublicMethod("getTextWithArgument", new ReflectionBean(), "argument");
    }

    @Test()
    public void testInvokePublicMethod_withNoArgument() {

        JstReflectionUtilHelper.invokePublicMethod("getTextWithArgument", new ReflectionBean());
    }

    @Test
    public void testRetrieveClass_valid1() {

        JstReflectionUtilHelper.retrieveClass(ReflectionBean.class.getName());
    }

    @Test
    public void testRetrieveClass_valid2() {

        JstReflectionUtilHelper.retrieveClass(ReflectionBean.class.getName());
    }

    @Test
    public void testRetrieveFieldValueByInvokingTheGetter_null() {

        assertNull(JstReflectionUtilHelper.retrieveValueFromMethodName(new ReflectionBean(), "fakeFieldName", true));
    }

    @Test
    public void testRetrieveFieldValueFromMethodName() {

        assertEquals(JstReflectionUtilHelper.retrieveValueFromMethodName(new ReflectionBean(), "getString", true),
                ReflectionBean.STRING);
    }

    @Test()
    public void testRetrieveFieldValueFromMethodName_exception() {

        JstReflectionUtilHelper.retrieveValueFromMethodName(new ReflectionBean(), "retrieveException", false);
    }

    @Test
    public void testRetrieveFieldWithDirectAccess() {

        assertEquals(JstReflectionUtilHelper.retrieveFieldWithDirectAccess(new ReflectionBean(), ReflectionBean.STRING),
                ReflectionBean.STRING);
    }

    @Test()
    public void testRetrieveFieldWithDirectAccess_exception() {

        JstReflectionUtilHelper.retrieveFieldWithDirectAccess(new ReflectionBean(), "fakeFieldName");
    }

    @Test()
    public void testRetrieveFileAsDataInputStream_exception() {

        JstReflectionUtilHelper.retrieveFileAsDataInputStreamAndBeSureToClose("fakeFileName");
    }

    @Test
    public void testRetrieveFileAsDataInputStream_valid() {

        assertNotNull(JstReflectionUtilHelper
                .retrieveFileAsDataInputStreamAndBeSureToClose(ReflectionUtilHelperTest.TEST_FILE_NAME));
    }

    @Test()
    public void testRetrieveFileAsReader_exception() {

        JstReflectionUtilHelper.retrieveFileAsReader("fakeFileName");
    }

    @Test
    public void testRetrieveFileAsReader_valid() {

        assertNotNull(JstReflectionUtilHelper.retrieveFileAsReader(ReflectionUtilHelperTest.TEST_FILE_NAME));
    }

    @Test
    public void testRetrievePublicMethod_fake() {

        assertFalse(JstReflectionUtilHelper
                .retrievePublicMethod(JstReflectionUtilHelper.class, ReflectionUtilHelperTest.FAKE_METHOD_NAME)
                .isPresent());
    }

    @Test
    public void testRetrievePublicMethod_valid() {

        assertTrue(JstReflectionUtilHelper
                .retrievePublicMethod(ReflectionBean.class, ReflectionUtilHelperTest.TEST_METHOD_NAME).isPresent());
    }

    @Test
    public void testVerifyPublicNoArgumentConstructorOnly_false() {
        assertFalse(JstReflectionUtilHelper.verifyPublicNoArgumentConstructorOnly(ReflectionBean.class));
    }
}
