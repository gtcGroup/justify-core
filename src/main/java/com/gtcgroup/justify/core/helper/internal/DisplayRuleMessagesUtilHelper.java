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

import org.junit.Rule;
import org.junit.runner.Description;

import com.gtcgroup.justify.core.rulechain.JstRuleChain;

/**
 * This Util Helper class provides support for {@link Rule} processing.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2016 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */

public enum DisplayRuleMessagesUtilHelper {

	@SuppressWarnings("javadoc") INSTANCE;

	protected static boolean isSuiteHeaderDisplayed = false;

	/**
	 * @param exception
	 */
	public static void displayConstructorException(final Throwable exception) {

		displayException(exception, "RULE CONSTRUCTOR EXCEPTION");
	}

	/**
	 * This method is recursive.
	 *
	 * @param throwable
	 * @param header
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
	 * @param status
	 * @param description
	 * @param elapsedNanoSeconds
	 * @param rulesForMethodFooter
	 */
	public static void displayMethodFooter(final String status, final Description description,
			final long elapsedNanoSeconds, final String rulesForMethodFooter) {

		if (0 == elapsedNanoSeconds) {
			System.out.println("\t*** Test Method End [" + status + "]: " + description.getDisplayName() + " ***");
		} else {

			System.out.println("\t*** Test Method End [" + status + " "
					+ ConversionUtilHelper.convertNanosecondToMillisecondString(elapsedNanoSeconds) + " ms]: "
					+ description.getDisplayName() + " ***");
		}
		System.out.println("\t\tActive Rule(s): [OuterRule]" + rulesForMethodFooter + "\n");
	}

	/**
	 * @param elapsedNanoSeconds
	 * @param status
	 */
	public static void displayMethodFooter(final String status, final long elapsedNanoSeconds) {

		// Interrupt logging (briefly) to help ensure readability.
		try {
			Thread.sleep(1);
		} catch (@SuppressWarnings("unused") final InterruptedException e) {
			// Ignore
		}

		DisplayRuleMessagesUtilHelper.displayMethodFooter(status,
				RuleChainCacheHelper.getRuleChainHelper().getDescription(), elapsedNanoSeconds,
				RuleChainCacheHelper.getRuleChainHelper().getRulesDisplayedInMethodFooter().toString());
	}

	/**
	 * @param descriptionFromJUnit
	 */
	public static void displayMethodHeader(final Description descriptionFromJUnit) {

		System.out.println("\n\t*** Test Method Begin: " + descriptionFromJUnit.getDisplayName() + " ***");
	}

	/**
	 * @param methodTimer
	 */
	public static void displaySucceedFooter(final TimerBeanHelper methodTimer) {

		DisplayRuleMessagesUtilHelper.displayMethodFooter("Succeeded",
				RuleChainCacheHelper.getRuleChainHelper().getDescription(), methodTimer.calculateElapsedNanoSeconds(),
				RuleChainCacheHelper.getRuleChainHelper().getRulesDisplayedInMethodFooter().toString());

		return;
	}

	/**
	 * This method display a header for initial rule configuration.
	 */
	public static void displaySuiteHeader() {

		if (DisplayRuleMessagesUtilHelper.isSuiteHeaderDisplayed) {

			return;
		}

		if (!JstRuleChain.suppressExampleExecutionSequence) {

			final String exampleMessage = "\t* Example Execution Sequence  - {suppress example with outerRule(true)} *";

			final StringBuilder border = ConversionUtilHelper.convertMessageLengthToBorder(exampleMessage);

			System.out.print(border.toString() + exampleMessage + border.toString());

			final StringBuilder doco = new StringBuilder();

			doco.append("\t  Rules: [FirstRule] [SecondRule]\n\n");

			doco.append("\t\t0 - @BeforeClass\n");
			doco.append("\t\t1 - FirstRule.beforeTM()\n");
			doco.append("\t\t2 - SecondRule.beforeTM()\n");
			doco.append("\t*** Test Method Begin ***\n");
			doco.append("\t\t4 - @Before\n");
			doco.append("\t\t5 - Test Method Invocation\n");
			doco.append("\t\t6 - @After\n");
			doco.append("\t\t7 - SecondRule.afterTM()\n");
			doco.append("\t\t8 - FirstRule.afterTM()\n");
			doco.append("\t*** Test Method End ***\n");

			doco.append("\t\t9 - @AfterClass");

			System.out.println(doco.toString());
		}

		final String message = "\t* Welcome to the JUnit Strategy for Testing [JUST] *";

		final StringBuilder border = ConversionUtilHelper.convertMessageLengthToBorder(message);

		System.out.print(border.toString() + message + border.toString());

		DisplayRuleMessagesUtilHelper.isSuiteHeaderDisplayed = true;
	}
}
