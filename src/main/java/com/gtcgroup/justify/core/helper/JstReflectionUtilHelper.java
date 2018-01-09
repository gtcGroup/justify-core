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
import java.util.Optional;

import com.gtcgroup.justify.core.po.JstStreamPO;

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

    /**
     * @return boolean
     */
    public static boolean containsPublicConstructorNoArgument(final Class<?> clazz) {

        return JstReflectionUtilHelper.retrievePublicConstructorWithNoArgument(clazz).isPresent();
    }

    /**
     * @return boolean
     */
    public static boolean containsPublicNoArgumentConstructorOnly(final Class<?> clazz) {

        return retrievePublicConstructorWithNoArgument(clazz).isPresent();
    }

    /**
     * @return boolean
     */
    public static boolean existsOnClasspath(final String className) {

        return JstReflectionUtilHelper.retrieveClass(className).isPresent();
    }

    /**
     * @return {@link Optional}
     */
    @SuppressWarnings("resource")
    public static Optional<InputStream> getResourceAsStreamAndBeSureToClose(final String resourceName) {

        ClassLoader classLoader = JstReflectionUtilHelper.class.getClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream(resourceName);

        if (null == inputStream) {

            // Try using the thread.
            classLoader = Thread.currentThread().getContextClassLoader();

            inputStream = classLoader.getResourceAsStream(resourceName);
        }
        return Optional.ofNullable(inputStream);
    }

    /**
     * @return {@link Optional}
     */
    @SuppressWarnings("resource")
    public static Optional<JstStreamPO> getResourceAsStreamPoAndBeSureToClose(final String resourceName) {

        ClassLoader classLoader = JstReflectionUtilHelper.class.getClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream(resourceName);

        if (null == inputStream) {

            // Try using the thread.
            classLoader = Thread.currentThread().getContextClassLoader();

            inputStream = classLoader.getResourceAsStream(resourceName);

            if (null == inputStream) {

                return Optional.empty();
            }
        }
        return Optional.of(new JstStreamPO().setInputStream(inputStream).setClassLoader(classLoader));
    }

    /**
     * @return {@link Optional}
     */
    private static Optional<Object> instantiateInstanceFromConstructor(final Constructor<?> constructor,
            final Object... initargs) {

        Object instantiated = null;

        try {

            instantiated = constructor.newInstance(initargs);

        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue
        }
        return Optional.ofNullable(instantiated);
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<Object> instantiateInstanceUsingConstructorWithParameters(final Class<?> clazz,
            final Object[] constructorParameterValues, final Class<?>... constructorParameterTypes) {

        final Optional<Constructor<?>> constructor = retrieveConstructorWithParameters(clazz,
                constructorParameterTypes);

        if (constructor.isPresent()) {

            return instantiateInstanceFromConstructor(constructor.get(), constructorParameterValues);
        }
        return Optional.empty();
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<Object> instantiateInstanceUsingNonPublicConstructorWithNoArgument(final Class<?> clazz) {

        final Optional<Constructor<?>> constructor = retrieveNonPublicConstructorWithNoArgument(clazz);

        if (constructor.isPresent()) {
            return instantiateInstanceFromConstructor(constructor.get());
        }
        return Optional.empty();
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<?> instantiateInstanceUsingPublicConstructorWithNoArgument(final Class<?> clazz) {

        final Optional<Constructor<?>> constructor = retrievePublicConstructorWithNoArgument(clazz);

        if (constructor.isPresent()) {

            return instantiateInstanceFromConstructor(constructor.get());
        }
        return Optional.empty();
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<Object> instantiateInstanceUsingPublicConstructorWithParameters(final Class<?> clazz,
            final Object[] constructorParameterValues, final Class<?>... constructorParameterTypes) {

        final Optional<Constructor<?>> constructor = retrieveConstructorWithParameters(clazz,
                constructorParameterTypes);

        if (constructor.isPresent()) {
            return instantiateInstanceFromConstructor(constructor.get(), constructorParameterValues);
        }
        return Optional.empty();
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<Object> instantiateInstanceUsingPublicConstructorWithParameters(final String className,
            final Object[] constructorParameterValues, final Class<?>... constructorParameterTypes) {

        try {

            final Class<?> clazz = Class.forName(className);

            return instantiateInstanceUsingPublicConstructorWithParameters(clazz, constructorParameterValues,
                    constructorParameterTypes);

        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue.
        }
        return Optional.empty();
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
        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue
        }
        return Optional.empty();
    }

    /**
     * @return {@link Optional}
     */
    private static Optional<Constructor<?>> iterateForNonPublicConstructorNoArgument(
            final Constructor<?>[] constructors) {

        Constructor<?> constructorTarget = null;

        for (final Constructor<?> constructor : constructors) {

            // Determine if there is a no argument constructor.
            if (0 == constructor.getParameterTypes().length) {

                verifyConstructorAccessible(constructor);

                // Determine if it is *not* public.
                if (!Modifier.isPublic(constructor.getModifiers())) {

                    constructorTarget = constructor;
                    break;
                }
            }
        }
        return Optional.ofNullable(constructorTarget);
    }

    /**
     * @return {@link Optional}
     */
    private static Optional<Constructor<?>> iterateForPublicConstructorNoArgument(final Constructor<?>[] constructors) {

        Constructor<?> constructorNoArg = null;

        for (final Constructor<?> constructor : constructors) {

            // Determine if there is a no argument constructor.
            if (0 == constructor.getParameterTypes().length) {

                JstReflectionUtilHelper.verifyConstructorAccessible(constructor);
                constructorNoArg = constructor;
                break;
            }
        }
        return Optional.ofNullable(constructorNoArg);
    }

    /**
     * @return {@link Optional}
     */
    @SuppressWarnings("unchecked")
    public static Optional<Class<?>> retrieveClass(final String className) {

        Class<Object> targetClass = null;

        try {

            targetClass = (Class<Object>) Class.forName(className, true,
                    JstReflectionUtilHelper.class.getClassLoader());

        } catch (@SuppressWarnings("unused") final Exception e) {

            // Continue.
        }
        return Optional.ofNullable(targetClass);
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<Constructor<?>> retrieveConstructorWithParameters(final Class<?> clazz,
            final Class<?>... constructorParameterTypes) {

        Constructor<?> constructor = null;

        try {
            constructor = clazz.getConstructor(constructorParameterTypes);

        } catch (@SuppressWarnings("unused") final Exception e1) {

            try {
                constructor = clazz.getDeclaredConstructor(constructorParameterTypes);
            } catch (@SuppressWarnings("unused") final Exception e2) {
                // Continue.
            }
        }
        return Optional.ofNullable(constructor);

    }

    /**
     * @return {@link Optional}
     */
    public static Optional<Object> retrieveFieldInstanceWithDirectAccess(final Object classInstance,
            final String fieldName) {

        final Optional<Field> field = retrieveFieldWithDirectAccess(classInstance, fieldName);

        if (field.isPresent()) {

            try {
                return Optional.ofNullable(field.get().get(classInstance));

            } catch (@SuppressWarnings("unused") final Exception e) {

                // Checked exception, not likely.
            }
        }
        return Optional.empty();
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<Field> retrieveFieldWithDirectAccess(final Object instance, final String fieldName) {

        try {
            final Field field = instance.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return Optional.of(field);

        } catch (@SuppressWarnings("unused") final Exception e) {

            return Optional.empty();
        }
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<DataInputStream> retrieveFileAsDataInputStreamAndBeSureToClose(final String fileName) {

        final Optional<InputStream> inputStream = JstReflectionUtilHelper
                .retrieveFileAsInputStreamAndBeSureToClose(fileName);

        if (inputStream.isPresent()) {

            return Optional.of(new DataInputStream(inputStream.get()));
        }
        return Optional.empty();
    }

    /**
     * @return {@link Optional}
     */
    @SuppressWarnings("resource")
    public static Optional<InputStream> retrieveFileAsInputStreamAndBeSureToClose(final String resourceName) {

        ClassLoader loader;
        InputStream inputStream = null;

        loader = JstReflectionUtilHelper.class.getClassLoader();

        inputStream = loader.getResourceAsStream(resourceName);

        if (null == inputStream) {

            loader = Thread.currentThread().getContextClassLoader();

            inputStream = loader.getResourceAsStream(resourceName);
        }
        return Optional.ofNullable(inputStream);
    }

    /**
     * @return {@link Optional}
     */
    public static Optional<BufferedReader> retrieveFileAsReader(final String resourceName) {

        final Optional<DataInputStream> dataInputStream = JstReflectionUtilHelper
                .retrieveFileAsDataInputStreamAndBeSureToClose(resourceName);

        if (dataInputStream.isPresent()) {

            return Optional.of(new BufferedReader(new InputStreamReader(dataInputStream.get())));
        }
        return Optional.empty();
    }

    /**
     * @return {@link Optional}
     */
    @SuppressWarnings("unchecked")
    public static Optional<Constructor<?>> retrieveNonPublicConstructorWithNoArgument(final Class<?> clazz) {

        final Constructor<Object>[] constructors = (Constructor<Object>[]) clazz.getDeclaredConstructors();

        return iterateForNonPublicConstructorNoArgument(constructors);
    }

    /**
     * @return {@link Constructor} or null
     */
    public static Optional<Constructor<?>> retrievePublicConstructorWithNoArgument(final Class<?> clazz) {

        final Constructor<?>[] constructors = clazz.getConstructors();

        return JstReflectionUtilHelper.iterateForPublicConstructorNoArgument(constructors);
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
        return Optional.empty();
    }

    private static void verifyConstructorAccessible(final Constructor<?> constructor) {

        constructor.setAccessible(true);
        return;
    }
}
