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

package com.gtcgroup.justify.core.helper;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.gtcgroup.justify.core.exception.JstReflectionSelfLoggingException;
import com.gtcgroup.justify.core.exception.JstSelfLoggingExceptionPO;
import com.gtcgroup.justify.core.po.JstStreamPO;
import com.gtcgroup.justify.core.test.exception.internal.JustifyTestingException;

/**
 * This Util Helper class provides convenience methods for reflection
 * processing.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public enum JstReflectionUtilHelper {

    INSTANCE;

    private static final String CLASS_NAME = JstReflectionUtilHelper.class.getName();

    /**
     * @return boolean
     */
    public static boolean containsPublicConstructorNoArgument(final Class<?> clazz) {

        return null != JstReflectionUtilHelper.retrievePublicConstructorNoArgument(clazz);
    }

    /**
     * @return boolean
     */
    public static boolean existsOnClasspath(final String className) {

        return null != JstReflectionUtilHelper.retrieveClass(className, true);
    }

    /**
     * @return {@link JstStreamPO} or null
     */
    @SuppressWarnings("resource")
    public static JstStreamPO getResourceAsStream(final String resourceName, final boolean suppressException) {

        ClassLoader classLoader = JstReflectionUtilHelper.class.getClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream(resourceName);

        if (null == inputStream) {

            // Try the thread.
            classLoader = Thread.currentThread().getContextClassLoader();

            inputStream = classLoader.getResourceAsStream(resourceName);

            if (null == inputStream) {

                throwRuntimeException("The resource [" + resourceName + "] could not be acquired.", suppressException);

                return null;
            }
        }
        return new JstStreamPO().setInputStream(inputStream).setClassLoader(classLoader);
    }

    /**
     * @return instance or null
     */
    public static Object instantiate(final Object[] constructorParameterValues, final boolean suppressException,
            final Constructor<?>[] constructors) {

        final List<Constructor<?>> constructorList = new ArrayList<>(Arrays.asList(constructors));

        return JstReflectionUtilHelper.instantiate(constructorParameterValues, constructorList, suppressException);
    }

    /**
     * @return instance or null
     */
    public static Object instantiate(final Object[] constructorParameterValues,
            final List<Constructor<?>> constructorList) {

        return JstReflectionUtilHelper.instantiate(constructorParameterValues, constructorList, false);
    }

    /**
     * @return instance or null
     */
    public static Object instantiate(final Object[] constructorParameterValues,
            final List<Constructor<?>> constructorList, final boolean suppressException) {

        Object object = null;

        for (int i = 0; i < constructorList.size(); i++) {

            try {

                object = constructorList.get(i).newInstance(constructorParameterValues);

                // Success - break out of loop.
                break;

            } catch (final Exception e) {

                // Determine if this is the last constructor.
                if (i + 1 >= constructorList.size()) {

                    throwRuntimeException(e.getMessage(), suppressException);
                }

                // Else - continue with another constructor.

            }
        }
        return object;
    }

    /**
     * @return instance or null
     */
    public static Object instantiateInstanceWithNonPublicConstructorNoArgument(final Class<Object> clazz) {

        return instantiateNonPublicConstructorNoArgument(clazz, false);
    }

    /**
     * @return instance or null
     */
    public static Object instantiateInstanceWithPublicConstructorNoArgument(final Class<?> clazz,
            final boolean suppressException) {

        try {
            return clazz.newInstance();

        } catch (final Exception e) {

            // Probably never invoked.
            throwRuntimeException(e.getMessage(), suppressException);
        }
        // Probably never invoked.
        return null;
    }

    /**
     * @return instance or null
     */
    public static Object instantiateInstanceWithPublicConstructorNoArgument(final Constructor<?> constructor,
            final boolean suppressException) {

        // Declare.
        Object objInstantiated = null;
        final Object[] intArgs = null;

        try {
            // Check for accessibility.
            JstReflectionUtilHelper.verifyConstructorAccessible(constructor);

            objInstantiated = constructor.newInstance(intArgs);

        } catch (final Exception e) {

            throwRuntimeException(e.getMessage(), suppressException);
        }

        return objInstantiated;
    }

    /**
     * @return instance or null
     */
    public static Object instantiateInstanceWithPublicConstructorWithArgument(final Class<?> clazz,
            final boolean suppressException, final Object[] constructorParameterValues,
            final Class<?>... parameterTypes) {

        return JstReflectionUtilHelper.instantiateInstanceWithPublicConstructorWithArgument(clazz.getName(),
                suppressException, constructorParameterValues, parameterTypes);
    }

    /**
     * @return instance or null
     */
    public static Object instantiateInstanceWithPublicConstructorWithArgument(final Object[] constructorParameterValues,
            final Class<?> clazz) {

        return JstReflectionUtilHelper.instantiateInstanceWithPublicConstructorWithArgument(constructorParameterValues,
                clazz, false);
    }

    /**
     * @return instance or null
     */
    public static Object instantiateInstanceWithPublicConstructorWithArgument(final Object[] constructorParameterValues,
            final Class<?> clazz, final boolean suppressException) {

        final Constructor<?>[] constructors = clazz.getConstructors();

        return JstReflectionUtilHelper.instantiate(constructorParameterValues, suppressException, constructors);
    }

    /**
     * @return instance or null
     */
    public static Object instantiateInstanceWithPublicConstructorWithArgument(final String className,
            final boolean suppressException, final Object[] constructorParameterValues,
            final Class<?>... parameterTypes) {

        Object object = null;

        try {

            final Class<?> clazz = Class.forName(className);

            final Constructor<?> constructor = clazz.getDeclaredConstructor(parameterTypes);

            // Ensure accessibility.
            JstReflectionUtilHelper.verifyConstructorAccessible(constructor);

            object = constructor.newInstance(constructorParameterValues);

        } catch (final Exception e) {

            throwRuntimeException(e.getMessage(), suppressException);
        }
        return object;
    }

    /**
     * @return instance or null
     */
    public static Object instantiateInstanceWithPublicConstructorWithArgument(final String className,
            final Object[] constructorParameterValues) {

        return JstReflectionUtilHelper.instantiateInstanceWithPublicConstructorWithArgument(className,
                constructorParameterValues, false);
    }

    /**
     * @return instance or null
     */
    public static Object instantiateInstanceWithPublicConstructorWithArgument(final String className,
            final Object[] constructorParameterValues, final boolean suppressException) {

        Class<Object> clazz;

        clazz = JstReflectionUtilHelper.retrieveClass(className, suppressException);

        if (null == clazz) {
            return null;
        }

        // Return instantiated instance or null.
        return JstReflectionUtilHelper.instantiateInstanceWithPublicConstructorWithArgument(constructorParameterValues,
                clazz, suppressException);
    }

    /**
     * This method returns an instance of type <code>Object</code> by invoking a
     * non-public no-argument constructor. A class may have many constructors.
     * Support is provided for suppressing an exception.
     *
     * @return instance or null
     */
    public static Object instantiateNonPublicConstructorNoArgument(final Class<Object> clazz,
            final boolean suppressException) {

        return instantiateInstanceWithPublicConstructorNoArgument(retrieveNonPublicConstructorNoArgument(clazz),
                suppressException);
    }

    /**
     * @return instance or null
     */
    public static Object instantiatePublicConstructorNoArgument(final Class<?> clazz) {

        return JstReflectionUtilHelper.instantiateInstanceWithPublicConstructorNoArgument(clazz, false);
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<Object> invokePublicMethod(final String methodName, final Object instance,
            final Object... args) {

        final Optional<Method> method = JstReflectionUtilHelper.retrievePublicMethod(instance.getClass(), methodName);

        try {
            if (method.isPresent()) {
                return Optional.ofNullable(method.get().invoke(instance, args));
            }
        } catch (final Exception e) {

            throwRuntimeException(e.getMessage(), false);
        }
        return Optional.ofNullable(null);
    }

    /**
     * This method iterates through an array of constructors in pursuit of a no
     * argument version.
     *
     * @return {@link Constructor}
     */
    private static Constructor<?> iterateForNonPublicConstructorNoArgument(final Constructor<?>[] constructors) {

        Constructor<?> constructorTemp = null;

        for (final Constructor<?> constructor : constructors) {

            // Determine if there is a no argument constructor.
            if (0 == constructor.getParameterTypes().length) {

                // Determine if it is accessible.
                verifyConstructorAccessible(constructor);

                // Determine if it is *not* public.
                if (!Modifier.isPublic(constructor.getModifiers())) {

                    constructorTemp = constructor;
                    break;
                }
            }
        }
        return constructorTemp;
    }

    /**
     * @return {@link Constructor}
     */
    private static Constructor<Object> iterateForPublicConstructorNoArgument(final Constructor<Object>[] constructors) {

        Constructor<Object> constructorTemp = null;

        for (final Constructor<Object> constructor : constructors) {

            // Determine if there is a no argument constructor.
            if (0 == constructor.getParameterTypes().length) {

                // Determine if it is accessible.
                JstReflectionUtilHelper.verifyConstructorAccessible(constructor);

                constructorTemp = constructor;
                break;
            }
        }
        return constructorTemp;
    }

    /**
     * @return {@link Class}
     */
    public static Class<Object> retrieveClass(final String className) {

        return JstReflectionUtilHelper.retrieveClass(className, false);
    }

    /**
     * @return {@link Class} of null
     */
    @SuppressWarnings("unchecked")
    public static Class<Object> retrieveClass(final String className, final boolean suppressException) {

        Class<Object> targetClass = null;

        try {

            targetClass = (Class<Object>) Class.forName(className, true,
                    JstReflectionUtilHelper.class.getClassLoader());

        } catch (final Exception e) {

            throwRuntimeException(e.getMessage(), suppressException);
        }
        return targetClass;
    }

    /**
     * @return {@link Field}
     */
    public static Object retrieveFieldInstanceWithDirectAccess(final Object classInstance, final String fieldName,
            final boolean suppressException) {

        final Field field = retrieveFieldWithDirectAccess(classInstance, fieldName);

        Object fieldInstance = null;

        try {
            fieldInstance = field.get(classInstance);

        } catch (final Exception e) {

            throwRuntimeException(e.getMessage(), suppressException);
        }

        return fieldInstance;
    }

    /**
     * @return {@link Field}
     */
    public static Object retrieveFieldValueFromMethodName(final Object instance, final String methodName,
            final boolean suppressException) {

        final Method[] methods = instance.getClass().getMethods();

        for (final Method method : methods) {

            if (method.getName().equals(methodName)) {

                try {
                    return method.invoke(instance);

                } catch (final Exception e) {

                    throwRuntimeException(e.getMessage(), suppressException);
                }
            }
        }

        throwRuntimeException("The method name [" + methodName + "] could not be found on the instance provided ["
                + instance.getClass().getSimpleName() + "].", suppressException);

        return null;

    }

    /**
     * @return {@link Field}
     */
    public static Field retrieveFieldWithDirectAccess(final Object instance, final String fieldName) {

        Field field = null;

        try {
            field = instance.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

        } catch (final Exception e) {

            throwRuntimeException(e.getMessage(), false);
        }

        return field;
    }

    /**
     * @return {@link DataInputStream}
     */
    public static DataInputStream retrieveFileAsDataInputStreamAndBeSureToClose(final String fileName) {

        final InputStream inputStream = JstReflectionUtilHelper.retrieveFileAsInputStreamAndBeSureToClose(fileName);

        return new DataInputStream(inputStream);
    }

    /**
     * @return {@link InputStream}
     */
    @SuppressWarnings("resource")
    public static InputStream retrieveFileAsInputStreamAndBeSureToClose(final String resourceName) {

        ClassLoader loader;
        InputStream inputStream = null;

        loader = JstReflectionUtilHelper.class.getClassLoader();

        inputStream = loader.getResourceAsStream(resourceName);

        if (null == inputStream) {

            loader = Thread.currentThread().getContextClassLoader();

            inputStream = loader.getResourceAsStream(resourceName);

            if (null == inputStream) {

                throwRuntimeException("Input stream is null", false);
            }
        }
        return inputStream;
    }

    /**
     * @return {@link BufferedReader}
     */
    public static BufferedReader retrieveFileAsReader(final String resourceName) {

        final DataInputStream dataInputStream = JstReflectionUtilHelper
                .retrieveFileAsDataInputStreamAndBeSureToClose(resourceName);

        return new BufferedReader(new InputStreamReader(dataInputStream));
    }

    /**
     * This method returns an accessible no-argument <code>Constructor</code> or
     * <code>null</code>.
     *
     * @return {@link Constructor} or null.
     */
    @SuppressWarnings("unchecked")
    public static Constructor<Object> retrieveNonPublicConstructorNoArgument(final Class<Object> clazz) {

        final Constructor<Object>[] constructors = (Constructor<Object>[]) clazz.getDeclaredConstructors();

        return (Constructor<Object>) iterateForNonPublicConstructorNoArgument(constructors);
    }

    /**
     * @return {@link Constructor} or null
     */
    @SuppressWarnings("unchecked")
    public static Constructor<Object> retrievePublicConstructorNoArgument(final Class<?> clazz) {

        final Constructor<?>[] constructors = clazz.getConstructors();

        return JstReflectionUtilHelper.iterateForPublicConstructorNoArgument((Constructor<Object>[]) constructors);
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<Method> retrievePublicMethod(final Class<?> clazz, final String methodName) {

        final Method[] methods = clazz.getMethods();

        for (final Method method : methods) {

            if (methodName.equalsIgnoreCase(method.getName())) {

                return Optional.of(method);
            }
        }
        return Optional.ofNullable(null);
    }

    /**
     * @throws JustifyTestingException
     */
    private static void throwRuntimeException(final String message, final boolean suppressException) {

        if (!suppressException) {
            throw new JstReflectionSelfLoggingException(
                    JstSelfLoggingExceptionPO.withMessage(message).withClassName(JstReflectionUtilHelper.CLASS_NAME));
        }
    }

    public static void verifyConstructorAccessible(final Constructor<?> constructor) {

        constructor.setAccessible(true);
        return;
    }

    /**
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean verifyPublicNoArgumentConstructorOnly(final Class<?> clazz) {

        final Constructor<Object>[] constructors = (Constructor<Object>[]) clazz.getConstructors();

        return constructors.length == 1
                && null != JstReflectionUtilHelper.iterateForPublicConstructorNoArgument(constructors);
    }
}
