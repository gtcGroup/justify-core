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

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.base.dependency.NoteDE;
import com.gtcgroup.justify.core.helper.JstReflectionUtilHelper;
import com.gtcgroup.justify.core.helper.dependency.ReflectionBeanHelper;
import com.gtcgroup.justify.core.helper.dependency.ReflectionExceptionDE;
import com.gtcgroup.justify.core.po.JstStreamPO;

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
@SuppressWarnings("all")
public class ReflectionUtilHelperTest {

    private static final String TEST_FILE_NAME = "testfile.xml";

    private final Object[] CONSTRUCTOR_PARAMETER_VALUES = new Object[] { "string value" };

    @Test
    public void testContainsPublicConstructorNoArgument_false2() {

        Assertions.assertFalse(
                JstReflectionUtilHelper.containsPublicConstructorNoArgument(JstReflectionUtilHelper.class));
    }

    @Test
    public void testContainsPublicConstructorNoArgument_valid1() {

        Assertions.assertTrue(JstReflectionUtilHelper.containsPublicConstructorNoArgument(NoteDE.class));
    }

    @Test
    public void testContainsPublicConstructorNoArgument_valid2() {

        Assertions.assertTrue(JstReflectionUtilHelper.containsPublicConstructorNoArgument(NoteDE.class));
    }

    @Test
    public void testExists_false() {

        Assertions.assertFalse(JstReflectionUtilHelper.existsOnClasspath("FakeClassName"));
    }

    @Test
    public void testExists_true() {

        Assertions.assertTrue(JstReflectionUtilHelper.existsOnClasspath(JstReflectionUtilHelper.class.getName()));
    }

    @Test
    public void testFieldInstanceWithDirectAccess() {

        JstReflectionUtilHelper.retrieveFieldInstanceWithDirectAccess(new NoteDE(), "text", false);
    }

    @Test()
    public void testFieldInstanceWithDirectAccess_exception() {

        JstReflectionUtilHelper.retrieveFieldInstanceWithDirectAccess(new NoteDE(), "fakeFieldName", false);
    }

    @Test
    public void testGetResourceAsStream() {
        final JstStreamPO jstStreamPO = JstReflectionUtilHelper.getResourceAsStream("log4j2.xml", false);
        Assertions.assertThat(jstStreamPO.getInputStreamToBeClosed()).isNotNull();
        Assertions.assertThat(jstStreamPO.getClassLoader()).isNotNull();
    }

    @Test()
    public void testGetResourceAsStream_exception() {
        JstReflectionUtilHelper.getResourceAsStream("fakeResourceName", false);
    }

    @Test
    public void testGetResourceAsStream_exception_suppress() {
        Assertions.assertThat(JstReflectionUtilHelper.getResourceAsStream("fakeResourceName", true)).isNull();
    }

    @Test
    public void testInstantiateNonPublicConstructorWithArgument_constructorListEmpty() {

        Assertions.assertNull(JstReflectionUtilHelper.instantiate(this.CONSTRUCTOR_PARAMETER_VALUES,
                new ArrayList<Constructor<?>>()));

    }

    @Test
    public void testInstantiateNonPublicConstructorWithArgument_constructorListNull() {

        final List<Constructor<?>> constructorList = new ArrayList<>();

        constructorList.add(null);

        Assertions.assertNull(
                JstReflectionUtilHelper.instantiate(this.CONSTRUCTOR_PARAMETER_VALUES, constructorList, true));

    }

    @Test
    public void testInstantiatePublicConstructorNoArgument() {

        final Constructor<?> constructor = JstReflectionUtilHelper
                .retrievePublicConstructorNoArgument(ReflectionBeanHelper.class);

        Assertions.assertNotNull(
                JstReflectionUtilHelper.instantiatePublicConstructorNoArgument(ReflectionBeanHelper.class));
    }

    @Test
    public void testInstantiatePublicConstructorNoArgument__valid() {

        Assertions.assertNotNull(
                JstReflectionUtilHelper.instantiatePublicConstructorNoArgument(ReflectionBeanHelper.class, true));
    }

    @Test()
    public void testInstantiatePublicConstructorNoArgument_exception() {

        Assertions.assertThat(
                JstReflectionUtilHelper.instantiatePublicConstructorNoArgument(ReflectionExceptionDE.class, false))
                .isNotNull();
    }

