package org.sdmlib.serialization.date;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or – as soon they
 will be approved by the European Commission - subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
*/

public enum DateField {
	MILLISECOND, MILLISECONDS, MILLISECONDSREAL, MILLISECOND_OF_DAY, MILLISECOND_OF_YEAR,
	SECOND_OF_MINUTE, MINUTE_OF_HOUR, HOUR_OF_DAY, AMPM, TIMEZONE, 
	DAY_OF_WEEK, DAY_OF_MONTH, DAY_OF_YEAR, WEEK_OF_MONTH, WEEK_OF_YEAR,  
	MONTH, YEAR;
}
