package com.ecivil.util;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * @author Milan 
 *	11 июня 2014 г.  -  0:07:35
 *
 */
public class DateTimeFormatBuilder {

	private PeriodFormatter periodFormatter;
	
	public DateTimeFormatBuilder() {
		this.periodFormatter = new PeriodFormatterBuilder()
	    .appendDays()
	    .appendSuffix(" day", " days")
	    .appendSeparator(" , ")
	    .appendMinutes()
	    .appendSuffix(" minute", " minutes")
	    .appendSeparator(" , ")
	    .appendSeconds()
	    .appendSuffix(" second", " seconds")
	    .toFormatter(); 
	}
	
	public String periodToString(Period period) {
		return this.periodFormatter.print(period);
	}
}