    @Test()
    public void testInstantiatePublicConstructorNoArgument_exception_null() {

        JstReflectionUtilHelper.instantiatePublicConstructorNoArgument(JstReflectionUtilHelper.class);
    }

    @Test
    public void testInstantiatePublicConstructorNoArgument_false() {

        Assertions.assertThat(
                JstReflectionUtilHelper.instantiatePublicConstructorNoArgument(ReflectionBeanHelper.class, false))
                .isNotNull();
    }

    @Test
    public void testInstantiatePublicConstructorNoArgument_suppress() {

        final Constructor<?> constructor = JstReflectionUtilHelper
                .retrievePublicConstructorNoArgument(ReflectionBeanHelper.class);

        Assertions.assertNotNull(
                JstReflectionUtilHelper.instantiatePublicConstructorNoArgument(ReflectionBeanHelper.class, false));
    }

    @Test
    public void testInstantiatePublicConstructorNoArgument_valid1() {

        Assertions.assertNotNull(
                JstReflectionUtilHelper.instantiatePublicConstructorNoArgument(ReflectionBeanHelper.class));
    }

    @Test
    public void testInstantiatePublicConstructorNoArgument_valid2() {

        JstReflectionUtilHelper.instantiatePublicConstructorNoArgument(ReflectionBeanHelper.class, true);
    }

    @Test
    public void testInstantiatePublicConstructorWithArgument_null() {

        Assertions.assertNull(JstReflectionUtilHelper.instantiatePublicConstructorWithArgument(
                ReflectionBeanHelper.class.getName(), true, null, String.class));

    }

    @Test
    public void testInstantiatePublicConstructorWithArgument_null1() {

        Assertions.assertNull(JstReflectionUtilHelper.instantiatePublicConstructorWithArgument("com.gtcgroup.FakeName",
                this.CONSTRUCTOR_PARAMETER_VALUES, true));
    }

    @Test()
    public void testInstantiatePublicConstructorWithArgument_null2() {

        Assertions.assertNull(JstReflectionUtilHelper.instantiatePublicConstructorWithArgument("com.gtcgroup.FakeName",
                this.CONSTRUCTOR_PARAMETER_VALUES, false));
    }

    @Test
    public void testInstantiatePublicConstructorWithArgument_valid() {

        Assertions.assertNotNull(JstReflectionUtilHelper.instantiatePublicConstructorWithArgument(
                this.CONSTRUCTOR_PARAMETER_VALUES, ReflectionBeanHelper.class));
    }

    @Test
    public void testInstantiatePublicConstructorWithArgument_valid1() {

        Assertions.assertNotNull(JstReflectionUtilHelper.instantiatePublicConstructorWithArgument(
                ReflectionBeanHelper.class, false, this.CONSTRUCTOR_PARAMETER_VALUES, String.class));

    }

    @Test
    public void testInstantiatePublicConstructorWithArgument_valid2() {

        Assertions.assertNotNull(JstReflectionUtilHelper.instantiatePublicConstructorWithArgument(
                ReflectionBeanHelper.class.getName(), false, this.CONSTRUCTOR_PARAMETER_VALUES, String.class));

    }

    @Test
    public void testInstantiatePublicConstructorWithArgument_valide() {

        Assertions.assertNotNull(JstReflectionUtilHelper.instantiatePublicConstructorWithArgument(
                this.CONSTRUCTOR_PARAMETER_VALUES, ReflectionBeanHelper.class, true));
    }

    @Test
    public void testInstantiatePublicConstructorWithArgument_validOne() {

        Assertions.assertNotNull(JstReflectionUtilHelper.instantiatePublicConstructorWithArgument(
                ReflectionBeanHelper.class.getName(), this.CONSTRUCTOR_PARAMETER_VALUES));
    }

    @Test
    public void testInstantiatePublicConstructorWithArgument_validTwo() {

        Assertions.assertNotNull(JstReflectionUtilHelper.instantiatePublicConstructorWithArgument(
                ReflectionBeanHelper.class.getName(), this.CONSTRUCTOR_PARAMETER_VALUES, true));
    }

    @Test
    public void testInvokePublicMethod() {

        Assertions.assertThat(JstReflectionUtilHelper.invokePublicMethod("getString", new ReflectionBeanHelper()))
                .isEqualTo(ReflectionBeanHelper.STRING);
    }

    @Test()
    public void testInvokePublicMethod_badString() {

        JstReflectionUtilHelper.invokePublicMethod("getFake", ReflectionBeanHelper.class);
    }

