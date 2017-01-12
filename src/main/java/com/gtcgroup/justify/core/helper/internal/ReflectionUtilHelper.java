/*
 * [Licensed per the Open Source "MIT License".]
 *
 * Copyright (c) 2006 - 2016 by
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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.gtcgroup.justify.core.exception.internal.TestingRuntimeException;
import com.gtcgroup.justify.core.po.internal.StreamPO;

/**
 * This Util Helper class provides convenience methods for reflection
 * processing.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2016 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
public enum ReflectionUtilHelper {

	@SuppressWarnings("javadoc")
	INSTANCE;

	/**
	 * @return boolean
	 */
	public static boolean containsPublicConstructorNoArgument(final Class<?> clazz) {

		return null != ReflectionUtilHelper.retrievePublicConstructorNoArgument(clazz);
	}

	/**
	 * @return Object
	 */
	public static Object copy(final Object instance) {

		ObjectOutputStream out = null;
		Object object = null;

		try {
			// Write the object out to a byte array
			final ByteArrayOutputStream bos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(bos);
			out.writeObject(instance);
			out.flush();
			out.close();

			// Make an input stream from the byte array and read
			// a copy of the object back in.
			final ObjectInputStream objectInputStream = new ObjectInputStream(
					new ByteArrayInputStream(bos.toByteArray()));
			object = objectInputStream.readObject();

		} catch (final Exception e) {

			// Probably never invoked.
			throw new TestingRuntimeException(e);
		} finally {

			if (null != out) {
				try {
					out.close();
				} catch (@SuppressWarnings("unused") final Exception e) {
					// Ignore.
				}
			}
		}
		return object;
	}

	/**
	 * @return boolean
	 */
	public static boolean existsOnClasspath(final String className) {

		return null != ReflectionUtilHelper.retrieveClass(className, true);
	}

	/**
	 * @return {@link StreamPO} or null
	 */
	@SuppressWarnings("resource")
	public static StreamPO getResourceAsStream(final String resourceName, final boolean suppressException) {

		ClassLoader classLoader = ReflectionUtilHelper.class.getClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(resourceName);

		if (null == inputStream) {

			// Try the thread.
			classLoader = Thread.currentThread().getContextClassLoader();

			inputStream = classLoader.getResourceAsStream(resourceName);

			if (null == inputStream) {

				throwTestingRuntimeException("The resources [" + resourceName + "] could not be acquired.",
						suppressException);

				return null;
			}
		}

		final StreamPO streamPO = new StreamPO().setInputStream(inputStream).setClassLoader(classLoader);

		return streamPO;
	}

	/**
	 * @return instance or null
	 */
	public static Object instantiate(final Object[] constructorParameterValues, final boolean suppressException,
			final Constructor<?>[] constructors) {

		final List<Constructor<?>> constructorList = new ArrayList<Constructor<?>>(Arrays.asList(constructors));

		return ReflectionUtilHelper.instantiate(constructorParameterValues, constructorList, suppressException);
	}

	/**
	 * @return instance or null
	 */
	public static Object instantiate(final Object[] constructorParameterValues,
			final List<Constructor<?>> constructorList) {

		return ReflectionUtilHelper.instantiate(constructorParameterValues, constructorList, false);
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

			} catch (final Throwable e) {

				// Determine if this is the last constructor.
				if (i + 1 >= constructorList.size()) {

					throwTestingRuntimeException(e.getMessage(), suppressException);
				}

				// Else - continue with another constructor.

			}
		}
		return object;
	}

	/**
	 * This method returns an instance of type <code>Object</code> by invoking a
	 * non-public no-argument constructor. A class may have many constructors.
	 *
	 * @return instance or null
	 */
	public static Object instantiateNonPublicConstructorNoArgument(final Class<?> clazz) {

		return instantiateNonPublicConstructorNoArgument(clazz, false);
	}

	/**
	 * This method returns an instance of type <code>Object</code> by invoking a
	 * non-public no-argument constructor. A class may have many constructors.
	 * Support is provided for suppressing an exception.
	 *
	 * @return instance or null
	 */
	public static Object instantiateNonPublicConstructorNoArgument(final Class<?> clazz,
			final boolean suppressException) {

		return instantiatePublicConstructorNoArgument(retrieveNonPublicConstructorNoArgument(clazz), suppressException);
	}

	/**
	 * @return instance or null
	 */
	public static Object instantiatePublicConstructorNoArgument(final Class<?> clazz) {

		return ReflectionUtilHelper.instantiatePublicConstructorNoArgument(clazz, false);
	}

	/**
	 * @return instance or null
	 */
	public static Object instantiatePublicConstructorNoArgument(final Class<?> clazz, final boolean suppressException) {

		try {

			final Object instance = clazz.newInstance();
			return instance;

		} catch (final Throwable e) {

			// Probably never invoked.
			throwTestingRuntimeException(e.getMessage(), suppressException);
		}
		// Probably never invoked.
		return null;
	}

	/**
	 * @return instance or null
	 */
	public static Object instantiatePublicConstructorNoArgument(final Constructor<?> constructor) {

		return ReflectionUtilHelper.instantiatePublicConstructorNoArgument(constructor, false);
	}

	/**
	 * @return instance or null
	 */
	public static Object instantiatePublicConstructorNoArgument(final Constructor<?> constructor,
			final boolean suppressException) {

		// Declare.
		Object objInstantiated = null;
		final Object[] intArgs = null;

		try {
			// Check for accessibility.
			ReflectionUtilHelper.verifyConstructorAccessible(constructor);

			objInstantiated = constructor.newInstance(intArgs);

		} catch (final Exception e) {

			throwTestingRuntimeException(e.getMessage(), suppressException);
		}

		return objInstantiated;
	}

	/**
	 * @return instance or null
	 */
	public static Object instantiatePublicConstructorWithArgument(final Class<?> clazz, final boolean suppressException,
			final Object constructorParameterValues[], final Class<?>... parameterTypes) {

		return ReflectionUtilHelper.instantiatePublicConstructorWithArgument(clazz.getName(), suppressException,
				constructorParameterValues, parameterTypes);
	}

	/**
	 * @return instance or null
	 */
	public static Object instantiatePublicConstructorWithArgument(final Object[] constructorParameterValues,
			final Class<?> clazz) {

		final Object instance = ReflectionUtilHelper
				.instantiatePublicConstructorWithArgument(constructorParameterValues, clazz, false);

		return instance;
	}

	/**
	 * @return instance or null
	 */
	public static Object instantiatePublicConstructorWithArgument(final Object[] constructorParameterValues,
			final Class<?> clazz, final boolean suppressException) {

		final Constructor<?>[] constructors = clazz.getConstructors();

		return ReflectionUtilHelper.instantiate(constructorParameterValues, suppressException, constructors);
	}

	/**
	 * @return instance or null
	 */
	public static Object instantiatePublicConstructorWithArgument(final String className,
			final boolean suppressException, final Object constructorParameterValues[],
			final Class<?>... parameterTypes) {

		Object object = null;

		try {

			final Class<?> clazz = Class.forName(className);

			final Constructor<?> constructor = clazz.getDeclaredConstructor(parameterTypes);

			// Ensure accessibility.
			ReflectionUtilHelper.verifyConstructorAccessible(constructor);

			object = constructor.newInstance(constructorParameterValues);

		} catch (final Exception e) {

			throwTestingRuntimeException(e.getMessage(), suppressException);
		}
		return object;
	}

	/**
	 * @return instance or null
	 */
	public static Object instantiatePublicConstructorWithArgument(final String className,
			final Object[] constructorParameterValues) {

		final Object instance = ReflectionUtilHelper.instantiatePublicConstructorWithArgument(className,
				constructorParameterValues, false);

		return instance;
	}

	/**
	 * @return instance or null
	 */
	public static Object instantiatePublicConstructorWithArgument(final String className,
			final Object[] constructorParameterValues, final boolean suppressException) {

		Class<Object> clazz;

		clazz = ReflectionUtilHelper.retrieveClass(className, suppressException);

		if (null == clazz) {
			return null;
		}

		final Object instance = ReflectionUtilHelper
				.instantiatePublicConstructorWithArgument(constructorParameterValues, clazz, suppressException);

		// Return instantiated instance or null.
		return instance;
	}

	/**
	 * @return {@link Object}
	 */
	public static Object invokePublicMethod(final String methodName, final Object instance, final Object... args) {

		final Method method = ReflectionUtilHelper.retrievePublicMethod(instance.getClass(), methodName, false);

		try {

			return method.invoke(instance, args);

		} catch (final Exception e) {

			throwTestingRuntimeException(e.getMessage(), false);
		}
		return method;
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
	private static Constructor<?> iterateForPublicConstructorNoArgument(final Constructor<?>[] constructors) {

		Constructor<?> constructorTemp = null;

		for (final Constructor<?> constructor : constructors) {

			// Determine if there is a no argument constructor.
			if (0 == constructor.getParameterTypes().length) {

				// Determine if it is accessible.
				ReflectionUtilHelper.verifyConstructorAccessible(constructor);

				constructorTemp = constructor;
				break;
			}
		}
		return constructorTemp;
	}

	/**
	 * @return {@link Class}
	 * @throws ClassNotFoundException
	 */
	public static Class<Object> retrieveClass(final String className) throws ClassNotFoundException {

		return ReflectionUtilHelper.retrieveClass(className, false);
	}

	/**
	 * @return {@link Class} of null
	 */
	@SuppressWarnings("unchecked")
	public static Class<Object> retrieveClass(final String className, final boolean suppressException) {

		Class<Object> targetClass = null;

		try {

			targetClass = (Class<Object>) Class.forName(className, true, ReflectionUtilHelper.class.getClassLoader());

		} catch (final Exception e) {

			throwTestingRuntimeException(e.getMessage(), suppressException);
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

			throwTestingRuntimeException(e.getMessage(), suppressException);
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

					throwTestingRuntimeException(e.getMessage(), suppressException);
				}
			}
		}

		throwTestingRuntimeException("The method name [" + methodName
				+ "] could not be found on the instance provided [" + instance.getClass().getSimpleName() + "].",
				suppressException);

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

			throwTestingRuntimeException(e.getMessage(), false);
		}

		return field;
	}

	/**
	 * @return {@link DataInputStream}
	 */
	public static DataInputStream retrieveFileAsDataInputStreamAndBeSureToClose(final String fileName) {

		final InputStream inputStream = ReflectionUtilHelper.retrieveFileAsInputStreamAndBeSureToClose(fileName);

		final DataInputStream dataInputStream = new DataInputStream(inputStream);

		return dataInputStream;
	}

	/**
	 * @return {@link InputStream}
	 */
	@SuppressWarnings("resource")
	public static InputStream retrieveFileAsInputStreamAndBeSureToClose(final String resourceName) {

		ClassLoader loader;
		InputStream inputStream = null;

		loader = ReflectionUtilHelper.class.getClassLoader();

		inputStream = loader.getResourceAsStream(resourceName);

		if (null == inputStream) {

			loader = Thread.currentThread().getContextClassLoader();

			inputStream = loader.getResourceAsStream(resourceName);

			if (null == inputStream) {

				throwTestingRuntimeException("Input stream is null", false);
			}
		}
		return inputStream;
	}

	/**
	 * @return {@link BufferedReader}
	 */
	public static BufferedReader retrieveFileAsReader(final String resourceName) {

		final DataInputStream dataInputStream = ReflectionUtilHelper
				.retrieveFileAsDataInputStreamAndBeSureToClose(resourceName);

		final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));

		return bufferedReader;
	}

	/**
	 * @return {@link Scanner}
	 */
	public static Scanner retrieveFileAsScanner(final String fileName) {

		InputStream inputStream = null;
		Scanner scanner;

		try {
			inputStream = ReflectionUtilHelper.retrieveFileAsInputStreamAndBeSureToClose(fileName);
			scanner = new Scanner(inputStream);

		} finally {

			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (@SuppressWarnings("unused") final Exception e) {
					// Ignore.
				}
			}
		}

		return scanner;
	}

	/**
	 * This method returns an accessible no-argument <code>Constructor</code> or
	 * <code>null</code>.
	 *
	 * @return {@link Constructor} or null.
	 */
	public static Constructor<?> retrieveNonPublicConstructorNoArgument(final Class<?> clazz) {

		final Constructor<?>[] constructors = clazz.getDeclaredConstructors();

		return iterateForNonPublicConstructorNoArgument(constructors);
	}

	/**
	 * @param clazz
	 * @return {@link Constructor} or null
	 */
	public static Constructor<?> retrievePublicConstructorNoArgument(final Class<?> clazz) {

		final Constructor<?>[] constructors = clazz.getConstructors();

		return ReflectionUtilHelper.iterateForPublicConstructorNoArgument(constructors);
	}

	/**
	 * @return {@link Method}
	 */
	public static Method retrievePublicMethod(final Class<?> clazz, final String methodName,
			final boolean suppressException) {

		try {
			final Method methods[] = clazz.getMethods();

			for (final Method methodTemp : methods) {

				if (methodName.equalsIgnoreCase(methodTemp.getName())) {

					return methodTemp;
				}
			}
			throwTestingRuntimeException("Method not found.", suppressException);

		} catch (final Exception e) {

			throwTestingRuntimeException(e.getMessage(), suppressException);

		}
		return null;
	}

	/**
	 * @throws TestingRuntimeException
	 */
	private static void throwTestingRuntimeException(final String message, final boolean suppressException) {

		if (!suppressException) {

			throw new TestingRuntimeException(message);
		}
	}

	/**
	 * This method provides a security verification.
	 */
	public static void verifyConstructorAccessible(final Constructor<?> constructor) {

		if (constructor.isAccessible()) {
			return;
		}

		// Enable use of a non-public constructor.
		constructor.setAccessible(true);
		return;
	}

	/**
	 * @return {@link Constructor} or null
	 */
	public static boolean verifyPublicNoArgumentConstructorOnly(final Class<?> clazz) {

		final Constructor<?>[] constructors = clazz.getConstructors();

		if (constructors.length == 1) {

			if (null != ReflectionUtilHelper.iterateForPublicConstructorNoArgument(constructors)) {
				return true;
			}
		}
		return false;
	}
}
