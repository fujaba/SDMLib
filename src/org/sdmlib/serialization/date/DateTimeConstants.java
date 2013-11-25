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

public class DateTimeConstants {
	/**
	 * BASE_YEAR
	 */
	public final static int BASE_YEAR = 1970;

	public final static int MILLISECOND_OF_DAY = 86400000;

	public static final int[] FIXED_DATES = { 719163, // 1970
			719528, // 1971
			719893, // 1972
			720259, // 1973
			720624, // 1974
			720989, // 1975
			721354, // 1976
			721720, // 1977
			722085, // 1978
			722450, // 1979
			722815, // 1980
			723181, // 1981
			723546, // 1982
			723911, // 1983
			724276, // 1984
			724642, // 1985
			725007, // 1986
			725372, // 1987
			725737, // 1988
			726103, // 1989
			726468, // 1990
			726833, // 1991
			727198, // 1992
			727564, // 1993
			727929, // 1994
			728294, // 1995
			728659, // 1996
			729025, // 1997
			729390, // 1998
			729755, // 1999
			730120, // 2000
			730486, // 2001
			730851, // 2002
			731216, // 2003
			731581, // 2004
			731947, // 2005
			732312, // 2006
			732677, // 2007
			733042, // 2008
			733408, // 2009
			733773, // 2010
			734138, // 2011
			734503, // 2012
			734869, // 2013
			735234, // 2014
			735599, // 2015
			735964, // 2016
			736330, // 2017
			736695, // 2018
			737060, // 2019
			737425, // 2020
			737791, // 2021
			738156, // 2022
			738521, // 2023
			738886, // 2024
			739252, // 2025
			739617, // 2026
			739982, // 2027
			740347, // 2028
			740713, // 2029
			741078, // 2030
			741443, // 2031
			741808, // 2032
			742174, // 2033
			742539, // 2034
			742904, // 2035
			743269, // 2036
			743635, // 2037
			744000, // 2038
			744365, // 2039
	};
	public static final long[] FIXED_MILLISECOND = { -3600L, // 1970
			31532400L, // 1971
			63068400L, // 1972
			63068400L, // 1973
			94690800L, // 1973
			126226800L, // 1974
			157762800L, // 1975
			189298800L, // 1976
			220921200L, // 1977
			252457200L, // 1978
			283993200L, // 1979
			315529200L, // 1980
			347151600L, // 1981
			378687600L, // 1982
			410223600L, // 1983
			441759600L, // 1984
			473382000L, // 1985
			504918000L, // 1986
			536454000L, // 1987
			567990000L, // 1988
			599612400L, // 1989
			631148400L, // 1990
			662684400L, // 1991
			694220400L, // 1992
			725842800L, // 1993
			757378800L, // 1994
			788914800L, // 1995
			820450800L, // 1996
			852073200L, // 1997
			883609200L, // 1998
			915145200L, // 1999
			946681200L, // 2000
			978303600L, // 2001
			1009839600L, // 2002
			1041375600L, // 2003
			1072911600L, // 2004
			1104534000L, // 2005
			1136070000L, // 2006
			1167606000L, // 2007
			1199142000L, // 2008
			1230764400L, // 2009
			1262300400L, // 2010
			1293836400L, // 2011
			1325372400L, // 2012
			1356994800L, // 2013
			1388530800L, // 2014
			1420066800L, // 2015
			1451602800L, // 2016
			1483225200L, // 2017
			1514761200L, // 2018
			1546297200L, // 2019
			1577833200L, // 2020
			1609455600L, // 2021
			1640991600L, // 2022
			1672527600L, // 2023
			1704063600L, // 2024
			1735686000L, // 2025
			1767222000L, // 2026
			1798758000L, // 2027
			1830294000L, // 2028
			1861916400L, // 2029
			1893452400L, // 2030
			1924988400L, // 2031
			1956524400L, // 2032
			1988146800L, // 2033
			2019682800L, // 2034
			2051218800L, // 2035
			2082754800L, // 2036
			2114377200L, // 2037
			2145913200L, // 2038
			2177449200L, // 2039
			2208985200L, // 2040
			2240607600L, // 2041
			2272143600L, // 2042
			2303679600L, // 2043
			2335215600L, // 2044
			2366838000L, // 2045
			2398374000L, // 2046
			2429910000L, // 2047
			2461446000L, // 2048
			2493068400L, // 2049
			2524604400L, // 2050
			2556140400L, // 2051
			2587676400L // 2052
	};

	public static final int[] ACCUMULATED_DAYS_IN_MONTH
	// 12/1 1/1 2/1 3/1 4/1 5/1 6/1 7/1 8/1 9/1 10/1 11/1 12/1
	= { -30, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };
	public static final int[] ACCUMULATED_DAYS_IN_MONTH_LEAP
	// 12/1 1/1 2/1 3/1 4/1 5/1 6/1 7/1 8/1 9/1 10/1 11/1 12/1
	= { -30, 0, 31, 59 + 1, 90 + 1, 120 + 1, 151 + 1, 181 + 1, 212 + 1,
			243 + 1, 273 + 1, 304 + 1, 334 + 1 };
}