    @Test()
    public void testInvokePublicMethod_exception() {

        JstReflectionUtilHelper.invokePublicMethod("retrieveException", new ReflectionBeanHelper());
    }

    @Test
    public void testRetrieveClass_valid1() throws ClassNotFoundException {

        JstReflectionUtilHelper.retrieveClass(ReflectionBeanHelper.class.getName());
    }

    @Test
    public void testRetrieveClass_valid2() throws ClassNotFoundException {

        JstReflectionUtilHelper.retrieveClass(ReflectionBeanHelper.class.getName());
    }

    @Test
    public void testRetrieveFieldValueByInvokingTheGetter_null() {

        Assertions.assertThat(JstReflectionUtilHelper.retrieveFieldValueFromMethodName(new ReflectionBeanHelper(),
                "fakeFieldName", true)).isNull();
    }

    @Test
    public void testRetrieveFieldValueFromMethodName() {

        Assertions.assertThat(
                JstReflectionUtilHelper.retrieveFieldValueFromMethodName(new ReflectionBeanHelper(), "getString", true))
                .isEqualTo("string");
    }

    @Test()
    public void testRetrieveFieldValueFromMethodName_exception() {

        JstReflectionUtilHelper.retrieveFieldValueFromMethodName(new ReflectionBeanHelper(), "retrieveException",
                false);
    }

    @Test
    public void testRetrieveFieldWithDirectAccess() {

        Assertions.assertThat(JstReflectionUtilHelper
                .retrieveFieldWithDirectAccess(new ReflectionBeanHelper(), "string").equals("string"));
    }

    @Test()
    public void testRetrieveFieldWithDirectAccess_exception() {

        JstReflectionUtilHelper.retrieveFieldWithDirectAccess(new ReflectionBeanHelper(), "fakeFieldName");
    }

    @Test()
    public void testRetrieveFileAsDataInputStream_exception() {

        JstReflectionUtilHelper.retrieveFileAsDataInputStreamAndBeSureToClose("fakeFileName");
    }

    @Test
    public void testRetrieveFileAsDataInputStream_valid() {

        Assertions.assertNotNull(JstReflectionUtilHelper
                .retrieveFileAsDataInputStreamAndBeSureToClose(ReflectionUtilHelperTest.TEST_FILE_NAME));
    }

    @Test()
    public void testRetrieveFileAsReader_exception() {

        JstReflectionUtilHelper.retrieveFileAsReader("fakeFileName");
    }

    @Test
    public void testRetrieveFileAsReader_valid() {

        Assertions.assertNotNull(JstReflectionUtilHelper.retrieveFileAsReader(ReflectionUtilHelperTest.TEST_FILE_NAME));
    }

    @Test()
    public void testRetrieveFileAsScanner_exception() {

        JstReflectionUtilHelper.retrieveFileAsScanner("fakeFileName");
    }

    @Test
    public void testRetrieveFileAsScanner_valid() {

        Assertions
                .assertNotNull(JstReflectionUtilHelper.retrieveFileAsScanner(ReflectionUtilHelperTest.TEST_FILE_NAME));
    }

    @Test()
    public void testRetrieveMethodProcess_exception() {

        JstReflectionUtilHelper.retrievePublicMethod(JstReflectionUtilHelper.class, "fakeFileName", false);
    }

    @Test
    public void testRetrieveMethodProcess_null() {

        JstReflectionUtilHelper.retrievePublicMethod(JstReflectionUtilHelper.class, "fakeFileName", true);
    }

    @Test()
    public void testRetrievePublicMethodInstance_null() {

        JstReflectionUtilHelper.invokePublicMethod(null, ReflectionBeanHelper.class);
    }

    public void testRetrievePublicMethodInstance_withArgument() {

        JstReflectionUtilHelper.invokePublicMethod("getTextWithArgument", new ReflectionBeanHelper(), "argument");
    }

    @Test()
    public void testRetrievePublicMethodInstance_withNoArgument() {

        JstReflectionUtilHelper.invokePublicMethod("getTextWithArgument", new ReflectionBeanHelper());
    }

    @Test
    public void testVerifyPublicNoArgumentConstructorOnly_false() {
        Assertions.assertThat(JstReflectionUtilHelper.verifyPublicNoArgumentConstructorOnly(ReflectionBeanHelper.class))
                .isFalse();
    }
}
