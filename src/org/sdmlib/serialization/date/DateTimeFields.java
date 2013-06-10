package org.sdmlib.serialization.date;

/*
 Json Id Serialisierung Map
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
 1. Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 3. All advertising materials mentioning features or use of this software
 must display the following acknowledgement:
 This product includes software developed by Stefan Lindel.
 4. Neither the name of contributors may be used to endorse or promote products
 derived from this software without specific prior written permission.

 THE SOFTWARE 'AS IS' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL STEFAN LINDEL BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
import java.util.HashMap;

public class DateTimeFields extends HashMap<String, DateTimeField> {
	private static final long serialVersionUID = 1L;
	public static final String ERA = "era";
	public static final String MONTH = "month";
	public static final String YEAR = "year";
	public static final String WEEK_OF_YEAR = "week_of_year";
	public static final String WEEK_OF_MONTH = "week_of_month";
	public static final String WEEK_OF_WEEK = "week_of_week";
	public static final String DAY_OF_MONTH = "day_of_month";
	public static final String DAY_OF_YEAR = "day_of_year";
	public static final String DAYS = "days";
	public static final String AMPM = "am/pm";
	public static final String HOURS = "hours";
	public static final String HOUR_OF_DAY = "hour_of_day";
	public static final String MINUTES = "minutes";
	public static final String MINUTE_OF_HOUR = "minute_of_hour";
	public static final String SECOND_OF_MINUTE = "second_of_minute";
	public static final String SECOND_OF_DAY = "second_of_minute";
	public static final String SECOND_OF_YEAR = "second_of_year";
	public static final String SECONDS = "seconds";
	public static final String MILLISECONDS = "milliseconds";
	public static final String MILLISECOND_OF_DAY = "millisecond_of_day";
	public static final String TIMEZONE = "zone";

	public DateTimeFields() {
		add(new DateTimeField(ERA, 0, null, 1));
		add(new DateTimeField(YEAR, 0, null, 1970));
		add(new DateTimeField(MONTH, 1, 12, 1));
		add(new DateTimeField(WEEK_OF_YEAR, 1, 53, 1));
		add(new DateTimeField(WEEK_OF_MONTH, 1, 5, 1));
		add(new DateTimeField(WEEK_OF_WEEK, 1, 7, 1));
		add(new DateTimeField(DAY_OF_MONTH, 1, 31, 1));
		add(new DateTimeField(DAY_OF_YEAR, 1, 366, 1));
		add(new DateTimeField(DAYS, 1, null, 1));
		add(new DateTimeField(AMPM, 0, 1, 0));
		add(new DateTimeField(HOUR_OF_DAY, 0, 23, 0));
		add(new DateTimeField(HOURS, 0, null, 0));
		add(new DateTimeField(MINUTES, 0, null, 0));
		add(new DateTimeField(MINUTE_OF_HOUR, 0, 59, 0));
		add(new DateTimeField(SECONDS, 0, null, 0));
		add(new DateTimeField(SECOND_OF_MINUTE, 0, 59, 0));
		add(new DateTimeField(MILLISECONDS, 0, null, 0));
		add(new DateTimeField(MILLISECOND_OF_DAY, 0, null, 0));
		add(new DateTimeField(TIMEZONE, 0, null, 0));
	}

	public void add(DateTimeField field) {
		put(field.getType(), field);
	}

	static final int MONTH_LENGTH[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
			30, 31 }; // 0-based
	static final int LEAP_MONTH_LENGTH[] = { 31, 29, 31, 30, 31, 30, 31, 31,
			30, 31, 30, 31 }; // 0-based

	// Useful millisecond constants. Although ONE_DAY and ONE_WEEK can fit
	// into ints, they must be longs in order to prevent arithmetic overflow
	// when performing (bug 4173516).
	public static final int ONE_SECOND = 1000;
	public static final int ONE_MINUTE = 60 * ONE_SECOND;
	public static final int ONE_HOUR = 60 * ONE_MINUTE;
	public static final long ONE_DAY = 24 * ONE_HOUR;
	public static final long ONE_WEEK = 7 * ONE_DAY;

	/**
	 * The normalized year of the gregorianCutover in Gregorian, with 0
	 * representing 1 BCE, -1 representing 2 BCE, etc.
	 */
	private transient int gregorianCutoverYear = 1582;

	/**
	 * The normalized year of the gregorianCutover in Julian, with 0
	 * representing 1 BCE, -1 representing 2 BCE, etc.
	 */
	private transient int gregorianCutoverYearJulian = 1582;

	// The default value of gregorianCutover.
	static final long DEFAULT_GREGORIAN_CUTOVER = -12219292800000L;

	/**
	 * Returns the length of the specified month in the specified year. The year
	 * number must be normalized.
	 * 
	 * @see #isLeapYear(int)
	 */
	public final int getMonthLength(int month, int year) {
		return isLeapYear(year) ? LEAP_MONTH_LENGTH[month]
				: MONTH_LENGTH[month];
	}

	/**
	 * Returns the length (in days) of the specified year. The year must be
	 * normalized.
	 */
	public final int getYearLength(int year) {
		return isLeapYear(year) ? 366 : 365;
	}

	/**
	 * Determines if the given year is a leap year. Returns <code>true</code> if
	 * the given year is a leap year. To specify BC year numbers,
	 * <code>1 - year number</code> must be given. For example, year BC 4 is
	 * specified as -3.
	 * 
	 * @param year
	 *            the given year.
	 * @return <code>true</code> if the given year is a leap year;
	 *         <code>false</code> otherwise.
	 */
	public boolean isLeapYear(int year) {
		if ((year & 3) != 0) {
			return false;
		}

		if (year > gregorianCutoverYear) {
			return (year % 100 != 0) || (year % 400 == 0); // Gregorian
		}
		if (year < gregorianCutoverYearJulian) {
			return true; // Julian
		}
		boolean gregorian = (year == gregorianCutoverYear);
		return gregorian ? (year % 100 != 0) || (year % 400 == 0) : true;
	}

	public int validate(String field, int value) {
		DateTimeField fieldValue = get(field);
		if (fieldValue != null) {
			return fieldValue.validate(value);
		}
		return 0;
	}
}
