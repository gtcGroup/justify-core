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

import java.util.Arrays;

import org.junit.Rule;
import org.junit.runner.Description;

import com.gtcgroup.justify.core.rulechain.JstRuleChain;

/**
 * This Util Helper class provides support for {@link Rule} processing.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */

public enum DisplayRuleMessagesUtilHelper {

	@SuppressWarnings("javadoc")
	INSTANCE;

	protected static boolean isTestingHeaderDisplayed = false;

	/**
	 * This method is for console display.
	 */
	public static void displayConstructorException(final Throwable exception) {

		displayException(exception, "RULE CONSTRUCTOR EXCEPTION");
	}

	/**
	 * This method is recursive.
	 */
	public static void displayException(final Throwable throwable, final String header) {

		final Throwable throwableNested = throwable.getCause();

		if (null == throwableNested) {

			final StringBuilder message = new StringBuilder();
			message.append("\n[");
			message.append(header);
			message.append("]");
			message.append("\n");

			System.out.println(message.toString());
			throwable.printStackTrace();
			System.out.println();
			return;
		}

		displayException(throwableNested, header);
	}

	/**
	 * This method is for console display.
	 */
	public static void displayMethodFooter(final String status, final Description description,
			final long elapsedNanoSeconds, final String rulesForMethodFooter) {

		if (null == description) {
			System.out.println("\t*** Test Method End [" + status + "] ***");
		}
		
		if (0 == elapsedNanoSeconds) {
			System.out.println("\t*** Test Method End [" + status + "]: " + description.getDisplayName() + " ***");
		} else {

			System.out.println("\t*** Test Method End [" + status + " "
					+ ConversionUtilHelper.convertNanosecondToMillisecondString(elapsedNanoSeconds) + " ms]: "
					+ description.getDisplayName() + " ***");
		}

		if (rulesForMethodFooter.isEmpty()) {
			System.out.println();
		} else {
			System.out.println("\t\tActive Rule(s): " + rulesForMethodFooter + "\n");
		}

	}

	/**
	 * This method is for console display.
	 */
	public static void displayMethodFooter(final String status, final long elapsedNanoSeconds) {

		// Interrupt logging (briefly) to help ensure readability.
		try {
			Thread.sleep(1);
		} catch (final InterruptedException e) {
			// Ignore
		}

		if (null == status || null == RuleChainCacheHelper.getRuleChainHelper().getDescription()
				|| null == RuleChainCacheHelper.getRuleChainHelper().getRulesDisplayedInMethodFooter().toString()) {

			return;
		}
		DisplayRuleMessagesUtilHelper.displayMethodFooter(status,
				RuleChainCacheHelper.getRuleChainHelper().getDescription(), elapsedNanoSeconds,
				RuleChainCacheHelper.getRuleChainHelper().getRulesDisplayedInMethodFooter().toString());
	}

	/**
	 * This method is for console display.
	 */
	public static void displayMethodHeader(final Description descriptionFromJUnit) {

		System.out.println("\n\t*** Test Method Begin: " + descriptionFromJUnit.getDisplayName() + " ***");
	}


	public static void displaySucceedFooter(final TimerBeanHelper methodTimer) {

		if (null == methodTimer) {
			return;
		}

		DisplayRuleMessagesUtilHelper.displayMethodFooter("Succeeded",
				RuleChainCacheHelper.getRuleChainHelper().getDescription(), methodTimer.calculateElapsedNanoSeconds(),
				RuleChainCacheHelper.getRuleChainHelper().getRulesDisplayedInMethodFooter().toString());

		return;
	}

	/**
	 * This method is for console display.
	 */
	public static void displayTestingHeader() {

		if (DisplayRuleMessagesUtilHelper.isTestingHeaderDisplayed) {

			return;
		}

		if (JstRuleChain.displayVerboseStartupLogging) {

			String exampleMessage = "\t* Example Execution Sequence  - {enable with outerRule(true)} *";

			StringBuilder border = ConversionUtilHelper.convertMessageLengthToBorder(exampleMessage);

			System.out.print(border.toString() + exampleMessage + border.toString());

			StringBuilder doco = new StringBuilder();

			doco.append("\t  Rules: [FirstRule] [SecondRule]\n\n");

			doco.append("\t\t01 TestClass: Ordered static field(s) and block(s)\n");
			doco.append("\t\t02 TestClass: @BeforeClass\n");
			doco.append("\t\t03 FirstRule: Ordered static field(s) and block(s)\n");
			doco.append("\t\t04 FirstRule: Constructor\n");
			doco.append("\t\t05 SecondRule:Ordered static field(s) and block(s)\n");
			doco.append("\t\t06 SecondRule:Constructor\n");
			doco.append("\t\t07 FirstRule: beforeTM()\n");
			doco.append("\t\t08 SecondRule:beforeTM()\n");

			doco.append("\t* Test Method Begin ***\n");

			doco.append("\t\t09 TestClass: @Before\n");
			doco.append("\t\t10 TestClass: testMethod()\n");
			doco.append("\t\t11 TestClass: @After\n");
			doco.append("\t\t12 SecondRule:afterTM()\n");
			doco.append("\t\t13 FirstRule: afterTM()\n");

			doco.append("\t* Test Method End ***\n");

			doco.append("\t\t14 TestClass: @AfterClass");

			System.out.println(doco.toString());

			exampleMessage = "\t* Environmental Context - {enable listing with outerRule(true)} *";

			border = ConversionUtilHelper.convertMessageLengthToBorder(exampleMessage);

            System.out.print(border.toString() + exampleMessage + border.toString());

			doco = new StringBuilder();

			doco.append("\t  Root Directory - ");
			doco.append(System.getProperty("user.dir") + "\n");
			doco.append("\t  Java Home      - ");
			doco.append(System.getProperty("java.home") + "\n");
			doco.append("\t  Java Version   - ");
			doco.append(System.getProperty("java.version") + "\n");

			doco.append("\t  Java Classpath -\n");

			final String[] tokens = System.getProperty("java.class.path").replace(":/", "~&~").replace(":\\", "~#~")
					.replace(":", ";").replace("~#~", ":\\").replace("~&~", ":/").split(";");

			Arrays.sort(tokens);
			for (final String token : tokens) {
				doco.append("\t\t" + token + "\n");
			}
			System.out.println(doco.toString());
		}

		final String message = "\t* Welcome to the JUnit Strategy for Testing [JUST] *";

		final StringBuilder border = ConversionUtilHelper.convertMessageLengthToBorder(message);

		System.out.print(border.toString() + message + border.toString());

		DisplayRuleMessagesUtilHelper.isTestingHeaderDisplayed = true;
	}
}
